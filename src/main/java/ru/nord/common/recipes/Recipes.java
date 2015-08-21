package ru.nord.common.recipes;


import net.minecraftforge.fml.common.FMLLog;
import ru.nord.common.lib.recipes.*;

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

        FMLLog.info("Loaded " + FlowingRecipes1I2O.INSTANCE().getRecipes().size() + " FlowingRecipes1I2O");
        FMLLog.info("Loaded " + SmelterRecipes2I2O.INSTANCE().getRecipes().size() + " SmelterRecipes2I2O");
        FMLLog.info("Loaded " + ExtractorRecipes1I2O.INSTANCE().getRecipes().size() + " ExtractorRecipes1I2O");
        FMLLog.info("Loaded " + FurnaceRecipes1I2O.INSTANCE().getRecipes().size() + " FurnaceRecipes1I2O");
        FMLLog.info("Loaded " + WasherRecipes1I2O.INSTANCE().getRecipes().size() + " WasherRecipes2I2O");

    }

}
