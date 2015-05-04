package ru.nord.common.tiles;



import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyAccumulator;

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
