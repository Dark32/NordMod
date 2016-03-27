package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
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
        if (this.block instanceof IBlockColor){
            IBlockColor iBlockColor = (IBlockColor)block;
//            return iBlockColor.colorMultiplier()
            //todo доделать это
        }
//        return this.block.getRenderColor(this.block.getStateFromMeta(stack.getMetadata()));
        return 0;
    }
}