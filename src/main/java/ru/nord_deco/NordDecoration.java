package ru.nord_deco;



import ru.nord_core.common.helpers.RegisterColorHelper;
import ru.nord_core.common.helpers.RegisterHelper2;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_deco.common.blocks.*;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractStairs;
import ru.nord_deco.common.items.ItemBlockSlabTile1;
import ru.nord_deco.common.utils.Version;
import ru.nord_deco.common.utils.enums.EnumAbomination;
import ru.nord_deco.common.utils.enums.EnumChairOther;
import ru.nord_deco.common.utils.enums.EnumChairType;
import ru.nord_deco.common.utils.enums.EnumTileType1;

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
        NordBloksDeco.chairWhite = new BlockChair(Version.MODID).setUnlocalizedName("chairWhite").setCreativeTab(NordTabsDeco.tabDecoration);
        NordBloksDeco.chairOther = new BlockChairOther(Version.MODID).setUnlocalizedName("chairOther").setCreativeTab(NordTabsDeco.tabDecoration);
        NordBloksDeco.halfSlabTile1 = new BlockHalfSlabTile().setUnlocalizedName("halfSlabTile1").setCreativeTab(NordTabsDeco.tabDecoration);
        NordBloksDeco.doubleHalfSlabTile1 = new BlockDoubleHalfSlabTile().setUnlocalizedName("doubleHalfSlabTile1").setCreativeTab(NordTabsDeco.tabDecoration);
        NordBloksDeco.stairs = new BlockAbstractStairs(NordBloksDeco.halfSlabTile1.getDefaultState()).setUnlocalizedName("stairs").setCreativeTab(NordTabsDeco.tabDecoration);

        NordBloksDeco.abomination = new BlockAbomination(EnumAbomination.getNames()).setUnlocalizedName("abomination").setCreativeTab(NordTabsDeco.tabDecoration);

    }

    public static void createItem() {
    }


    public static void registerBlock() {
        RegisterHelper2.registerMetadataBlock(
                NordBloksDeco.chairWhite,
                ItemBlockMetadata.class,
                "chairWhite"
        );

//        RegisterHelper2.registerMetadataBlock(
//                NordBloksDeco.chairOther,
//                ItemBlockMetadata.class,
//                "chairOther"
//        );
        RegisterHelper2.registerMetadataBlockWithArgumenedItemBlock(
                NordBloksDeco.halfSlabTile1,
                ItemBlockSlabTile1.class,
                "halfSlabTile1",
                NordBloksDeco.halfSlabTile1,
                NordBloksDeco.doubleHalfSlabTile1,
                false
        );

        RegisterHelper2.registerMetadataBlockWithArgumenedItemBlock(
                NordBloksDeco.doubleHalfSlabTile1,
                ItemBlockSlabTile1.class,
                "doubleHalfSlabTile1",
                NordBloksDeco.halfSlabTile1,
                NordBloksDeco.doubleHalfSlabTile1,
                true
        );
        RegisterHelper2.registerSingleBlock(NordBloksDeco.stairs, "stairs");
        RegisterHelper2.registerMetadataBlock(
                NordBloksDeco.abomination,
                ItemBlockMetadata.class,
                "abomination");

    }

    public static void registerItem() {
    }

    private static void registerItemModel() {

    }

    private static void registerBlockModel() {
        for (EnumChairType enumType : EnumChairType.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloksDeco.chairWhite, enumType.getMetadata(), "facing=west,type=" + enumType.getName());
        }
        for (EnumChairOther enumType : EnumChairOther.values()) {
//            modelRegister().registerBlockItemModelForMeta(NordBloksDeco.chairOther, enumType.getMetadata(), "facing=west,type=" + enumType.getName());
        }
        for (EnumTileType1 enumType : EnumTileType1.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloksDeco.halfSlabTile1, enumType.getMetadata(), "half=bottom,variant=" + enumType.getName());
            modelRegister().registerBlockItemModelForMeta(NordBloksDeco.doubleHalfSlabTile1, enumType.getMetadata(), "seamless=false,variant=" + enumType.getName());
        }
        for (EnumAbomination enumType : EnumAbomination.values()) {
            modelRegister().registerBlockItemModelForMeta(NordBloksDeco.abomination, enumType.getMetadata(), "type=" + enumType.getName());
        }
        modelRegister().registerBlockItemModelForMeta(NordBloksDeco.stairs,0, "facing=west,half=bottom,shape=straight");
    }
    private static void registerColor(){
        RegisterColorHelper.registerColor().registerBlockColorHandler(NordBloksDeco.halfSlabTile1);
        RegisterColorHelper.registerColor().registerItemColorHandler(NordBloksDeco.halfSlabTile1);
        RegisterColorHelper.registerColor().registerBlockColorHandler(NordBloksDeco.doubleHalfSlabTile1);
        RegisterColorHelper.registerColor().registerItemColorHandler(NordBloksDeco.doubleHalfSlabTile1);
    }
}
