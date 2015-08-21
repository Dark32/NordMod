package ru.nord.common.tiles;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.client.gui.inventory.GuiWasher;
import ru.nord.common.container.ContainerWasher;
import ru.nord.common.lib.recipes.WasherRecipes1I2O;
import ru.nord_core.common.recipes.interfaces.IAbstractRecipes;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachineWithWaterConsumer;
import ru.nord_core.common.utils.Constants;

public class TileWasher extends TileAbstractEnergyMachineWithWaterConsumer {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.washer";
    }

    @Override
    public IAbstractRecipes getRecipes() {
        return WasherRecipes1I2O.INSTANCE();
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
        return new ContainerWasher(player.inventory,this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiWasher(player,this);
    }


    @Override
    public int getFluidWorkPacket() {
        return 1;
    }
}
