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
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.utils.enums.EnumCrystal;

import java.util.List;

public class BlockCrystal extends Block {
    private final String[] names;
    private String unlocalizedName;
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumCrystal.class);
    public BlockCrystal(String[] names){
        super(Material.iron);
        this.names = names;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        for (int i = 0; i < names.length; ++i)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        return this.getDefaultState().withProperty(TYPE, EnumCrystal.byMetadata(meta));
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {

        return ((EnumCrystal)state.getValue(TYPE)).getMetadata();
    }
    @Override
    protected BlockState createBlockState()
    {
        return new BlockState(this, new IProperty[] {TYPE});
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
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((EnumCrystal)state.getValue(TYPE)).getMetadata();
    }

}
