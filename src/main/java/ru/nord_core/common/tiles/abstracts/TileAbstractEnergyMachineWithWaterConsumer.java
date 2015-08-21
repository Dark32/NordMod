package ru.nord_core.common.tiles.abstracts;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fluids.*;
import ru.nord_core.common.recipes.interfaces.IRecipe1I2O;
import ru.nord_core.common.tiles.interfaces.IFluidTankBlock;
import ru.nord_core.common.tiles.interfaces.IMachine;

/**
 * @author andrew
 *         Абстрактная машина с 1 входом, 2 выходами и энергией
 */
public abstract class TileAbstractEnergyMachineWithWaterConsumer extends TileAbstractEnergyMachine
        implements IMachine, IFluidHandler, IFluidTankBlock {

    protected final FluidTank tank = new FluidTank(5 * FluidContainerRegistry.BUCKET_VOLUME);

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        tank.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tank.writeToNBT(tag);
    }

    /* IFluidHandler */
    @Override
    public int fill(EnumFacing from, FluidStack resource, boolean doFill) {
        return tank.fill(resource, doFill);
    }

    @Override
    public FluidStack drain(EnumFacing from, FluidStack resource, boolean doDrain) {
        if (resource == null || !resource.isFluidEqual(tank.getFluid())) {
            return null;
        }
        return tank.drain(resource.amount, doDrain);
    }

    @Override
    public FluidStack drain(EnumFacing from, int maxDrain, boolean doDrain) {
        return tank.drain(maxDrain, doDrain);
    }

    @Override
    public boolean canFill(EnumFacing from, Fluid fluid) {
        return true;
    }

    @Override
    public boolean canDrain(EnumFacing from, Fluid fluid) {
        return false;
    }

    @Override
    public FluidTankInfo[] getTankInfo(EnumFacing from) {
        return new FluidTankInfo[]{tank.getInfo()};
    }

    public float getWaterLevel() {
        return ((float) tank.getFluidAmount()) / ((float) tank.getCapacity());
    }

    @Override
    public FluidTank getTank() {
        return tank;
    }

    @Override
    public int getField(int id) {
        if (id == getFieldCount()) {
            return this.getTank().getFluidAmount();
        }
        return 0;
    }

    @Override
    public void setField(int id, int value) {
        if (id == 4) {
            if (this.getTank().getFluid() == null) {
                this.getTank().setFluid(new FluidStack(FluidRegistry.WATER, 1));
            }
            this.getTank().setCapacity(value);
        }
    }

    @Override
    public int getFieldCount() {
        return 5;
    }

    @Override
    public void update() {
        boolean updated = false;
        //Если горит топливо
        if (burnFuel()) {
            updated = true;
        }

        if (chargeFromFuel()) {
            updated = true;
        }

        if (isWork() && canStartWorking()) { // работаем и можем ли работать ?
            currentItemEnergyProgress += getWorkPacketEnergy(); // прибавляем к прогрессу пакет работы
            this.subEnergy(getWorkPacketEnergy()); // отнимаем пакет энергии
            this.tank.drain(getFluidWorkPacket(), true); // отнимаем пакет жидкости
            updated = true; // помечаем на обновление
            if (canWorkResult()) { // можем ли получить результат работы
                work(); // работаем
            }
        } else if (canStartWorking()) { // можем ли начать работать
            IRecipe1I2O rec = getRecipe(getStackInSlot(input_slot)); // получаем рецепт
            if (rec != null) { // если есть рецепт
                currentItemEnergyNeed = rec.getEnergy(); // устанавливаем требование энергии из рецепта
                updated = true; // помечаем на обновление
            } else { // инача
                currentItemEnergyNeed = 0; // треование энергии обнуляем
            }
        }

        if (updated) { // если поменчено на обновление
            this.markDirty(); // обновляем
        }
    }

    @Override
    public boolean isWork() {
        return currentItemEnergyNeed > 0
                && this.getEnergy() >= this.getPacketEnergy()
                && this.tank.getFluidAmount() >= this.getFluidWorkPacket();
    }
}
