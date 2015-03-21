package ru.nord.common.lib.helpers;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.Nord;

public class RegisterHelper {
    public static void registerSingleBlock(Block block, String name) {
        GameRegistry.registerBlock(block, name);
        Nord.proxy.registerBlockRender(block, 0, name);
    }

    public static void registerSingleItem(Item item, String name) {
        GameRegistry.registerItem(item, name);
        Nord.proxy.registerItemRender(item, 0, name);
    }
}