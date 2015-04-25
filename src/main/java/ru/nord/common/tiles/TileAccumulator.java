package ru.nord.common.tiles;


import com.sun.istack.internal.NotNull;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.client.gui.inventory.GuiFlowing;
import ru.nord.common.container.ContainerFlowing;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyAccumulator;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyMachina;

public class TileAccumulator extends TileAbstractEnergyAccumulator {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.flowing";
    }

    @Override
    public int getMaxEnergy() {
        return 12800;
    }

    @Override
    public int getPacketEnergy() {
        return 16;
    }

    @Override
    public Container getContainer(EntityPlayer player) {
        return null;
    }

    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return null;
    }

    @Override
    public int getLineOnEnergy() {
        return 4;
    }
}
