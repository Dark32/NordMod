package ru.nord;

import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumFoodNord;
import ru.nord.common.utils.enums.EnumGlassFood;
import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.*;

public class NordFood {
    public static void preInit() {
        createItem();
        createBlock();
        registerItem();
        registerBlock();
        registerBlockModel();
        registerItemModel();
    }


    private static void registerBlockModel() {

    }

    public static void init() {
        registerTileEntity();

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
        RegisterHelper2.registerMetadataItem(NordItems.itemFoodNord, "itemFood");
        RegisterHelper2.registerMetadataItem(NordItems.itemGlassFood, "itemGlassFood");
    }

    private static void registerBlock() {
    }

    private static void registerTileEntity() {
    }
    private static void registerItemModel() {
        for (EnumFoodNord enumType : EnumFoodNord.values()) {
            Nord.proxy.registerModel().registerItemModelForMeta(NordItems.itemFoodNord, enumType.getMetadata(),"type=" + enumType.getName());
        }
        for (EnumGlassFood enumType : EnumGlassFood.values()) {
            Nord.proxy.registerModel().registerItemModelForMeta(NordItems.itemGlassFood, enumType.getMetadata(),"type=" + enumType.getName());
        }
    }


}

