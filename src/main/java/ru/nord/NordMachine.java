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
import static ru.nord_core.common.helpers.RegisterRenderHelper.modelRegister;
public class NordMachine {
    public static void preInit() {
        createItem();
        createBlock();
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
//        NordBloks.energyCableBlock = new BlockEnergoCable().setUnlocalizedName("energyCableBlock").setCreativeTab(NordTabs.tabMachine);
//        NordBloks.energyCableBlock2 = new BlockEnergoCable2().setUnlocalizedName("energyCableBlock2").setCreativeTab(NordTabs.tabMachine);
        NordBloks.frame = new BlockFrame().setUnlocalizedName("frame").setCreativeTab(NordTabs.tabMachine);
        NordBloks.smelterBlock = new BlockSmelter().setUnlocalizedName("smelterBlock").setCreativeTab(NordTabs.tabMachine);
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
        modelRegister().registerItemModel(NordItems.energyStorageItem);
        modelRegister().registerItemModel(NordItems.wrench );
    }

    private static void registerBlockModel() {
        modelRegister().registerBlockItemModelForMeta(NordBloks.washerBlock, 0, "facing=north");
        modelRegister().registerBlockItemModelForMeta(NordBloks.furnaceBlock, 0, "facing=north");
        modelRegister().registerBlockItemModelForMeta(NordBloks.extractorBlock, 0, "facing=north");
        modelRegister().registerBlockItemModelForMeta(NordBloks.smelterBlock, 0, "facing=north");
        modelRegister().registerBlockItemModelForMeta(NordBloks.accumulatorBlock, 0, "facing=north");
        modelRegister().registerBlockItemModelForMeta(NordBloks.generatorBlock, 0, "facing=north");
        modelRegister().registerBlockItemModelForMeta(NordBloks.flowingBlock, 0, "facing=north");
        for (EnumFrame enumType : EnumFrame.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.frame, enumType.getMetadata(), "type=" + enumType.getName());
        }
    }

    private static void registerItem() {
        RegisterHelper2.registerItem(NordItems.energyStorageItem, "itemEnergyStorage");
        RegisterHelper2.registerItem(NordItems.wrench, "itemWrench");
//        RegisterHelper2.registerItem(NordItems.itemBlades, "itemblades");//todo
    }

    private static void registerBlock() {
        RegisterHelper2.registerBlock(NordBloks.flowingBlock, "flowingBlock");
        RegisterHelper2.registerBlock(NordBloks.generatorBlock, "generatorBlock");
        RegisterHelper2.registerBlock(NordBloks.accumulatorBlock, "accumulatorBlock");
//        RegisterHelper2.registerBlock(NordBloks.energyCableBlock, "energyCableBlock"); //todo
//        RegisterHelper2.registerBlock(NordBloks.energyCableBlock2, "energyCableBlock2");//todo
        RegisterHelper2.registerBlock(NordBloks.smelterBlock, "smelterBlock");
        RegisterHelper2.registerBlock(NordBloks.extractorBlock, "extractorBlock");
        RegisterHelper2.registerBlock(NordBloks.furnaceBlock, "furnaceBlock");
        RegisterHelper2.registerBlock(NordBloks.washerBlock, "washerBlock");
        RegisterHelper2.registerBlock(NordBloks.frame, new ItemBlockMetadata(NordBloks.frame), "frame");
    }
}

