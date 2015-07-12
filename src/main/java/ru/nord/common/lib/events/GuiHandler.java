package ru.nord.common.lib.events;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.network.IGuiHandler;
import ru.nord.common.tiles.interfaces.ITileWithGui;

public class GuiHandler implements IGuiHandler {

    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof ITileWithGui) {
            ITileWithGui tileWithGui = (ITileWithGui) tileEntity;
            if (tileWithGui.getContainer(player) != null) {
                return tileWithGui.getContainer(player);
            } else {
                FMLLog.warning(tileWithGui.toString() + " Container not set");
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        TileEntity tileEntity = world.getTileEntity(new BlockPos(x, y, z));
        if (tileEntity instanceof ITileWithGui) {
            ITileWithGui tileWithGui = (ITileWithGui) tileEntity;
            if (tileWithGui.getContainer(player) != null) {
                return tileWithGui.getGui(player);
            } else {
                FMLLog.warning(tileWithGui.toString() + " GUI not set");
            }
        }
        return null;
    }
}