package ru.nord.client.lib.models.iSmartBlockModel;
/*
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.client.model.Attributes;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.abstracts.BlockAbstractEnergyCable;

import java.util.LinkedList;
import java.util.List;

@SuppressWarnings("deprecation")
@SideOnly(Side.CLIENT)
public class SmartEnergyCable implements IFlexibleBakedModel, ISmartBlockModel {
    private IBakedModel modelCore;
    private IBakedModel modelUp;
    private IBakedModel modelDown;
    private IBakedModel modelWest;
    private IBakedModel modelEast;
    private IBakedModel modelNorth;
    private IBakedModel modelSouth;

    private boolean up = false;
    private boolean down = false;
    private boolean west = false;
    private boolean east = false;
    private boolean north = false;
    private boolean south = false;


    public SmartEnergyCable(
            IBakedModel i_modelCore, IBakedModel i_modelUp, IBakedModel i_modelDown,
            IBakedModel i_modelWest, IBakedModel i_modelEast,
            IBakedModel i_modelNorth, IBakedModel i_modelSouth
    ) {
        modelCore = i_modelCore;
        modelUp = i_modelUp;
        modelDown = i_modelDown;
        modelWest = i_modelWest;
        modelEast = i_modelEast;
        modelNorth = i_modelNorth;
        modelSouth = i_modelSouth;
    }

    @Override
    public boolean isAmbientOcclusion() {
        return modelCore.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d() {
        return modelCore.isGui3d();
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return modelCore.getParticleTexture();
    }

    @Override
    public ItemCameraTransforms getItemCameraTransforms() {
        return modelCore.getItemCameraTransforms();
    }

    // returns the vertex format for this model (each vertex in the list of quads can have a variety of information
    //   associated with it - for example, not just position and texture information, but also colour, world brightness,
    //   etc.  Just use DEFAULT_BAKED_FORMAT unless you really know what you're doing...
    @Override
    public VertexFormat getFormat() {
        return Attributes.DEFAULT_BAKED_FORMAT;
    }


    @Override
    public List<BakedQuad> getFaceQuads(EnumFacing side) {
        List<BakedQuad> allFaceQuads = new LinkedList<BakedQuad>();

        allFaceQuads.addAll(modelCore.getFaceQuads(side));
        if (up) {
            allFaceQuads.addAll(modelUp.getFaceQuads(side));
        }
        if (down) {
            allFaceQuads.addAll(modelDown.getFaceQuads(side));
        }
        if (west) {
            allFaceQuads.addAll(modelWest.getFaceQuads(side));
        }
        if (east) {
            allFaceQuads.addAll(modelEast.getFaceQuads(side));
        }
        if (north) {
            allFaceQuads.addAll(modelNorth.getFaceQuads(side));
        }
        if (south) {
            allFaceQuads.addAll(modelSouth.getFaceQuads(side));
        }
        return allFaceQuads;
    }

    @Override
    public List<BakedQuad> getGeneralQuads() {
        List<BakedQuad> allGeneralQuads = new LinkedList<BakedQuad>();
        allGeneralQuads.addAll(modelCore.getGeneralQuads());
        if (up) {
            allGeneralQuads.addAll(modelUp.getGeneralQuads());
        }
        if (down) {
            allGeneralQuads.addAll(modelDown.getGeneralQuads());
        }
        if (west) {
            allGeneralQuads.addAll(modelWest.getGeneralQuads());
        }
        if (east) {
            allGeneralQuads.addAll(modelEast.getGeneralQuads());
        }
        if (north) {
            allGeneralQuads.addAll(modelNorth.getGeneralQuads());
        }
        if (south) {
            allGeneralQuads.addAll(modelSouth.getGeneralQuads());
        }
        return allGeneralQuads;
    }

    @Override
    public IBakedModel handleBlockState(IBlockState iBlockState) {
        if (iBlockState instanceof IExtendedBlockState) {
            IExtendedBlockState iExtendedBlockState = (IExtendedBlockState) iBlockState;
            Boolean linkUp = iExtendedBlockState.getValue(BlockAbstractEnergyCable.LINK_UP);
            if (linkUp != null) {
                up = linkUp;
            }
            Boolean linkDown = iExtendedBlockState.getValue(BlockAbstractEnergyCable.LINK_DOWN);
            if (linkDown != null) {
                down = linkDown;
            }
            Boolean linkWest = iExtendedBlockState.getValue(BlockAbstractEnergyCable.LINK_WEST);
            if (linkWest != null) {
                west = linkWest;
            }
            Boolean linkEast = iExtendedBlockState.getValue(BlockAbstractEnergyCable.LINK_EAST);
            if (linkEast != null) {
                east = linkEast;
            }
            Boolean linkNorth = iExtendedBlockState.getValue(BlockAbstractEnergyCable.LINK_NORTH);
            if (linkNorth != null) {
                north = linkNorth;
            }
            Boolean linkSouth = iExtendedBlockState.getValue(BlockAbstractEnergyCable.LINK_SOUTH);
            if (linkSouth != null) {
                south = linkSouth;
            }
        }
        return this;
    }
}
*/
