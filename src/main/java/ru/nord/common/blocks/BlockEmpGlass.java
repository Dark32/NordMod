package ru.nord.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordBloks;
import ru.nord.NordItems;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumEmpGlass;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public class BlockEmpGlass extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumEmpGlass.class);
    public BlockEmpGlass(String[] names) {
        super(Material.iron, names, EnumEmpGlass.class, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightOpacity(0);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumEmpGlass.byMetadata(meta));
    }
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{TYPE});
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }
    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.TRANSLUCENT;
    }
    @Override
    public boolean isFullCube()
    {
        return false;
    }
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }


    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        IBlockState iblockstate = worldIn.getBlockState(pos);
        Block block = iblockstate.getBlock();

        if (this == NordBloks.empireGlass)
        {
            if (worldIn.getBlockState(pos.offset(side.getOpposite())) != iblockstate)
            {
                return true;
            }

            if (block == this)
            {
                return false;
            }
        }

        return super.shouldSideBeRendered(worldIn, pos, side);
    }
}
