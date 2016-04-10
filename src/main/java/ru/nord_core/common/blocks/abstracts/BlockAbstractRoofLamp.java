package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nord_core.common.blocks.BlockMetadata;
import ru.nord_core.common.utils.enums.AxisAlignedBBEnum;

import java.util.List;

public abstract class BlockAbstractRoofLamp extends BlockMetadata {

    public BlockAbstractRoofLamp(String modid) {
        super(Material.circuits, modid);
        setLightLevel(0.8375F);
        setStepSound(SoundType.WOOD);
        setHardness(0.0F);
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }


    private boolean canPlaceOn(World worldIn, BlockPos pos) {
        if (worldIn.isSideSolid(pos, EnumFacing.UP) || worldIn.getBlockState(pos).getBlock() == this) {
            return true;
        } else {
            Block block = worldIn.getBlockState(pos).getBlock();
            return block.canPlaceTorchOnTop(worldIn.getBlockState(pos), worldIn, pos);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return this.canPlaceOn(worldIn, pos.up()) || this.canPlaceOn(worldIn, pos.down());
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return AxisAlignedBBEnum.AbstractRoofLamp.getBound();
    }

    @Override
    public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB aabb,
                                      List<AxisAlignedBB> list, Entity entity) {
        addCollisionBoxToList(pos, aabb, list, AxisAlignedBBEnum.AbstractRoofLamp.getBound());
    }
}
