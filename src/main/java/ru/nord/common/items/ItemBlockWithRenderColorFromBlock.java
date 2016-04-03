package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.client.utils.IColorizeBlock;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockWithRenderColorFromBlock extends ItemBlockMetadata implements IItemColor {
    public ItemBlockWithRenderColorFromBlock(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
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