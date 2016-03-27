package ru.nord_core.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;

public abstract class BlockMetadata extends Block  implements IVariantMetadata {
    private String unlocalizedName;
    protected String modid;

    public BlockMetadata(Material mat, String[] names, String modid) {
        super(mat);
        this.modid=modid;

    }
    public BlockMetadata(Material mat, String modid) {
        super(mat);
        this.modid=modid;

    }
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < getVariant().getAllowedValues().toArray().length; ++i) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

//    @Override
//    public abstract IBlockState getStateFromMeta(int meta);

    public abstract PropertyEnum getVariant();

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
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getVariant());
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((IMetadataEnum)state.getValue(getVariant())).getMetadata();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((IMetadataEnum)state.getValue(getVariant())).getMetadata();
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(getVariant(), getEnumByMetadata(meta));
    }

}
