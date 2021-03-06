package ru.nord.common.tiles;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.client.gui.inventory.GuiSmelter;
import ru.nord.common.container.ContainerSmelter;
import ru.nord_core.common.recipes.interfaces.IAbstractRecipes;
import ru.nord.common.lib.recipes.SmelterRecipes2I2O;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachineDoubleInput;
import ru.nord_core.common.utils.Constants;

public class TileSmelter extends TileAbstractEnergyMachineDoubleInput {
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
        return new ContainerSmelter(player.inventory,this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiSmelter(player,this);
    }
}
