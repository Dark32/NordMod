package ru.nord_core.common.utils.enums.interfaces;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.util.IStringSerializable;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata2;


public interface IMetadata2Enum extends IStringSerializable, IMetadataEnum {
    /**
     * Получить переиндексованную методату из списка
     * @param list список
     * @return метадата
     */
    int getReIndexMetadata(ImmutableList list);

    /**
     * Получить переиндексованную методату для блока
     * @param block блок
     * @return метадата
     */
    int getReIndexMetadata(Block block);
}
