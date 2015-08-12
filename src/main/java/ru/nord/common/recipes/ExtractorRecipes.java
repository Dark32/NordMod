package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.ExtractorRecipes1I2O;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord_core.common.utils.enums.EnumMetal;

/**
 * Created by andrew on 03.08.15.
 */
public class ExtractorRecipes {
    public static void postInit() {
        ExtractorRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gold_ore), 1),
                new ItemStack(NordItems.itemOreDrop, 2, 1),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                , 80, 90, 0);
    }
}
