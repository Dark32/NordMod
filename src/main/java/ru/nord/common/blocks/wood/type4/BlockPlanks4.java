package ru.nord.common.blocks.wood.type4;


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
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;

public class BlockPlanks4 extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("variant", EnumNordPlank4.class);

    public BlockPlanks4(String[] names)
    {
        super(Material.wood,names, Version.MODID);
        this.setCreativeTab(NordTabs.tabWood);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(TYPE, EnumNordPlank4.byMetadata(meta));
    }

    @Override
    public PropertyEnum getTypes() {
        return TYPE;
    }

}
