package ru.nord.common.blocks.wood.type4;


import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

public class BlockNordLog4 extends BlockAbstractLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank4.class);

    public BlockNordLog4() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank4.APRICOT)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank4.byMetadata(meta);
    }

}
