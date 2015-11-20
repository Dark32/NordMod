package ru.nord.common.utils.enums;

import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumNordPlank4 implements IMetadataEnum {
   BAOBAB(0, "baobab") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    ASH(1, "ash") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    APRICOT(2, "apricot ") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    CAMPHOR(3, "camphor") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },;
    private final String name;
    private final int meta;

    EnumNordPlank4(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
    }

    public static EnumNordPlank4 byMetadata(int meta) {
        return meta < EnumNordPlank4.values().length ? EnumNordPlank4.values()[meta] : EnumNordPlank4.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumNordPlank4.values().length];
        for (int i = 0; i < EnumNordPlank4.values().length; i++) {
            array[i] = EnumNordPlank4.values()[i].getName();
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
