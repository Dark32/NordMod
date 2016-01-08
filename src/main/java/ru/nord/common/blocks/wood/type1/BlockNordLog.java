package ru.nord.common.blocks.wood.type1;

import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

public class BlockNordLog extends BlockAbstractLog {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank.class);

    public BlockNordLog() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(getVariant(), EnumNordPlank.SAKURA)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }


    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank.byMetadata(meta);
    }

}
