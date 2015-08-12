package ru.nord_core.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import ru.nord_core.common.CommonProxy;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRender(Item item, int sub, String name,String modid) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + name, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, sub, itemModelResourceLocation);
        ModelBakery.addVariantName(item, modid+ ":" + name);
    }

    @Override
    public void registerBlockRender(Block block, int sub, String model,String modid) {
        System.err.print(Item.getItemFromBlock(block));
        ModelBakery.addVariantName(Item.getItemFromBlock(block),modid + ":" + model);
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + model, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), sub, itemModelResourceLocation);
    }

}