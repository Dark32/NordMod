package ru.nord.common.blocks.wood.type3;


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
import ru.nord.NordTabs;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;

public class BlockPlanks3 extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("variant", EnumNordPlank3.class);

    public BlockPlanks3(String[] names)
    {
        super(Material.wood,names,EnumNordPlank3.class, Version.MODID);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNordPlank3.COFFEA));
        this.setCreativeTab(NordTabs.tabWood);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(TYPE, EnumNordPlank3.byMetadata(meta));
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
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i=0;i<EnumNordPlank3.values().length;i++){
                 list.add(new ItemStack(itemIn, 1, EnumNordPlank3.values()[i].getMetadata()));
        }
    }
}
