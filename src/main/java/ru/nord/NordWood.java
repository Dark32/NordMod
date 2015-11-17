package ru.nord;

import ru.nord.common.blocks.wood.BlockNordLeaves;
import ru.nord.common.blocks.wood.BlockNordLeaves1;
import ru.nord.common.blocks.wood.BlockPlanks;
import ru.nord.common.blocks.wood.BlockSapling;
import ru.nord.common.helpers.RegisterHelper;
import ru.nord.common.items.wood.ItemBlockNordLeaves;
import ru.nord.common.items.wood.ItemBlockNordPlank;
import ru.nord.common.items.wood.ItemBlockNordSapling;
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
        NordBloks.nordPlank = new BlockPlanks(EnumNordPlank.getNames()).setUnlocalizedName("nordPlank").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordSapling = new BlockSapling().setUnlocalizedName("nordSapling").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves0 = new BlockNordLeaves().setUnlocalizedName("nordLeaves").setCreativeTab(NordTabs.tabWood);
        NordBloks.nordLeaves1 = new BlockNordLeaves1().setUnlocalizedName("nordLeaves").setCreativeTab(NordTabs.tabWood);
    }

    public static void createItem() {
        }


    public static void registerBlock() {
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordPlank,
                ItemBlockNordPlank.class,
                "nordPlank",
                "nordPlank",
                EnumNordPlank.getNames()
        );
        RegisterHelper.registerMetadataBlock(
                NordBloks.nordSapling,
                ItemBlockNordSapling.class,
                "nordSapling",
                "nordSapling",
                EnumNordPlank.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloks.nordLeaves0,
                ItemBlockNordLeaves.class,
                "nordLeaves0",
                "nordLeaves0",
                Arrays.copyOfRange(EnumNordPlank.getNames(),0,4)
        );

//        RegisterHelper.registerMetadataBlock(
//                NordBloks.nordLeaves1,
//                ItemBlockNordLeaves.class,
//                "nordLeaves1",
//                "nordLeaves1",
//                Arrays.copyOfRange(EnumNordPlank.getNames(),4,8)
//        );
    }

    public static void registerItem() {

    }
}
