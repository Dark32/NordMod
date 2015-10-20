package ru.nord;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.common.blocks.*;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.tiles.*;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumFoodNord;
import ru.nord.common.utils.enums.EnumGlassFood;
import ru.nord_core.common.items.ItemBase;
import ru.nord_core.common.items.ItemEnergyStorageDamagable;
import ru.nord_core.common.items.ItemFoodNord;
import ru.nord_core.common.items.ItemWrench;

public class NordMachine {
    public static void preInit() {
        createItem();
        createBlock();
    }

    public static void init() {
        registerTileEntity();
        registerItem();
        registerBlock();
    }

    public static void postInit() {

    }

    private static void createItem() {
        NordItems.energyStorageItem = new ItemEnergyStorageDamagable(16000, Version.MODID).setUnlocalizedName("itemEnergyStorage").setCreativeTab(NordTabs.tabMachine);
        NordItems.wrench = new ItemWrench(Version.MODID).setUnlocalizedName("itemWrench").setCreativeTab(NordTabs.tabMachine);
        NordItems.itemBlades = new ItemBase(Version.MODID).setUnlocalizedName("blades");
        NordItems.itemFoodNord = new ItemFoodNord(1, 1, false).setUnlocalizedName("itemFood");
    }

    private static void createBlock() {
        NordBloks.flowingBlock = new BlockFlowing().setUnlocalizedName("flowingBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.generatorBlock = new BlockGenerator().setUnlocalizedName("generatorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.accumulatorBlock = new BlockAccumulator().setUnlocalizedName("accumulatorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.energyCableBlock = new BlockEnergoCable().setUnlocalizedName("energyCableBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.frame = new BlockFrame().setUnlocalizedName("frame").setCreativeTab(NordTabs.tabMachine);
        NordBloks.smelterBlock = new BlockSmelter().setUnlocalizedName("smelterBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.placeDeco = new BlockDecoPlacer().setUnlocalizedName("deco_placer").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.diggerWell = new BlockDiggerWell().setUnlocalizedName("digger_well").setCreativeTab(NordTabs.tabMachine);
        NordBloks.extractorBlock = new BlockExtractor().setUnlocalizedName("extractorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.furnaceBlock = new BlockFurnace().setUnlocalizedName("furnaceBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.washerBlock = new BlockWasher().setUnlocalizedName("washerBlock").setCreativeTab(NordTabs.tabMachine);

    }

    private static void registerItem() {
        RegisterHelper.registerSingleItem(NordItems.energyStorageItem, "itemEnergyStorage");
        RegisterHelper.registerSingleItem(NordItems.wrench, "itemWrench");
        RegisterHelper.registerSingleItem(NordItems.itemBlades, "itemblades");
        RegisterHelper.registerMetadataItem(NordItems.itemFoodNord, "itemFood", "itemFood", EnumFoodNord.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemGlassFood, "itemGlassFood", "itemGlassFood", EnumGlassFood.getNames());
    }

    private static void registerBlock() {
        RegisterHelper.registerSingleBlock(NordBloks.flowingBlock, "flowingBlock");
        RegisterHelper.registerSingleBlock(NordBloks.generatorBlock, "generatorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.accumulatorBlock, "accumulatorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.energyCableBlock, "energyCableBlock");
        RegisterHelper.registerSingleBlock(NordBloks.smelterBlock, "smelterBlock");
        RegisterHelper.registerSingleBlock(NordBloks.diggerWell, "digger_well");
        RegisterHelper.registerSingleBlock(NordBloks.placeDeco, "deco_placer");
        RegisterHelper.registerSingleBlock(NordBloks.extractorBlock, "extractorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.furnaceBlock, "furnaceBlock");
        RegisterHelper.registerSingleBlock(NordBloks.washerBlock, "washerBlock");
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
    }

}

