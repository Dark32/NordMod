package ru.nord;

import ru.nord.common.lib.recipes.FlowingRecipes1I2O;

/**
 * Created by nikit_000 on 06.07.2015.
 */
public class Recipe {
    public static void addAll(){
        System.out.print("Start add simply recipe... ");
        addAllRecipeVanilla();
        System.out.println("Done");
        System.out.print("Start add advanced recipe...");
        addAllRecipeMacerator();
        System.out.println("Done");
    }
    public static void addAllRecipeVanilla(){
        //GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock, 1), "xxx", "x x", "xxx", 'x', new ItemStack(Blocks.stone, 1));

    }
    public static void addAllRecipeMacerator(){
        //FlowingRecipes1I2O.addRecipe(ItemStack input, ItemStack output1,ItemStack output2, int needEnergy, float percent, float exp)

    }
}
