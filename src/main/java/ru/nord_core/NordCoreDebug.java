package ru.nord_core;

import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.ItemDebugStickSchematicLoad;
import ru.nord_core.common.items.ItemDebugStickSchematicSave;
import ru.nord_core.common.utils.Version;

import static ru.nord_core.common.helpers.RegisterRenderHelper.modelRegister;

public class NordCoreDebug {
    public static void preInit() {
        createItem();
        createBlock();
        registerItem();
        registerBlock();
        registerItemModel();
    }

    public static void init() {
        registerTileEntity();
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
        RegisterHelper2.registerItem(NordCoreItems.debugStickItemSchematickSave, "debugStickItemSchematickSave");
        RegisterHelper2.registerItem(NordCoreItems.debugStickItemSchematickLoad, "debugStickItemSchematickLoad");
    }

    private static void registerBlock() {
    }

    private static void registerTileEntity() {
    }
    private static void registerItemModel() {
        modelRegister().registerItemModelForMeta(NordCoreItems.debugStickItemSchematickSave, 0, "#inventory");
        modelRegister().registerItemModelForMeta(NordCoreItems.debugStickItemSchematickSave, 1, "#inventory");
        modelRegister().registerItemModelForMeta(NordCoreItems.debugStickItemSchematickLoad, 0, "#inventory");
    }
}

