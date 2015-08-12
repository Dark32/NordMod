package ru.nord_core.common.tiles.interfaces;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface ITileWithGui {

    public Container getContainer(EntityPlayer player);

    @SideOnly(Side.CLIENT)
    public GuiContainer getGui(EntityPlayer player);
}
