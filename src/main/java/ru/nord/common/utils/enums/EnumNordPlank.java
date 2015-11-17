package ru.nord.common.utils.enums;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord.common.world.generator.WorldGenSomeTree;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumNordPlank implements IMetadataEnum {
//    SOME_PLANK(0, "some_plank") {
//        @Override
//        public WorldGenAbstractTree generate() {
//            return new WorldGenSomeTree(true);
//        }
//
//        @Override
//        public ItemStack dropFruit() {
//            return new ItemStack(Items.apple);
//        }
//    },

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
    PALM_TREES(4, "palm_trees") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    ORANGE(5, "orange") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    OLIVE(6, "olive") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    MULBERRY(7, "mulberry") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    MALUS(8, "malus") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    LEMON(9, "lemon") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    COFFEA(10, "coffea") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    BEECH(11, "beech") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    BAOBAB(12, "baobab") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    ASH(13, "ash") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    APRICOT(14, "apricot ") {
        @Override
        public WorldGenAbstractTree generate() {
            return null;
        }

        @Override
        public ItemStack dropFruit() {
            return null;
        }
    },
    CAMPHOR(15, "camphor") {
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

    EnumNordPlank(int _meta, String _name) {
        this.name = _name;
        this.meta = _meta;
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

    public abstract WorldGenAbstractTree generate();

    public abstract ItemStack dropFruit();
}
