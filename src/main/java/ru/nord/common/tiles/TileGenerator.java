package ru.nord.common.tiles;


import com.sun.istack.internal.NotNull;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import ru.nord.client.gui.inventory.GuiGenerator;
import ru.nord.common.container.ContainerGenerator;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyGenerator;

public class TileGenerator extends TileAbstractEnergyGenerator {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.generator";
    }

    @Override
    public int getMaxEnergy() {
        return 12800;
    }

    @Override
    public int getBurnPacketEnergy() {
        return 16;
    }
    @Override
    public int getPacketEnergy() {
        return 16;
    }

    @Override
    @NotNull
    public Container getContainer(EntityPlayer player) {
        return new ContainerGenerator(player.inventory,this);
    }

    @Override
    @NotNull
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiGenerator(player,this);
    }
}
