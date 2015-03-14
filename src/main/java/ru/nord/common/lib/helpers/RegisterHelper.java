package ru.nord.common.lib.helpers;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ru.nord.Nord;

public class RegisterHelper {
        public static void registerBlock(Block block, String name) {
                GameRegistry.registerBlock(block, Nord.MODID + name);
        }

        public static void registerSingleItem(Item item, String name) {
                GameRegistry.registerItem(item, name);
                Nord.proxy.registerItemRender(item,0,name);
        }
}