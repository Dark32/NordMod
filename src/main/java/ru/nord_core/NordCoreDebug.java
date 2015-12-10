package ru.nord_core;


import ru.nord_core.common.helpers.RegisterHelper;
import ru.nord_core.common.items.ItemDebugStick;
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
        NordCoreItems.debugStickItem= new ItemDebugStick(Version.MODID).setUnlocalizedName("debugStickItem");
        }

    private static void createBlock() {

    }

    private static void registerItem() {
        RegisterHelper.registerSingleItem( NordCoreItems.debugStickItem, "debugStickItem",Version.MODID);
         }

    private static void registerBlock() {
    }

    private static void registerTileEntity() {
    }

}

