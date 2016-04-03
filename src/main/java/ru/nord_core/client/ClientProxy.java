package ru.nord_core.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord_core.client.event.RenderWorldLast;
import ru.nord_core.client.helpers.RegisterColorHelper;
import ru.nord_core.client.helpers.RegisterRenderHelper;
import ru.nord_core.common.CommonProxy;

import java.io.File;
import java.io.IOException;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRender(Item item, int sub, String name, String modid) {
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(modid + ":" + name, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, sub, itemModelResourceLocation);
        ModelBakery.registerItemVariants(item, itemModelResourceLocation);
    }

    @Override
    public void registerBlockRender(Block block, int sub, String model, String modid) {
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

        MinecraftForge.EVENT_BUS.register(new RenderWorldLast());
//        MinecraftForge.EVENT_BUS.register(new EventForgeClient());
    }

    @Override
    public RegisterRenderHelper registerModel() {
        return RegisterRenderHelper.INSTANCE;
    }

    @Override
    public RegisterColorHelper registerColor() {
        return RegisterColorHelper.INSTANCE;
    }

    @Override
    public File getDataDirectory() {
        final File file = Minecraft.getMinecraft().mcDataDir;
        try {
            return file.getCanonicalFile();
        } catch (final IOException e) {
            FMLLog.getLogger().debug("Could not canonize path!", e);
        }
        return file;
    }
}