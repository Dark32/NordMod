package ru.nord_deco;

import net.minecraft.block.material.Material;
import ru.nord_deco.common.blocks.BlockChair;
import ru.nord_deco.common.blocks.BlockChairOther;
import ru.nord_deco.common.blocks.BlockHalfSlabTile;
import ru.nord_deco.common.helpers.RegisterHelper;
import ru.nord_deco.common.items.ItemBlockChair;
import ru.nord_deco.common.items.ItemBlockChairOther;
import ru.nord_deco.common.items.ItemBlockSlabTile1;
import ru.nord_deco.common.utils.Version;
import ru.nord_deco.common.utils.enums.EnumChairOther;
import ru.nord_deco.common.utils.enums.EnumChairType;
import ru.nord_deco.common.utils.enums.EnumTileType1;

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
        NordBloksDeco.halfSlabTile1 = new BlockHalfSlabTile(Material.rock,false).setUnlocalizedName("halfSlabTile1").setCreativeTab(NordTabsDeco.tabDecoration);
        NordBloksDeco.doubleHalfSlabTile1 = new BlockHalfSlabTile(Material.rock,true).setUnlocalizedName("doubleHalfSlabTile1").setCreativeTab(NordTabsDeco.tabDecoration);
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
        RegisterHelper.registerMetadataBlockWithArgumenedItemBlock(
                NordBloksDeco.halfSlabTile1,
                ItemBlockSlabTile1.class,
                "halfSlabTile1",
                "halfSlabTile1",
                EnumTileType1.getNames(),
                NordBloksDeco.halfSlabTile1,
                NordBloksDeco.doubleHalfSlabTile1
        );
    }

    public static void registerItem() {
    }
}
