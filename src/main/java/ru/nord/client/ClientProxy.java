package ru.nord.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import ru.nord.common.CommonProxy;
import ru.nord.common.lib.utils.Version;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {

    }

    @Override
    public void init() {
    }


    public void registerItemRender(Item item, int sub, String name) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(Version.MODID + ":" + name, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, sub, itemModelResourceLocation);

    }

    @Override
    public void registerBlockRender(Block block, int sub, String name) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(Version.MODID + ":" + name, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), sub, itemModelResourceLocation);
    }
}