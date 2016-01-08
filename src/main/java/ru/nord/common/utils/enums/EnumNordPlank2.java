package ru.nord.common.utils.enums;

import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord.common.world.generator.WorldGenOliveTree;
import ru.nord.common.world.generator.WorldGenOrangeTree;
import ru.nord_core.common.utils.enums.interfaces.IBiomeColoredEnum;
import ru.nord_core.common.utils.enums.interfaces.IDropItemEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;
import ru.nord_core.common.utils.enums.interfaces.IWorldGeneratorEnum;

public enum EnumNordPlank2 implements IMetadataEnum, IBiomeColoredEnum, IDropItemEnum,IWorldGeneratorEnum {
    PALM_TREES(0, "palm_trees") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    ORANGE(1, "orange") {
        @Override
        public WorldGenAbstractTree generate() {
            return new WorldGenOrangeTree(true);
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    OLIVE(2, "olive") {
        @Override
        public WorldGenAbstractTree generate() {
            return new WorldGenOliveTree(true);
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    MULBERRY(3, "mulberry") {
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

    EnumNordPlank2(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;

        this.colorize = false;
    }

    public static EnumNordPlank2 byMetadata(int meta) {
        return meta < EnumNordPlank2.values().length ? EnumNordPlank2.values()[meta] : EnumNordPlank2.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumNordPlank2.values().length];
        for (int i = 0; i < EnumNordPlank2.values().length; i++) {
            array[i] = EnumNordPlank2.values()[i].getName();
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
