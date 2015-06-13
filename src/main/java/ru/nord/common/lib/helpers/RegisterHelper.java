package ru.nord.common.lib.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.Nord;

public class RegisterHelper {
    public static void registerSingleBlock(Block block, String name) {
        registerSingleBlock(block, name, name);
    }

    public static void registerSingleBlock(Block block, String name, String model) {
        GameRegistry.registerBlock(block, name);
        Nord.proxy.registerBlockRender(block, 0, model);
    }

    public static void registerSingleItem(Item item, String name) {
        registerSingleItem(item, name, name);
    }

    public static void registerSingleItem(Item item, String name, String model) {
        GameRegistry.registerItem(item, name);
        Nord.proxy.registerItemRender(item, 0, model);
    }

    public static void registerMetadataBlock(Block block, Class<? extends ItemBlock> itemBlock,
                                             String name, String[] additionals) {
        registerMetadataBlock(block, itemBlock, name, name, additionals);
    }

    public static void registerMetadataBlock(Block block, Class<? extends ItemBlock> itemBlock,
                                             String name,int count) {
        registerMetadataBlock(block, itemBlock, name, name,count);
    }

    public static void registerMetadataBlock(
            Block block,
            Class<? extends ItemBlock> itemBlock,
            String name,
            String model,
            String[] additionals
    ) {
        GameRegistry.registerBlock(block, itemBlock, name);
        for (int i = 0; i < additionals.length; i++) {
            Nord.proxy.registerBlockRender(block, i, model + "." + additionals[i]);
        }

    }

    public static void registerMetadataBlock(
            Block block,
            Class<? extends ItemBlock> itemBlock,
            String name,
            String model,
            int count) {
        GameRegistry.registerBlock(block, itemBlock, name);
        for (int i = 0; i < count; i++) {
        Nord.proxy.registerBlockRender(block, i, model);
        }

    }
}