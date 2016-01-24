package ru.nord;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.common.blocks.*;
import ru.nord.common.tiles.*;
import ru.nord.common.utils.Version;
import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.ItemBase;
import ru.nord_core.common.items.ItemEnergyStorageDamagable;
import ru.nord_core.common.items.ItemWrench;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumFrame;

public class NordMachine {
    public static void preInit() {
        createItem();
        createBlock();
        createItem();
        registerBlock();
        registerItem();
        registerBlockModel();
        registerItemModel();
    }


    public static void init() {
        registerTileEntity();
    }

    public static void postInit() {

    }

    private static void createItem() {
        NordItems.energyStorageItem = new ItemEnergyStorageDamagable(16000, Version.MODID).setUnlocalizedName("itemEnergyStorage").setCreativeTab(NordTabs.tabMachine);
        NordItems.wrench = new ItemWrench(Version.MODID).setUnlocalizedName("itemWrench").setCreativeTab(NordTabs.tabMachine);
        NordItems.itemBlades = new ItemBase(Version.MODID).setUnlocalizedName("blades");
    }

    private static void createBlock() {
        NordBloks.flowingBlock = new BlockFlowing().setUnlocalizedName("flowingBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.generatorBlock = new BlockGenerator().setUnlocalizedName("generatorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.accumulatorBlock = new BlockAccumulator().setUnlocalizedName("accumulatorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.energyCableBlock = new BlockEnergoCable().setUnlocalizedName("energyCableBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.energyCableBlock2 = new BlockEnergoCable2().setUnlocalizedName("energyCableBlock2").setCreativeTab(NordTabs.tabMachine);
        NordBloks.frame = new BlockFrame().setUnlocalizedName("frame").setCreativeTab(NordTabs.tabMachine);
        NordBloks.smelterBlock = new BlockSmelter().setUnlocalizedName("smelterBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.placeDeco = new BlockDecoPlacer().setUnlocalizedName("deco_placer").setCreativeTab(NordTabs.tabColorStone);
        NordBloks.extractorBlock = new BlockExtractor().setUnlocalizedName("extractorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.furnaceBlock = new BlockFurnace().setUnlocalizedName("furnaceBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.washerBlock = new BlockWasher().setUnlocalizedName("washerBlock").setCreativeTab(NordTabs.tabMachine);

    }

    private static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileFlowing.class, "TileEntityFlowing");
        GameRegistry.registerTileEntity(TileExtractor.class, "TileEntityExtractor");
        GameRegistry.registerTileEntity(TileFurnace.class, "TileEntityFurnace");
        GameRegistry.registerTileEntity(TileSmelter.class, "TileEntitySmelter");
        GameRegistry.registerTileEntity(TileWasher.class, "TileEntityWasher");
        GameRegistry.registerTileEntity(TileGenerator.class, "TileEntityGenerator");
        GameRegistry.registerTileEntity(TileAccumulator.class, "TileEntityAccumulator");
        GameRegistry.registerTileEntity(TileEnergyCable.class, "TileEntityEnergyCable");
        GameRegistry.registerTileEntity(TileEnergyCable2.class, "TileEntityEnergyCable2");
    }

    private static void registerItemModel() {
        Nord.proxy.registerModel().registerItemModel(NordItems.energyStorageItem);
        Nord.proxy.registerModel().registerItemModel(NordItems.wrench );
    }

    private static void registerBlockModel() {
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.washerBlock, 0, "facing=north");
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.furnaceBlock, 0, "facing=north");
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.extractorBlock, 0, "facing=north");
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.smelterBlock, 0, "facing=north");
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.accumulatorBlock, 0, "facing=north");
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.generatorBlock, 0, "facing=north");
        Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.flowingBlock, 0, "facing=north");
        for (EnumFrame enumType : EnumFrame.values()) {
            Nord.proxy.registerModel().registerBlockItemModelForMeta(NordBloks.frame, enumType.getMetadata(), "type=" + enumType.getName());
        }
    }

    private static void registerItem() {
        RegisterHelper2.registerSingleItem(NordItems.energyStorageItem, "itemEnergyStorage");
        RegisterHelper2.registerSingleItem(NordItems.wrench, "itemWrench");
//        RegisterHelper2.registerSingleItem(NordItems.itemBlades, "itemblades");//todo
    }

    private static void registerBlock() {
        RegisterHelper2.registerSingleBlock(NordBloks.flowingBlock, "flowingBlock");
        RegisterHelper2.registerSingleBlock(NordBloks.generatorBlock, "generatorBlock");
        RegisterHelper2.registerSingleBlock(NordBloks.accumulatorBlock, "accumulatorBlock");
//        RegisterHelper2.registerSingleBlock(NordBloks.energyCableBlock, "energyCableBlock"); //todo
//        RegisterHelper2.registerSingleBlock(NordBloks.energyCableBlock2, "energyCableBlock2");//todo
        RegisterHelper2.registerSingleBlock(NordBloks.smelterBlock, "smelterBlock");
//        RegisterHelper2.registerSingleBlock(NordBloks.placeDeco, "deco_placer");//todo
        RegisterHelper2.registerSingleBlock(NordBloks.extractorBlock, "extractorBlock");
        RegisterHelper2.registerSingleBlock(NordBloks.furnaceBlock, "furnaceBlock");
        RegisterHelper2.registerSingleBlock(NordBloks.washerBlock, "washerBlock");
        RegisterHelper2.registerMetadataBlock(NordBloks.frame, ItemBlockMetadata.class, "frame");
    }
}

