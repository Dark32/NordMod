package ru.nord_deco.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import ru.nord_deco.common.CommonProxy;
//todo fix it
public class ClientProxy extends CommonProxy {
    //todo update
    @Override
    public void registerItemRender(Item item, int sub, String name,String modid) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + name, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, sub, itemModelResourceLocation);
        ModelBakery.addVariantName(item, modid+ ":" + name);
    }
    //todo update
    @Override
    public void registerBlockRender(Block block, int sub, String model,String modid) {
        ModelBakery.addVariantName(Item.getItemFromBlock(block),modid + ":" + model);
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + model, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), sub, itemModelResourceLocation);
    }

}