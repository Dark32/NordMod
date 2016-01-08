package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public abstract class BlockAbstractLog extends BlockLog {

    public abstract PropertyEnum getVariant();

    public abstract Comparable getEnumByMetadata(int meta);

    public static final class SwitchEnumAxis {
        public static final int[] AXIS_LOOKUP = new int[EnumAxis.values().length];

        static {
            AXIS_LOOKUP[EnumAxis.X.ordinal()] = 1;
            AXIS_LOOKUP[EnumAxis.Z.ordinal()] = 2;
            AXIS_LOOKUP[EnumAxis.NONE.ordinal()] = 3;

        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(getVariant(), getEnumByMetadata(meta & 3));
        switch (meta & 12) {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, EnumAxis.NONE);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((IMetadataEnum) state.getValue(getVariant())).getMetadata();

        switch (SwitchEnumAxis.AXIS_LOOKUP[((net.minecraft.block.BlockLog.EnumAxis) state.getValue(LOG_AXIS)).ordinal()]) {
            case 1:
                i |= 4;
                break;
            case 2:
                i |= 8;
                break;
            case 3:
                i |= 12;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, getVariant(), LOG_AXIS);
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((IMetadataEnum) state.getValue(getVariant())).getMetadata());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
    }


}
