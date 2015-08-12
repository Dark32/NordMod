package ru.nord_core;

;import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ru.nord_core.common.handler.GuiHandler;
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

    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {

    }
}