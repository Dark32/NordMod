package ru.nord.common;

import net.minecraft.item.Item;

public class CommonProxy {

        // Client stuff
        public void registerRenderers() {
                // Nothing here as the server doesn't render graphics or entities!
        }

        public void init() {
        }

    public void registerItemRender(Item item, int sub, String name){}
}