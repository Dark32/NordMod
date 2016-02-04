package ru.nord_core.common.utils.schematic;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import ru.nord_core.NordCore;
import ru.nord_core.common.utils.Constants;
import ru.nord_core.common.utils.schematic.abstracts.ASchematic;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Schematic extends ASchematic {

    private IBlockState[][][] template; //lhw=xyz
    public Schematic() {
    }

    public void getFromFile(String fileName) {
        fileName = "schematics/" + fileName;
        NBTTagCompound tagCompound;
        File file = new File(NordCore.proxy.getDataDirectory(), fileName);
        try {
            tagCompound = SchematicUtils.get().readTagCompoundFromFile(file);
        } catch (final Exception ex) {
            ex.printStackTrace();
            return;

        }

        boolean extra = false;
        byte extraBlocks[] = null;
        byte extraBlocksNibble[];

        String version = tagCompound.getString(Constants.NBT.VERSION);

        if (!version.equals(Constants.NBT.NordShem)) {
            return;
        }
        setXLayers(tagCompound.getShort(Constants.NBT.WIDTH));
        setYLayers(tagCompound.getShort(Constants.NBT.HEIGHT));
        setZLayers(tagCompound.getShort(Constants.NBT.LENGTH));
        short ox = tagCompound.getShort(Constants.NBT.OriginX);
        short oy = tagCompound.getShort(Constants.NBT.OriginY);
        short oz = tagCompound.getShort(Constants.NBT.OriginZ);

        setOrigin(new BlockPos(ox, oy, oz));

        final byte[] localBlocks = tagCompound.getByteArray(Constants.NBT.BLOCKS);
        final byte[] localMetadata = tagCompound.getByteArray(Constants.NBT.DATA);

        if (tagCompound.hasKey(Constants.NBT.ADD_BLOCKS)) {
            extra = true;
            extraBlocksNibble = tagCompound.getByteArray(Constants.NBT.ADD_BLOCKS);
            extraBlocks = new byte[extraBlocksNibble.length * 2];
            for (int i = 0; i < extraBlocksNibble.length; i++) {
                extraBlocks[i * 2 + 0] = (byte) ((extraBlocksNibble[i] >> 4) & 0xF);
                extraBlocks[i * 2 + 1] = (byte) (extraBlocksNibble[i] & 0xF);
            }
        } else if (tagCompound.hasKey(Constants.NBT.ADD_BLOCKS_SCHEMATICA)) {
            extra = true;
            extraBlocks = tagCompound.getByteArray(Constants.NBT.ADD_BLOCKS_SCHEMATICA);
        }

        Short id;
        final Map<Short, Short> oldToNew = new HashMap<>();
        if (tagCompound.hasKey(Constants.NBT.MAPPING_SCHEMATICA)) {
            final NBTTagCompound mapping = tagCompound.getCompoundTag(Constants.NBT.MAPPING_SCHEMATICA);
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

    public NBTTagCompound getFromWorld(AxisAlignedBB aabb, World worldIn, BlockPos pos) {
        NBTTagCompound nbtdata = new NBTTagCompound();
        final Map<String, Short> mappings = new HashMap<>();
        short l = (short) (aabb.maxX - aabb.minX + 1);
        short h = (short) (aabb.maxY - aabb.minY + 1);
        short w = (short) (aabb.maxZ - aabb.minZ + 1);
        final int size = w * l * h;
        final byte[] localBlocks = new byte[size];
        final byte[] localMetadata = new byte[size];
        final byte[] extraBlocks = new byte[size];
        boolean extra = false;
        final byte[] extraBlocksNibble = new byte[(int) Math.ceil(size / 2.0)];

        nbtdata.setShort(Constants.NBT.WIDTH, w);
        nbtdata.setShort(Constants.NBT.HEIGHT, h);
        nbtdata.setShort(Constants.NBT.LENGTH, l);

        nbtdata.setString(Constants.NBT.MATERIALS, Constants.NBT.FORMAT_ALPHA);
        nbtdata.setString(Constants.NBT.VERSION, Constants.NBT.NordShem);

        nbtdata.setShort(Constants.NBT.OriginX, (short) pos.getX());
        nbtdata.setShort(Constants.NBT.OriginY, (short) pos.getY());
        nbtdata.setShort(Constants.NBT.OriginZ, (short) pos.getZ());

        for (int xi = (int) aabb.minX; xi <= aabb.maxX; xi++) {
            final int x = xi - (int) aabb.minX;
            for (int yi = (int) aabb.minY; yi <= aabb.maxY; yi++) {
                final int y = yi - (int) aabb.minY;
                for (int zi = (int) aabb.minZ; zi <= aabb.maxZ; zi++) {
                    final int z = zi - (int) aabb.minZ;
                    final int index = x + (y * l + z) * w;
                    IBlockState blockState = worldIn.getBlockState(new BlockPos(xi, yi, zi));
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

    public void generate(World world, BlockPos pos) {
        pos = pos.add(getOrigin());
        for (int x = 0; x < getYLayers(); x++) {
            for (int y = 0; y < getXLayers(); y++) {
                for (int z = 0; z < getZLayers(); z++) {
                    IBlockState blockState = this.template[x][y][z];
                    if (blockState != null && blockState.getBlock() != Blocks.air) {
                        world.setBlockState(pos.add(x, y, z), blockState, 3);
                    }
                }
            }
        }
    }

}