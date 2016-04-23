package ru.nord_core.common.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegisterHelper2 {
    /**
     * Регистрируем одиночный блок
     *
     * @param block блок
     * @param name  имя регистрации
     */
    public static void registerBlock(Block block, String name) {
        registerBlock(block, new ItemBlock(block),name);
    }

    /**
     * Регистрируем предмет
     *
     * @param item предмет
     * @param name имя регистрации
     */
    public static void registerItem(Item item, String name) {
        item.setRegistryName(name);
        GameRegistry.register(item);
    }

    /**
     * Регистрируем блок с ItemBlock
     *
     * @param block     блок
     * @param itemBlock предмет для блока
     * @param name      имя регистрации
     */
    public static <ITEM_BLOCK extends ItemBlock> void registerBlock(Block block, ITEM_BLOCK itemBlock, String name) {
        block.setRegistryName(name);
        GameRegistry.register(block);
        itemBlock.setRegistryName(name);
        GameRegistry.register(itemBlock);
    }


}