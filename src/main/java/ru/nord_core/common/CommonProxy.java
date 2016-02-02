package ru.nord_core.common;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord_core.common.helpers.RegisterRenderHelper;

import java.io.File;
import java.io.IOException;

public class CommonProxy {

    public void registerItemRender(Item item, int sub, String name,String modid) {
    }

    public void registerBlockRender(Block block, int i, String name,String modid) {
    }

    public void registerEventHandlers() {}

    public RegisterRenderHelper registerModel(){
        return RegisterRenderHelper.INSTANCE;
    }

    public File getDataDirectory() {
        final File file = MinecraftServer.getServer().getFile(".");
        try {
            return file.getCanonicalFile();
        } catch (final IOException e) {
            FMLLog.getLogger().info("Could not canonize path!", e);
        }
        return file;
    }
}
