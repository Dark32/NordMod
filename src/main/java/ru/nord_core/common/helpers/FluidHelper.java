package ru.nord_core.common.helpers;


import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidStack;

public class FluidHelper {

    public static void bucktDrain(EntityPlayer playerIn, ItemStack item, World worldIn){
        if (!playerIn.capabilities.isCreativeMode){
            ItemStack newBucket = FluidContainerRegistry.drainFluidContainer(item);
            if (item.stackSize == 1) {
                playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, (ItemStack)newBucket);
            } else {
                item.stackSize--;
                SpawnEntityHelper.spawnItemStack(worldIn,playerIn.getPosition(),newBucket);
            }
        }
    }
    public static void bucktFill(EntityPlayer playerIn, ItemStack item, World worldIn, FluidStack fluid){
        if (!playerIn.capabilities.isCreativeMode){
            ItemStack newBucket = FluidContainerRegistry.fillFluidContainer(fluid, item);
            if (item.stackSize == 1) {
                playerIn.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, newBucket);
            } else {
                item.stackSize--;
                SpawnEntityHelper.spawnItemStack(worldIn,playerIn.getPosition(),newBucket);
            }
        }
    }
}
