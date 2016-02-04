package ru.nord_core.common.items;


import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.utils.schematic.Schematic;

import java.util.List;


public class ItemDebugStickSchematicLoad extends ItemBase {

    public ItemDebugStickSchematicLoad(String modid) {
        super(modid);
        this.setMaxStackSize(1);
        this.setCreativeTab(CreativeTabs.tabAllSearch);
    }

    @Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (worldIn.isRemote) return false;
        if (!playerIn.isSneaking()) {
            System.out.println("Load");
            Schematic schematic = new Schematic();
            schematic.getFromFile("1454608275206.schematic");
            schematic.generate(worldIn,pos);
        }
        return false;
    }


    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn) {
        return itemStackIn;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
          }

    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack stack) {
        return true;
    }

    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }

}