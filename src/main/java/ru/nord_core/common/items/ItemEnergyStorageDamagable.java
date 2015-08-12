package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.items.interfaces.IEnergyCharges;
import ru.nord_core.common.utils.Constants;

import java.util.List;

public class ItemEnergyStorageDamagable extends ItemBase implements IEnergyCharges {
    private int _maxEnergy;

    public ItemEnergyStorageDamagable(int maxEnergy,String modid) {
        super(modid);
        this._maxEnergy = maxEnergy;
        this.setMaxStackSize(1);
        this.setHasSubtypes(false);
        this.setMaxDamage(maxEnergy + 1);
    }

    @Override
    public int currectEnergy(ItemStack itemStack) {
        return _maxEnergy - itemStack.getItemDamage();
    }

    @Override
    public int maxEnergy(ItemStack itemStack) {
        return _maxEnergy;
    }

    @Override
    public int packetEnergy(ItemStack itemStack) {
        return Constants.SHARE_MULTIPLE;
    }

    @Override
    public int setEnergy(ItemStack itemStack, int energy) {
        itemStack.setItemDamage(maxEnergy(itemStack) - energy);
        return currectEnergy(itemStack);
    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        tooltip.add("Energy: " + EnumChatFormatting.RED + currectEnergy(stack) / Constants.SHARE_MULTIPLE + "/" + maxEnergy(stack) / Constants.SHARE_MULTIPLE + " " +Constants.ENERGY);
        tooltip.add("dEnergy: " + EnumChatFormatting.RED + currectEnergy(stack) % Constants.SHARE_MULTIPLE +  " " +Constants.ENERGY);
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(itemIn, 1,_maxEnergy));
        subItems.add(new ItemStack(itemIn, 1, 0));
    }
}