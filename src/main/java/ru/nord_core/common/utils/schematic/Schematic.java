package ru.nord_core.common.utils.schematic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord_core.NordCore;
import ru.nord_core.common.utils.Constants;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Schematic {

    private short width;
    private short height;
    private short length;
    private IBlockState[][][] template; //lwh=xyz
    private BlockPos posOrigin;

    public Schematic() {
    }

    public void readFromFile(String fileName) {
        fileName = "schematics/" + fileName;
        NBTTagCompound schematic;
        NBTTagCompound nbtdata;
        try {
            File file = new File(NordCore.proxy.getDataDirectory(), fileName);
            FileInputStream stream = new FileInputStream(file);
            GZIPInputStream gzip = new GZIPInputStream(stream);
            schematic = CompressedStreamTools.readCompressed(gzip);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        nbtdata = schematic.getCompoundTag(Constants.NBT.ROOT);

        String version = nbtdata.getString(Constants.NBT.VERSION);

        if (!version.equals(Constants.NBT.NordShem)) {
            return;
        }

        width = nbtdata.getShort(Constants.NBT.WIDTH);
        height = nbtdata.getShort(Constants.NBT.HEIGHT);
        length = nbtdata.getShort(Constants.NBT.LENGTH);

        short x = nbtdata.getShort(Constants.NBT.OriginX);
        short y = nbtdata.getShort(Constants.NBT.OriginY);
        short z = nbtdata.getShort(Constants.NBT.OriginZ);

        this.posOrigin = new BlockPos(x, y, z);
        int[] blocksIndex = nbtdata.getIntArray(Constants.NBT.BLOCKS);
        int[] blocksMeta = nbtdata.getIntArray(Constants.NBT.DATA);
        NBTTagCompound dictoary = (NBTTagCompound) nbtdata.getTag(Constants.NBT.MAPPING_SCHEMATICA);
        int sizeDictoary = dictoary.getInteger(Constants.NBT.SIZE);
        String blockIndex;
        HashMap<Integer, Block> blockDictoary = new HashMap<Integer, Block>();

        for (int d = 1; d <= sizeDictoary; d++) {
            blockIndex = dictoary.getString(String.valueOf(d));
            String[] blockID = blockIndex.split(":");
            String mod = blockID[0];
            String name = blockID[1];
            Block block = GameRegistry.findBlock(mod, name);
            blockDictoary.put(d, block);
        }

        this.template = new IBlockState[length][width][height];
        int counter = 0;

        for (int h = 0; h < height; h++) {
            for (int l = 0; l < length; l++) {
                for (int w = 0; w < width; w++) {
                    template[l][w][h] =  blockDictoary.get(blocksIndex[counter]).getStateFromMeta(blocksMeta[counter]);
                    counter++;
                }
            }
        }
    }

    public void writeToFile(String fileName, NBTTagCompound nbtdata) {
        fileName = "schematics/" + fileName;
        try {
            File file = new File(NordCore.proxy.getDataDirectory(), fileName);
            FileOutputStream stream = new FileOutputStream(file);
            GZIPOutputStream gzip = new GZIPOutputStream(stream);
            final DataOutputStream dataOutputStream = new DataOutputStream(gzip);
            CompressedStreamTools.writeCompressed(nbtdata, dataOutputStream);
            System.out.println(dataOutputStream);
        } catch (final Exception ex) {
            FMLLog.getLogger().error("Failed to write schematic!", ex);
        }

    }

    public NBTTagCompound getMapCuboid(AxisAlignedBB aabb, World worldIn, BlockPos pos) {
        NBTTagCompound schematic = new NBTTagCompound();
        NBTTagCompound nbtdata = new NBTTagCompound();
        NBTTagCompound nbtDictoaryIndex = new NBTTagCompound();
        HashMap<String, Integer> blockDictoaryIndex = new HashMap<String, Integer>(10);
        List<Integer> blockMapIndexed = new ArrayList<Integer>();
        List<Integer> blockMetaData = new ArrayList<Integer>();

        int dictoarSyze = 1;

        short l = (short) (aabb.maxX - aabb.minX + 1);
        short w = (short) (aabb.maxY - aabb.minY + 1);
        short h = (short) (aabb.maxZ - aabb.minZ + 1);

        nbtdata.setShort(Constants.NBT.WIDTH, w);
        nbtdata.setShort(Constants.NBT.HEIGHT, h);
        nbtdata.setShort(Constants.NBT.LENGTH, l);

        nbtdata.setString(Constants.NBT.MATERIALS, Constants.NBT.FORMAT_ALPHA);
        nbtdata.setString(Constants.NBT.VERSION, Constants.NBT.NordShem);

        nbtdata.setShort(Constants.NBT.OriginX, (short) pos.getX());
        nbtdata.setShort(Constants.NBT.OriginY, (short) pos.getY());
        nbtdata.setShort(Constants.NBT.OriginZ, (short) pos.getZ());

        for (int yi = (int) aabb.minY; yi <= aabb.maxY; yi++) {
            for (int xi = (int) aabb.minX; xi <= aabb.maxX; xi++) {
                for (int zi = (int) aabb.minZ; zi <= aabb.maxZ; zi++) {
                    IBlockState state = worldIn.getBlockState(new BlockPos(xi, yi, zi));
                    Block block = state.getBlock();
                    int meta = block.getMetaFromState(state);
                    Item item = Item.getItemFromBlock(block);
                    int blockInd = Block.getIdFromBlock(block);

                    if (block != Blocks.air && item != null) {
                        String blockName = ((ResourceLocation) Item.itemRegistry.getNameForObject(item)).toString();
                        if (!blockDictoaryIndex.containsKey(blockName)) {
                            blockDictoaryIndex.put(blockName, blockInd);
                            nbtDictoaryIndex.setString(String.valueOf(blockInd), blockName);
                            dictoarSyze++;
                        }
                    }else{
                        blockInd = 0;
                    }
                    blockMapIndexed.add(blockInd);
                    blockMetaData.add(meta);
                }
            }
        }

        nbtDictoaryIndex.setInteger(Constants.NBT.SIZE, dictoarSyze);
        nbtdata.setIntArray(Constants.NBT.BLOCKS, convertIntegers(blockMapIndexed));
        nbtdata.setIntArray(Constants.NBT.DATA, convertIntegers(blockMetaData));
        nbtdata.setTag(Constants.NBT.MAPPING_SCHEMATICA, nbtDictoaryIndex);

        schematic.setTag(Constants.NBT.ROOT, nbtdata);
        return schematic;
    }

    private static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        for (int i = 0; i < ret.length; i++) {
            ret[i] = integers.get(i).intValue();
        }
        return ret;
    }

    public void generate(World world, BlockPos pos) {
        for (int h = 0; h < height; h++) {
            for (int l = 0; l < length; l++) {
                for (int w = 0; w < width; w++) {
                    IBlockState blockState = this.template[l][w][h];
                    if (blockState != null) {
                        world.setBlockState(pos.add(l, w, h).add(this.posOrigin), blockState, 3);
                    }
                }
            }
        }
    }

}