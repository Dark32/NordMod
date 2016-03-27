package ru.nord_core.client.helpers;

import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;

public class ItemMesh implements ItemMeshDefinition {
    final ModelResourceLocation fullModelLocation;

    public  ItemMesh(ModelResourceLocation fullModelLocation){
        this.fullModelLocation = fullModelLocation;
    }

    @Override
    public ModelResourceLocation getModelLocation(ItemStack stack) {
        return this.fullModelLocation;
    }
}
