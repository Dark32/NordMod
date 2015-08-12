package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.items.interfaces.IEnergyCharges;

import java.util.List;

public class ItemEnergyStorageNBT extends ItemEnergyStorageDamagable implements IEnergyCharges {

    public ItemEnergyStorageNBT(int maxEnergy) {
        super(maxEnergy);
        this.setMaxStackSize(1);
        this.setHasSubtypes(false);
//        this.setMaxDamage(maxEnergy + 1);
    }

    private NBTTagCompound getNBT(ItemStack itemStack) {
        NBTTagCompound nbttagcompound;
        if (itemStack.getTagCompound() != null) {
            nbttagcompound = itemStack.getTagCompound();
        } else {
            nbttagcompound = new NBTTagCompound();
        }
        return nbttagcompound;
    }

    @Override
    public int currectEnergy(ItemStack itemStack) {
        NBTTagCompound nbt = getNBT(itemStack);
        if (nbt.hasKey("energy")) {
            return nbt.getInteger("energy");
        } else {
            nbt.setInteger("energy", 0);
            return currectEnergy(itemStack);
        }
    }

    @Override
    public int setEnergy(ItemStack itemStack, int energy) {
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.setInteger("energy", energy);
        itemStack.setTagCompound(nbt);
        return currectEnergy(itemStack);
    }

//        @Override
//    public int getDamage(ItemStack stack) {
//        return maxEnergy(stack)-currectEnergy(stack);
//    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return (double) currectEnergy(stack) / (double) maxEnergy(stack);
    }
    @Override
    public boolean showDurabilityBar(ItemStack stack)
    {
        return true;
    }
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {
        ItemStack itemStack = new ItemStack(itemIn, 1);
        NBTTagCompound nbt = getNBT(itemStack);
        setEnergy(itemStack, maxEnergy(itemStack));
        itemStack.setTagCompound(nbt);
        subItems.add(itemStack);
        subItems.add(new ItemStack(itemIn, 1));
    }
}