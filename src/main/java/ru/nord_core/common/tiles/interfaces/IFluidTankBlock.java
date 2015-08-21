package ru.nord_core.common.tiles.interfaces;

import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidHandler;

public interface IFluidTankBlock extends IFluidHandler {

    /**
     * Получаем внутренний танк
     * @return внутренний танк
     */
    FluidTank getTank() ;
    /**
     * Пакет Жидкости для работы
     *
     */
    int getFluidWorkPacket();
}
