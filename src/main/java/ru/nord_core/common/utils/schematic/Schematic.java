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

    private final short VERSION = 1;
    private short width;
    private short height;
    private short length;
    private HashMap<Integer, IBlockState> blockDictoary = new HashMap<Integer, IBlockState>();
    private int[][][] template; //lwh=xyz
    private BlockPos posOrigin;

    public Schematic() {
    }

    public void readFromFile(String fileName) {
        NBTTagCompound nbtdata;
        try {
            nbtdata = CompressedStreamTools.readCompressed(new GZIPInputStream(new FileInputStream(new File(NordCore.proxy.getDataDirectory(), fileName))));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        short version = nbtdata.getShort("version");
        if (version != VERSION) {
            return;
        }
        width = nbtdata.getShort("Width");
        height = nbtdata.getShort("Height");
        length = nbtdata.getShort("Length");
        short x = nbtdata.getShort("originX");
        short y = nbtdata.getShort("originY");
        short z = nbtdata.getShort("originZ");
        this.posOrigin = new BlockPos(x,y,z);
        int[] blocksIndex = nbtdata.getIntArray("Blocks");
        NBTTagCompound dictoary = (NBTTagCompound) nbtdata.getTag("Dictoary");
        int sizeDictoary = dictoary.getInteger("Size");
        String blockIndex;
        for (int d = 1; d <= sizeDictoary; d++) {
            blockIndex = dictoary.getString(String.valueOf(d));
            String[] blockID = blockIndex.split(":");
            String mod = blockID[0];
            String name = blockID[1];
            String meta = blockID[2];
            Block block = GameRegistry.findBlock(mod, name);
            IBlockState state = block.getStateFromMeta(Integer.parseInt(meta));
            blockDictoary.put(d, state);

        }
        this.template = new int[length][width][height];
        int counter = 0;

        for (int h = 0; h < height; h++) {
            for (int l = 0; l < length; l++) {
                for (int w = 0; w < width; w++) {
                    template[l][w][h] = blocksIndex[counter];
                    counter++;
                }
            }
        }


    }

    public void writeToFile(String fileName, NBTTagCompound nbtdata) {
        fileName = "schematics/"+fileName;
        try {
            final DataOutputStream dataOutputStream = new DataOutputStream(new GZIPOutputStream(new FileOutputStream(new File(NordCore.proxy.getDataDirectory(), fileName))));
            CompressedStreamTools.writeCompressed(nbtdata, dataOutputStream);
            System.out.println(dataOutputStream);
        } catch (final Exception ex) {
            FMLLog.getLogger().error("Failed to write schematic!", ex);
        }

    }

    public NBTTagCompound getMapCuboid(AxisAlignedBB aabb, World worldIn, BlockPos pos) {
        NBTTagCompound nbtdata = new NBTTagCompound();
        NBTTagCompound nbtDictoaryIndex = new NBTTagCompound();
        HashMap<String, Integer> blockDictoaryIndex = new HashMap<String, Integer>(10);
        List<Integer> blockMapIndexed = new ArrayList<Integer>();
        int index = 1;
        short l = (short) (aabb.maxX - aabb.minX + 1);
        short w = (short) (aabb.maxY - aabb.minY + 1);
        short h = (short) (aabb.maxZ - aabb.minZ + 1);
        nbtdata.setShort("Width", w);
        nbtdata.setShort("Height", h);
        nbtdata.setShort("Length", l);
        nbtdata.setShort("version", (short) 1);
        nbtdata.setShort("originX", (short) pos.getX());
        nbtdata.setShort("originY", (short) pos.getY());
        nbtdata.setShort("originZ", (short) pos.getZ());
        for (int yi = (int) aabb.minY; yi <= aabb.maxY; yi++) {
            for (int xi = (int) aabb.minX; xi <= aabb.maxX; xi++) {
                for (int zi = (int) aabb.minZ; zi <= aabb.maxZ; zi++) {
                    IBlockState state = worldIn.getBlockState(new BlockPos(xi, yi, zi));
                    Block block = state.getBlock();
                    int meta = block.getMetaFromState(state);
                    int blockIndex;
                    Item item = Item.getItemFromBlock(block);
                    if (block != Blocks.air && item != null) {
                        String blockName = ((ResourceLocation) Item.itemRegistry.getNameForObject(item)).toString();
                        if (blockDictoaryIndex.containsKey(blockName + ":" + meta)) {
                            blockIndex = blockDictoaryIndex.get(blockName + ":" + meta);
                        } else {
                            String sBlockIndex = blockName + ":" + meta;
                            blockDictoaryIndex.put(sBlockIndex, index);
                            nbtDictoaryIndex.setString(String.valueOf(index), sBlockIndex);
                            blockIndex = index;
                            index++;
                        }
                    } else {
                        blockIndex = 0;
                    }
                    blockMapIndexed.add(blockIndex);
                }
            }
        }
        nbtDictoaryIndex.setInteger("Size", index);
        nbtdata.setIntArray("Blocks", convertIntegers(blockMapIndexed));
        nbtdata.setTag("Dictoary", nbtDictoaryIndex);
        return nbtdata;
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
                    int index = this.template[l][w][h];
                    if (index != 0) {
                        world.setBlockState(pos.add(l, w, h).add(this.posOrigin), this.blockDictoary.get(index), 3);
                    }
                }
            }
        }
    }

}