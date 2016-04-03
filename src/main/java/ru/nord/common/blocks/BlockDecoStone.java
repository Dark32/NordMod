package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.client.utils.IColorizeBlock;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.EnumStone;

import java.util.List;

public class BlockDecoStone extends Block implements IVariantMetadata,IColorizeBlock {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumStone.class);
    private final int color;
    private String unlocalizedName;

    public BlockDecoStone(int color) {
        super(Material.rock);
        this.color = color;
    }
    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumStone.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {

        return ((EnumStone)state.getValue(TYPE)).getMetadata();
    }
    @Override
    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {TYPE});
    }
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumStone)state.getValue(TYPE)).getMetadata();
    }

//    @SideOnly(Side.CLIENT)
//    public int getBlockColor()
//    {
//        return this.color;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int getRenderColor(IBlockState state)
//    {
//        return this.color;
//    }
//
//    @SideOnly(Side.CLIENT)
//    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
//    {
//        return getRenderColor(worldIn.getBlockState(pos));
//    }
    @Override
    public String getUnlocalizedName() {
        return "tile." + Version.MODID + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumStone.byMetadata(meta);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex) {
        return this.color;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorForStack(ItemStack stack, Block block)  {
        return this.color;
    }
}
