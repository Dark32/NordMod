package ru.nord.common.blocks.wood;

import com.google.common.base.Predicate;
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
import ru.nord.common.utils.enums.EnumNordPlank;

import java.util.List;

public class BlockNordLog extends BlockLog {

    private static int shift = 0;
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant",EnumNordPlank.class, new Predicate()
    {
        public boolean apply(EnumNordPlank type)
        {
            int meta =  type.getMetadata();
            return meta>=shift*4 && meta< (shift+1)*4;
        }
        public boolean apply(Object object)
        {
            return this.apply((EnumNordPlank)object);
        }
    });
    private static final String __OBFID = "CL_00000281";

    public BlockNordLog()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(VARIANT,  EnumNordPlank.SAKURA).withProperty(LOG_AXIS, net.minecraft.block.BlockLog.EnumAxis.Y));
    }

    /**
     * returns a list of blocks with the same ID, but different meta (eg: wood returns 4 blocks)
     */
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i=0;i<4;i++){
            int c = i+shift*4;
            if (EnumNordPlank.values().length >= c)
                list.add(new ItemStack(itemIn, 1, EnumNordPlank.values()[c].getMetadata()-shift*4));
        }
    }

    /**
     * Convert the given metadata into a BlockState for this Block
     */
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumNordPlank.byMetadata((meta & 3) + shift*4));

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
        int i = b0 | ((EnumNordPlank)state.getValue(VARIANT)).getMetadata()-(shift*4);

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
        return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumNordPlank)state.getValue(VARIANT)).getMetadata()-shift*4);
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumNordPlank)state.getValue(VARIANT)).getMetadata()-shift*4;
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
