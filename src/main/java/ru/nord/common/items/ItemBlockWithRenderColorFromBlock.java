package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockWithRenderColorFromBlock extends ItemBlockMetadata {
    public ItemBlockWithRenderColorFromBlock(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        return this.block.getRenderColor(this.block.getStateFromMeta(stack.getMetadata()));
    }
}