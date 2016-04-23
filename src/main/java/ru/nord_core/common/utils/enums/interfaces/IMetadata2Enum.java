package ru.nord_core.common.utils.enums.interfaces;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.IStringSerializable;


public interface IMetadata2Enum extends IStringSerializable, IMetadataEnum {
    /**
     * Получить переиндексованную методату из списка
     * @param list список
     * @return метадата
     */
    int getReIndexMetadata(ImmutableList list);

}
