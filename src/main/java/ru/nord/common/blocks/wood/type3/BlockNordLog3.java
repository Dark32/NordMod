package ru.nord.common.blocks.wood.type3;


import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

import java.util.List;

public class BlockNordLog3 extends BlockAbstractLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank3.class);

    public BlockNordLog3() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank3.COFFEA)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumNordPlank3.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, EnumNordPlank3.values()[i].getMetadata()));
        }
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank3.byMetadata(meta);
    }
}
