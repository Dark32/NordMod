package ru.nord_core.common.blocks.interfaces;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IWrenchable {
    /**
     * Клик ключём по блоку
     * @param worldIn мир
     * @param pos позиция
     * @param state состояние
     * @param playerIn игрок
     * @param side сторона
     * @param hitX позиция клика
     * @param hitY позиция клика
     * @param hitZ позиция клика
     * @return действие
     */
    EnumActionResult wrenche(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ);

    /**
     * Скрутили
     * @param world мир
     * @param pos место
     * @param tile тайл
     */
    void doItemDrop(final World world, final BlockPos pos, TileEntity tile);
}
