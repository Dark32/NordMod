package ru.nord.common.tiles;



import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.client.gui.inventory.GuiAccumulator;
import ru.nord.common.container.ContainerAccumulator;
import ru.nord.common.lib.utils.Constants;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyAccumulator;

public class TileAccumulator extends TileAbstractEnergyAccumulator {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.flowing";
    }

    @Override
    public int getMaxEnergy() {
        return 64000;
    }

    @Override
    public int getPacketEnergy() {
        return Constants.SHARE_MULTIPLE*4;
    }

    @Override
    public Container getContainer(EntityPlayer player) {
        return new ContainerAccumulator(player.inventory,this) {
        };
    }

    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiAccumulator(player,this);
    }

    @Override
    public int getLineOnEnergy() {
        return 4;
    }
}
