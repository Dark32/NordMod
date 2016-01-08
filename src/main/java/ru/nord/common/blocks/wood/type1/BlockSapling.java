package ru.nord.common.blocks.wood.type1;

import net.minecraft.block.properties.PropertyEnum;
import ru.nord.NordTabs;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord_core.common.blocks.abstracts.BlockAbstractSapling;

public class BlockSapling extends BlockAbstractSapling {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumNordPlank.class);

    public BlockSapling() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNordPlank.SAKURA).withProperty(STAGE, Integer.valueOf(0)));
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(NordTabs.tabWood);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank.byMetadata(meta);
    }
}
