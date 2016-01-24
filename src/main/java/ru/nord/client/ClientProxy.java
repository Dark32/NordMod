package ru.nord.client;


import net.minecraftforge.client.model.ModelLoaderRegistry;
import ru.nord.client.events.NordModelLoader;
import ru.nord.common.CommonProxy;
//import ru.nord_core.client.helpers.ModModelManager;
import ru.nord_core.client.helpers.RegisterRenderHelper;

public class ClientProxy extends CommonProxy {

    @Override
    public void registerRenderers() {

        ModelLoaderRegistry.registerLoader(new NordModelLoader());

    }

    @Override
    public void preInit() {
//        ModModelManager.INSTANCE.registerAllModels();
    }

    @Override
    public RegisterRenderHelper registerModel() {
        return RegisterRenderHelper.INSTANCE;
    }
}