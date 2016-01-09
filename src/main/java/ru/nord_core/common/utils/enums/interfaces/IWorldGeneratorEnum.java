package ru.nord_core.common.utils.enums.interfaces;

import net.minecraft.world.gen.feature.WorldGenAbstractTree;
/**
 *  * Используется, как пример, в ростке {@link ru.nord_core.common.blocks.abstracts.BlockAbstractSapling#generateTree}
 * Реализуется в enum {@link ru.nord.common.utils.enums.EnumNordPlank}
 **/
public interface IWorldGeneratorEnum {
    /**
     * Что генерировать
     * @return генератор
     */
    WorldGenAbstractTree generate();
}
