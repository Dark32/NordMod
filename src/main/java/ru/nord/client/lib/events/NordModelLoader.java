package ru.nord.client.lib.events;

import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.client.lib.models.iModel.ModelEnergyCable;

@SideOnly(Side.CLIENT)
public class NordModelLoader implements ICustomModelLoader {

    private IResourceManager resourceManager;
    public final String SMART_MODEL_RESOURCE_LOCATION = "models/block/builtin/";

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    @Override
    public boolean accepts(ResourceLocation resourceLocation) {
        return resourceLocation.getResourceDomain().equals("nord")
                && resourceLocation.getResourcePath().startsWith(SMART_MODEL_RESOURCE_LOCATION);
    }

    @Override
    public IModel loadModel(ResourceLocation l) {
        String resourcePath = l.getResourcePath();
        String name = l.getResourcePath().substring(SMART_MODEL_RESOURCE_LOCATION.length());
        if(name.equals("pipe_energy")) {
            return new ModelEnergyCable(resourceManager);
        }
        throw new RuntimeException("A builtin model '" + name + "' is not defined.");
    }
}
