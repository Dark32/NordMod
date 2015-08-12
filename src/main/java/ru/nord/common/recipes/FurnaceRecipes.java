package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.FurnaceRecipes1I2O;

import java.util.Iterator;
import java.util.Map;

public class FurnaceRecipes {
    public static void postInit() {
        addVanilaRecipes();
        FurnaceRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gold_block), 1),
                new ItemStack(NordItems.itemOreDrop, 2, 1),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                , 80, 90, 0);
    }

    private static void addVanilaRecipes(){
        Map recipes = net.minecraft.item.crafting.FurnaceRecipes.instance().getSmeltingList();
        Iterator iterator = recipes.entrySet().iterator();
        Map.Entry entry;

        while (iterator.hasNext())
        {
            entry = (Map.Entry)iterator.next();
            ItemStack input = (ItemStack)entry.getKey();
            ItemStack output = (ItemStack)entry.getValue();
            FurnaceRecipes1I2O.addRecipe(input,output,output,200,0,0);
        }

    }
}
