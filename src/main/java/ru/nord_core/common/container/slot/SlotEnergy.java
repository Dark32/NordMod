package ru.nord_core.common.container.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.items.interfaces.IEnergyCharges;

public class SlotEnergy extends Slot {

    public SlotEnergy(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }
    public boolean isItemValid(ItemStack stack)
    {
        return stack.getItem() instanceof IEnergyCharges;
    }

}
