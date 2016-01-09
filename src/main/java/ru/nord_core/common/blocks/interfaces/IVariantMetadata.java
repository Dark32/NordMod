package ru.nord_core.common.blocks.interfaces;

import net.minecraft.block.properties.PropertyEnum;

/**
 * Created by andrew on 09.01.16.
 * Отвечает за блоки с простой метадатой типа перечесления
 * Сопряжённ c {@link ru.nord_core.common.items.abstracts.ItemBlockMetadata}
 */
public interface IVariantMetadata {
    /**
     * Получить варианты блока
     * @return варианты
     */
    public PropertyEnum getVariant();

    /**
     * Получить Enum по методате
     * Ожидается что Enum реализует {@link ru.nord_core.common.utils.enums.interfaces.IMetadataEnum}
     * @param meta методата
     * @return enum
     */
    Comparable getEnumByMetadata(int meta);
}
