package ru.nord_deco.common.items.abstracts;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.client.utils.IColorizeBlock;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;

public class ItemBlockAbstractSlab extends ItemSlab implements IItemColor
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

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemstack(ItemStack stack, int renderPass)
    {
        if (this.block instanceof IColorizeBlock){
            IColorizeBlock iBlockColor = (IColorizeBlock)block;
            return iBlockColor.getColorForStack(stack,block);
        }
        return 0;
    }
}