package ru.nord.common.tiles.interfaces;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public interface ITileWithGui {

    public Container getContainer(EntityPlayer player);

    public GuiContainer getGui(EntityPlayer player);
}
