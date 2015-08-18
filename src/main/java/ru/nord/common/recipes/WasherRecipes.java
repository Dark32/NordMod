package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nord.common.lib.recipes.WasherRecipes2I2O;

/**
 * Created by andrew on 03.08.15.
 */
public class WasherRecipes {
    public static void postInit(){
        WasherRecipes2I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.dirt), 1),
                new ItemStack(Items.diamond, 5),
                400, 30, 5, true);
    }
}
