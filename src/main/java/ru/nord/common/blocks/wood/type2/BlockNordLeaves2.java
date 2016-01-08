package ru.nord.common.blocks.wood.type2;


import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordBloks;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLeaves;

import java.util.List;
import java.util.Random;

public class BlockNordLeaves2 extends BlockAbstractLeaves {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank2.class);

    public BlockNordLeaves2() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank2.MULBERRY)
                        .withProperty(CHECK_DECAY, true)
                        .withProperty(DECAYABLE, true));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank2.byMetadata(meta);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumNordPlank2.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, EnumNordPlank2.values()[i].getMetadata()));
        }
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(NordBloks.nordSapling2);
    }
}
