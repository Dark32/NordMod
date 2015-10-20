package ru.nord;

import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumFoodNord;
import ru.nord.common.utils.enums.EnumGlassFood;
import ru.nord_core.common.items.*;

public class NordFood {
    public static void preInit() {
        createItem();
        createBlock();
    }

    public static void init() {
        registerTileEntity();
        registerItem();
        registerBlock();
    }

    public static void postInit() {

    }

    private static void createItem() {
        NordItems.itemFoodNord = new ItemFoodNord().setUnlocalizedName("itemFood");
        NordItems.itemGlassFood = new ItemGlassFood(Version.MODID).setUnlocalizedName("itemGlassFood");
    }

    private static void createBlock() {
    }

    private static void registerItem() {
        RegisterHelper.registerMetadataItem(NordItems.itemFoodNord, "itemFood", "itemFood", EnumFoodNord.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemGlassFood, "itemGlassFood", "itemGlassFood", EnumGlassFood.getNames());
    }

    private static void registerBlock() {
    }

    private static void registerTileEntity() {
    }

}

