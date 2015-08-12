package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord_core.common.utils.enums.EnumMetal;

/**
 * Created by andrew on 03.08.15.
 */
public class FlowingRecipes {
    public static void postInit() {
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gold_ore), 1),
                new ItemStack(NordItems.itemOreDrop, 2, 1),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                , 80, 90, 0);
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.iron_ore), 1),
                new ItemStack(NordItems.itemOreDrop, 2, 0),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                , 80, 90, 0);
/*
        for (EnumOre ore : EnumOre.values()) {

            FlowingRecipes1I2O.addRecipe(
                    new ItemStack(NordBloks.metalOre,1,ore.getMetadata()),
                    new ItemStack(NordItems.itemOreDrop,2,ore.getDust().getMetadata()),
                    new ItemStack(Item.getItemFromBlock(Blocks.cobblestone),1)
                    ,80,90,0);
        }
        */
        for (EnumMetal metal : EnumMetal.values()) {
            FlowingRecipes1I2O.addRecipe(
                    new ItemStack(NordItems.itemIngot, 1, metal.getMetadata()),
                    new ItemStack(NordItems.itemOreDrop, 1, metal.getDust().getMetadata()),
                    new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                    , 80, 0, 0);
        }
    }
}
