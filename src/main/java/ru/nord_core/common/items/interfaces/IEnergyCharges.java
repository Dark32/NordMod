package ru.nord_core.common.items.interfaces;

import net.minecraft.item.ItemStack;

public interface IEnergyCharges {
    /**
     * Текущая энергия
     *
     * @param itemStack предмет
     * @return значение
     */
    int currectEnergy(ItemStack itemStack);

    /**
     * Максимум энергии
     *
     * @param itemStack предмет
     * @return значение
     */
    int maxEnergy(ItemStack itemStack);

    /**
     * Установить энергию
     * @param itemStack предмет
     * @param energy значение
     * @return новое значение
     */
    int setEnergy(ItemStack itemStack, int energy);


    /**
     * Разер пакета энергии. Используется при разрядке предмета.
     * При зарядке используется размер пакета заряжающего устройства
     *
     * @param itemStack предмет
     * @return значение
     */
    int packetEnergy(ItemStack itemStack);
}
