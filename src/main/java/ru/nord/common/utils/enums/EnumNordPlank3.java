package ru.nord.common.utils.enums;

import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord_core.common.utils.enums.interfaces.IBiomeColoredEnum;
import ru.nord_core.common.utils.enums.interfaces.IDropItemEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;
import ru.nord_core.common.utils.enums.interfaces.IWorldGeneratorEnum;

public enum EnumNordPlank3 implements IMetadataEnum, IBiomeColoredEnum, IDropItemEnum,IWorldGeneratorEnum {
    MALUS(0, "malus") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    LEMON(1, "lemon") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    COFFEA(2, "coffea") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    BEECH(3, "beech") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    ;
    private final String name;
    private final int meta;
    private final boolean colorize;

    EnumNordPlank3(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
        this.colorize = false;
    }

    public static EnumNordPlank3 byMetadata(int meta) {
        return meta < EnumNordPlank3.values().length ? EnumNordPlank3.values()[meta] : EnumNordPlank3.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumNordPlank3.values().length];
        for (int i = 0; i < EnumNordPlank3.values().length; i++) {
            array[i] = EnumNordPlank3.values()[i].getName();
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
    public boolean getColorize(){return this.colorize;}
    public abstract WorldGenAbstractTree generate();

    public abstract ItemStack dropItem();
}
