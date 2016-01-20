package ru.nord.common.container;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.tiles.TileWasher;
import ru.nord_core.common.container.abstracts.ContainerAbstactMachine;
import ru.nord_core.common.container.slot.SlotFuelAndEnergy;
import ru.nord_core.common.container.slot.SlotOutput1I2O;
import ru.nord_core.common.tiles.interfaces.IFluidTankBlock;
import ru.nord_core.common.tiles.interfaces.IMachine;

public class ContainerWasher extends ContainerAbstactMachine {

    protected IFluidTankBlock tileFluidTankBlock;
    public ContainerWasher(InventoryPlayer invPlayer, TileWasher ent) {
        init(invPlayer, ent);
        this.tileFluidTankBlock = ent;
    }
    public void init(InventoryPlayer invPlayer, IMachine ent) {
        tileEntity = ent;

        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, slot_fuel, 18, 58)); // fuel
        addSlotToContainer(new Slot(tileEntity, slot_input, 48, 36)); // item to work
        addSlotToContainer(new SlotOutput1I2O(invPlayer.player, tileEntity, slot_result1, 94, 36)); // result1
        addSlotToContainer(new SlotOutput1I2O(invPlayer.player, tileEntity, slot_result2, 115, 36)); // result2
        bindPlayerInventory(invPlayer);
    }

    private int lastFluifCapacity;
    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();
        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);
            if (this.lastFluifCapacity != this.tileFluidTankBlock.getTank().getFluidAmount()) {
                icrafting.sendProgressBarUpdate(this, 5, this.tileFluidTankBlock.getTank().getFluidAmount());
            }
        }
        this.lastFluifCapacity = this.tileFluidTankBlock.getTank().getFluidAmount();
    }
    @Override
    public void onCraftGuiOpened(ICrafting icrafter) {
        super.onCraftGuiOpened(icrafter);
        icrafter.sendProgressBarUpdate(this, 5, this.tileFluidTankBlock.getTank().getFluidAmount());
    }
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var, int val) {
        super.updateProgressBar(var, val);
        if  (var == 5) {
              this.tileFluidTankBlock.getTank().setFluid(new FluidStack(FluidRegistry.WATER, val));
        }
    }
}
