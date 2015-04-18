package ru.nord.common.container.abstracts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.container.Slot.SlotFuel;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.tiles.interfaces.IGenerator;

abstract public class ContainerAbstactGenerator extends Container {
    protected IGenerator tileEntity;

    private int lastEnergy;
    private int lastBurnTime;
    private int lastFuelBurnTime;

    protected int slot_fuel = 0;
    protected int slot_charge = 1;

    public void init(InventoryPlayer invPlayer, IGenerator ent) {
        tileEntity = ent;

        addSlotToContainer(new SlotFuel(tileEntity, slot_fuel, 18, 58)); // fuel
        addSlotToContainer(new Slot(tileEntity, slot_charge, 18, 23)); // item to work
        bindPlayerInventory(invPlayer);
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafter) {
        super.addCraftingToCrafters(icrafter);
        icrafter.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
        icrafter.sendProgressBarUpdate(this, 1, this.tileEntity.getBurnTime());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastEnergy != this.tileEntity.getEnergy()) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
            }

            if (this.lastBurnTime != this.tileEntity.getBurnTime()) {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.getBurnTime());
            }

            if (this.lastFuelBurnTime != this.tileEntity.getFuelBurnTime()) {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.getFuelBurnTime());
            }
        }

        this.lastEnergy = this.tileEntity.getEnergy();
        this.lastBurnTime = this.tileEntity.getBurnTime();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var, int val) {
        super.updateProgressBar(var, val);
        switch (var) {
            case 0:
                this.tileEntity.setEnergy(val);
                break;
            case 1:
                this.tileEntity.setBurnTime(val);
                break;
            case 2:
                this.tileEntity.setFuelBurnTime(val);
                break;
        }
    }

    protected void bindPlayerInventory(InventoryPlayer invPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(invPlayer, j + i * 9 + 9, 8 + j * 18, 87 + i * 18));
            }
        }

        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 145));
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
        ItemStack stack = null;
        Slot slotObject = (Slot) inventorySlots.get(slot);
        Slot fuelSlot = (Slot) this.inventorySlots.get(0);

        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();

            //boolean check = false;

            if (slot != slot_charge && slot != slot_fuel) {
               if (Fuel.getInstance().getEnergy(stackInSlot) > 0 && (fuelSlot.getStack() == null || fuelSlot.getStack().stackSize < 64)) {
                    if (!this.mergeItemStack(stackInSlot, 0, 1, false))
                        return null;
                } else if (slot >= 2 && slot <= 28) {
                    if (!this.mergeItemStack(stackInSlot, 29, 38, false))
                        return null;
                } else if (slot >= 29 && slot < 38 && !this.mergeItemStack(stackInSlot, 2, 29, false))
                    return null;

            } else if (!this.mergeItemStack(stackInSlot, 2, 38, false))
                return null;

            if (stackInSlot.stackSize == 0) {
                slotObject.putStack(null);
            } else {
                slotObject.onSlotChanged();
            }

            if (stackInSlot.stackSize == stack.stackSize) {
                return null;
            }
            slotObject.onPickupFromSlot(player, stackInSlot);
        }
        return null;
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return tileEntity.isUseableByPlayer(player);
    }
}
