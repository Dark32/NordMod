package ru.nord_core.common.blocks.interfaces;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

/**
 * Created by andrew on 03.04.16.
 */
public interface IWorkable {
    boolean hasWorking(IBlockAccess world, BlockPos pos);
}
