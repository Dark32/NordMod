package ru.nord.common.tiles;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.client.gui.inventory.GuiGenerator;
import ru.nord.common.container.ContainerGenerator;
import ru.nord.common.lib.utils.Constants;
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
        return Constants.SHARE_MULTIPLE*2;
    }
    @Override
    public int getPacketEnergy() {
        return Constants.SHARE_MULTIPLE;
    }

    @Override
    public Container getContainer(EntityPlayer player) {
        return new ContainerGenerator(player.inventory,this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiGenerator(player,this);
    }
}
