package ru.nord.common.tiles.interfaces;

import net.minecraft.inventory.IInventory;
import ru.nord.common.lib.recipes.Interfaces.IAbstractRecipes;

public interface IMachine extends IInventory, IEnergyTile {
    /**
     * Получаем время горения
     *
     * @return int
     */
    int getBurnTime();

    /**
     * @return Текущий прогресс обработки
     */
    int getCurrentItemEnergyProgress();

    /**
     * @return Текущие требование энергии
     */
    int getCurrentItemEnergyNeed();

    /**
     * @param val Установить время горения
     */
    void setBurnTime(int val);

    /**
     * @param val установить текущий прогресс обработки
     */
    void setCurrentItemEnergyProgress(int val);

    /**
     * @param val установить требование энергии
     */
    void setCurrentItemEnergyNeed(int val);

    /**
     * @param val установить время горения топлиа
     */
    void setFuelBurnTime(int val);

    /**
     * @return получить время горения топлва
     */
    int getFuelBurnTime();

    /**
     * @return получить список рецептов
     */
    IAbstractRecipes getRecipes();

    /**
     * Пакет энергии для обработки предмета. Сколько энергии отнять за тик работы.
     * workSpeed
     */
    int getWorkPacketEnergy();

    /**
     * Пакет энергии для горения
     * burnSpeed
     */
    int getBurnPacketEnergy();

}
