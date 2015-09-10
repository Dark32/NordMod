package ru.nord.common.recipes.minetweaker;

import minetweaker.MineTweakerAPI;

/**
 * Created by nikit on 10.09.2015.
 */
public class MineTweaker {
    public static void Init(){
        MineTweakerAPI.registerClass(FlowingRecipe.class);
        MineTweakerAPI.registerClass(ExtractorRecipe.class);
        MineTweakerAPI.registerClass(SmelterRecipe.class);
    }
}
