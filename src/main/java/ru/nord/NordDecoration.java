package ru.nord;

import ru.nord.common.blocks.*;
import ru.nord.common.items.*;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord_core.common.items.ItemBase;
import ru.nord_core.common.blocks.BlockBase;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord_core.common.utils.enums.EnumColors;
import ru.nord_core.common.utils.enums.EnumCrystal;
import ru.nord_core.common.utils.enums.EnumStone;

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
        NordBloks.tutorialBlock = new BlockBase(Version.MODID).setUnlocalizedName("tutorialBlock").setCreativeTab(NordTabs.tabGeneral);
        NordBloks.empireLamp1 = new BlockEmperiaLamp().setUnlocalizedName("empireLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.empireFloorLamp1 = new BlockEmpireFloorLamp().setUnlocalizedName("empireFloorLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.oilLamp = new BlockOilLamp().setUnlocalizedName("oilLamp").setCreativeTab(NordTabs.tabLamp);
        NordBloks.floorLamp1 = new BlockFloorLamp().setUnlocalizedName("floorLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.empireDecoration1 = new BlockEmpPaper(EnumPaperEmp.getNames()).setUnlocalizedName("empPaper").setCreativeTab(NordTabs.tabGeneral);

        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            NordBloks.decoStoneBlock[i] = new BlockDecoStone(color.getSecondColor()).
                    setUnlocalizedName("decoStoneBlock." + color.name()).setCreativeTab(NordTabs.tabDecoration);
        }
    }

    public static void createItem() {
        NordItems.tutorialItem = new ItemBase(Version.MODID).setUnlocalizedName("itemBase").setCreativeTab(NordTabs.tabDecoration);
    }

    public static void registerBlock() {
        RegisterHelper.registerSingleBlock(NordBloks.tutorialBlock, "tutorialBlock");
        RegisterHelper.registerSingleBlock(NordBloks.oilLamp, "oilLamp");
        RegisterHelper.registerMetadataBlock(NordBloks.empireDecoration1, ItemBlockEmpPaper.class, "empPaper", "empPaper", EnumPaperEmp.getNames());

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
    }
}
