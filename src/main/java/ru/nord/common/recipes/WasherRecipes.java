package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.common.lib.recipes.WasherRecipes1I2O;

/**
 * Created by andrew on 03.08.15.
 */
public class WasherRecipes {
    public static void postInit(){
        WasherRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.dirt), 1),
                400, 30, 5);
        WasherRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.dirt), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.sand), 2),
                new ItemStack(Item.getItemFromBlock(Blocks.dirt), 1),
                400, 30, 5);
    }
}
