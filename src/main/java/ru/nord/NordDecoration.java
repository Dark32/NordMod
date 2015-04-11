package ru.nord;

import ru.nord.common.blocks.BlockBase;
import ru.nord.common.blocks.BlockDecoStone;
import ru.nord.common.items.ItemBase;
import ru.nord.common.items.ItemBlockDecoStone;
import ru.nord.common.lib.helpers.RegisterHelper;
import ru.nord.common.lib.utils.enums.EnumColors;
import ru.nord.common.lib.utils.enums.EnumStone;

public class NordDecoration {
    public static void preInit(){
        createBlock();
        createItem();
    }
    public static void init(){
        registerBlock();
        registerItem();
    }
    public static void postInit(){

    }

    public static void createBlock(){
        NordBloks.tutorialBlock = new BlockBase().setUnlocalizedName("tutorialBlock").setCreativeTab(NordTabs.tabGeneral);
        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            NordBloks.decoStoneBlock[i] = new BlockDecoStone(color.getSecondColor()).
                    setUnlocalizedName("decoStoneBlock."+color.name()).setCreativeTab(NordTabs.tabGeneral);
        }
    }
    public static void createItem(){
        NordItems.tutorialItem = new ItemBase().setUnlocalizedName("itemBase").setCreativeTab(NordTabs.tabGeneral);
    }

    public static void registerBlock(){
        RegisterHelper.registerSingleBlock(NordBloks.tutorialBlock, "tutorialBlock");
        for (int i = 0; i < 16; i++) {
            EnumColors color = EnumColors.values()[i];
            RegisterHelper.registerMetadataBlock(
                    NordBloks.decoStoneBlock[i],
                    ItemBlockDecoStone.class,
                    "decoStoneBlock."+color.name(),
                    "decoStoneBlock",
                    EnumStone.getNames()
            );
        }
    }

    public static void registerItem(){
        RegisterHelper.registerSingleItem(NordItems.tutorialItem, "itemBase");
    }
}
