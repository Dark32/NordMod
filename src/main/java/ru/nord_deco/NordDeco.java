package ru.nord_deco;

import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import ru.nord_deco.common.CommonProxy;
import ru.nord_deco.common.entity.EntitySittableBlock;
import ru.nord_deco.common.utils.Version;

import java.util.Random;

@Mod(modid = Version.MODID, name = Version.NAME, version = Version.VERSION,
        dependencies = "required-after:nord_core")
public class NordDeco {


    @Instance(value = Version.MODID)
    public static NordDeco instance;

    @SidedProxy(clientSide = "ru.nord_deco.client.ClientProxy",
                serverSide = "ru.nord_deco.common.CommonProxy")
    public static CommonProxy proxy;
    public static Random rand = new Random(); // Не использовать. Возможен Десинк

    @EventHandler
    public void preInit(final FMLPreInitializationEvent event)
    {
        FMLLog.info("Deco Nord Mod start init");
        NordDecoration.preInit();
    }

    @EventHandler
    public void init(final FMLInitializationEvent event) {
        NordDecoration.init();
        EntityRegistry.registerModEntity(EntitySittableBlock.class, "MountableBlock", 0, this, 80, 1, false);
    }

    @EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        NordDecoration.postInit();

    }
}
