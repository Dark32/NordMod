package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.ExtractorRecipes1I2O;
import ru.nord_core.common.utils.enums.EnumNugget;
import ru.nord_core.common.utils.enums.EnumOreDrop;

public class ExtractorRecipes {
    public static void postInit() {
        ExtractorRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gravel), 1),
                new ItemStack(Items.flint, 2),
                new ItemStack(Item.getItemFromBlock(Blocks.sand))
                , 400, 99, 0);
        ExtractorRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.redstone_ore), 1),
                new ItemStack(NordItems.itemOreNugget, 5, EnumNugget.MERCURY.getMetadata()),
                new ItemStack(Items.redstone,2)
                , 800, 99, 0);
        /*
        из пыли руды в пыль маталла
         */
        for (EnumOreDrop ore : EnumOreDrop.values()) {
            ExtractorRecipes1I2O.addRecipe(new ItemStack(NordItems.itemOreClearPowder, 2,ore.getMetadata()),
                    new ItemStack(NordItems.itemMetalDust, 3,ore.getDust().getMetadata()),
                    null, 200, 0, 0);
        }
    }
}
