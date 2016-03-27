package ru.nord_deco.common.items.abstracts;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;

public class ItemBlockAbstractSlab extends ItemSlab
{

    public ItemBlockAbstractSlab(Block block, BlockAbstractSlab singleSlab, BlockAbstractSlab doubleSlab , final boolean stacked)
    {
        super(block, singleSlab, doubleSlab);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }
    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return ((BlockAbstractSlab)(this.block)).getUnlocalizedName(stack.getMetadata());
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public int getColorFromItemStack(ItemStack stack, int renderPass)
//    {
//        return this.block.getRenderColor(this.block.getStateFromMeta(stack.getMetadata()));
//    }
}