package ru.nord.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import ru.nord.Nord;
import ru.nord.common.CommonProxy;
import ru.nord.common.items.ItemBase;

public class ClientProxy extends CommonProxy {

        @Override
        public void registerRenderers() {

        }

        @Override
        public void init() {
        }
        @Override
        public void registerItemRender(Item item, int sub, String name){
            System.err.print(name);
            ModelResourceLocation itemModelResourceLocation =
                    new ModelResourceLocation(Nord.MODID+":"+name, "inventory");
            Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, sub, itemModelResourceLocation);

        }
}