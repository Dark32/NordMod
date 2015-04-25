package ru.nord.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.items.interfaces.IEnergyCharges;

import java.util.List;

public class ItemEnergyStorageDamagable extends ItemBase implements IEnergyCharges {
    private int _maxEnergy;

    public ItemEnergyStorageDamagable(int maxEnergy) {
        this._maxEnergy = maxEnergy;
        this.setMaxStackSize(1);
        this.setHasSubtypes(false);
        this.setMaxDamage(maxEnergy + 1);
    }

    @Override
    public int currectEnergy(ItemStack itemStack) {
        return _maxEnergy - itemStack.getItemDamage() + 1;
    }

    @Override
    public int maxEnergy(ItemStack itemStack) {
        return _maxEnergy;
    }

    @Override
    public int addEnergy(ItemStack itemStack, int add) {
        if (currectEnergy(itemStack) <= (maxEnergy(itemStack)) - add)
            itemStack.setItemDamage(itemStack.getItemDamage() - add);
        return currectEnergy(itemStack);
    }

    @Override
    public int subEnergy(ItemStack itemStack, int sub) {
        if (currectEnergy(itemStack) >= sub)
            itemStack.setItemDamage(itemStack.getItemDamage() + sub);
        return currectEnergy(itemStack);
    }

    @Override
    public int addEnergy(ItemStack itemStack) {
        return addEnergy(itemStack, packetEnergy(itemStack));
    }

    @Override
    public int subEnergy(ItemStack itemStack) {
        return subEnergy(itemStack, packetEnergy(itemStack));
    }

    @Override
    public boolean hasEnergy(ItemStack itemStack, int energy) {
        return currectEnergy(itemStack) > energy;
    }

    @Override
    public boolean hasEnergy(ItemStack itemStack) {
        return hasEnergy(itemStack, packetEnergy(itemStack));
    }

    @Override
    public int packetEnergy(ItemStack itemStack) {
        return 16;
    }

    @Override
    public int setEnergy(ItemStack itemStack, int energy) {
        itemStack.setItemDamage(maxEnergy(itemStack) - energy);
        return currectEnergy(itemStack);
    }

    @Override
    public int getDeficient(ItemStack itemStack) {
        return maxEnergy(itemStack)-currectEnergy(itemStack);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List tooltip, boolean advanced) {
        tooltip.add("Energy: " + EnumChatFormatting.RED + currectEnergy(stack) / 16 + "/" + maxEnergy(stack) / 16);
        tooltip.add("dEnergy: " + EnumChatFormatting.RED + currectEnergy(stack) % 16 + " /16");
    }

    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        subItems.add(new ItemStack(itemIn, 1, this.getMaxDamage()));
        subItems.add(new ItemStack(itemIn, 1, 0));
    }
}