package ru.nord.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordTabs;

import java.util.List;

/**
 * Created by nikit_000 on 08.07.2015.
 */
public class ItemIngot extends Item {
    public static String[] nameIngot;
    public static String[] getArray(){
        nameIngot = new String[]{"bronze","copper","electrum","silver","steel","tin","brass"};
        return nameIngot;
    }
    public ItemIngot()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(NordTabs.tabMetallurgy);
    }
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + nameIngot[stack.getMetadata()];
    }
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (int i = 0; i < nameIngot.length; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }
}
