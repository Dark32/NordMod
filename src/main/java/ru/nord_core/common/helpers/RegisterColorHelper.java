package ru.nord_core.common.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.Item;
import ru.nord_core.NordCore;

/**
 * Created by andrew on 03.04.16.
 */

/**
 * Заглушка. На серверной стороне нет необходимости регистрировать цвет
 * {@link ru.nord_core.client.helpers.RegisterColorHelper} для просмотра реализации
 */
public class RegisterColorHelper {
    public static final RegisterColorHelper INSTANCE = new RegisterColorHelper();

    public static RegisterColorHelper registerColor() {
        return NordCore.proxy.registerColor();
    }

    public void registerBlockColorHandler(Block block) {
    }
    public void registerItemColorHandler(Block block){
    }
    public void registerItemColorHandler(Item item){
    }
}
