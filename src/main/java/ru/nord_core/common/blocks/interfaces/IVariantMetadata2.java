package ru.nord_core.common.blocks.interfaces;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.state.IBlockState;

/**
 * Created by andrew on 09.01.16.
 * Отвечает за блоки с простой метадатой типа перечесления хранящийся в тайле {@link ru.nord_core.common.tiles.abstracts.TileMetadata}
 * Сопряжённ c {@link ru.nord_core.common.items.abstracts.ItemBlockMetadata2}
 */
public interface IVariantMetadata2 extends IVariantMetadata {
    /**
     * Получаем список состояний блока после манипуляций с предикатами или без оных
     * @return список
     */
    ImmutableList getAllowedValues();

    /**
     * Получаем переиндексированную метадату
     * @param state состояние
     * @return переиндексированная метадата
     */
    int getReIndexMeta(IBlockState state);
}
