package ru.nord;

import ru.nord.common.blocks.*;
import ru.nord.common.items.ItemBase;
import ru.nord.common.items.ItemBlockDecoStone;
import ru.nord.common.items.ItemBlockFloorLamp;
import ru.nord.common.items.ItemBlockRoofLamp;
import ru.nord.common.lib.helpers.RegisterHelper;
import ru.nord.common.lib.utils.enums.EnumColors;
import ru.nord.common.lib.utils.enums.EnumState;
import ru.nord.common.lib.utils.enums.EnumStone;

public class NordDecoration {
    public static void preInit() {
        createBlock();
        createItem();
    }

    public static void init() {
        registerBlock();
        registerItem();
    }

    public static void postInit() {

    }

    public static void createBlock() {
        NordBloks.tutorialBlock = new BlockBase().setUnlocalizedName("tutorialBlock").setCreativeTab(NordTabs.tabGeneral);
        NordBloks.empireLamp1 = new BlockEmperiaLamp().setUnlocalizedName("empireLamp1").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.empireFloorLamp1 = new BlockEmpireFloorLamp().setUnlocalizedName("empireFloorLamp1").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.oilLamp = new BlockOilLamp().setUnlocalizedName("oilLamp").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.floorLamp1 = new BlockFloorLamp().setUnlocalizedName("floorLamp1").setCreativeTab(NordTabs.tabDecoration);

        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            NordBloks.decoStoneBlock[i] = new BlockDecoStone(color.getSecondColor()).
                    setUnlocalizedName("decoStoneBlock." + color.name()).setCreativeTab(NordTabs.tabDecoration);
        }
    }

    public static void createItem() {
        NordItems.tutorialItem = new ItemBase().setUnlocalizedName("itemBase").setCreativeTab(NordTabs.tabDecoration);
    }

    public static void registerBlock() {
        RegisterHelper.registerSingleBlock(NordBloks.tutorialBlock, "tutorialBlock");
        RegisterHelper.registerSingleBlock(NordBloks.oilLamp, "oilLamp");

        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            RegisterHelper.registerMetadataBlock(
                    NordBloks.decoStoneBlock[i],
                    ItemBlockDecoStone.class,
                    "decoStoneBlock." + color.name(),
                    "decoStoneBlock",
                    EnumStone.getNames()
            );
        }

        RegisterHelper.registerMetadataBlock(
                NordBloks.empireLamp1,
                ItemBlockRoofLamp.class,
                "empireLamp1", 16);
        RegisterHelper.registerMetadataBlock(
                NordBloks.empireFloorLamp1,
                ItemBlockRoofLamp.class,
                "empireFloorLamp1", 16);

        RegisterHelper.registerMetadataBlock(
                NordBloks.floorLamp1,
                ItemBlockFloorLamp.class,
                "floorLamp1",4);
    }

    public static void registerItem() {
        RegisterHelper.registerSingleItem(NordItems.tutorialItem, "itemBase");
    }
}
