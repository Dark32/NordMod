package ru.nord;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import ru.nord.common.CommonProxy;
import ru.nord.common.items.ItemBase;
import ru.nord.common.lib.events.GuiHandler;
import ru.nord.common.lib.helpers.RegisterHelper;
import ru.nord.common.lib.network.PacketPipeline;
import ru.nord.common.lib.utils.Fuel;

import java.util.Random;

@Mod(modid = Nord.MODID, name = Nord.NAME, version = Nord.VERSION)
public class Nord {
        public static final String MODID = "nord";
        public static final String VERSION = "0.0.2";
        public static final String NAME = "Nord Mod 1.8";
        public static final int[] colors = new int[]{0x1E1B1B, 0xB3312C, 0x3B511A,
                0x51301A, 0x253192, 0x7B2FBE, 0x287697, 0xABABAB, 0x434343,
                0xD88198, 0x41CD34, 0xDECF2A, 0x6689D3, 0xC354CD, 0xEB8844,
                0xF0F0F0};

        public static final PacketPipeline packetPipeline = new PacketPipeline();

        public static Item tutorialItem;

        public static Block tutorialBlock;

        @Instance(value = Nord.MODID)
        public static Nord instance;

        @SidedProxy(clientSide = "ru.nord.client.ClientProxy", serverSide = "ru.nord.common.CommonProxy")
        public static CommonProxy proxy;
        public static Random rand = new Random();

        @EventHandler
        public void preInit(final FMLPreInitializationEvent event) {
            Fuel.init();
//            tutorialBlock = new BlockBase();
            //items
            tutorialItem = new ItemBase().setUnlocalizedName("itemBase"). setCreativeTab(CreativeTabs.tabMisc);


        }

        @EventHandler
        public void init(final FMLInitializationEvent event) {
            RegisterHelper.registerSingleItem(tutorialItem, "itemBase");
            Nord.proxy.registerRenderers();
            Nord.proxy.init();
            packetPipeline.initialise();
            NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
        }

        @EventHandler
        public void postInit(final FMLPostInitializationEvent event) {
                packetPipeline.postInitialise();
        }
}
