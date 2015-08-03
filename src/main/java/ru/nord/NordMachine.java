package ru.nord;

import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.common.blocks.*;
import ru.nord.common.items.*;
import ru.nord.common.lib.helpers.RegisterHelper;
import ru.nord.common.recipes.Recipes;
import ru.nord.common.tiles.*;

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
        addRecipe();
    }

    private static void createItem() {
        NordItems.energyStorageItem = new ItemEnergyStorageDamagable(16000).setUnlocalizedName("itemEnergyStorage").setCreativeTab(NordTabs.tabMachine);
        NordItems.wrench = new ItemWrench().setUnlocalizedName("itemWrench").setCreativeTab(NordTabs.tabMachine);
        NordItems.itemBlades = new ItemBase().setUnlocalizedName("blades");
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

    }

    private static void registerItem() {
        RegisterHelper.registerSingleItem(NordItems.energyStorageItem, "itemEnergyStorage");
        RegisterHelper.registerSingleItem(NordItems.wrench, "itemWrench");
        RegisterHelper.registerSingleItem(NordItems.itemBlades, "itemblades");
        }

    private static void registerBlock() {
        RegisterHelper.registerSingleBlock(NordBloks.flowingBlock, "flowingBlock");
        RegisterHelper.registerSingleBlock(NordBloks.generatorBlock, "generatorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.accumulatorBlock, "accumulatorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.energyCableBlock, "energyCableBlock");
        RegisterHelper.registerSingleBlock(NordBloks.smelterBlock, "smelterBlock");
        RegisterHelper.registerSingleBlock(NordBloks.diggerWell,"digger_well");
        RegisterHelper.registerSingleBlock(NordBloks.placeDeco, "deco_placer");

    }

    private static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileFlowing.class, "TileEntityFlowing");
        GameRegistry.registerTileEntity(TileSmelter.class, "TileEntitySmelter");
        GameRegistry.registerTileEntity(TileGenerator.class, "TileEntityGenerator");
        GameRegistry.registerTileEntity(TileAccumulator.class, "TileEntityAccumulator");
        GameRegistry.registerTileEntity(TileEnergyCable.class, "TileEntityEnergyCable");
    }


    private static void addRecipe() {
//        GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock, 1), "xxx", "x x", "xxx", // TODO fix recipe
//                'x', new ItemStack(Blocks.stone, 1));
        Recipes.addAll();

    }

}

