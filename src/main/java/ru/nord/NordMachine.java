package ru.nord;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.common.blocks.*;
import ru.nord.common.blocks.abstracts.BlockAbstractEnergyCable;
import ru.nord.common.items.*;
import ru.nord.common.lib.helpers.RegisterHelper;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord.common.lib.recipes.Recipe;
import ru.nord.common.lib.utils.enums.EnumFrame;
import ru.nord.common.lib.utils.enums.EnumStone;
import ru.nord.common.tiles.TileAccumulator;
import ru.nord.common.tiles.TileEnergyCable;
import ru.nord.common.tiles.TileFlowing;
import ru.nord.common.tiles.TileGenerator;

public class NordMachine {
    public static void preInit() {
        createItem();
        createBlock();
    }

    public static void init() {
        registerItem();
        registerBlock();
        registerTileEntity();
        addRecipe();
    }

    public static void postInit() {

    }

    private static void createItem() {
        ItemIngot.getArray();
        ItemDust.createArray();
        NordItems.energyStorageItem = new ItemEnergyStorageDamagable(16000).setUnlocalizedName("itemEnergyStorage").setCreativeTab(NordTabs.tabMachine);
        NordItems.wrench = new ItemWrench().setUnlocalizedName("itemWrench").setCreativeTab(NordTabs.tabMachine);
        NordItems.dustItem = new ItemDust().setUnlocalizedName("dust");
        NordItems.itemStick = new ItemStick().setUnlocalizedName("stick");
        NordItems.itemBlades = new ItemBlades().setUnlocalizedName("blades");
        NordItems.itemIngot =new ItemIngot().setUnlocalizedName("ingotmod");
    }

    private static void createBlock() {
        NordBloks.flowingBlock = new BlockFlowing().setUnlocalizedName("flowingBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.generatorBlock = new BlockGenerator().setUnlocalizedName("generatorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.accumulatorBlock = new BlockAccumulator().setUnlocalizedName("accumulatorBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.energyCableBlock = new BlockEnergoCable().setUnlocalizedName("energyCableBlock").setCreativeTab(NordTabs.tabMachine);
        NordBloks.frame = new BlockFrame().setUnlocalizedName("frame").setCreativeTab(NordTabs.tabMachine);
        NordBloks.metalBlock = new BlockMetal().setUnlocalizedName("metalblock").setCreativeTab(NordTabs.tabMetallurgy);
    }

    private static void registerItem() {
        RegisterHelper.registerSingleItem(NordItems.energyStorageItem, "itemEnergyStorage");
        RegisterHelper.registerSingleItem(NordItems.wrench, "itemWrench");
        RegisterHelper.registerMetadataItem(NordItems.dustItem, "dust", "dust", ItemDust.namedust);
        RegisterHelper.registerMetadataItem(NordItems.itemStick,"stick","stick", ItemStick.nameStick);
        RegisterHelper.registerSingleItem(NordItems.itemBlades, "itemblades");
        RegisterHelper.registerMetadataItem(NordItems.itemIngot,"ingotmod","ingotmod",ItemIngot.nameIngot);
    }

    private static void registerBlock() {
        RegisterHelper.registerSingleBlock(NordBloks.flowingBlock, "flowingBlock");
        RegisterHelper.registerSingleBlock(NordBloks.generatorBlock, "generatorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.accumulatorBlock, "accumulatorBlock");
        RegisterHelper.registerSingleBlock(NordBloks.energyCableBlock, "energyCableBlock");
        RegisterHelper.registerMetadataBlock(NordBloks.metalBlock,ItemBlockMetal.class,"metalblock","metalblock",ItemIngot.nameIngot);
        RegisterHelper.registerMetadataBlock(
                NordBloks.frame,
                ItemBlockFrame.class,
                "frame",
                "frame",
                EnumFrame.getNames()
                );

    }

    private static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileFlowing.class, "TileEntityFlowing");
        GameRegistry.registerTileEntity(TileGenerator.class, "TileEntityGenerator");
        GameRegistry.registerTileEntity(TileAccumulator.class, "TileEntityAccumulator");
        GameRegistry.registerTileEntity(TileEnergyCable.class, "TileEntityEnergyCable");
    }


    private static void addRecipe() {
        GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock, 1), "xxx", "x x", "xxx", // TODO fix recipe
                'x', new ItemStack(Blocks.stone, 1));
        Recipe.addAll();

    }

}

