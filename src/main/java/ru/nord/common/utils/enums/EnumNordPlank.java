package ru.nord.common.utils.enums;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import ru.nord.NordWoodStorage;
import ru.nord.common.utils.WeightRandom;
import ru.nord_core.common.utils.enums.interfaces.IBiomeColoredEnum;
import ru.nord_core.common.utils.enums.interfaces.IDropItemEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;
import ru.nord_core.common.utils.enums.interfaces.IWorldGeneratorEnum;
import ru.nord_core.common.world.generator.WorldGenSchematic;

public enum EnumNordPlank implements IMetadataEnum, IBiomeColoredEnum, IDropItemEnum, IWorldGeneratorEnum {
/*    SOME_PLANK(0, "some_plank") {
        @Override
        public WorldGenAbstractTree generate() {
            return new WorldGenSomeTree(true);
        }

        @Override
        public ItemStack dropItem() {
            return new ItemStack(Items.apple);
        }
    },
*/

    SAKURA(0, "sakura") {

        @Override
        public WeightRandom<String> getWeightRandom() {
            return NordWoodStorage.sakura;
        }

        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    PUNICA(1, "punica") {
        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    PEAR(2, "pear") {
        @Override
        public ItemStack dropItem() {
            return null;
        }
    },
    PEACH(3, "peach") {
        @Override
        public ItemStack dropItem() {
            return null;
        }
    },;
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

    public WeightRandom<String> getWeightRandom() {
      return null;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    public boolean getColorize() {
        return this.colorize;
    }

    @Override
    public WorldGenerator generate(World world) {
        if (this.getWeightRandom() == null) {
            return null;
        }
        this.getWeightRandom().setRandom(world.rand);
        try {
            return new WorldGenSchematic(true, this.getWeightRandom().getRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract ItemStack dropItem();
}
