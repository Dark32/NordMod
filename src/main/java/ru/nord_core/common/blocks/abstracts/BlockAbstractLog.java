package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.BlockLog;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.Iterator;
import java.util.List;

public abstract class BlockAbstractLog extends BlockLog implements IVariantMetadata{
    @Override
    public abstract PropertyEnum getVariant();
    @Override
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
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getVariant(), LOG_AXIS);
    }

    @Override
    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((IMetadataEnum) state.getValue(getVariant())).getMetadata());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        Iterator iterator = getVariant().getAllowedValues().iterator();
        while (iterator.hasNext())
        {
            IMetadataEnum oenum = (IMetadataEnum)iterator.next();
            list.add(new ItemStack(itemIn, 1, oenum.getMetadata()));
        }
    }

}
