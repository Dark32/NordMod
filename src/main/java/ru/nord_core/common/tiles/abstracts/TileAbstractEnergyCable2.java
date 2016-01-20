package ru.nord_core.common.tiles.abstracts;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.world.World;
import ru.nord_core.common.utils.enums.EnumCableState;

/**
 * @author andrew
 */
public abstract class TileAbstractEnergyCable2 extends TileAbstractEnergyBlockWithOutContainer /*TileAbstractEnergyBlock*/
        implements ITickable {
    protected String machineCustomName;

    protected EnumCableState[] state = new EnumCableState[6];
//    protected EnumColors color = EnumColors.BLACK;

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        writeToNBT(nbtTagCompound);
        int metadata = getBlockMetadata();
        return new S35PacketUpdateTileEntity(this.pos, metadata, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.state[EnumFacing.DOWN.getIndex()] = EnumCableState.getByType(
                compound.getByte(EnumFacing.DOWN.getName())
        );
        this.state[EnumFacing.UP.getIndex()] = EnumCableState.getByType(
                compound.getByte(EnumFacing.UP.getName())
        );
        this.state[EnumFacing.WEST.getIndex()] = EnumCableState.getByType(
                compound.getByte(EnumFacing.WEST.getName())
        );
        this.state[EnumFacing.EAST.getIndex()] = EnumCableState.getByType(
                compound.getByte(EnumFacing.EAST.getName())
        );
        this.state[EnumFacing.NORTH.getIndex()] = EnumCableState.getByType(
                compound.getByte(EnumFacing.NORTH.getName())
        );
        this.state[EnumFacing.SOUTH.getIndex()] = EnumCableState.getByType(
                compound.getByte(EnumFacing.SOUTH.getName())
        );

        int energy = compound.getInteger("energy");
        setEnergy(energy);
//        color = EnumColors.byMetadata(compound.getByte("Color"));

    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("energy", (short) this.getEnergy());
        compound.setByte(
                EnumFacing.DOWN.getName(),
                (byte) this.state[EnumFacing.DOWN.getIndex()].getMetadata()
        );
        compound.setByte(
                EnumFacing.UP.getName(),
                (byte) this.state[EnumFacing.UP.getIndex()].getMetadata()
        );
        compound.setByte(
                EnumFacing.WEST.getName(),
                (byte) this.state[EnumFacing.WEST.getIndex()].getMetadata()
        );
        compound.setByte(
                EnumFacing.EAST.getName(),
                (byte) this.state[EnumFacing.EAST.getIndex()].getMetadata()
        );
        compound.setByte(
                EnumFacing.NORTH.getName(),
                (byte) this.state[EnumFacing.NORTH.getIndex()].getMetadata()
        );
        compound.setByte(
                EnumFacing.SOUTH.getName(),
                (byte) this.state[EnumFacing.SOUTH.getIndex()].getMetadata()
        );

//        compound.setByte("Color", (byte) this.color.getMetadata());
    }

    /**
     * Updates the JList with a new model.
     */
    @Override
    public void update() {
    /*
        boolean updated = false;
        if (sendEnergy()) {
            updated = true;
        }
//        System.err.println(this.getEnergy());
        if (updated) { // если поменчено на обновление
            this.markDirty(); // обновляем
        }*/
    }


    @Override
    public boolean isAcepter() {
        return true;
    }

    @Override
    public boolean isDispenser() {
        return true;
    }

    public void setCableStateOnFacing(EnumFacing face, EnumCableState state) {
        this.state[face.getIndex()] = state;
    }

    public EnumCableState getCableStateOnFacing(EnumFacing face) {
        return this.state[face.getIndex()];
    }

    //    public EnumColors getColor(){
//        return color;
//    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return false;
    }


}
