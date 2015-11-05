package ru.nord_deco.common.blocks;


import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_deco.common.utils.enums.EnumChairOther;

public class BlockChairOther extends BlockChair {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumChairOther.class);

    public BlockChairOther(String modid, String[] names) {
        super(modid, names);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int face = ((EnumFacing) state.getValue(FACING)).getIndex();
        int type = ((EnumChairOther) state.getValue(TYPE)).getMetadata();
        return (face << 2) + (type & 15);
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING, TYPE});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int face = meta >> 2;
        int type = meta & 3;
        EnumFacing enumfacing = EnumFacing.getFront(face);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState()
                .withProperty(FACING, enumfacing)
                .withProperty(TYPE, EnumChairOther.values()[type]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.SOUTH)
                .withProperty(TYPE, ((EnumChairOther) state.getValue(TYPE)).getMetadata());
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing face = placer.getHorizontalFacing().getOpposite();
        EnumChairOther type = EnumChairOther.values()[meta & 3];
        return this.getDefaultState()
                .withProperty(FACING, face)
                .withProperty(TYPE,type);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        IBlockState state = world.getBlockState(pos);
        int type = ((EnumChairOther) state.getValue(TYPE)).getMetadata();
        return new ItemStack(this,1,type & 3);
    }

}