package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord_core.common.blocks.BlockMetadata;

import java.util.List;

public class BlockEmpPaperPanel extends BlockMetadata {
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumPaperEmp.class);
    protected static final AxisAlignedBB[] aabbs = new AxisAlignedBB[]{
            new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 0.5625D, 1.0D, 1.0D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 0.5625D, 1.0D, 1.0D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.0D, 0.0D, 0.4375D, 1.0D, 1.0D, 1.0D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.4375D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 0.5625D),
            new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 1.0D, 1.0D)};


    public BlockEmpPaperPanel(String[] names) {
        super(Material.iron, names, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumPaperEmp.byMetadata(meta);
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, NORTH, EAST, WEST, SOUTH, getVariant());
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        boolean N = canPaneConnectTo(worldIn, pos, EnumFacing.NORTH);
        boolean S = canPaneConnectTo(worldIn, pos, EnumFacing.SOUTH);
        boolean W = canPaneConnectTo(worldIn, pos, EnumFacing.WEST);
        boolean E = canPaneConnectTo(worldIn, pos, EnumFacing.EAST);
        return state
                .withProperty(NORTH, N)
                .withProperty(SOUTH, S)
                .withProperty(WEST, W)
                .withProperty(EAST, E)
                ;

    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB p_185477_4_, List<AxisAlignedBB> p_185477_5_, Entity p_185477_6_) {
        state = this.getActualState(state, worldIn, pos);
        addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, aabbs[0]);

        if ((Boolean) state.getValue(NORTH)) {
            addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, aabbs[getBoundingBoxIndex(EnumFacing.NORTH)]);
        }

        if ((Boolean) state.getValue(SOUTH)) {
            addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, aabbs[getBoundingBoxIndex(EnumFacing.SOUTH)]);
        }

        if ((Boolean) state.getValue(EAST)) {
            addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, aabbs[getBoundingBoxIndex(EnumFacing.EAST)]);
        }

        if ((Boolean) state.getValue(WEST)) {
            addCollisionBoxToList(pos, p_185477_4_, p_185477_5_, aabbs[getBoundingBoxIndex(EnumFacing.WEST)]);
        }
    }


    public final boolean canPaneConnectToBlock(Block blockIn) {
        return blockIn.getDefaultState().isFullCube() || blockIn == this || blockIn == Blocks.glass || blockIn == Blocks.stained_glass || blockIn == Blocks.stained_glass_pane || blockIn instanceof BlockEmpPaperPanel;
    }

    public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir) {
        BlockPos off = pos.offset(dir);
        IBlockState state = world.getBlockState(off);
        return canPaneConnectToBlock(state.getBlock()) || state.isSideSolid(world, off, dir.getOpposite());
    }

    private static int getBoundingBoxIndex(IBlockState p_185728_0_) {
        int i = 0;

        if ((Boolean) p_185728_0_.getValue(NORTH)) {
            i |= getBoundingBoxIndex(EnumFacing.NORTH);
        }

        if ((Boolean) p_185728_0_.getValue(EAST)) {
            i |= getBoundingBoxIndex(EnumFacing.EAST);
        }

        if ((Boolean) p_185728_0_.getValue(SOUTH)) {
            i |= getBoundingBoxIndex(EnumFacing.SOUTH);
        }

        if ((Boolean) p_185728_0_.getValue(WEST)) {
            i |= getBoundingBoxIndex(EnumFacing.WEST);
        }

        return i;
    }

    private static int getBoundingBoxIndex(EnumFacing face) {
        return 1 << face.getHorizontalIndex();
    }

    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        state = this.getActualState(state, source, pos);
        return aabbs[getBoundingBoxIndex(state)];
    }
}
