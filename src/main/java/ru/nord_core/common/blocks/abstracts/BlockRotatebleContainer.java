package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachine;

public abstract class BlockRotatebleContainer extends BlockAbstractContainer {

    public static final PropertyDirection FACING = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    protected BlockRotatebleContainer(Material mat, String modid) {
        super(mat, modid);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote) {
            Block north = worldIn.getBlockState(pos.north()).getBlock();
            Block south = worldIn.getBlockState(pos.south()).getBlock();
            Block west = worldIn.getBlockState(pos.west()).getBlock();
            Block east = worldIn.getBlockState(pos.east()).getBlock();
            EnumFacing enumfacing = state.getValue(FACING);
            if (enumfacing == EnumFacing.NORTH && north.isFullBlock(state) && !south.isFullBlock(state)) {
                enumfacing = EnumFacing.SOUTH;
            } else if (enumfacing == EnumFacing.SOUTH && south.isFullBlock(state) && !north.isFullBlock(state)) {
                enumfacing = EnumFacing.NORTH;
            } else if (enumfacing == EnumFacing.WEST && west.isFullBlock(state) && !east.isFullBlock(state)) {
                enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && east.isFullBlock(state) && !west.isFullBlock(state)) {
                enumfacing = EnumFacing.WEST;
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
        if (stack.hasDisplayName()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            if (tileentity instanceof TileAbstractEnergyMachine) {
                ((TileAbstractEnergyMachine) tileentity).setCustomInventoryName(stack.getDisplayName());
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.getFront(meta);
        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }


    @SideOnly(Side.CLIENT)
    static final class SwitchEnumFacing {
        static final int[] FACING_LOOKUP = new int[EnumFacing.values().length];
        static {
            try {
                FACING_LOOKUP[EnumFacing.WEST.ordinal()] = 1;
                FACING_LOOKUP[EnumFacing.EAST.ordinal()] = 2;
                FACING_LOOKUP[EnumFacing.NORTH.ordinal()] = 3;
                FACING_LOOKUP[EnumFacing.SOUTH.ordinal()] = 4;
            } catch (NoSuchFieldError ignored) {
                ;
            }
        }
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }
}
