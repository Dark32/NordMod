package ru.nord_core.common.property;

import net.minecraftforge.common.property.IUnlistedProperty;

public class UnlistedPropertyInteget implements IUnlistedProperty<Integer> {
    private final String name;

    public UnlistedPropertyInteget(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public boolean isValid(Integer value) {
        return true;
    }
    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }
    @Override
    public String valueToString(Integer value) {
        return value.toString();
    }
}
