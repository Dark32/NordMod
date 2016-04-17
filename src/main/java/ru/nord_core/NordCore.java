package ru.nord_core;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLMissingMappingsEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord_core.common.CommonProxy;
import ru.nord_core.common.tiles.abstracts.TileMetadata;
import ru.nord_core.common.utils.JsonConfig;
import ru.nord_core.common.utils.Version;

import java.io.File;
import java.util.Random;

@Mod(modid = Version.MODID, name = Version.NAME, version = Version.VERSION)
public class NordCore {


    @Instance(value = Version.MODID)
    public static NordCore instance;

    @SidedProxy(clientSide = "ru.nord_core.client.ClientProxy",
            serverSide = "ru.nord_core.common.CommonProxy")
    public static CommonProxy proxy;
    public static Random rand = new Random(); // Не использовать. Возможен Десинк
    private static JsonConfig JSONconfig;

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        FMLLog.info("Core Nord Mod start init");
        JSONconfig = new JsonConfig("Nord.json");
        NordCoreDebug.preInit();
        proxy.registerEventHandlers();
        getSchematic();
        GameRegistry.registerTileEntity(TileMetadata.class, "TileMetadata");
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        NordCoreDebug.init();
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        NordCoreDebug.postInit();
    }

    @EventHandler
    public void missMap(final FMLMissingMappingsEvent event) {
        FMLLog.info("Core Nord Mod start FMLMissingMappingsEvent");
    }

    private void getSchematic() {
        File schematicDir = new File(proxy.getDataDirectory(), "schematics/");
        boolean create;
        if (!schematicDir.exists()) {
            create = schematicDir.mkdirs();
        }
    }

    public static JsonConfig getJSONConfig() {
        return JSONconfig;
    }

}
