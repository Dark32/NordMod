package ru.nord.common.container.Slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ru.nord.common.lib.utils.Fuel;

public class SlotFuel extends Slot {

    public SlotFuel(IInventory inventoryIn, int slotIndex, int xPosition, int yPosition)
    {
        super(inventoryIn, slotIndex, xPosition, yPosition);
    }
    public boolean isItemValid(ItemStack stack)
    {
        return Fuel.getInstance().isFuel(stack);
    }

}
