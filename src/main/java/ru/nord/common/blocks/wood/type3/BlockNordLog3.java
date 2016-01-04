package ru.nord.common.blocks.wood.type3;

import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumNordPlank3;

import java.util.List;

public class BlockNordLog3 extends BlockLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant",EnumNordPlank3.class);

    public BlockNordLog3()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,  EnumNordPlank3.COFFEA).withProperty(LOG_AXIS, net.minecraft.block.BlockLog.EnumAxis.Y));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i=0;i<EnumNordPlank3.values().length;i++){
                list.add(new ItemStack(itemIn, 1, EnumNordPlank3.values()[i].getMetadata()));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumNordPlank3.byMetadata((meta & 3)));

        switch (meta & 12)
        {
            case 0:
                iblockstate = iblockstate.withProperty(LOG_AXIS, net.minecraft.block.BlockLog.EnumAxis.Y);
                break;
            case 4:
                iblockstate = iblockstate.withProperty(LOG_AXIS, net.minecraft.block.BlockLog.EnumAxis.X);
                break;
            case 8:
                iblockstate = iblockstate.withProperty(LOG_AXIS, net.minecraft.block.BlockLog.EnumAxis.Z);
                break;
            default:
                iblockstate = iblockstate.withProperty(LOG_AXIS, net.minecraft.block.BlockLog.EnumAxis.NONE);
        }

        return iblockstate;
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state)
    {
        byte b0 = 0;
        int i = b0 | ((EnumNordPlank3)state.getValue(VARIANT)).getMetadata();

        switch (SwitchEnumAxis.AXIS_LOOKUP[((net.minecraft.block.BlockLog.EnumAxis)state.getValue(LOG_AXIS)).ordinal()])
        {
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
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {VARIANT, LOG_AXIS});
    }
    @Override
    protected ItemStack createStackedBlock(IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumNordPlank3)state.getValue(VARIANT)).getMetadata());
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumNordPlank3)state.getValue(VARIANT)).getMetadata();
    }

    static final class SwitchEnumAxis
    {
        static final int[] AXIS_LOOKUP = new int[EnumAxis.values().length];

        static
        {
            AXIS_LOOKUP[EnumAxis.X.ordinal()]       = 1;
            AXIS_LOOKUP[EnumAxis.Z.ordinal()]       = 2;
            AXIS_LOOKUP[EnumAxis.NONE.ordinal()]    = 3;

        }
    }
}
