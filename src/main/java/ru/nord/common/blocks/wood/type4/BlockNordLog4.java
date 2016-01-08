package ru.nord.common.blocks.wood.type4;


import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

import java.util.List;

public class BlockNordLog4 extends BlockAbstractLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank4.class);

    public BlockNordLog4() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank4.APRICOT)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumNordPlank4.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, EnumNordPlank4.values()[i].getMetadata()));
        }
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank4.byMetadata(meta);
    }

}
