package ru.nord.common.tiles;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.client.gui.inventory.GuiExtractor;
import ru.nord.common.container.ContainerExtractor;
import ru.nord.common.lib.recipes.ExtractorRecipes1I2O;
import ru.nord.common.lib.recipes.FlowingRecipes1I2O;
import ru.nord_core.common.recipes.Interfaces.IRecipes1I2O;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachina;
import ru.nord_core.common.utils.Constants;

public class TileExtractor extends TileAbstractEnergyMachina {

    @Override
    public String getName() {
        return this.hasCustomName() ? this.machineCustomName : "nord.tile.extractor";
    }

    @Override
    public int getMaxEnergy() {
        return 12800;
    }
    @Override
    public IRecipes1I2O getRecipes() {
        return ExtractorRecipes1I2O.INSTANCE();
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
        return new ContainerExtractor(player.inventory,this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public GuiContainer getGui(EntityPlayer player) {
        return new GuiExtractor(player,this);
    }
}
