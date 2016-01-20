package ru.nord_core.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import ru.nord_core.client.event.DrawBlockHighlight;
import ru.nord_core.client.event.RenderWorldLast;
import ru.nord_core.common.CommonProxy;

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
    //todo update
    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
//        DrawBlockHighlight eventHandler = new DrawBlockHighlight();
//        FMLCommonHandler.instance().bus().register(eventHandler);
//        MinecraftForge.EVENT_BUS.register(eventHandler);//

        RenderWorldLast eventHandler = new RenderWorldLast();
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }
}