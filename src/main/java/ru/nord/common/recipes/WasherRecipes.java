package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.nord.NordBloks;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.WasherRecipes1I2O;
import ru.nord_core.common.utils.enums.EnumOreDrop;

public class WasherRecipes {
    public static void postInit(){
        WasherRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.dirt), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.sand), 1),
                null,400, 0, 5);
        WasherRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.sand), 10),
                new ItemStack(Item.getItemFromBlock(Blocks.sand), 5),
                new ItemStack(Items.gold_nugget, 1),
                2000, 20, 5);

        for (EnumOreDrop ore : EnumOreDrop.values()) {
            if(ore.getCrystal()!=null){
                WasherRecipes1I2O.addRecipe(new ItemStack(NordItems.itemOreDirtyPowder, 1, ore.getMetadata()),
                        new ItemStack(NordItems.itemOreClearPowder, 1, ore.getCrystal().getMetadata()),
                        null, 400, 0, 5);
            }
        }
    }
}
