package ru.nord_core.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumFoodNord;

import java.util.List;

public class ItemFoodNord extends ItemFood {
    public ItemFoodNord(int amount, float saturation, boolean isWolfFood) {
        super(amount, saturation, isWolfFood);
        this.setHasSubtypes(true);
        setCreativeTab(CreativeTabs.tabFood);
    }

        public int getHealAmount(ItemStack stack)
    {
        return EnumFoodNord.values()[stack.getMetadata()].getHeal();
    }

    public float getSaturationModifier(ItemStack stack)
    {
        return EnumFoodNord.values()[stack.getMetadata()].getSaturation();
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumFoodNord.values().length) {
            return super.getUnlocalizedName() + "." + EnumFoodNord.values()[stack.getMetadata()].getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item itemIn, CreativeTabs tab, List subItems) {

        for (int i = 0; i < EnumFoodNord.values().length; ++i) {
            subItems.add(new ItemStack(itemIn, 1, i));
        }
    }

}
