package ru.nord.common.container.Slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.nord.common.items.interfaces.IEnergyCharges;
import ru.nord.common.lib.utils.Fuel;

public class SlotFuelAndEnergy extends Slot {

    public SlotFuelAndEnergy(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }
    public boolean isItemValid(ItemStack stack)
    {
        return Fuel.getInstance().isFuel(stack) || stack.getItem() instanceof IEnergyCharges;
    }

}
