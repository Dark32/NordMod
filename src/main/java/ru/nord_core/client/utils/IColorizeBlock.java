package ru.nord_core.client.utils;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.item.ItemStack;

/**
 * Created by andrew on 03.04.16.
 */

public interface IColorizeBlock extends IBlockColor {
    int getColorForStack(ItemStack stack,Block block);
}
