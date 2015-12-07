package ru.nord.common.utils.enums;

import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumNordPlank implements IMetadataEnum {
/*    SOME_PLANK(0, "some_plank") {
        @Override
        public WorldGenAbstractTree generate() {
            return new WorldGenSomeTree(true);
        }

        @Override
        public ItemStack dropFruit() {
            return new ItemStack(Items.apple);
        }
    },
*/

    SAKURA(0, "sakura") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    PUNICA(1, "punica") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    PEAR(2, "pear") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    PEACH(3, "peach") {
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
    private final boolean colorize;

    EnumNordPlank(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
        this.colorize = false;
    }

    public static EnumNordPlank byMetadata(int meta) {
        return meta < EnumNordPlank.values().length ? EnumNordPlank.values()[meta] : EnumNordPlank.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumNordPlank.values().length];
        for (int i = 0; i < EnumNordPlank.values().length; i++) {
            array[i] = EnumNordPlank.values()[i].getName();
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

    public abstract ItemStack dropFruit();
}
