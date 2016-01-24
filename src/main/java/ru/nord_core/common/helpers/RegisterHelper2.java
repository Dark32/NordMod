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
    public static void registerSingleBlock(Block block, String name) {
        GameRegistry.registerBlock(block, name);
    }

    /**
     * Регистрируем блок с ItemBlock и с дополнительными аргументами
     *
     * @param block        блок
     * @param name         имя регистрации
     * @param itemCtorArgs аргумент для ItemBlock
     */
    public static void registerSingleBlockWithArgumenedItemBlock(Block block, String name, Object... itemCtorArgs) {
        GameRegistry.registerBlock(block, ItemBlock.class, name, itemCtorArgs);
    }

    /**
     * Регистрируем предмет
     *
     * @param item предмет
     * @param name имя регистрации
     */
    public static void registerSingleItem(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }

    /**
     * Регистрируем блок с ItemBlock
     *
     * @param block     блок
     * @param itemBlock предмет для блока
     * @param name      имя регистрации
     */
    public static void registerMetadataBlock(Block block, Class<? extends ItemBlock> itemBlock, String name) {
        GameRegistry.registerBlock(block, itemBlock, name);
    }

    /**
     * Регистрируем блок с ItemBlock и с дополнительными аргументами
     *
     * @param block        блок
     * @param itemBlock    предмет для блока
     * @param name         имя регистрации
     * @param itemCtorArgs аргумент для ItemBlock
     */
    public static void registerMetadataBlockWithArgumenedItemBlock(
            Block block, Class<? extends ItemBlock> itemBlock, String name, Object... itemCtorArgs) {
        GameRegistry.registerBlock(block, itemBlock, name, itemCtorArgs);

    }

    /**
     * Регистрируем предмет с методатой
     *
     * @param item предмет
     * @param name имя регистрации
     */
    public static void registerMetadataItem(Item item, String name) {
        GameRegistry.registerItem(item, name);
    }


}