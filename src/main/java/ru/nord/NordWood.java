package ru.nord;

import ru.nord.common.blocks.wood.type1.BlockNordLeaves;
import ru.nord.common.blocks.wood.type1.BlockNordLog;
import ru.nord.common.blocks.wood.type1.BlockPlanks;
import ru.nord.common.blocks.wood.type1.BlockSapling;
import ru.nord.common.blocks.wood.type2.BlockNordLeaves2;
import ru.nord.common.blocks.wood.type2.BlockNordLog2;
import ru.nord.common.blocks.wood.type2.BlockPlanks2;
import ru.nord.common.blocks.wood.type2.BlockSapling2;
import ru.nord.common.blocks.wood.type3.BlockNordLeaves3;
import ru.nord.common.blocks.wood.type3.BlockNordLog3;
import ru.nord.common.blocks.wood.type3.BlockPlanks3;
import ru.nord.common.blocks.wood.type3.BlockSapling3;
import ru.nord.common.blocks.wood.type4.BlockNordLeaves4;
import ru.nord.common.blocks.wood.type4.BlockNordLog4;
import ru.nord.common.blocks.wood.type4.BlockPlanks4;
import ru.nord.common.blocks.wood.type4.BlockSapling4;
import ru.nord.common.items.wood.type1.ItemBlockNordLeaves;
import ru.nord.common.items.wood.type1.ItemBlockNordLog;
import ru.nord.common.items.wood.type1.ItemBlockNordPlank;
import ru.nord.common.items.wood.type1.ItemBlockNordSapling;
import ru.nord.common.items.wood.type2.ItemBlockNordLeaves2;
import ru.nord.common.items.wood.type2.ItemBlockNordLog2;
import ru.nord.common.items.wood.type2.ItemBlockNordPlank2;
import ru.nord.common.items.wood.type2.ItemBlockNordSapling2;
import ru.nord.common.items.wood.type3.ItemBlockNordLeaves3;
import ru.nord.common.items.wood.type3.ItemBlockNordLog3;
import ru.nord.common.items.wood.type3.ItemBlockNordPlank3;
import ru.nord.common.items.wood.type3.ItemBlockNordSapling3;
import ru.nord.common.items.wood.type4.ItemBlockNordLeaves4;
import ru.nord.common.items.wood.type4.ItemBlockNordLog4;
import ru.nord.common.items.wood.type4.ItemBlockNordPlank4;
import ru.nord.common.items.wood.type4.ItemBlockNordSapling4;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.helpers.RegisterHelper2;

import static ru.nord_core.common.helpers.RegisterRenderHelper.modelRegister;

public class NordWood {
    public static void preInit() {
        createBlock();
        createItem();
        registerBlock();
        registerItem();
        registerBlockModel();
        registerItemModel();
    }

    public static void init() {

    }

    public static void postInit() {

    }

    public static void createBlock() {
        NordBloks.nordPlank1 = new BlockPlanks(EnumNordPlank.getNames()).setUnlocalizedName("nordPlank1").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling1 = new BlockSapling().setUnlocalizedName("nordSapling1").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves1 = new BlockNordLeaves().setUnlocalizedName("nordLeaves1").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLog1 = new BlockNordLog().setUnlocalizedName("nordLog1").setCreativeTab(NordTabs.tabWood);

        NordBloks.nordPlank2 = new BlockPlanks2(EnumNordPlank2.getNames()).setUnlocalizedName("nordPlank2").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling2 = new BlockSapling2().setUnlocalizedName("nordSapling2").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves2 = new BlockNordLeaves2().setUnlocalizedName("nordLeaves2").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLog2 = new BlockNordLog2().setUnlocalizedName("nordLog2").setCreativeTab(NordTabs.tabWood);

        NordBloks.nordPlank3 = new BlockPlanks3(EnumNordPlank3.getNames()).setUnlocalizedName("nordPlank3").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling3 = new BlockSapling3().setUnlocalizedName("nordSapling3").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves3 = new BlockNordLeaves3().setUnlocalizedName("nordLeaves3").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLog3 = new BlockNordLog3().setUnlocalizedName("nordLog3").setCreativeTab(NordTabs.tabWood);

        NordBloks.nordPlank4 = new BlockPlanks4(EnumNordPlank4.getNames()).setUnlocalizedName("nordPlank4").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling4 = new BlockSapling4().setUnlocalizedName("nordSapling4").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves4 = new BlockNordLeaves4().setUnlocalizedName("nordLeaves4").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLog4 = new BlockNordLog4().setUnlocalizedName("nordLog4").setCreativeTab(NordTabs.tabWood);
    }

    public static void createItem() {
    }


    public static void registerBlock() {
        RegisterHelper2.registerBlock(NordBloks.nordPlank1, new  ItemBlockNordPlank(NordBloks.nordPlank1), "nordPlank1");
        RegisterHelper2.registerBlock(NordBloks.nordSapling1, new  ItemBlockNordSapling(NordBloks.nordSapling1), "nordSapling1");
        RegisterHelper2.registerBlock(NordBloks.nordLeaves1, new  ItemBlockNordLeaves(NordBloks.nordLeaves1), "nordLeaves1");
        RegisterHelper2.registerBlock(NordBloks.nordLog1, new  ItemBlockNordLog(NordBloks.nordLog1), "nordLog1");

        RegisterHelper2.registerBlock(NordBloks.nordPlank2, new  ItemBlockNordPlank2(NordBloks.nordPlank2), "nordPlank2");
        RegisterHelper2.registerBlock(NordBloks.nordSapling2, new  ItemBlockNordSapling2(NordBloks.nordSapling2), "nordSapling2");
        RegisterHelper2.registerBlock(NordBloks.nordLeaves2, new  ItemBlockNordLeaves2(NordBloks.nordLeaves2), "nordLeaves2");
        RegisterHelper2.registerBlock(NordBloks.nordLog2, new  ItemBlockNordLog2(NordBloks.nordLog2), "nordLog2");

        RegisterHelper2.registerBlock(NordBloks.nordPlank3, new  ItemBlockNordPlank3(NordBloks.nordPlank3), "nordPlank3");
        RegisterHelper2.registerBlock(NordBloks.nordSapling3, new  ItemBlockNordSapling3(NordBloks.nordSapling3), "nordSapling3");
        RegisterHelper2.registerBlock(NordBloks.nordLeaves3, new  ItemBlockNordLeaves3(NordBloks.nordLeaves3), "nordLeaves3");
        RegisterHelper2.registerBlock(NordBloks.nordLog3, new  ItemBlockNordLog3(NordBloks.nordLog3), "nordLog3");

        RegisterHelper2.registerBlock(NordBloks.nordPlank4, new  ItemBlockNordPlank4(NordBloks.nordPlank4), "nordPlank4");
        RegisterHelper2.registerBlock(NordBloks.nordSapling4, new  ItemBlockNordSapling4(NordBloks.nordSapling4), "nordSapling4");
        RegisterHelper2.registerBlock(NordBloks.nordLeaves4, new  ItemBlockNordLeaves4(NordBloks.nordLeaves4), "nordLeaves4");
        RegisterHelper2.registerBlock(NordBloks.nordLog4, new  ItemBlockNordLog4(NordBloks.nordLog4), "nordLog4");
    }

    public static void registerItem() {

    }

    private static void registerItemModel() {

    }

    private static void registerBlockModel() {
        for (EnumNordPlank enumType : EnumNordPlank.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordPlank1, enumType.getMetadata(), "variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordSapling1, enumType.getMetadata(), "stage=1,type=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLeaves1, enumType.getMetadata(), "check_decay=false,decayable=false,variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLog1, enumType.getMetadata(), "axis=y,variant=" + enumType.getName());
        }
        for (EnumNordPlank2 enumType : EnumNordPlank2.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordPlank2, enumType.getMetadata(), "variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordSapling2, enumType.getMetadata(), "stage=0,type=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLeaves2, enumType.getMetadata(), "check_decay=false,decayable=false,variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLog2, enumType.getMetadata(), "axis=y,variant=" + enumType.getName());
        }
        for (EnumNordPlank3 enumType : EnumNordPlank3.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordPlank3, enumType.getMetadata(), "variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordSapling3, enumType.getMetadata(), "stage=0,type=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLeaves3, enumType.getMetadata(), "check_decay=false,decayable=false,variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLog3, enumType.getMetadata(), "axis=y,variant=" + enumType.getName());
        }
        for (EnumNordPlank4 enumType : EnumNordPlank4.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordPlank4, enumType.getMetadata(), "variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordSapling4, enumType.getMetadata(), "stage=0,type=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLeaves4, enumType.getMetadata(), "check_decay=false,decayable=false,variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloks.nordLog4, enumType.getMetadata(), "axis=y,variant=" + enumType.getName());
        }
    }
}
