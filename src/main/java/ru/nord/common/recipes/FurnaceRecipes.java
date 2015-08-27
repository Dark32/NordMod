package ru.nord.common.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.FurnaceRecipes1I2O;
import ru.nord_core.common.utils.enums.EnumDust;
import ru.nord_core.common.utils.enums.EnumOreDrop;

import java.util.Iterator;
import java.util.Map;

public class FurnaceRecipes {
    public static void postInit() {
        addVanilaRecipes();
        /*
        Плавим дпропы
         */
        for (EnumOreDrop ore : EnumOreDrop.values()) {
            System.err.println(ore + " " + ore.getMetadata() + " " + ore.getDust().getMetal());
            if (ore.getDust() == EnumDust.IRON) {
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreClearPowder, 1, ore.getMetadata()),
                        new ItemStack(Items.iron_ingot, 1), null, 200, 0, 0);
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreDirtyPowder, 3, ore.getMetadata()),
                        new ItemStack(Items.iron_ingot, 2), null, 200, 0, 0);

            } else if (ore.getDust() == EnumDust.GOLD) {
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreClearPowder, 1, ore.getMetadata()),
                        new ItemStack(Items.gold_ingot, 1), null, 200, 0, 0);
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreDirtyPowder, 3, ore.getMetadata()),
                        new ItemStack(Items.gold_ingot, 2), null, 200, 0, 0);
            } else if (ore.getDust().getMetal() != null) {
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreClearPowder, 1, ore.getMetadata()),
                        new ItemStack(NordItems.itemIngot, 1, ore.getDust().getMetal().getMetadata()),
                        null, 200, 0, 0);
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreDirtyPowder, 3, ore.getMetadata()),
                        new ItemStack(NordItems.itemIngot, 2, ore.getDust().getMetal().getMetadata()),
                        null, 200, 0, 0);
            }
        }
        for (EnumOreDrop ore2 : EnumOreDrop.values()) {
            if (ore2.getDust() == EnumDust.IRON) {
                ItemStack is = new ItemStack(NordItems.itemOreDrop, 2, ore2.getMetadata());
                System.err.println(is +" "+ ore2+ " "+ ore2.getDust());
                FurnaceRecipes1I2O.addRecipe(
                        is,
                        new ItemStack(Items.iron_ingot, 1), null, 200, 0, 0);
            } else if (ore2.getDust() == EnumDust.GOLD) {
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreDrop, 2, ore2.getMetadata()),
                        new ItemStack(Items.gold_ingot, 1), null, 200, 0, 0);
            } else if (ore2.getDust().getMetal() != null) {
                FurnaceRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreDrop, 2, ore2.getMetadata()),
                        new ItemStack(NordItems.itemIngot, 1, ore2.getDust().getMetal().getMetadata()),
                        null, 200, 0, 0);
            }
        }
    }

    private static void addVanilaRecipes() {
        Map recipes = net.minecraft.item.crafting.FurnaceRecipes.instance().getSmeltingList();
        Iterator iterator = recipes.entrySet().iterator();
        Map.Entry entry;

        while (iterator.hasNext()) {
            entry = (Map.Entry) iterator.next();
            ItemStack input = (ItemStack) entry.getKey();
            ItemStack output = (ItemStack) entry.getValue();
            FurnaceRecipes1I2O.addRecipe(input, output, output, 200, 0, 0);
        }

    }
}
