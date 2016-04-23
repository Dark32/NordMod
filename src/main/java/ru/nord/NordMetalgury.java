package ru.nord;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.Item;
import ru.nord.common.blocks.BlockClearMetal;
import ru.nord.common.blocks.BlockCrystal;
import ru.nord.common.blocks.BlockMetal;
import ru.nord.common.blocks.BlockMetalOre;
import ru.nord.common.items.ItemDrill;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata2;
import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.ItemMetaData;
import ru.nord_core.common.items.ItemMetaData2;
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
        //todo куда нибудь спрятать эти предикаты
        Predicate<ru.nord_core.common.utils.metal.EnumOre> drops = new Predicate<ru.nord_core.common.utils.metal.EnumOre>() {
            @Override
            public boolean apply(ru.nord_core.common.utils.metal.EnumOre _enum) {
                return _enum.getDrop() != null;
            }
        };

        NordItems.itemOreDrop = new ItemMetaData2<>(ru.nord_core.common.utils.metal.EnumOre.class, drops).setUnlocalizedName("oreDrop").setCreativeTab(NordTabs.tabMetallurgy);
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
        NordBloks.metalBlock = new BlockMetal().setUnlocalizedName("metalBlock").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalClearBlock = new BlockClearMetal().setUnlocalizedName("metalClearBlock").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalOre = new BlockMetalOre().setUnlocalizedName("metalOre").setCreativeTab(NordTabs.tabMetallurgy);
        NordBloks.metalCrystal = new BlockCrystal().setUnlocalizedName("metalCrystal").setCreativeTab(NordTabs.tabMetallurgy);

    }

    private static void registerItem() {
        RegisterHelper2.registerItem(NordItems.itemOreDrop, "oreDrop");
        RegisterHelper2.registerItem(NordItems.itemOreDirtyPowder, "oreDirtyPowder");
        RegisterHelper2.registerItem(NordItems.itemOreClearPowder, "oreClearPowder");
        RegisterHelper2.registerItem(NordItems.itemOreNugget, "oreNugget");
        RegisterHelper2.registerItem(NordItems.itemMetalDust, "metalDust");
        RegisterHelper2.registerItem(NordItems.itemOreCrystal, "oreCrystal");
        RegisterHelper2.registerItem(NordItems.itemIngot, "ingot");
        RegisterHelper2.registerItem(NordItems.itemClearIngot, "clearIngot");
        RegisterHelper2.registerItem(NordItems.itemStick, "stick");
//        RegisterHelper2.registerItem(NordItems.itemTestHammer, "itemTestHammer");
    }

    private static void registerBlock() {
        RegisterHelper2.registerBlock(NordBloks.metalBlock, new ItemBlockMetadata(NordBloks.metalBlock), "metalBlock");
        RegisterHelper2.registerBlock(NordBloks.metalOre, new ItemBlockMetadata(NordBloks.metalOre), "metalOre");
        RegisterHelper2.registerBlock(NordBloks.metalClearBlock, new ItemBlockMetadata(NordBloks.metalClearBlock), "metalClearBlock");
        RegisterHelper2.registerBlock(NordBloks.metalCrystal, new ItemBlockMetadata(NordBloks.metalCrystal), "metalCrystal");

//        for (int i = 0; i < EnumOre.getNames().length; i++) {
//            RegisterHelper.registerOreInOverWithString(EnumOre.getNames()[i], NordBloks.metalOre.getStateFromMeta(i));
//        }
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

        ImmutableList list = ((IVariantMetadata2) NordBloks.metalBlock).getAllowedValues();
        for (Object v : list) {
            ru.nord_core.common.utils.metal.EnumMetal enumType = (ru.nord_core.common.utils.metal.EnumMetal) v;
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalBlock, enumType.getReIndexMetadata(list), "type=" + enumType.getName());
        }
        list = ((IVariantMetadata2) NordBloks.metalClearBlock).getAllowedValues();
        for (Object v : list) {
            ru.nord_core.common.utils.metal.EnumMetal enumType = (ru.nord_core.common.utils.metal.EnumMetal) v;
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalClearBlock, enumType.getReIndexMetadata(list), "type=" + enumType.getName());
        }
        list = ((IVariantMetadata2) NordBloks.metalOre).getAllowedValues();
        for (Object v : list) {
            ru.nord_core.common.utils.metal.EnumOre enumType = (ru.nord_core.common.utils.metal.EnumOre) v;
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalOre, enumType.getReIndexMetadata(list), "type=" + enumType.getName());
        }

        list = ((IVariantMetadata2) NordBloks.metalCrystal).getAllowedValues();
        for (Object v : list) {
            ru.nord_core.common.utils.metal.EnumOre enumType = (ru.nord_core.common.utils.metal.EnumOre) v;
            modelRegister().registerBlockItemModelForMeta(NordBloks.metalCrystal, enumType.getReIndexMetadata(list), "type=" + enumType.getName());
        }
    }

}

