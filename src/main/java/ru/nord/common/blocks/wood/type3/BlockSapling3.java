package ru.nord.common.blocks.wood.type3;

import net.minecraft.block.*;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordTabs;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.blocks.abstracts.BlockAbstractSapling;

import java.util.List;
import java.util.Random;

public class BlockSapling3 extends BlockAbstractSapling {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumNordPlank3.class);

    public BlockSapling3()
    {
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNordPlank3.COFFEA).withProperty(STAGE, Integer.valueOf(0)));
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(NordTabs.tabWood);
        this.setStepSound(soundTypeGrass);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        EnumNordPlank3[] aenumtype = EnumNordPlank3.values();
        int i = aenumtype.length;

        for (int j = 0; j < i; ++j)
        {
            EnumNordPlank3 enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }
    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank3.byMetadata(meta);
    }
}
