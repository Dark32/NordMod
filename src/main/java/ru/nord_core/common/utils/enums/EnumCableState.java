package ru.nord_core.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumCableState implements IMetadataEnum {
//    INVALID(-1,"invalid"),// Что-то пошло не так
    UNDEFINED(0,"undefined",false),// Неизвестно, не к чему подключаться, не к чему подсоеденятся
    CONNECT(1, "connect",true),// Подсоеденён
    DISCONNECT(2,"disconnect",false),// Рассоеденён
    INPUT(3, "input",true),//В режиме прима
    OUTPUT(4, "output",true),//в режиме передачи
    ;
    private final int metadata;
    private final boolean bound;
    private final String name;

    EnumCableState(int metadata, String name, boolean bound) {
        this.metadata = metadata;
        this.bound = bound;
        this.name = name;
    }

    @Override
    public int getMetadata() {
        return this.metadata;
    }

    public boolean getBound() {
        return this.bound;
    }

    @Override
    public String getName() {
        return this.name;
    }
    static public EnumCableState getByType(int type) {
        for (int i = 0; i < EnumCableState.values().length; i++) {
            EnumCableState state = EnumCableState.values()[i];
            if (state.getMetadata() == type) return state;
        }
        return EnumCableState.UNDEFINED;
    }
}
