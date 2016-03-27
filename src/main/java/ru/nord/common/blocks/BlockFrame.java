package ru.nord.common.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumFrame;


public class BlockFrame extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumFrame.class);

    public BlockFrame() {
        super(Material.iron, Version.MODID);
        setStepSound(SoundType.LADDER);
        setHardness(0.0F);
    }


    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumFrame.byMetadata(meta);
    }


    @Override
    public boolean isOpaqueCube( IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube( IBlockState state) {
        return false;
    }

//    @SideOnly(Side.CLIENT)
//    public AxisAlignedBB getCollisionBoundingBox(IBlockState worldIn, World pos, BlockPos state)
//    {
//        float f = 0.1f;
//        return new AxisAlignedBB(
//                (double) pos.getX() + f,
//                (double) pos.getY(),
//                (double) pos.getZ() + f,
//                (double) pos.getX() + 1 - f,
//                (double) pos.getY() + 1.0f,
//                (double) pos.getZ() + 1 - f
//        );
//    }

    @SideOnly(Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
//        this.setBlockBoundsBasedOnState(worldIn, pos);
        return super.getSelectedBoundingBox(blockState,worldIn, pos);
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean isLadder(IBlockState state, IBlockAccess world, BlockPos pos, EntityLivingBase entity) {
        return true;
    }

}
