package ru.nord.common.blocks.wood.type3;


import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

public class BlockNordLog3 extends BlockAbstractLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank3.class);

    public BlockNordLog3() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank3.COFFEA)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank3.byMetadata(meta);
    }
}
