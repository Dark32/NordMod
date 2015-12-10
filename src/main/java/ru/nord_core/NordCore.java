package ru.nord_core;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import ru.nord_core.common.CommonProxy;
import ru.nord_core.common.utils.Version;


import java.util.Random;

@Mod(modid = Version.MODID, name = Version.NAME, version = Version.VERSION)
public class NordCore {


    @Instance(value = Version.MODID)
    public static NordCore instance;

    @SidedProxy(clientSide = "ru.nord_core.client.ClientProxy",
                serverSide = "ru.nord_core.common.CommonProxy")
    public static CommonProxy proxy;
    public static Random rand = new Random(); // Не использовать. Возможен Десинк

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        FMLLog.info("Core Nord Mod start init");
        NordCoreDebug.preInit();
        proxy.registerEventHandlers();
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
}
