package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumGlassFood;

import java.util.List;

public class ItemGlassFood extends ItemBase {
    public ItemGlassFood(String modid) {
        super(modid);
        this.setHasSubtypes(true);

    }


    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumGlassFood.values().length) {
            return super.getUnlocalizedName() + "." + EnumGlassFood.values()[stack.getMetadata()].getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {

        for (int i = 0; i < EnumGlassFood.values().length; ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

}
