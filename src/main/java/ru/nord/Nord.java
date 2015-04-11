package ru.nord;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ru.nord.common.CommonProxy;
import ru.nord.common.lib.events.GuiHandler;
import ru.nord.common.lib.network.PacketPipeline;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.lib.utils.Version;

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
        Fuel.init();
        NordMachine.preInit();
        NordDecoration.preInit();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        NordMachine.init();
        NordDecoration.init();
        Nord.proxy.registerRenderers();
        Nord.proxy.init();
        packetPipeline.initialise();
        NetworkRegistry.INSTANCE.registerGuiHandler(Nord.instance, new GuiHandler());
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        packetPipeline.postInitialise();
        NordTabs.postInit();
        NordMachine.postInit();
        NordDecoration.postInit();
    }
}
