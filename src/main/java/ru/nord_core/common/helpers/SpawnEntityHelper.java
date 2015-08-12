package ru.nord_core.common.helpers;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class SpawnEntityHelper {

    static void spawnItemStack(World world, BlockPos pos,ItemStack itemstack ){
        EntityItem entityitem = new EntityItem(world,
                pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                itemstack);
        entityitem.setDefaultPickupDelay();
        world.spawnEntityInWorld(entityitem);
    }
}
