package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordTabs;
import ru.nord.common.utils.enums.EnumGlowstoneDust;

import java.util.List;

public class ItemGlowstoneDust extends ItemBase {
    public ItemGlowstoneDust(String modid) {
        super(modid);
        this.setHasSubtypes(true);

        this.setCreativeTab(NordTabs.tabOthers);
    }


    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumGlowstoneDust.values().length) {
            return super.getUnlocalizedName() + "." + EnumGlowstoneDust.values()[stack.getMetadata()].getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {

        for (int i = 0; i < EnumGlowstoneDust.values().length; ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

}
