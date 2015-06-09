package ru.nord.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import ru.nord.client.lib.events.NordModelLoader;
import ru.nord.common.CommonProxy;
import ru.nord.common.lib.utils.Version;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {

        ModelLoaderRegistry.registerLoader(new NordModelLoader());
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
    public void registerBlockRender(Block block, int sub, String model) {
        ModelBakery.addVariantName(Item.getItemFromBlock(block),Version.MODID + ":" + model);
        ModelResourceLocation itemModelResourceLocation =
                new ModelResourceLocation(Version.MODID + ":" + model, "inventory");
        Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), sub, itemModelResourceLocation);
    }
}