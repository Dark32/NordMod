package ru.nord.common.tiles;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.common.lib.utils.Constants;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyCable;

public class TileEnergyCable extends TileAbstractEnergyCable {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.energyCable";
    }

    @Override
    public int getMaxEnergy() {
        return Constants.SHARE_MULTIPLE*10;
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
