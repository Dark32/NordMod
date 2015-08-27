package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.NordBloks;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord_core.common.utils.enums.*;

public class FlowingRecipes {
    private static final int oreToDrop = 8;
    private static final int oreToNugget = 11;

    public static void postInit() {

        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone)),
                null, 80, 0, 0);
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.gravel)),
                null , 80, 0, 0);
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gravel), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.sand)),
                new ItemStack(Items.flint, 1)
                , 80, 10, 0);
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.redstone_ore), 1),
                new ItemStack(Items.redstone, 12),
                new ItemStack(NordItems.itemOreNugget, 1, EnumNugget.MERCURY.getMetadata())
                , 80, 10, 0);

        /*
        Измалываем руду в 6 дропов
         */
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.iron_ore), 1),
                new ItemStack(NordItems.itemOreDrop, oreToDrop, EnumOreDrop.MAGNETITE.getMetadata()),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                , 80, 10, 0);

        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gold_ore), 1),
                new ItemStack(Items.gold_nugget, oreToNugget),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone), 1)
                , 80, 90, 0);
        for (EnumOre ore : EnumOre.values()) {
            if (ore.getOreDrop() != null)
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(NordBloks.metalOre, 1, ore.getMetadata()),
                        new ItemStack(NordItems.itemOreDrop, oreToDrop, ore.getOreDrop().getMetadata()),
                        new ItemStack(Item.getItemFromBlock(Blocks.sand), 1)
                        , 80, 80, 0);
            if (ore.getNugget() != null) {
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(NordBloks.metalOre, 1, ore.getMetadata()),
                        new ItemStack(NordItems.itemOreNugget, oreToNugget, ore.getNugget().getMetadata()),
                        new ItemStack(Item.getItemFromBlock(Blocks.sand), 1)
                        , 80, 80, 0);
            }
        }
 /*
        Измалываем дропы  в грязную пыль
         */
        for (EnumOreDrop ore : EnumOreDrop.values()) {
            if (ore.getDust() != null)
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemOreDrop, 1, ore.getMetadata()),
                        new ItemStack(NordItems.itemOreDirtyPowder, 1, ore.getDust().getMetadata()),
                        new ItemStack(NordItems.itemOreDirtyPowder, 1, ore.getDust().getMetadata()),
                        80, 5, 0);
        }

        /*
        Измалывыаем слитки в пыль
         */
        FlowingRecipes1I2O.addRecipe(new ItemStack(Items.iron_ingot, 1),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.IRON.getMetadata()),
                null, 80, 0, 0);

        FlowingRecipes1I2O.addRecipe(new ItemStack(Items.gold_ingot, 1),
                new ItemStack(NordItems.itemMetalDust, 1, EnumDust.GOLD.getMetadata()),
                null, 80, 0, 0);

        for (EnumMetal metal : EnumMetal.values()) {
            FlowingRecipes1I2O.addRecipe(
                    new ItemStack(NordItems.itemIngot, 1, metal.getMetadata()),
                    new ItemStack(NordItems.itemMetalDust, 1, metal.getDust().getMetadata()),
                    null, 80, 0, 0);
        }
        for (EnumClearMetal metal : EnumClearMetal.values()) {
            if (metal == EnumClearMetal.IRON) {
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemClearIngot, 1, metal.getMetadata()),
                        new ItemStack(NordItems.itemMetalDust, 1, EnumDust.IRON.getMetadata()),
                        null, 80, 0, 0);
            } else if (metal == EnumClearMetal.GOLD) {
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemClearIngot, 1, metal.getMetadata()),
                        new ItemStack(NordItems.itemMetalDust, 1, EnumDust.GOLD.getMetadata()),
                        null, 80, 0, 0);
            } else {
                FlowingRecipes1I2O.addRecipe(
                        new ItemStack(NordItems.itemClearIngot, 1, metal.getMetadata()),
                        new ItemStack(NordItems.itemMetalDust, 1, metal.getMetal().getDust().getMetadata()),
                        null, 80, 0, 0);
            }
        }
    }
}
