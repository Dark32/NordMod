package ru.nord;

import ru.nord.common.blocks.wood.type1.*;
import ru.nord.common.blocks.wood.type2.BlockNordLeaves2;
import ru.nord.common.blocks.wood.type2.BlockNordLog2;
import ru.nord.common.blocks.wood.type2.BlockPlanks2;
import ru.nord.common.blocks.wood.type2.BlockSapling2;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.items.wood.type1.ItemBlockNordLeaves;
import ru.nord.common.items.wood.type1.ItemBlockNordLog;
import ru.nord.common.items.wood.type1.ItemBlockNordPlank;
import ru.nord.common.items.wood.type1.ItemBlockNordSapling;
import ru.nord.common.items.wood.type2.ItemBlockNordLeaves2;
import ru.nord.common.items.wood.type2.ItemBlockNordLog2;
import ru.nord.common.items.wood.type2.ItemBlockNordPlank2;
import ru.nord.common.items.wood.type2.ItemBlockNordSapling2;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord.common.utils.enums.EnumNordPlank2;

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
        NordBloks.nordPlank1 = new BlockPlanks(EnumNordPlank.getNames()).setUnlocalizedName("nordPlank").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling1 = new BlockSapling().setUnlocalizedName("nordSapling").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves1 = new BlockNordLeaves().setUnlocalizedName("nordLeaves").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLog1 = new BlockNordLog().setUnlocalizedName("nordLog").setCreativeTab(NordTabs.tabWood);

        NordBloks.nordPlank2 = new BlockPlanks2(EnumNordPlank2.getNames()).setUnlocalizedName("nordPlank").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling2 = new BlockSapling2().setUnlocalizedName("nordSapling").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves2 = new BlockNordLeaves2().setUnlocalizedName("nordLeaves").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLog2 = new BlockNordLog2().setUnlocalizedName("nordLog").setCreativeTab(NordTabs.tabWood);
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
                EnumNordPlank.getNames()
        );
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordSapling2,
                ItemBlockNordSapling2.class,
                "nordSapling2",
                "nordSapling2",
                EnumNordPlank.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLeaves2,
                ItemBlockNordLeaves2.class,
                "nordLeaves2",
                "nordLeaves2",
                EnumNordPlank.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLog2,
                ItemBlockNordLog2.class,
                "nordLog2",
                "nordLog2",
                EnumNordPlank.getNames()
        );

    }

    public static void registerItem() {

    }
}
