package ru.nord.common.utils.enums;

import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumNordPlank3 implements IMetadataEnum {
    MALUS(0, "malus") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    LEMON(1, "lemon") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    COFFEA(2, "coffea") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    BEECH(3, "beech") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    ;
    private final String name;
    private final int meta;

    EnumNordPlank3(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
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

    public abstract WorldGenAbstractTree generate();

    public abstract ItemStack dropFruit();
}