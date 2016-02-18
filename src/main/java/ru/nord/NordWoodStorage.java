package ru.nord;

import ru.nord.common.lib.wood.WoodStorage;
import ru.nord.common.utils.WeightRandom;

public class NordWoodStorage {
    /*                              */
    public static WeightRandom<String> sakura;

    /*                              */
    private static WoodStorage sakuraWS;

    public static void preInit() {
        sakuraWS = new WoodStorage("sakura").addDefault();

    }

    public static void init(){
        sakura = new WeightRandom<String>(sakuraWS.getWeights(),sakuraWS.getValues());
    }

    public static void postInit() {

    }
}
