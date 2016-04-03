package ru.nord;

import ru.nord.common.blocks.*;
import ru.nord.common.items.ItemBlockWithRenderColorFromBlock;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumEmpGlass;
import ru.nord.common.utils.enums.EnumGlowstoneDust;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord.common.utils.enums.EnumWhiteStone;
import ru.nord_core.common.helpers.RegisterColorHelper;
import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.ItemBase;
import ru.nord_core.common.items.ItemGlowstoneDust;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumColors;
import ru.nord_core.common.utils.enums.EnumOilLamp;
import ru.nord_core.common.utils.enums.EnumState;
import ru.nord_core.common.utils.enums.EnumStone;

import static ru.nord_core.common.helpers.RegisterRenderHelper.modelRegister;

public class NordDecoration {
    public static void preInit() {
        createBlock();
        createItem();
        registerBlock();
        registerItem();
        registerBlockModel();
        registerItemModel();
    }


    public static void init() {
        registerColor();
    }

    public static void postInit() {

    }


    public static void createBlock() {
        NordBloks.empireFloorLamp1 = new BlockEmpireFloorLamp().setUnlocalizedName("empireFloorLamp1").setCreativeTab(NordTabs.tabLamp);
        NordBloks.empireLamp1 = new BlockEmperiaLamp().setUnlocalizedName("empireLamp1").setCreativeTab(NordTabs.tabLamp);
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
        RegisterHelper2.registerMetadataBlock(NordBloks.oilLamp, ItemBlockMetadata.class, "oilLamp");
        RegisterHelper2.registerMetadataBlock(NordBloks.empireLamp1, ItemBlockWithRenderColorFromBlock.class, "empireLamp1");
        RegisterHelper2.registerMetadataBlock(NordBloks.empireFloorLamp1, ItemBlockWithRenderColorFromBlock.class, "empireFloorLamp1");
        RegisterHelper2.registerMetadataBlock(NordBloks.floorLamp1, ItemBlockMetadata.class, "floorLamp1");
        RegisterHelper2.registerMetadataBlock(NordBloks.empireDecoration1, ItemBlockMetadata.class, "empPaper");
        RegisterHelper2.registerMetadataBlock(NordBloks.empireDecorationPanel1, ItemBlockMetadata.class, "empPaperPanel");
        RegisterHelper2.registerMetadataBlock(NordBloks.empireGlass, ItemBlockMetadata.class, "empGlass");
        RegisterHelper2.registerMetadataBlock(NordBloks.whiteStone, ItemBlockMetadata.class, "whiteStone");
        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            RegisterHelper2.registerMetadataBlock(NordBloks.decoStoneBlock[i], ItemBlockWithRenderColorFromBlock.class, "decoStoneBlock." + color.name());
        }
    }

    public static void registerBlockModel() {

        for (EnumOilLamp enumType : EnumOilLamp.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.oilLamp, enumType.getMetadata(), "type=" + enumType.getName());
        }

        for (EnumColors enumType : EnumColors.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.empireLamp1, enumType.getMetadata(), "type=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.empireFloorLamp1, enumType.getMetadata(), "type=" + enumType.getName());
        }

        for (EnumState enumType : EnumState.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.floorLamp1, enumType.getMetadata(), "type=" + enumType.getName());
        }
        for (EnumWhiteStone enumType : EnumWhiteStone.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.whiteStone, enumType.getMetadata(), "type=" + enumType.getName());
        }
        for (EnumEmpGlass enumType : EnumEmpGlass.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.empireGlass, enumType.getMetadata(), "type=" + enumType.getName());
        }
        for (EnumPaperEmp enumType : EnumPaperEmp.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.empireDecoration1, enumType.getMetadata(), "type=" + enumType.getName());
//todo это пока не работает. Заставить работать
//           modelRegister()().registerBlockItemModelForMeta(NordBloks.empireDecorationPanel1, enumType.getMetadata(), "north=true,east=false,south=true,west=false,type=" + enumType.getName());
        }
        for (int i = 0; i < 16; i++) {
            for (EnumStone enumType : EnumStone.values()) {
                modelRegister().registerBlockItemModelForMeta(NordBloks.decoStoneBlock[i], enumType.getMetadata(), "type=" + enumType.getName());
            }
        }
    }

    public static void registerItem() {
        RegisterHelper2.registerMetadataItem(NordItems.itemGlowstoneDust, "itemGlowstoneDust");
    }

    private static void registerItemModel() {
        for (EnumGlowstoneDust enumType : EnumGlowstoneDust.values()) {
            modelRegister().registerItemModelForMeta(NordItems.itemGlowstoneDust, enumType.getMetadata(), "type=" + enumType.getName());
        }
    }

    private static void registerColor(){
        for (int i = 0; i < 16; i++) {
            RegisterColorHelper.registerColor().registerBlockColorHandler(NordBloks.decoStoneBlock[i]);
            RegisterColorHelper.registerColor().registerItemColorHandler(NordBloks.decoStoneBlock[i]);
        }
        RegisterColorHelper.registerColor().registerBlockColorHandler(NordBloks.empireFloorLamp1);
        RegisterColorHelper.registerColor().registerItemColorHandler(NordBloks.empireFloorLamp1);
        RegisterColorHelper.registerColor().registerBlockColorHandler(NordBloks.empireLamp1);
        RegisterColorHelper.registerColor().registerItemColorHandler(NordBloks.empireLamp1);

    }
}
