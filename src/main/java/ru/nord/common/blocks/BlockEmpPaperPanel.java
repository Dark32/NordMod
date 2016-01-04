package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;

public class BlockEmpPaperPanel extends BlockMetadata {
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumPaperEmp.class);


    public BlockEmpPaperPanel(String[] names) {
        super(Material.iron, names,  Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumPaperEmp.byMetadata(meta));
    }

    @Override
    public PropertyEnum getTypes() {
        return TYPE;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{NORTH, EAST, WEST, SOUTH,TYPE});
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
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
//        return worldIn.getBlockState(pos).getBlock() != this && super.shouldSideBeRendered(worldIn, pos, side);
//    }

    @Override
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity) {
        boolean N = this.canPaneConnectTo(worldIn, pos, EnumFacing.NORTH);
        boolean S = this.canPaneConnectTo(worldIn, pos, EnumFacing.SOUTH);
        boolean W = this.canPaneConnectTo(worldIn, pos, EnumFacing.WEST);
        boolean E = this.canPaneConnectTo(worldIn, pos, EnumFacing.EAST);

        if ((!W || !E) && (W || E || N || S)) {
            if (W) {
                this.setBlockBounds(0.0F, 0.0F, 0.4375F, 0.5F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            } else if (E) {
                this.setBlockBounds(0.5F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
        } else {
            this.setBlockBounds(0.0F, 0.0F, 0.4375F, 1.0F, 1.0F, 0.5625F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }

        if ((!N || !S) && (W || E || N || S)) {
            if (N) {
                this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 0.5F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            } else if (S) {
                this.setBlockBounds(0.4375F, 0.0F, 0.5F, 0.5625F, 1.0F, 1.0F);
                super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
            }
        } else {
            this.setBlockBounds(0.4375F, 0.0F, 0.0F, 0.5625F, 1.0F, 1.0F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
    }

    /**
     * Sets the block's bounds for rendering it as an item
     */
//    @Override
//    public void setBlockBoundsForItemRender() {
//        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
//    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos) {
        float f = 0.4375F;
        float f1 = 0.5625F;
        float f2 = 0.4375F;
        float f3 = 0.5625F;
        boolean N = this.canPaneConnectToBlock(worldIn.getBlockState(pos.north()).getBlock());
        boolean S = this.canPaneConnectToBlock(worldIn.getBlockState(pos.south()).getBlock());
        boolean W = this.canPaneConnectToBlock(worldIn.getBlockState(pos.west()).getBlock());
        boolean E = this.canPaneConnectToBlock(worldIn.getBlockState(pos.east()).getBlock());

        if ((!W || !E) && (W || E || N || S)) {
            if (W) {
                f = 0.0F;
            } else if (E) {
                f1 = 1.0F;
            }
        } else {
            f = 0.0F;
            f1 = 1.0F;
        }

        if ((!N || !S) && (W || E || N || S)) {
            if (N) {
                f2 = 0.0F;
            } else if (S) {
                f3 = 1.0F;
            }
        } else {
            f2 = 0.0F;
            f3 = 1.0F;
        }

        this.setBlockBounds(f, 0.0F, f2, f1, 1.0F, f3);
    }

    public final boolean canPaneConnectToBlock(Block blockIn) {
        return blockIn.isFullBlock() || blockIn == this || blockIn == Blocks.glass || blockIn == Blocks.stained_glass || blockIn instanceof BlockEmpPaperPanel;
    }

    public boolean canPaneConnectTo(IBlockAccess world, BlockPos pos, EnumFacing dir) {
        BlockPos off = pos.offset(dir);
        Block block = world.getBlockState(off).getBlock();
        return canPaneConnectToBlock(block) || block.isSideSolid(world, off, dir.getOpposite());
    }
}
