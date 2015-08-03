package ru.nord;

import net.minecraftforge.common.MinecraftForge;
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
import ru.nord.common.CommonProxy;
import ru.nord.common.lib.events.OreDropEvent;
import ru.nord.common.lib.handler.GuiHandler;
import ru.nord.common.lib.network.PacketPipeline;
import ru.nord.common.recipes.Recipes;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.lib.utils.Version;
import ru.nord.common.lib.utils.generator.NordOre;

import java.util.Random;

@Mod(modid = Version.MODID, name = Version.NAME, version = Version.VERSION)
public class Nord {

    public static final PacketPipeline packetPipeline = new PacketPipeline();

    @Instance(value = Version.MODID)
    public static Nord instance;

    @SidedProxy(clientSide = "ru.nord.client.ClientProxy", serverSide = "ru.nord.common.CommonProxy")
    public static CommonProxy proxy;
    public static Random rand = new Random(); // Не использовать. Возможен Десинк

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event) {
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
        Nord.proxy.init();
        packetPipeline.initialise();
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        MinecraftForge.EVENT_BUS.register(new OreDropEvent());
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
