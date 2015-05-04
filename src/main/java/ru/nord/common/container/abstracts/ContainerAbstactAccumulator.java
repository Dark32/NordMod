package ru.nord.common.container.abstracts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
import ru.nord.common.container.Slot.SlotFuelAndEnergy;
import ru.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.tiles.interfaces.IAccumulator;

abstract public class ContainerAbstactAccumulator extends Container {
    protected IAccumulator tileEntity;

    private int lastEnergy;
    private int lastBurnTime;
    private int lastFuelBurnTime;
    private int lastCurrentItemEnergyProgress;
    private int lastCurrentItemEnergyNeed;

    private final int charge_slot_in = 0;
    private final int charge_slot_out = 1;
    private final int dis_charge_slot_in = 2;
    private final int dis_charge_slot_out = 3;
    private final int[] upgrade = new int[]{4, 5, 6, 7};

    public void init(InventoryPlayer invPlayer, IAccumulator ent) {
        tileEntity = ent;

        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, charge_slot_in, 125, 24));
        addSlotToContainer(new Slot(tileEntity, charge_slot_out, 125, 54));
        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, dis_charge_slot_in, 147, 24));
        addSlotToContainer(new Slot(tileEntity, dis_charge_slot_out, 147, 54));

        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, upgrade[0], 82, 37));
        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, upgrade[1], 100, 37));
        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, upgrade[2], 82, 55));
        addSlotToContainer(new SlotFuelAndEnergy(tileEntity, upgrade[3], 100, 55));

        bindPlayerInventory(invPlayer);
    }

    @Override
    public void addCraftingToCrafters(ICrafting icrafter) {
        super.addCraftingToCrafters(icrafter);
        icrafter.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i) {
            ICrafting icrafting = (ICrafting) this.crafters.get(i);

            if (this.lastEnergy != this.tileEntity.getEnergy()) {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.getEnergy());
            }
        }

        this.lastEnergy = this.tileEntity.getEnergy();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int var, int val) {
        super.updateProgressBar(var, val);
        switch (var) {
            case 0:
                this.tileEntity.setEnergy(val);
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
        if (slotObject != null && slotObject.getHasStack()) {
            ItemStack stackInSlot = slotObject.getStack();
            stack = stackInSlot.copy();


            if (slot == charge_slot_out || slot == dis_charge_slot_out) {
                if (!this.mergeItemStack(stackInSlot, 8, 44, true)) {
                    return null;
                }
                slotObject.onSlotChange(stackInSlot, stack);
            } else if (!this.mergeItemStack(stackInSlot, 8, 44, false))
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
