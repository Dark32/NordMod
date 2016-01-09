package ru.nord_core.common.utils.enums.interfaces;

/**
 * Created by andrew on 08.01.16.
 * Отвечает за окрашиваемость блока в цвет биома.
 * Используется, как пример, в листве {@link  ru.nord_core.common.blocks.abstracts.BlockAbstractLeaves#colorMultiplier}
 * Реализуется в enum {@link ru.nord.common.utils.enums.EnumNordPlank}
 */
public interface IBiomeColoredEnum {
    /**
     * Окрашивать или нет блок в цвет биома
     * @return окрашиваемость
     */
    boolean getColorize();
}
