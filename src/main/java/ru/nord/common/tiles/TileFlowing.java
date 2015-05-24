package ru.nord.common.tiles;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.client.gui.inventory.GuiFlowing;
import ru.nord.common.container.ContainerFlowing;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nord.common.lib.utils.Constants;
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
        return Constants.SHARE_MULTIPLE;
    }
    @Override
    public int getPacketEnergy() {
        return Constants.SHARE_MULTIPLE;
    }

    @Override
    public Container getContainer(EntityPlayer player) {
        return new ContainerFlowing(player.inventory,this);
    }

    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiFlowing(player,this);
    }
}