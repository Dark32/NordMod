package ru.nord_core.common;


import net.minecraft.block.Block;
import net.minecraft.item.Item;
import ru.nord_core.common.helpers.RegisterRenderHelper;

public class CommonProxy {

    public void registerItemRender(Item item, int sub, String name,String modid) {
    }

    public void registerBlockRender(Block block, int i, String name,String modid) {
    }

    public void registerEventHandlers() {}

    public RegisterRenderHelper registerModel(){
        return RegisterRenderHelper.INSTANCE;
    }
}
