package ru.nord.common.blocks.wood.type1;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordTabs;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord_core.common.blocks.abstracts.BlockAbstractSapling;

import java.util.List;

public class BlockSapling extends BlockAbstractSapling {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumNordPlank.class);

    public BlockSapling() {
        this.setDefaultState(this.blockState.getBaseState().withProperty(TYPE, EnumNordPlank.SAKURA).withProperty(STAGE, Integer.valueOf(0)));
        float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        this.setCreativeTab(NordTabs.tabWood);
        this.setStepSound(soundTypeGrass);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        EnumNordPlank[] aenumtype = EnumNordPlank.values();
        int i = aenumtype.length;

        for (int j = 0; j < i; ++j) {
            EnumNordPlank enumtype = aenumtype[j];
            list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
        }
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank.byMetadata(meta);
    }
}
