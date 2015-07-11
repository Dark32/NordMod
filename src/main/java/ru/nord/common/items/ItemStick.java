package ru.nord.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordTabs;

import java.util.List;

/**
 * Created by nikit_000 on 07.07.2015.
 */
public class ItemStick extends Item {
    public static String[] nameStick = new String[]{"iron","gold"};
    public ItemStick()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(NordTabs.tabMetallurgy);
    }
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + nameStick[stack.getMetadata()];
    }
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (int i = 0; i < nameStick.length; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
