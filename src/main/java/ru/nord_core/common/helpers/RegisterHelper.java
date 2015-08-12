package ru.nord_core.common.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord_core.NordCore;

public class RegisterHelper {


    public static void registerSingleBlock(Block block,
                                           String name,
                                           String modid) {
        registerSingleBlock(block, name, name, modid);
    }

    public static void registerSingleBlock(Block block,
                                           String name,
                                           String model,
                                           String modid) {
        GameRegistry.registerBlock(block, name);
        NordCore.proxy.registerBlockRender(block, 0, model, modid);
    }

    public static void registerSingleItem(Item item,
                                          String name,
                                          String modid) {
        registerSingleItem(item, name, name, modid);
    }

    public static void registerSingleItem(Item item,
                                          String name,
                                          String model,
                                          String modid) {
        GameRegistry.registerItem(item, name);
        NordCore.proxy.registerItemRender(item, 0, model, modid);
    }

    public static void registerMetadataBlock(Block block,
                                             Class<? extends ItemBlock> itemBlock,
                                             String name,
                                             String[] additionals,
                                             String modid) {
        registerMetadataBlock(block, itemBlock, name, name, additionals, modid);
    }

    public static void registerMetadataBlock(Block block,
                                             Class<? extends ItemBlock> itemBlock,
                                             String name,
                                             int count,
                                             String modid) {
        registerMetadataBlock(block, itemBlock, name, name, count, modid);
    }

    public static void registerMetadataBlock(
            Block block,
            Class<? extends ItemBlock> itemBlock,
            String name,
            String model,
            String[] additionals,
            String modid
    ) {
        GameRegistry.registerBlock(block, itemBlock, name);
        for (int i = 0; i < additionals.length; i++) {
            NordCore.proxy.registerBlockRender(block, i, model + "." + additionals[i], modid);
        }

    }

    public static void registerMetadataItem(
            Item itemVar,
            String name,
            String model,
            String[] additionals,
            String modid
    ) {
        GameRegistry.registerItem(itemVar, name);
        for (int i = 0; i < additionals.length; i++) {
            NordCore.proxy.registerItemRender(itemVar, i, model + "." + additionals[i], modid);
        }
    }

    public static void registerMetadataBlock(
            Block block,
            Class<? extends ItemBlock> itemBlock,
            String name,
            String model,
            int count,
            String modid) {
        GameRegistry.registerBlock(block, itemBlock, name);
        for (int i = 0; i < count; i++) {
            NordCore.proxy.registerBlockRender(block, i, model, modid);
        }

    }

}