package ru.nord.common.blocks.wood.type2;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLog;

import java.util.List;

public class BlockNordLog2 extends BlockAbstractLog {

    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank2.class);

    public BlockNordLog2() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank2.MULBERRY)
                        .withProperty(LOG_AXIS, EnumAxis.Y));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumNordPlank2.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, EnumNordPlank2.values()[i].getMetadata()));
        }
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank2.byMetadata(meta);
    }

}
