package ru.nord.client.lib.models.iModel;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IFlexibleBakedModel;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.IModelState;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.client.lib.models.iSmartBlockModel.SmartEnergyCable;

import java.util.Collection;

@SideOnly(Side.CLIENT)
public class ModelEnergyCable implements IModel {

    public static final ResourceLocation TEXTURE_SHEET = new ResourceLocation("nord:blocks/energy_cable");

    public static final ModelResourceLocation MODEL_CORE = new ModelResourceLocation("nord:block/cable/energy_cable_0");
    public static final ModelResourceLocation MODEL_UP = new ModelResourceLocation("nord:block/cable/energy_cable_u");
    public static final ModelResourceLocation MODEL_DOWN = new ModelResourceLocation("nord:block/cable/energy_cable_d");
    public static final ModelResourceLocation MODEL_NORTH = new ModelResourceLocation("nord:block/cable/energy_cable_n");
    public static final ModelResourceLocation MODEL_SOUTH = new ModelResourceLocation("nord:block/cable/energy_cable_s");
    public static final ModelResourceLocation MODEL_EAST = new ModelResourceLocation("nord:block/cable/energy_cable_e");
    public static final ModelResourceLocation MODEL_WEST  = new ModelResourceLocation("nord:block/cable/energy_cable_w");

    public ModelEnergyCable(IResourceManager resourceManager) {

    }

    @Override
    public Collection<ResourceLocation> getDependencies() {
        return ImmutableList.copyOf(new ResourceLocation[]{MODEL_CORE, MODEL_UP, MODEL_DOWN, MODEL_WEST, MODEL_EAST, MODEL_NORTH, MODEL_SOUTH});

    }

    @Override
    public Collection<ResourceLocation> getTextures() {
        return ImmutableList.copyOf(new ResourceLocation[]{TEXTURE_SHEET});
    }

    @Override
    public IFlexibleBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter) {
        IModel subComponent = ModelLoaderRegistry.getModel(MODEL_CORE);
        IBakedModel bakedModelCore = subComponent.bake(state, format, bakedTextureGetter);

        subComponent = ModelLoaderRegistry.getModel(MODEL_UP);
        IBakedModel bakedModelUp = subComponent.bake(state, format, bakedTextureGetter);

        subComponent = ModelLoaderRegistry.getModel(MODEL_DOWN);
        IBakedModel bakedModelDown = subComponent.bake(state, format, bakedTextureGetter);

        subComponent = ModelLoaderRegistry.getModel(MODEL_WEST);
        IBakedModel bakedModelWest = subComponent.bake(state, format, bakedTextureGetter);

        subComponent = ModelLoaderRegistry.getModel(MODEL_EAST);
        IBakedModel bakedModelEast = subComponent.bake(state, format, bakedTextureGetter);

        subComponent = ModelLoaderRegistry.getModel(MODEL_NORTH);
        IBakedModel bakedModelNorth = subComponent.bake(state, format, bakedTextureGetter);

        subComponent = ModelLoaderRegistry.getModel(MODEL_SOUTH);
        IBakedModel bakedModelSouth = subComponent.bake(state, format, bakedTextureGetter);


        return new SmartEnergyCable(bakedModelCore, bakedModelUp, bakedModelDown,
                bakedModelWest, bakedModelEast, bakedModelNorth, bakedModelSouth);

    }

    @Override
    public IModelState getDefaultState() {
        return null;
    }
}
