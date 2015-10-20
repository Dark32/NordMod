package ru.nord.common.utils.enums;

import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumFoodNord implements IMetadataEnum {

    a_lot_of_pancakes(0,"a_lot_of_pancakes",8,4),
    Pancake(1,"Pancake",2,1),
    Pancakeswithcottagecheese(2,"Pancakeswithcottagecheese",4,2),
    pancakeswithcurd(3,"pancakeswithcurd",4,2),
    pancakeswithfish(4,"pancakeswithfish",6,3),
    Pancakeswithjam(5,"Pancakeswithjam",4,2),
    Pancakeswithjam2(6,"Pancakeswithjam2",5,2),
    pancakeswithmeat(7,"pancakeswithmeat",8,4),
    pancakeswithonionsandeggs(8,"pancakeswithonionsandeggs",5,2),
    pancakeswithpotatoes(9,"pancakeswithpotatoes",8,4),
    pancakeswithsorrel(10,"pancakeswithsorrel",4,2),;
    private final String name;
    private final int meta;
    private final int saturation;
    private final int heal;

    EnumFoodNord(int _meta, String _name, int _saturation, int _heal) {
        this.name = _name;
        this.meta = _meta;
        this.saturation = _saturation;
        this.heal = _heal;
    }

    public static EnumFoodNord byMetadata(int meta) {
        return meta < EnumFoodNord.values().length ? EnumFoodNord.values()[meta] : EnumFoodNord.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumFoodNord.values().length];
        for (int i = 0; i < EnumFoodNord.values().length; i++) {
            array[i] = EnumFoodNord.values()[i].getName();
        }
        return array;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }
    public int getSaturation() {
        return this.saturation;
    }
    public int getHeal() {
        return this.heal;
    }

}
