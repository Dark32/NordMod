package ru.nord.common.items.interfaces;

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
     * Добавить энергию
     *
     * @param itemStack предмет
     * @param add       сколько доббавить
     * @return новое значение
     */
    int addEnergy(ItemStack itemStack, int add);

    /**
     * Отнять энергию
     *
     * @param itemStack предмет
     * @param sub       сколько отнять
     * @return новое значение
     */
    int subEnergy(ItemStack itemStack, int sub);

    /**
     * Добавить пакет энергии
     *
     * @param itemStack предмет
     * @return новое значение
     */
    int addEnergy(ItemStack itemStack);

    /**
     * Отнять пакет энергии
     *
     * @param itemStack предмет
     * @return новое значение
     */
    int subEnergy(ItemStack itemStack);

    /**
     * Есть ли столько энергии
     *
     * @param itemStack предмет
     * @param energy    энерия
     * @return есть ли
     */
    boolean hasEnergy(ItemStack itemStack, int energy);

    /**
     * Есть ли пакет энергии
     *
     * @param itemStack предмет
     * @return есть ли
     */
    boolean hasEnergy(ItemStack itemStack);

    /**
     * Разер пакета энергии. Используется при разрядке предмета.
     * При зарядке используется размер пакета заряжающего устройства
     *
     * @param itemStack предмет
     * @return значение
     */
    int packetEnergy(ItemStack itemStack);

    /**
     * Установить энергию
     * @param itemStack предмет
     * @param energy значение
     * @return новое значение
     */
    int setEnergy(ItemStack itemStack, int energy);

    int getDeficient(ItemStack itemStack);
}
