package ru.nord;

import ru.nord.common.blocks.wood.type1.BlockNordLeaves;
import ru.nord.common.blocks.wood.type1.BlockNordLog;
import ru.nord.common.blocks.wood.type1.BlockPlanks;
import ru.nord.common.blocks.wood.type1.BlockSapling;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.items.wood.type1.ItemBlockNordLeaves;
import ru.nord.common.items.wood.type1.ItemBlockNordLog;
import ru.nord.common.items.wood.type1.ItemBlockNordPlank;
import ru.nord.common.items.wood.type1.ItemBlockNordSapling;
import ru.nord.common.utils.enums.EnumNordPlank;

import java.util.Arrays;

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

    }

    public static void registerItem() {

    }
}
