package ru.nord_core;


import ru.nord_core.common.helpers.RegisterHelper;
import ru.nord_core.common.items.ItemDebugStickSchematicLoad;
import ru.nord_core.common.items.ItemDebugStickSchematicSave;
import ru.nord_core.common.utils.Version;

public class NordCoreDebug {
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
        NordCoreItems.debugStickItemSchematickSave = new ItemDebugStickSchematicSave(Version.MODID).setUnlocalizedName("debugStickItemSchematickSave");
        NordCoreItems.debugStickItemSchematickLoad = new ItemDebugStickSchematicLoad(Version.MODID).setUnlocalizedName("debugStickItemSchematickLoad");
    }

    private static void createBlock() {

    }

    private static void registerItem() {
        RegisterHelper.registerSingleItem(NordCoreItems.debugStickItemSchematickSave, "debugStickItemSchematickSave", Version.MODID);
        RegisterHelper.registerSingleItem(NordCoreItems.debugStickItemSchematickLoad, "debugStickItemSchematickLoad", Version.MODID);
    }

    private static void registerBlock() {
    }

    private static void registerTileEntity() {
    }

}

