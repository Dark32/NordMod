package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.biome.BiomeColorHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.lib.utils.Version;
import ru.nord.common.lib.utils.enums.EnumStone;

import java.util.List;

public class BlockDecoStone extends Block {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumStone.class);
    private final int color;
    private String unlocalizedName;

    public BlockDecoStone(int color) {
        super(Material.rock);
        this.color = color;
    }
    /**
     * Convert the given metadata into a BlockState for this Block
     */


    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumStone.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {

        return ((EnumStone)state.getValue(TYPE)).getMetadata();
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
    }
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        for (int i = 0; i < 16; ++i)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
    /**
     * Get the damage value that this Block should drop
     */
    public int damageDropped(IBlockState state)
    {
        return ((EnumStone)state.getValue(TYPE)).getMetadata();
    }

    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
        return this.color;
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return this.color;
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return getRenderColor(worldIn.getBlockState(pos));
    }
    @Override
    public String getUnlocalizedName() {
        return "tile." + Version.MODID + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
}
