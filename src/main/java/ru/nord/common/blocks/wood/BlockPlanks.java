package ru.nord.common.blocks.wood;

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
import ru.nord.NordTabs;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;

/**
 * Created by andrew on 07.11.15.
 */
public class BlockPlanks extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("variant", EnumNordPlank.class);

    public BlockPlanks(String[] names)
    {
        super(Material.wood,names,EnumNordPlank.class, Version.MODID);
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNordPlank.SAKURA));
        this.setCreativeTab(NordTabs.tabWood);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(TYPE, EnumClearMetal.byMetadata(meta));
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
        for (int i=0;i<EnumNordPlank.values().length;i++){
                 list.add(new ItemStack(itemIn, 1, EnumNordPlank.values()[i].getMetadata()));
        }
    }
}
