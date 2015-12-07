package ru.nord;

import ru.nord.common.blocks.wood.type1.*;
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
import ru.nord.common.helpers.RegisterHelper;
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

public class NordWood {
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
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordPlank1,
                ItemBlockNordPlank.class,
                "nordPlank1",
                "nordPlank1",
                EnumNordPlank.getNames()
        );
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordSapling1,
                ItemBlockNordSapling.class,
                "nordSapling1",
                "nordSapling1",
                EnumNordPlank.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLeaves1,
                ItemBlockNordLeaves.class,
                "nordLeaves1",
                "nordLeaves1",
                EnumNordPlank.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLog1,
                ItemBlockNordLog.class,
                "nordLog1",
                "nordLog1",
                EnumNordPlank.getNames()
        );



        RegisterHelper.registerMetadataBlock(
                NordBloks.nordPlank2,
                ItemBlockNordPlank2.class,
                "nordPlank2",
                "nordPlank2",
                EnumNordPlank2.getNames()
        );
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordSapling2,
                ItemBlockNordSapling2.class,
                "nordSapling2",
                "nordSapling2",
                EnumNordPlank2.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLeaves2,
                ItemBlockNordLeaves2.class,
                "nordLeaves2",
                "nordLeaves2",
                EnumNordPlank2.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLog2,
                ItemBlockNordLog2.class,
                "nordLog2",
                "nordLog2",
                EnumNordPlank2.getNames()
        );




        RegisterHelper.registerMetadataBlock(
                NordBloks.nordPlank3,
                ItemBlockNordPlank3.class,
                "nordPlank3",
                "nordPlank3",
                EnumNordPlank3.getNames()
        );
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordSapling3,
                ItemBlockNordSapling3.class,
                "nordSapling3",
                "nordSapling3",
                EnumNordPlank3.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLeaves3,
                ItemBlockNordLeaves3.class,
                "nordLeaves3",
                "nordLeaves3",
                EnumNordPlank3.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLog3,
                ItemBlockNordLog3.class,
                "nordLog3",
                "nordLog3",
                EnumNordPlank3.getNames()
        );


        RegisterHelper.registerMetadataBlock(
                NordBloks.nordPlank4,
                ItemBlockNordPlank4.class,
                "nordPlank4",
                "nordPlank4",
                EnumNordPlank4.getNames()
        );
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordSapling4,
                ItemBlockNordSapling4.class,
                "nordSapling4",
                "nordSapling4",
                EnumNordPlank4.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLeaves4,
                ItemBlockNordLeaves4.class,
                "nordLeaves4",
                "nordLeaves4",
                EnumNordPlank4.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLog4,
                ItemBlockNordLog4.class,
                "nordLog4",
                "nordLog4",
                EnumNordPlank4.getNames()
        );


    }

    public static void registerItem() {

    }
}
