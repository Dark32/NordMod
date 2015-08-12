package ru.nord.client;

import net.minecraft.block.Block;
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

    @Override
    public void init() {
    }

    @Override
    public void registerItemRender(Item item, int sub, String name) {
        NordCore.proxy.registerItemRender(item, sub, name, Version.MODID);
    }

    @Override
    public void registerBlockRender(Block block, int sub, String model) {
        NordCore.proxy.registerBlockRender(block, sub, model, Version.MODID);
    }
}