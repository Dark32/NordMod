package ru.nord_core.common.items;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.utils.enums.interfaces.IMetadata2Enum;

import java.util.EnumSet;
import java.util.List;

public class ItemMetaData2<TYPE extends Enum<TYPE> & IMetadata2Enum> extends Item {
    private ImmutableList<TYPE> types;

    public ItemMetaData2(Class<TYPE> elemType, Predicate<TYPE> predicate) {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        EnumSet<TYPE> rawCollection = EnumSet.allOf(elemType);
        this.types = ImmutableList.copyOf(Collections2.filter(rawCollection, predicate));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < types.size()) {
            return super.getUnlocalizedName() + "." + types.get(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List<ItemStack> subItems) {
        for (int i = 0; i < types.size(); ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
