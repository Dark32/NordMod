package ru.nord_core.common.utils;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import ru.nord_core.common.entity.EntitySittableBlock;


public class SittableUtil {

    public static boolean sitOnBlock(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer, double par6)
    {
        if (!checkForExistingEntity(par1World, x, y, z, par5EntityPlayer))
        {
            EntitySittableBlock nemb = new EntitySittableBlock(par1World, x, y, z, par6);
            par1World.spawnEntityInWorld(nemb);
            par5EntityPlayer.mountEntity(nemb);
        }
        return true;
    }

    public static boolean sitOnBlockWithRotationOffset(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer, double par6, int metadata, double offset)
    {
        if (!checkForExistingEntity(par1World, x, y, z, par5EntityPlayer))
        {
            EntitySittableBlock nemb = new EntitySittableBlock(par1World, x, y, z, par6, metadata, offset);
            par1World.spawnEntityInWorld(nemb);
            par5EntityPlayer.mountEntity(nemb);
        }
        return true;
    }

    public static boolean checkForExistingEntity(World par1World, double x, double y, double z, EntityPlayer par5EntityPlayer)
    {
        List<EntitySittableBlock> listEMB = par1World.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
        for (EntitySittableBlock mount : listEMB)
        {
            if (mount.blockPosX == x && mount.blockPosY == y && mount.blockPosZ == z)
            {
                if (mount.riddenByEntity == null)
                {
                    par5EntityPlayer.mountEntity(mount);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean isSomeoneSitting(World world, double x, double y, double z)
    {
        List<EntitySittableBlock> listEMB = world.getEntitiesWithinAABB(EntitySittableBlock.class, new AxisAlignedBB(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1D, 1D, 1D));
        for (EntitySittableBlock mount : listEMB)
        {
            if (mount.blockPosX == x && mount.blockPosY == y && mount.blockPosZ == z)
            {
                return mount.riddenByEntity != null;
            }
        }
        return false;
    }
}
