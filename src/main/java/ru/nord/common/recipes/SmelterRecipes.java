package ru.nord.common.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.EnumDust;
import ru.nord_core.common.utils.enums.EnumMetal;
import ru.nord_core.common.utils.enums.EnumNugget;

/**
 * Created by andrew on 03.08.15.
 */
public class SmelterRecipes {
    public static void postInit() {
        /* Сплавы */
        // Бронза
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 3, EnumDust.COPPER.getMetadata()),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.TIN.getMetadata()),
                new ItemStack(NordItems.itemIngot, 4, EnumMetal.BRONZE.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemIngot, 3, EnumMetal.COPPER.getMetadata()),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.TIN.getMetadata()),
                new ItemStack(NordItems.itemIngot, 4, EnumMetal.BRONZE.getMetadata()),
                null, 800, 0, 0, true);
        //конец бронза

        // Steel
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.IRON.getMetadata()),
                new ItemStack(Items.coal, 3, 1),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.STEEL.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(Items.iron_ingot, 1),
                new ItemStack(Items.coal, 3, 1),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.STEEL.getMetadata()),
                null, 800, 0, 0, true);
        // end steel
        //invar
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(Items.iron_ingot, 2),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.NICEL.getMetadata()),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.INVAR.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 2, EnumDust.IRON.getMetadata()),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.NICEL.getMetadata()),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.INVAR.getMetadata()),
                null, 800, 0, 0, true);
        // end invar
        // DURALUMIN
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemIngot, 8, EnumMetal.ALUMINUM.getMetadata()),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.COPPER.getMetadata()),
                new ItemStack(NordItems.itemIngot, 9, EnumMetal.DURALUMIN.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 8, EnumDust.ALUMINUM.getMetadata()),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.COPPER.getMetadata()),
                new ItemStack(NordItems.itemIngot, 9, EnumMetal.DURALUMIN.getMetadata()),
                null, 800, 0, 0, true);
        // end DURALUMIN

        // NICHROME
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemIngot, 2, EnumMetal.NICEL.getMetadata()),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.CHROM.getMetadata()),
                new ItemStack(NordItems.itemIngot, 3, EnumMetal.NICHROME.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 2, EnumDust.NICEL.getMetadata()),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.CHROM.getMetadata()),
                new ItemStack(NordItems.itemIngot, 3, EnumMetal.NICHROME.getMetadata()),
                null, 800, 0, 0, true);
        // end NICHROME
        // CAST_IRON
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.IRON.getMetadata()),
                new ItemStack(Items.coal, 5, 0),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.CAST_IRON.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(Items.iron_ingot, 1),
                new ItemStack(Items.coal, 5, 0),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.CAST_IRON.getMetadata()),
                null, 800, 0, 0, true);
        //end CAST_IRON

        // чистые металлы
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(Items.iron_ingot, 1),
                new ItemStack(NordItems.itemOreNugget, 1, EnumNugget.MERCURY.getMetadata()),
                new ItemStack(NordItems.itemClearIngot, 1, EnumClearMetal.IRON.getMetadata()),
                null, 800, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(Items.gold_ingot, 1),
                new ItemStack(NordItems.itemOreNugget, 1, EnumNugget.MERCURY.getMetadata()),
                new ItemStack(NordItems.itemClearIngot, 1, EnumClearMetal.GOLD.getMetadata()),
                null, 800, 0, 0, true);
        for (EnumClearMetal ingot : EnumClearMetal.values()) {
            if (ingot.getMetal() != null) {
                SmelterRecipes2I2O.addRecipe(
                        new ItemStack(NordItems.itemMetalDust, 1, ingot.getMetal().getDust().getMetadata()),
                        new ItemStack(NordItems.itemOreNugget, 1, EnumNugget.MERCURY.getMetadata()),
                        new ItemStack(NordItems.itemClearIngot, 1, ingot.getMetadata()),
                        null, 800, 0, 0, true);
            }
        }
        // end чистые металлы
    }

}
