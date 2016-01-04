package ru.nord_core.common.blocks.abstracts;

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
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;

public abstract class BlockMetadata extends Block {
    private final String[] names;
    private String unlocalizedName;
    protected String modid;

    public BlockMetadata(Material mat, String[] names, String modid) {
        super(mat);
        this.names = names;
        this.modid=modid;

    }

    @Override
    @SideOnly(Side.CLIENT)
    final public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < names.length; ++i) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public abstract IBlockState getStateFromMeta(int meta);

    public abstract PropertyEnum getTypes();

    @Override
    public String getUnlocalizedName() {
        return "tile." + modid + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, getTypes());
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((IMetadataEnum)state.getValue(getTypes())).getMetadata();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((IMetadataEnum)state.getValue(getTypes())).getMetadata();
    }

}
