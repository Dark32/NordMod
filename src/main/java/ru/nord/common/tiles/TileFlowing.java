package ru.nord.common.tiles;


import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyMachina;

public class TileFlowing extends TileAbstractEnergyMachina {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.flowing";
    }

    @Override
    public int getMaxEnergy() {
        return 12800;
    }
    @Override
    public IRecipes1I2O getRecipes() {
        return FlowingRecipes1I2O.INSTANCE();
    }
    @Override
    public int getWorkPacketEnergy() {
        return 4;
    }
    @Override
    public int getBurnPacketEnergy() {
        return 16;
    }
    @Override
    public int getPacketEnergy() {
        return 16;
    }
}
