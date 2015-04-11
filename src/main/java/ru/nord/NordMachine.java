package ru.nord;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.common.blocks.BlockFlowing;
import ru.nord.common.lib.helpers.RegisterHelper;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord.common.tiles.TileFlowing;

public class NordMachine {
    public static void preInit() {
        createBlock();
        createItem();
    }

    public static void init() {
        registerBlock();
        registerItem();
        registerTileEntity();
        addRecipe();
    }

    public static void postInit() {

    }

    private static void createItem() {
    }

    private static void createBlock() {
        NordBloks.flowingBlock = new BlockFlowing().setUnlocalizedName("flowingBlock").setCreativeTab(NordTabs.tabGeneral);

    }

    private static void registerItem() {

    }

    private static void registerBlock() {
        RegisterHelper.registerSingleBlock(NordBloks.flowingBlock, "flowingBlock");

    }

    private static void registerTileEntity() {
        GameRegistry.registerTileEntity(TileFlowing.class, "TileEntityFlowing");
    }


    private static void addRecipe() {
        GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock, 1), "xxx", "x x", "xxx", // TODO fix recipe
                'x', new ItemStack(Blocks.stone, 1));

        FlowingRecipes1I2O.addRecipe(
                new ItemStack(Item.getItemFromBlock(Blocks.stone), 1),
                new ItemStack(Item.getItemFromBlock(Blocks.dirt), 2),
                new ItemStack(Items.diamond, 5),
                60, 30, 5);
    }

}

