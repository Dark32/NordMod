package ru.nord.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordItems;
import ru.nord.NordTabs;
import ru.nord.common.lib.helpers.RegisterHelper;

import java.util.List;

/**
 * Created by nikit_000 on 06.07.2015.
 */
public class ItemDust extends Item {
    public static String[] namedust;
    public static void createArray(){
        namedust = new String[2+ItemIngot.nameIngot.length];
        namedust[0]="iron";
        namedust[1]="gold";
        for(int i=0;i<ItemIngot.nameIngot.length;i++){
            namedust[i+2]=ItemIngot.nameIngot[i];
        }
    }
    public ItemDust()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(NordTabs.tabMetallurgy);
    }
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + namedust[stack.getMetadata()];
    }
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems)
    {
        for (int i = 0; i < namedust.length; ++i)
        {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

}
