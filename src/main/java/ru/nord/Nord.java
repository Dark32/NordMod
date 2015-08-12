package ru.nord;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.*;
import ru.nord.common.*;
import ru.nord.common.events.*;
import ru.nord.common.network.*;
import ru.nord.common.recipes.*;
import ru.nord.common.utils.*;
import ru.nord.common.utils.generator.*;
import ru.nord_core.common.handler.GuiHandler;
import ru.nord_core.common.utils.Fuel;

import java.util.Random;

@Mod(modid = Version.MODID, name = Version.NAME, version = Version.VERSION,
        dependencies = "required-after:nord_core")
public class Nord {

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @Instance(value = Version.MODID)
    public static Nord instance;

    @SidedProxy(clientSide = "ru.nord.client.ClientProxy", serverSide = "ru.nord.common.CommonProxy")
    public static CommonProxy proxy;
    public static Random rand = new Random(); // Не использовать. Возможен Десинк

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        FMLLog.info("Nord Mod start init");
        NordConfig.preInit();
        NordMachine.preInit();
        NordMetalgury.preInit();
        NordDecoration.preInit();
        Recipes.preInit();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        NordMachine.init();
        NordMetalgury.init();
        NordDecoration.init();
        Nord.proxy.registerRenderers();
        packetPipeline.initialise();
        MinecraftForge.EVENT_BUS.register(new OreDropEvent());
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        GameRegistry.registerWorldGenerator(new NordOre(), 2);
        Recipes.init();
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
        NordTabs.postInit();
        NordMachine.postInit();
        NordMetalgury.postInit();
        NordDecoration.postInit();
        Fuel.postInit();
        Recipes.postInit();
    }
    public void setupModInfo(ModMetadata meta){
        Version.setupModInfo(meta);
    }
}
