package ru.nord_core.common.helpers;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class NBTHelper {

    public static void setBlockPos(NBTTagCompound nbt, String tag, BlockPos pos) {
        NBTTagCompound posTag = new NBTTagCompound();
        posTag.setInteger("PosX", pos.getX());
        posTag.setInteger("PosY", pos.getY());
        posTag.setInteger("PosZ", pos.getZ());
        nbt.setTag(tag, posTag);
    }

    public static BlockPos getBlockPos(NBTTagCompound nbt, String tag) {
        NBTTagCompound pos1Tag = nbt.getCompoundTag(tag);
        int x = pos1Tag.getInteger("PosX");
        int y = pos1Tag.getInteger("PosY");
        int z = pos1Tag.getInteger("PosZ");
        return new BlockPos(x, y, z);
    }

    public static void setAxisAlignedBB(NBTTagCompound nbt, String tag, AxisAlignedBB aabb) {
        NBTTagCompound aabbTag = new NBTTagCompound();
        NBTHelper.setBlockPos(aabbTag, "MinPos", new BlockPos(aabb.minX, aabb.minY, aabb.minZ));
        NBTHelper.setBlockPos(aabbTag, "MaxPos", new BlockPos(aabb.maxX, aabb.maxY, aabb.maxZ));
        nbt.setTag(tag, aabbTag);
    }

    public static AxisAlignedBB getAxisAlignedBB(NBTTagCompound nbt, String tag) {
        NBTTagCompound aabbTag = nbt.getCompoundTag(tag);
        BlockPos minPos = NBTHelper.getBlockPos(aabbTag, "MinPos");
        BlockPos maxPos = NBTHelper.getBlockPos(aabbTag, "MaxPos");
        return new AxisAlignedBB(minPos,maxPos);
    }
}
