package ru.nord_core.common.items.abstracts;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;
public abstract class AEnergyToolNBT extends AEnergyTool {

    protected AEnergyToolNBT(float attackDamage, ToolMaterial material, int energyByUse, int energyByHit) {
        super(attackDamage, material, energyByUse, energyByHit);
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
            itemStack.setTagCompound(nbt);
            return 0;
        }
    }

    @Override
    public int setEnergy(ItemStack itemStack, int energy) {
        NBTTagCompound nbt = getNBT(itemStack);
        nbt.setInteger("energy", energy);
        itemStack.setTagCompound(nbt);
        return currectEnergy(itemStack);
    }

    @Override
    public double getDurabilityForDisplay(ItemStack stack) {
        return 1.00d - ((double)currectEnergy(stack)) / maxEnergy(stack);
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
        nbt.setInteger("energy", maxEnergy(itemStack));
        itemStack.setTagCompound(nbt);
        subItems.add(itemStack);
        subItems.add(new ItemStack(itemIn, 1));
    }
}
