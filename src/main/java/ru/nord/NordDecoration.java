package ru.nord;

import ru.nord.common.blocks.*;
import ru.nord.common.items.*;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumEmpGlass;
import ru.nord.common.utils.enums.EnumGlowstoneDust;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord.common.utils.enums.EnumWhiteStone;
import ru.nord_core.common.items.ItemBase;
import ru.nord_core.common.blocks.BlockBase;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord_core.common.items.ItemGlowstoneDust;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumColors;
import ru.nord_core.common.utils.enums.EnumOilLamp;
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
        NordBloks.empireLamp1 = new BlockEmperiaLamp().setUnlocalizedName("empireLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.empireFloorLamp1 = new BlockEmpireFloorLamp().setUnlocalizedName("empireFloorLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.oilLamp = new BlockOilLamp().setUnlocalizedName("oilLamp").setCreativeTab(NordTabs.tabLamp);
        NordBloks.floorLamp1 = new BlockFloorLamp().setUnlocalizedName("floorLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.empireDecoration1 = new BlockEmpPaper(EnumPaperEmp.getNames()).setUnlocalizedName("empPaper").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.empireDecorationPanel1 = new BlockEmpPaperPanel(EnumPaperEmp.getNames()).setUnlocalizedName("empPaperPanel").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.empireGlass = new BlockEmpGlass(EnumEmpGlass.getNames()).setUnlocalizedName("empGlass").setCreativeTab(NordTabs.tabDecoration);
        NordBloks.whiteStone = new BlockWhiteStone(EnumWhiteStone.getNames()).setUnlocalizedName("whiteStone").setCreativeTab(NordTabs.tabDecoration);

        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            NordBloks.decoStoneBlock[i] = new BlockDecoStone(color.getSecondColor()).
                    setUnlocalizedName("decoStoneBlock." + color.name()).setCreativeTab(NordTabs.tabColorStone);
        }
    }

    public static void createItem() {
        NordItems.tutorialItem = new ItemBase(Version.MODID).setUnlocalizedName("itemBase").setCreativeTab(NordTabs.tabColorStone);
        NordItems.itemGlowstoneDust = new ItemGlowstoneDust(Version.MODID).setUnlocalizedName("itemGlowstoneDust").setCreativeTab(NordTabs.tabOthers);
    }

    public static void registerBlock() {
        RegisterHelper.registerMetadataBlock(NordBloks.oilLamp,
                ItemBlockMetadata.class,
                "oilLamp",
                "oilLamp",
                EnumOilLamp.getNames());
        RegisterHelper.registerMetadataBlock(
                NordBloks.empireDecoration1,
                ItemBlockMetadata.class,
                "empPaper",
                "empPaper",
                EnumPaperEmp.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.whiteStone,
                ItemBlockMetadata.class,
                "whiteStone",
                "whiteStone",
                EnumWhiteStone.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.empireGlass,
                ItemBlockMetadata.class,
                "empGlass",
                "empGlass",
                EnumEmpGlass.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.empireDecorationPanel1,
                ItemBlockMetadata.class,
                "empPaperPanel",
                "empPaperPanel",
                EnumPaperEmp.getNames()
        );

        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            RegisterHelper.registerMetadataBlock(
                    NordBloks.decoStoneBlock[i],
                    ItemBlockWithRenderColorFromBlock.class,
                    "decoStoneBlock." + color.name(),
                    "decoStoneBlock",
                    EnumStone.getNames()
            );
        }

        RegisterHelper.registerMetadataBlock(
                NordBloks.empireLamp1,
                ItemBlockWithRenderColorFromBlock.class,
                "empireLamp1", 16);
        RegisterHelper.registerMetadataBlock(
                NordBloks.empireFloorLamp1,
                ItemBlockWithRenderColorFromBlock.class,
                "empireFloorLamp1", 16);

        RegisterHelper.registerMetadataBlock(
                NordBloks.floorLamp1,
                ItemBlockWithRenderColorFromBlock.class,
                "floorLamp1",4);
    }

    public static void registerItem() {
        RegisterHelper.registerMetadataItem(
                NordItems.itemGlowstoneDust,
                "itemGlowstoneDust",
                "itemGlowstoneDust",
                EnumGlowstoneDust.getNames()
        );
    }
}
