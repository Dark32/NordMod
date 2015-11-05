package ru.nord_deco;

import ru.nord_deco.common.blocks.BlockChair;
import ru.nord_deco.common.blocks.BlockChairOther;
import ru.nord_deco.common.helpers.RegisterHelper;
import ru.nord_deco.common.items.ItemBlockChair;
import ru.nord_deco.common.items.ItemBlockChairOther;
import ru.nord_deco.common.utils.Version;
import ru.nord_deco.common.utils.enums.EnumChairOther;
import ru.nord_deco.common.utils.enums.EnumChairType;

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
        NordBloksDeco.chairWhite = new BlockChair(Version.MODID,EnumChairType.getNames()).setUnlocalizedName("chairWhite").setCreativeTab(NordTabsDeco.tabDecoration);
        NordBloksDeco.chairOther = new BlockChairOther(Version.MODID,EnumChairOther.getNames()).setUnlocalizedName("chairOther").setCreativeTab(NordTabsDeco.tabDecoration);
    }

    public static void createItem() {
    }

    public static void registerBlock() {
        RegisterHelper.registerMetadataBlock(
                NordBloksDeco.chairWhite,
                ItemBlockChair.class,
                "chairWhite",
                "chairWhite",
                EnumChairType.getNames()
        );

        RegisterHelper.registerMetadataBlock(
                NordBloksDeco.chairOther,
                ItemBlockChairOther.class,
                "chairOther",
                "chairOther",
                EnumChairOther.getNames()
        );
    }

    public static void registerItem() {
    }
}
