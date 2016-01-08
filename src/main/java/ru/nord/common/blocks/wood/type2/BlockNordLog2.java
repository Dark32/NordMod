package ru.nord.common.blocks.wood.type2;

import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

public class BlockNordLog2 extends BlockAbstractLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank2.class);

    public BlockNordLog2() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank2.MULBERRY)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank2.byMetadata(meta);
    }

}
