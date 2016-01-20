package ru.nord.common.recipes;


import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Loader;
import ru.nord.common.lib.recipes.*;
//import ru.nord.common.recipes.minetweaker.MineTweaker;

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
        ExtractorRecipes.postInit();
        FurnaceRecipes.postInit();
        WasherRecipes.postInit();
        if (Loader.isModLoaded("MineTweaker3"))
//            MineTweaker.Init();
        FMLLog.info("[NORD] Loaded " + FlowingRecipes1I2O.INSTANCE().getRecipes().size() + " FlowingRecipes1I2O");
        FMLLog.info("[NORD] Loaded " + SmelterRecipes2I2O.INSTANCE().getRecipes().size() + " SmelterRecipes2I2O");
        FMLLog.info("[NORD] Loaded " + ExtractorRecipes1I2O.INSTANCE().getRecipes().size() + " ExtractorRecipes1I2O");
        FMLLog.info("[NORD] Loaded " + FurnaceRecipes1I2O.INSTANCE().getRecipes().size() + " FurnaceRecipes1I2O");
        FMLLog.info("[NORD] Loaded " + WasherRecipes1I2O.INSTANCE().getRecipes().size() + " WasherRecipes2I2O");

    }

}
