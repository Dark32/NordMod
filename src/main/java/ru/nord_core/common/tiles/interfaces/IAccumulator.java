package ru.nord_core.common.tiles.interfaces;

import net.minecraft.inventory.IInventory;

public interface IAccumulator extends IInventory, IEnergyTile {

    /**
     * Получаем бонусную энергию с улучшения
     * @param slot слот
     * @return бонусная энергия
     */
    int getUpgradeEnergy(int slot);

    /**
     * Энергии на обычную шкалу
     * @return энергии
     */
    int getEnergyByLine();

    /**
     * Шкад энергии
     * @return шкал
     */
    int getLineOnEnergy();

    /**
     * Установить энергию апгрейдам
     * @param energy значение
     * @return новое значение энергии
     */
    int setBonusEnergy(int energy) ;

    /**
     * Получить энергию со всех апгрейдов
     * @return значение энергии
     */
    int getBonusEnergy();

    /**
     * Получить максимальную энергию со всех апгрейдов
     * @return значение энергии
     */
    int getBonusMaxEnergy();

    /**
     * Добавить бонусную энергию
     * @param energy значение
     * @return новое значение энергии
     */
    int addBonusEnergy(int energy);

    /**
     * Отнять бонусную эергию
     * @param energy значение
     * @return новое значение энергии
     */
    int subBonusEnergy(int energy);

    /**
     * Можно ли отнять бонусную энергию
     * @param energy сколько
     * @return можно ли
     */
    boolean hasSubBonusEnergy(int energy);

    /**
     * Можно ли прибавить бонусную энергию
     * @param energy значение
     * @return можно ли
     */
    boolean hasAddBonusEnergy(int energy);

    /**
     * Выравнять энергию из бонусных в обычную. Выравнивается по пакету с каждого бонуса
     */
    boolean conserved ();
}
