package ru.nord;

import net.minecraft.item.Item;
import ru.nord.common.blocks.BlockClearMetal;
import ru.nord.common.blocks.BlockCrystal;
import ru.nord.common.blocks.BlockMetal;
import ru.nord.common.blocks.BlockMetalOre;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.items.ItemDrill;
import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.ItemMetaData;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.*;

import static ru.nord_core.common.helpers.RegisterRenderHelper.modelRegister;

public class NordMetalgury {
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
        NordItems.itemOreDrop = new ItemMetaData(EnumOreDrop.getNames()).setUnlocalizedName("oreDrop").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreDirtyPowder = new ItemMetaData(EnumOreDrop.getNames()).setUnlocalizedName("oreDirtyPowder").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreClearPowder = new ItemMetaData(EnumOreDrop.getNames()).setUnlocalizedName("oreClearPowder").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreNugget = new ItemMetaData(EnumNugget.getNames()).setUnlocalizedName("oreNugget").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemMetalDust = new ItemMetaData(EnumDust.getNames()).setUnlocalizedName("metalDust").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemOreCrystal = new ItemMetaData(EnumCrystal.getNames()).setUnlocalizedName("oreCrystal").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemIngot = new ItemMetaData(EnumMetal.getNames()).setUnlocalizedName("ingot").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemClearIngot = new ItemMetaData(EnumClearMetal.getNames()).setUnlocalizedName("clearIngot").setCreativeTab(NordTabs.tabMetallurgy);
        NordItems.itemStick = new ItemMetaData(EnumDust.getNames()).setUnlocalizedName("stick").setCreativeTab(NordTabs.tabMetallurgy);

        NordItems.itemTestHammer = new ItemDrill(1, Item.ToolMaterial.IRON, 16, 16).setUnlocalizedName("itemTestHammer").setCreativeTab(NordTabs.tabMetallurgy);
    }

    private static void createBlock() {
        NordBloks.metalBlock = new BlockMetal(EnumMetal.getNames()).setUnlocalizedName("metalBlock").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalClearBlock = new BlockClearMetal(EnumClearMetal.getNames()).setUnlocalizedName("metalClearBlock").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalOre = new BlockMetalOre(EnumOre.getNames()).setUnlocalizedName("metalOre").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalCrystal = new BlockCrystal(EnumCrystal.getNames()).setUnlocalizedName("metalCrystal").setCreativeTab(NordTabs.tabMetallurgy);

    }

    private static void registerItem() {
        RegisterHelper2.registerMetadataItem(NordItems.itemOreDrop, "oreDrop");
        RegisterHelper2.registerMetadataItem(NordItems.itemOreDirtyPowder, "oreDirtyPowder");
        RegisterHelper2.registerMetadataItem(NordItems.itemOreClearPowder, "oreClearPowder");
        RegisterHelper2.registerMetadataItem(NordItems.itemOreNugget, "oreNugget");
        RegisterHelper2.registerMetadataItem(NordItems.itemMetalDust, "metalDust");
        RegisterHelper2.registerMetadataItem(NordItems.itemOreCrystal, "oreCrystal");
        RegisterHelper2.registerMetadataItem(NordItems.itemIngot, "ingot");
        RegisterHelper2.registerMetadataItem(NordItems.itemClearIngot, "clearIngot");
        RegisterHelper2.registerMetadataItem(NordItems.itemStick, "stick");
//        RegisterHelper2.registerSingleItem(NordItems.itemTestHammer, "itemTestHammer");
    }

    private static void registerBlock() {
        RegisterHelper2.registerMetadataBlock(NordBloks.metalBlock, ItemBlockMetadata.class, "metalBlock");
        RegisterHelper2.registerMetadataBlock(NordBloks.metalOre, ItemBlockMetadata.class, "metalOre");
        RegisterHelper2.registerMetadataBlock(NordBloks.metalClearBlock, ItemBlockMetadata.class, "metalClearBlock");
        RegisterHelper2.registerMetadataBlock(NordBloks.metalCrystal, ItemBlockMetadata.class, "metalCrystal");

        for (int i = 0; i < EnumOre.getNames().length; i++) {
            RegisterHelper.registerOreInOverWithString(EnumOre.getNames()[i], NordBloks.metalOre.getStateFromMeta(i));
        }
    }

    private static void registerTileEntity() {
    }

    private static void registerItemModel() {
        for (EnumOreDrop enumType : EnumOreDrop.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemOreDrop, enumType, "type=");
            modelRegister().registerItemModelForMeta(NordItems.itemOreDirtyPowder, enumType, "type=");
            modelRegister().registerItemModelForMeta(NordItems.itemOreClearPowder, enumType, "type=");
        }

        for (EnumNugget enumType : EnumNugget.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemOreNugget, enumType, "type=");
        }

        for (EnumCrystal enumType : EnumCrystal.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemOreCrystal, enumType, "type=");
        }

        for (EnumClearMetal enumType : EnumClearMetal.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemClearIngot, enumType, "type=");
        }
        for (EnumMetal enumType : EnumMetal.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemIngot, enumType, "type=");
        }
        for (EnumDust enumType : EnumDust.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemMetalDust, enumType, "type=");
            modelRegister().registerItemModelForMeta(NordItems.itemStick, enumType, "type=");
        }
    }

    private static void registerBlockModel() {
        for (EnumMetal enumType : EnumMetal.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalBlock, enumType.getMetadata(), "type=" + enumType.getName());
        }
        for (EnumClearMetal enumType : EnumClearMetal.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalClearBlock, enumType.getMetadata(), "type=" + enumType.getName());
        }
        for (EnumOre enumType : EnumOre.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalOre, enumType.getMetadata(), "type=" + enumType.getName());
        }
        for (EnumCrystal enumType : EnumCrystal.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalCrystal, enumType.getMetadata(), "type=" + enumType.getName());
        }
    }

}

