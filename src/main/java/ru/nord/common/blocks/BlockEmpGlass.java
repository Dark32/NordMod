package ru.nord.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordBloks;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumEmpGlass;
import ru.nord_core.common.blocks.BlockMetadata;

public class BlockEmpGlass extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumEmpGlass.class);

    public BlockEmpGlass(String[] names) {
        super(Material.iron, names, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightOpacity(0);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumEmpGlass.byMetadata(meta);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();
        if (this == NordBloks.empireGlass) {
            if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate) {
                return true;
            }
            if (block == this) {
                return false;
            }
        }
        return super.shouldSideBeRendered(blockState,worldIn, pos, side);
    }
}
