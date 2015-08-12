package ru.nord_core.common.items.abstracts;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public abstract class ItemBlockMetadata extends ItemBlock {
    public ItemBlockMetadata(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public abstract String getUnlocalizedName(ItemStack stack);
//    {
//        int meta = stack.getMetadata();
//        if (meta < EnumOre.getNames().length) {
//            return super.getUnlocalizedName() + "." + EnumOre.byMetadata(stack.getMetadata()).getName();
//        } else {
//            return super.getUnlocalizedName() + ".errorData";
//        }
//    }
//    @Override
//    public abstract void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4);
//    {
//        list.add(EnumOre.byMetadata(stack.getMetadata()).getMetal());
//    }

}
