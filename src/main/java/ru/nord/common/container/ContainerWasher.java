package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.tiles.TileWasher;
import ru.nord_core.common.container.abstracts.ContainerAbstactMachine;
import ru.nord_core.common.tiles.interfaces.IFluidTankBlock;
public class ContainerWasher extends ContainerAbstactMachine {

    protected IFluidTankBlock tileFluidTankBlock;
    public ContainerWasher(InventoryPlayer invPlayer, TileWasher ent) {
        init(invPlayer, ent);
        this.tileFluidTankBlock = ent;
    }

    private int capacity;
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.capacity != this.tileFluidTankBlock.getTank().getFluidAmount()) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
            }
        }
        this.capacity = this.tileFluidTankBlock.getTank().getFluidAmount();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var, int val) {
        super.updateProgressBar(var, val);
        if  (var == this.tileEntity.getFieldCount()) {
                if(this.tileFluidTankBlock.getTank().getFluid()==null){
                    this.tileFluidTankBlock.getTank().setFluid(new FluidStack(FluidRegistry.WATER,1));
                }
                this.tileFluidTankBlock.getTank().setCapacity(val);
        }
    }
}
