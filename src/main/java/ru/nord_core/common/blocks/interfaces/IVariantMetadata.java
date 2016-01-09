package ru.nord_core.common.blocks.interfaces;

import net.minecraft.block.properties.PropertyEnum;

/**
 * Created by andrew on 09.01.16.
 */
public interface IVariantMetadata {

    public PropertyEnum getVariant();

    Comparable getEnumByMetadata(int meta);
}
