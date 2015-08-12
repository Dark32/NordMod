package ru.nord_core.common.tiles.interfaces;

import net.minecraft.inventory.IInventory;

public interface IGenerator extends IInventory, IEnergyTile {
    /**
     * Получаем время горения
     *
     * @return int
     */
    int getBurnTime();

    /**
     * @param val Установить время горения
     */
    void setBurnTime(int val);


    /**
     * @param val установить время горения топлиа
     */
    void setFuelBurnTime(int val);

    /**
     * @return получить время горения топлва
     */
    int getFuelBurnTime();

    /**
     * Пакет энергии для горения
     * burnSpeed
     */
    int getBurnPacketEnergy();

}
