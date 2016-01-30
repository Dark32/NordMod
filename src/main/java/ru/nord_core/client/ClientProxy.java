package ru.nord_core.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import ru.nord_core.client.event.RenderWorldLast;
import ru.nord_core.common.CommonProxy;
import ru.nord_core.client.helpers.RegisterRenderHelper;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRender(Item item, int sub, String name,String modid) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + name, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, sub, itemModelResourceLocation);
        ModelBakery.registerItemVariants(item, itemModelResourceLocation);
    }

    @Override
    public void registerBlockRender(Block block, int sub, String model,String modid) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + model, "inventory");
        ModelBakery.registerItemVariants(Item.getItemFromBlock(block), itemModelResourceLocation);
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), sub, itemModelResourceLocation);
    }
    @Override
    public void registerEventHandlers() {
        super.registerEventHandlers();
//        DrawBlockHighlight eventHandler = new DrawBlockHighlight();
//        FMLCommonHandler.instance().bus().register(eventHandler);
//        MinecraftForge.EVENT_BUS.register(eventHandler);//

        RenderWorldLast eventHandler = new RenderWorldLast();
        MinecraftForge.EVENT_BUS.register(eventHandler);
    }
    public RegisterRenderHelper registerModel(){
        return RegisterRenderHelper.INSTANCE;
    }
}