package ru.nord;

import net.minecraft.item.Item;
import ru.nord.common.blocks.BlockClearMetal;
import ru.nord.common.blocks.BlockCrystal;
import ru.nord.common.blocks.BlockMetal;
import ru.nord.common.blocks.BlockMetalOre;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.items.*;
import ru.nord_core.common.items.ItemMetaData;
import ru.nord_core.common.items.abstracts.AItemDrill;
import ru.nord_core.common.utils.enums.*;

public class NordMetalgury {
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
        NordItems.itemOreDrop = new ItemMetaData(EnumOreDrop.getNames()).setUnlocalizedName("oreDrop").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreDirtyPowder = new ItemMetaData(EnumOreDrop.getNames()).setUnlocalizedName("oreDirtyPowder").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreClearPowder = new ItemMetaData(EnumOreDrop.getNames()).setUnlocalizedName("oreClearPowder").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreNugget = new ItemMetaData(EnumNugget.getNames()).setUnlocalizedName("oreNugget").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemMetalDust = new ItemMetaData(EnumDust.getNames()).setUnlocalizedName("metalDust").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreCrystal = new ItemMetaData(EnumCrystal.getNames()).setUnlocalizedName("oreCrystal").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemIngot = new ItemMetaData(EnumMetal.getNames()).setUnlocalizedName("ingot").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemClearIngot = new ItemMetaData(EnumClearMetal.getNames()).setUnlocalizedName("clearIngot").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemStick = new ItemMetaData(EnumDust.getNames()).setUnlocalizedName("stick").setCreativeTab(NordTabs.tabMetallurgy);

        NordItems.itemTestHammer = new ItemDrill(1, Item.ToolMaterial.IRON,16,16).setUnlocalizedName("itemTestHammer").setCreativeTab(NordTabs.tabMetallurgy);
    }

    private static void createBlock() {
        NordBloks.metalBlock = new BlockMetal(EnumMetal.getNames()).setUnlocalizedName("metalBlock").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalClearBlock = new BlockClearMetal(EnumClearMetal.getNames()).setUnlocalizedName("metalClearBlock").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalOre = new BlockMetalOre(EnumOre.getNames()).setUnlocalizedName("metalOre").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalCrystal = new BlockCrystal(EnumCrystal.getNames()).setUnlocalizedName("metalCrystal").setCreativeTab(NordTabs.tabMetallurgy);

    }

    private static void registerItem() {
        RegisterHelper.registerMetadataItem(NordItems.itemOreDrop, "oreDrop", "oreDrop", EnumOreDrop.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemOreDirtyPowder, "oreDirtyPowder", "oreDirtyPowder", EnumOreDrop.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemOreClearPowder, "oreClearPowder", "oreClearPowder", EnumOreDrop.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemOreNugget, "oreNugget", "oreNugget", EnumNugget.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemMetalDust, "metalDust", "metalDust", EnumDust.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemOreCrystal, "oreCrystal", "oreCrystal", EnumCrystal.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemIngot, "ingot", "ingot", EnumMetal.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemClearIngot, "clearIngot", "clearIngot", EnumClearMetal.getNames());
        RegisterHelper.registerMetadataItem(NordItems.itemStick, "stick", "stick", EnumDust.getNames());
        RegisterHelper.registerSingleItem(NordItems.itemTestHammer, "itemTestHammer");
    }

    private static void registerBlock() {
        RegisterHelper.registerMetadataBlock(NordBloks.metalBlock, ItemBlockMetal.class, "metalBlock", "metalBlock", EnumMetal.getNames());
        RegisterHelper.registerMetadataBlock(NordBloks.metalOre, ItemBlockMetalOre.class, "metalOre", "metalOre", EnumOre.getNames());
        RegisterHelper.registerMetadataBlock(NordBloks.metalClearBlock, ItemBlockClearMetal.class, "metalClearBlock", "metalClearBlock", EnumClearMetal.getNames());
        RegisterHelper.registerMetadataBlock(NordBloks.metalCrystal, ItemBlockCrystall.class, "metalCrystal", "metalCrystal", EnumCrystal.getNames());

        for (int i = 0; i < EnumOre.getNames().length; i++) {
            RegisterHelper.registerOreInOverWithString(EnumOre.getNames()[i], NordBloks.metalOre.getStateFromMeta(i));
        }
    }

    private static void registerTileEntity() {
    }


}

