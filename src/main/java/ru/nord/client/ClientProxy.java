package ru.nord.client;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import ru.nord.client.events.NordModelLoader;
import ru.nord.common.CommonProxy;
import ru.nord.common.utils.Version;
import ru.nord_core.NordCore;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {

        ModelLoaderRegistry.registerLoader(new NordModelLoader());
    }

}