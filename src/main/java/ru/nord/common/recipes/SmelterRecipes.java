package ru.nord.common.recipes;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nord_core.common.utils.enums.EnumDust;
import ru.nord_core.common.utils.enums.EnumMetal;
import ru.nord_core.common.utils.enums.EnumOreDrop;

/**
 * Created by andrew on 03.08.15.
 */
public class SmelterRecipes {
    public static void postInit() {
        /*
        Сплавы
         */
        // Бронза
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemMetalDust, 3, EnumDust.COPPER.getMetadata()),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.TIN.getMetadata()),
                new ItemStack(NordItems.itemIngot, 4, EnumMetal.BRONZE.getMetadata()),
                null, 400, 0, 0, true);
        SmelterRecipes2I2O.addRecipe(
                new ItemStack(NordItems.itemIngot, 3, EnumMetal.COPPER.getMetadata()),
                new ItemStack(NordItems.itemIngot, 1, EnumMetal.TIN.getMetadata()),
                new ItemStack(NordItems.itemIngot, 4, EnumMetal.BRONZE.getMetadata()),
                null, 800, 0, 0, true);
        for (EnumOreDrop copper : EnumOreDrop.values()) {
            if (copper.getDust() == EnumDust.COPPER) {
                for (EnumOreDrop tin : EnumOreDrop.values()) {
                    if (tin.getDust() == EnumDust.TIN) {
                        SmelterRecipes2I2O.addRecipe(
                                new ItemStack(NordItems.itemIngot, 3, copper.getMetadata()),
                                new ItemStack(NordItems.itemIngot, 1, tin.getMetadata()),
                                new ItemStack(NordItems.itemIngot, 4, EnumMetal.BRONZE.getMetadata()),
                                null, 1200, 0, 0, true);
                    }
                }
            }
        }
        //конец бронза
    }

}
