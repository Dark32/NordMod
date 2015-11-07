package ru.nord.common.utils.enums;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import ru.nord.common.world.generator.WorldGenSomeTree;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumNordPlank implements IMetadataEnum {
    // есть ограничение на количество
    SOME_PLANK(0,"some_plank") {
        @Override
        public WorldGenAbstractTree generate() {
            return new WorldGenSomeTree(true);
        }

        @Override
        public ItemStack dropFruit() {
            return new ItemStack(Items.apple);
        }
    },
    ;
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
