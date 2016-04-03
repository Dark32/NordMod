package ru.nord;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.common.events.OreDropEvent;
import ru.nord.common.lib.dictoary.Dictoary;
import ru.nord.common.network.PacketPipeline;
import ru.nord.common.recipes.Recipes;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.generator.NordOre;
import ru.nord.common.IProxy;
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
    public static IProxy proxy;
    public static Random rand = new Random(); // Не использовать. Возможен Десинк

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
        FMLLog.info("Nord Mod start preInit");
        NordConfig.preInit();
        NordMachine.preInit();
        NordMetalgury.preInit();
        NordDecoration.preInit();
        NordFood.preInit();
        NordWood.preInit();
        Recipes.preInit();
        NordWoodStorage.preInit();
        proxy.preInit();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        NordMachine.init();
        NordMetalgury.init();
        NordDecoration.init();
        NordFood.init();
        NordWood.init();
        NordWoodStorage.init();
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
        NordFood.postInit();
        NordWood.postInit();
        NordWoodStorage.postInit();
        Fuel.postInit();
        Dictoary.postInit();
        Recipes.postInit();

    }

    public void setupModInfo(ModMetadata meta) {
        Version.setupModInfo(meta);
    }
}
