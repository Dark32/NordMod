package ru.nord.common;

import net.minecraft.entity.player.EntityPlayer;
import ru.nord_core.common.helpers.RegisterRenderHelper;

public interface IProxy {
	void preInit();

	void init();

	void postInit();

    void registerRenderers();

}
