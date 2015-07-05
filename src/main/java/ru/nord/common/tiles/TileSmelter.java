package ru.nord.common.tiles;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.common.lib.recipes.Interfaces.IAbstractRecipes;
import ru.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nord.common.lib.utils.Constants;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyMachinaDoubleInput;

public class TileSmelter extends TileAbstractEnergyMachinaDoubleInput {
    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.smelter";
    }


    @Override
    public IAbstractRecipes getRecipes() {
        return SmelterRecipes2I2O.INSTANCE();
    }

    @Override
    public int getWorkPacketEnergy() {
        return 4;
    }

    @Override
    public int getBurnPacketEnergy() {
        return Constants.SHARE_MULTIPLE;
    }

    @Override
    public int getMaxEnergy() {
        return 12800;
    }

    @Override
    public int getPacketEnergy() {
        return Constants.SHARE_MULTIPLE;
    }

    @Override
    public Container getContainer(EntityPlayer player) {
        return null;
    }

    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return null;
    }
}
