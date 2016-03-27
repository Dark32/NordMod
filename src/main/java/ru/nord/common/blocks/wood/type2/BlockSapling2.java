package ru.nord.common.blocks.wood.type2;

import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.NordTabs;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.blocks.abstracts.BlockAbstractSapling;

public class BlockSapling2 extends BlockAbstractSapling {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumNordPlank2.class);

    public BlockSapling2() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNordPlank2.MULBERRY).withProperty(STAGE, Integer.valueOf(0)));
        float f = 0.4F;
//        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(NordTabs.tabWood);
        this.setStepSound(SoundType.GROUND);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank2.byMetadata(meta);
    }
}
