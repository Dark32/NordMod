package ru.nord.common.tiles.interfaces;

public interface IEnergyTile {
    /**
     * Установить значение энергию
     *
     * @param energy новое значение энергии
     * @return новое значение энергии
     */
    public int setEnergy(int energy);

    /**
     * Получить значение энергии
     *
     * @return значение энергии
     */
    public int getEnergy();

    /**
     * Добавить энергию, учитывая максимальное значение
     *
     * @param energy сколько добавить
     * @return новое значение энергии
     */
    public int addEnergy(int energy);

    /**
     * Убавить энегию, учитывая минимальное значение (ноль)
     *
     * @param energy сколько убавить
     * @return новое значение энергии
     */
    public int subEnergy(int energy);

    /**
     * Получить максимальное значение энергии
     *
     * @return максимальное значение энергии
     */
    public int getMaxEnergy();

    /**
     * Принимает ли энергию
     *
     * @return boolean
     */
    public boolean isAcepter();

    /**
     * Раздаёт ли энергию
     *
     * @return boolean
     */
    public boolean isDispenser();

    /**
     * Можем ли убавить энергию
     *
     * @param energy на сколько пробуем убавить
     * @return boolean
     */
    public boolean hasSubEnergy(int energy);

    /**
     * Можем ли прибавить энергию
     *
     * @param energy на сколько хотим прибавить
     * @return boolean
     */
    public boolean hasAddEnergy(int energy);

    /**
     * Размер пакета энергии для расхода на зарядку и передачу
     * @return int
     */
    public int getPacketEnergy();
}
