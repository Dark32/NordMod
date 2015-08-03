package ru.nord.common.recipes;


/**
 * Created by nikit_000 on 06.07.2015.
 */
public class Recipes {


    public static void preInit() {
    }

    public static void init() {
    }

    public static void postInit() {
        SmeltVanila.postInit();
        WorkBrenchVanila.postInit();
        FlowingRecipes.postInit();
        SmelterRecipes.postInit();
    }

}
