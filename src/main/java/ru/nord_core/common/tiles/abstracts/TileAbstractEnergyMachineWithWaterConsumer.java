package ru.nord_core.common.tiles.abstracts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;
import ru.nord_core.common.tiles.interfaces.IFluidTankBlock;
import ru.nord_core.common.tiles.interfaces.IMachine;

/**
 * @author andrew
 *         Абстрактная машина с 1 входом, 2 выходами и энергией
 */
public abstract class TileAbstractEnergyMachineWithWaterConsumer extends TileAbstractEnergyMachine
        implements IMachine, IFluidHandler,IFluidTankBlock {

    protected final FluidTank tank = new FluidTank(5*FluidContainerRegistry.BUCKET_VOLUME);

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
    }

    /* IFluidHandler */
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill)
    {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain)
    {
        if (resource == null || !resource.isFluidEqual(tank.getFluid()))
        {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain)
    {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid)
    {
        return true;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid)
    {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from)
    {
        return new FluidTankInfo[] { tank.getInfo() };
    }

    public float getWaterLevel(){
        return ((float)tank.getFluidAmount()) / ((float)tank.getCapacity());
    }

    public FluidTank getTank() {
        return tank;
    }
}
