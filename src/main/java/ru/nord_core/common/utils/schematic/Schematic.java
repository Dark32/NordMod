package ru.nord_core.common.utils.schematic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3i;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import org.apache.commons.io.IOUtils;
import ru.nord_core.NordCore;
import ru.nord_core.common.helpers.NBTHelper;
import ru.nord_core.common.utils.Constants;
import ru.nord_core.common.utils.schematic.abstracts.ASchematic;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Schematic extends ASchematic {

    private IBlockState[][][] template; //whl=xyz
    public AxisAlignedBB collusion;

    public Schematic() {
    }

    public Schematic getFromFile(String fileName) {
        fileName = "schematics/" + fileName;
        File file = new File(NordCore.proxy.getDataDirectory(), fileName);
        try {
            getFromNBT(SchematicUtils.get().readTagCompoundFromFile(file));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public Schematic getFromInnerStream(String modid, String schemname) {
        File file = new File(NordCore.proxy.getDataDirectory(), "schematics" + File.separator + schemname);
        if (!file.exists()) {
            InputStream initialStream = getClass().getResourceAsStream("assets/" + modid + "/schematics/" + schemname);
            if (initialStream == null) {
                FMLLog.getLogger().error("[NORD CORE] Ressourse assets/" + modid + "/schematics/" + schemname + " not found!");
                return null;
            }
            try {
                file.createNewFile();
                OutputStream outStream = new FileOutputStream(file);
                IOUtils.copy(initialStream, outStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return getFromFile(schemname);
    }

    public void getFromNBT(NBTTagCompound schematicNBT) {

        boolean extra = false;
        byte extraBlocks[] = null;
        byte extraBlocksNibble[];

        setXLayers(schematicNBT.getShort(Constants.NBT.WIDTH));
        setYLayers(schematicNBT.getShort(Constants.NBT.HEIGHT));
        setZLayers(schematicNBT.getShort(Constants.NBT.LENGTH));
        if (schematicNBT.hasKey("COLLUION")) {
            collusion = NBTHelper.getAxisAlignedBB(schematicNBT, "COLLUION");
        } else {
            collusion = new AxisAlignedBB(0, 0, 0, getXLayers(), getYLayers(), getZLayers());
        }

        short ox = 0;
        short oy = 0;
        short oz = 0;
        if (schematicNBT.hasKey(Constants.NBT.OriginX) &&
                schematicNBT.hasKey(Constants.NBT.OriginY) &&
                schematicNBT.hasKey(Constants.NBT.OriginZ)) {
            ox = schematicNBT.getShort(Constants.NBT.OriginX);
            oy = schematicNBT.getShort(Constants.NBT.OriginY);
            oz = schematicNBT.getShort(Constants.NBT.OriginZ);
        }

        setOrigin(new BlockPos(ox, oy, oz));

        final byte[] localBlocks = schematicNBT.getByteArray(Constants.NBT.BLOCKS);
        final byte[] localMetadata = schematicNBT.getByteArray(Constants.NBT.DATA);

        if (schematicNBT.hasKey(Constants.NBT.ADD_BLOCKS)) {
            extra = true;
            extraBlocksNibble = schematicNBT.getByteArray(Constants.NBT.ADD_BLOCKS);
            extraBlocks = new byte[extraBlocksNibble.length * 2];
            for (int i = 0; i < extraBlocksNibble.length; i++) {
                extraBlocks[i * 2 + 0] = (byte) ((extraBlocksNibble[i] >> 4) & 0xF);
                extraBlocks[i * 2 + 1] = (byte) (extraBlocksNibble[i] & 0xF);
            }
        } else if (schematicNBT.hasKey(Constants.NBT.ADD_BLOCKS_SCHEMATICA)) {
            extra = true;
            extraBlocks = schematicNBT.getByteArray(Constants.NBT.ADD_BLOCKS_SCHEMATICA);
        }

        Short id;
        final Map<Short, Short> oldToNew = new HashMap<Short, Short>();
        if (schematicNBT.hasKey(Constants.NBT.MAPPING_SCHEMATICA)) {
            final NBTTagCompound mapping = schematicNBT.getCompoundTag(Constants.NBT.MAPPING_SCHEMATICA);
            final Set<String> names = mapping.getKeySet();
            for (final String name : names) {
                oldToNew.put(mapping.getShort(name), (short) BLOCK_REGISTRY.getId(new ResourceLocation(name)));
            }
        }

        this.template = new IBlockState[getXLayers()][getYLayers()][getZLayers()];
        for (int x = 0; x < getXLayers(); x++) {
            for (int y = 0; y < getYLayers(); y++) {
                for (int z = 0; z < getZLayers(); z++) {
                    final int index = x + (y * getZLayers() + z) * getXLayers();
                    int blockID = (localBlocks[index] & 0xFF) | (extra ? ((extraBlocks[index] & 0xFF) << 8) : 0);
                    final int meta = localMetadata[index] & 0xFF;

                    if ((id = oldToNew.get((short) blockID)) != null) {
                        blockID = id;
                    }

                    final Block block = BLOCK_REGISTRY.getObjectById(blockID);
                    if (block != null) {
                        final IBlockState blockState = block.getStateFromMeta(meta);
                        template[x][y][z] = blockState;
                    }
                }
            }
        }
    }

    public void save(String fileName, NBTTagCompound nbtdata) {
        fileName += ".schematic";
        SchematicUtils.get().writeToFile(fileName, nbtdata);
    }

    public NBTTagCompound getFromWorld(AxisAlignedBB schematicBox, World worldIn, BlockPos pos) {
        return getFromWorld(schematicBox, worldIn, pos, new AxisAlignedBB(
                        0, 0, 0,
                        schematicBox.maxX - schematicBox.minX,
                        schematicBox.maxY - schematicBox.minY,
                        schematicBox.maxZ - schematicBox.minZ)
        );
    }

    public NBTTagCompound getFromWorld(AxisAlignedBB schematicBox, World worldIn, BlockPos posO, AxisAlignedBB collBox) {
        NBTTagCompound nbtdata = new NBTTagCompound();
        final Map<String, Short> mappings = new HashMap<String, Short>();

        short width = (short) (schematicBox.maxX - schematicBox.minX + 1);
        short height = (short) (schematicBox.maxY - schematicBox.minY + 1);
        short length = (short) (schematicBox.maxZ - schematicBox.minZ + 1);
        final int size = width * length * height;
        final byte[] localBlocks = new byte[size];
        final byte[] localMetadata = new byte[size];
        final byte[] extraBlocks = new byte[size];
        boolean extra = false;
        final byte[] extraBlocksNibble = new byte[(int) Math.ceil(size / 2.0)];
//        System.err.println("size" + size);
        nbtdata.setShort(Constants.NBT.WIDTH, width);
        nbtdata.setShort(Constants.NBT.HEIGHT, height);
        nbtdata.setShort(Constants.NBT.LENGTH, length);

        nbtdata.setString(Constants.NBT.MATERIALS, Constants.NBT.FORMAT_ALPHA);
        nbtdata.setString(Constants.NBT.VERSION, Constants.NBT.NordShem);

        nbtdata.setShort(Constants.NBT.OriginX, (short) (schematicBox.minX - posO.getX()));
        nbtdata.setShort(Constants.NBT.OriginY, (short) (schematicBox.minY - posO.getY()));
        nbtdata.setShort(Constants.NBT.OriginZ, (short) (schematicBox.minZ - posO.getZ()));
        NBTHelper.setAxisAlignedBB(nbtdata, "COLLUION", collBox);

        final BlockPos startPos = new BlockPos(schematicBox.minX, schematicBox.minY, schematicBox.minZ);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                for (int z = 0; z < length; z++) {
                    final int index = x + (y * length + z) * width;
//                    System.err.println(x + "," + y + "," + z +" / "+width+","+height+","+length+" /" + index);
                    IBlockState blockState = worldIn.getBlockState(new BlockPos(x, y, z).add(startPos));
                    Block block = blockState.getBlock();
                    int blockId = BLOCK_REGISTRY.getId(block);
                    localBlocks[index] = (byte) blockId;
                    localMetadata[index] = (byte) block.getMetaFromState(blockState);
                    if ((extraBlocks[index] = (byte) (blockId >> 8)) > 0) {
                        extra = true;
                    }
                    final String name = String.valueOf(BLOCK_REGISTRY.getNameForObject(block));
                    if (!mappings.containsKey(name)) {
                        mappings.put(name, (short) blockId);
                    }
                }
            }
        }
        for (int i = 0; i < extraBlocksNibble.length; i++) {
            if (i * 2 + 1 < extraBlocks.length) {
                extraBlocksNibble[i] = (byte) ((extraBlocks[i * 2 + 0] << 4) | extraBlocks[i * 2 + 1]);
            } else {
                extraBlocksNibble[i] = (byte) (extraBlocks[i * 2 + 0] << 4);
            }
        }
        final NBTTagCompound nbtMapping = new NBTTagCompound();
        for (final Map.Entry<String, Short> entry : mappings.entrySet()) {
            nbtMapping.setShort(entry.getKey(), entry.getValue());
        }
        nbtdata.setByteArray(Constants.NBT.BLOCKS, localBlocks);
        nbtdata.setByteArray(Constants.NBT.DATA, localMetadata);
        if (extra) {
            nbtdata.setByteArray(Constants.NBT.ADD_BLOCKS, extraBlocksNibble);
        }
        nbtdata.setTag(Constants.NBT.MAPPING_SCHEMATICA, nbtMapping);
        return nbtdata;
    }

    public boolean isVecInside(Vec3i vec, AxisAlignedBB aabb) {
        return vec.getX() >= aabb.minX && vec.getX() <= aabb.maxX &&
                vec.getY() >= aabb.minY && vec.getY() <= aabb.maxY &&
                vec.getZ() >= aabb.minZ && vec.getZ() <= aabb.maxZ;
    }

    public void generate(World world, BlockPos pos) {
        pos = pos.add(getOrigin());
        for (int x = 0; x < getXLayers(); x++) {
            for (int y = 0; y < getYLayers(); y++) {
                for (int z = 0; z < getZLayers(); z++) {
                    IBlockState blockState = this.template[x][y][z];
                    BlockPos localPos = new BlockPos(x, y, z);
                    if (blockState != null && blockState.getBlock() != Blocks.air) {
//                        System.err.println(localPos);
//                        System.err.println(collusion);
                        if (isVecInside(localPos, collusion)) {
                            world.setBlockState(pos.add(localPos), blockState, 3);
                        } else {
                            if (world.getBlockState(pos.add(localPos)).getBlock().isReplaceable(world, pos)) {
                                world.setBlockState(pos.add(localPos), blockState, 3);
                            }
                        }
                    }
                }
            }
        }
    }

    public boolean checkGenerate(World world, BlockPos pos) {
        if (this.getOrigin() == null) {
            return false;
        }


        pos = pos.add(getOrigin());
        int xStart = (int) (0 + collusion.minX);
        int yStart = (int) (0 + collusion.minY);
        int zStart = (int) (0 + collusion.minZ);
        int xL = (int) (collusion.maxX + collusion.minX);
        int yL = (int) (collusion.maxY + collusion.minY);
        int zL = (int) (collusion.maxZ + collusion.minZ);
        for (int x = xStart; x < xL; x++) {
            for (int y = yStart; y < yL; y++) {
                for (int z = zStart; z < zL; z++) {
                    IBlockState blockState = this.template[x][y][z];
                    BlockPos localPos = new BlockPos(x, y, z);
                    if (!world.getBlockState(pos.add(localPos)).getBlock().isReplaceable(world, pos) ||
                            !world.isAirBlock(pos.add(localPos))
                            ) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}