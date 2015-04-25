package ru.nord.common.tiles.abstracts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.items.interfaces.IEnergyCharges;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.tiles.interfaces.IGenerator;

/**
 * @author andrew
 *         Абстрактный генератор энергии
 */
public abstract class TileAbstractEnergyGenerator extends TileAbstractEnergyBlock
        implements IGenerator {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{1};
    private static final int[] slotsSides = new int[]{0, 1};
    private ItemStack[] inventory = new ItemStack[3];
    /**
     * inv[0] - fuel
     * inv[1] - charge item
     * inv[2] - charge item
     */
    private final int fuel_slot = 0;
    private final int charge_slot1 = 1;
    private final int charge_slot2 = 2;

    private boolean charge1 = false;
    private boolean charge2 = false;

    protected int burnTime; // текущие время горения
    protected int fuelBurnTime; // время горения

    protected String machineCustomName;

    /**
     * Returns the number of slots in the inventory.
     */
    @Override
    public int getSizeInventory() {
        return this.inventory.length;
    }

    /**
     * Returns the stack in slot i
     */
    @Override
    public ItemStack getStackInSlot(int index) {
        return this.inventory[index];
    }

    /**
     * Removes from an inventory slot (first arg) up to a specified number (second arg) of items and returns them in a
     * new stack.
     */
    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (getStackInSlot(index) != null) {
            ItemStack itemstack;

            if (getStackInSlot(index).stackSize <= count) {
                itemstack = getStackInSlot(index);
                setInventorySlotContents(index, null);
                return itemstack;
            } else {
                itemstack = getStackInSlot(index).splitStack(count);

                if (getStackInSlot(index).stackSize == 0) {
                    setInventorySlotContents(index, null);
                }

                return itemstack;
            }
        } else {
            return null;
        }
    }


    /**
     * When some containers are closed they call this on each slot, then drop whatever it returns as an EntityItem -
     * like when you close a workbench GUI.
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int index) {
        ItemStack itemstack = getStackInSlot(index);
        if (itemstack != null) {
            setInventorySlotContents(index, null);
        }
        return itemstack;

    }

    /**
     * Sets the given item stack to the specified slot in the inventory (can be crafting or armor sections).
     */
    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        boolean flag = stack != null
                && stack.isItemEqual(getStackInSlot(index))
                && ItemStack.areItemStackTagsEqual(stack,
                getStackInSlot(index));
        this.inventory[index] = stack;
        if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    @Override
    abstract public String getName();

    /**
     * Returns true if this thing is named
     */
    @Override
    public boolean hasCustomName() {
        return this.machineCustomName != null && this.machineCustomName.length() > 0;
    }

    public void setCustomInventoryName(String name) {
        this.machineCustomName = name;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        NBTTagList tagList = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        this.inventory = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < tagList.tagCount(); ++i) {
            NBTTagCompound tag = tagList.getCompoundTagAt(i);
            byte b0 = tag.getByte("Slot");

            if (b0 >= 0 && b0 < this.inventory.length) {
                this.inventory[b0] = ItemStack.loadItemStackFromNBT(tag);
            }
        }


        this.setEnergy(compound.getShort("energy"));
        burnTime = compound.getShort("burnTime");
        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
            this.machineCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setShort("energy", (short) this.getEnergy());
        compound.setShort("burnTime", (short) burnTime);
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < this.inventory.length; ++i) {
            ItemStack stack = inventory[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }

        compound.setTag("Items", itemList);

        if (this.hasCustomName()) {
            compound.setString("CustomName", this.machineCustomName);
        }
    }

    /**
     * Returns the maximum stack size for a inventory slot. Seems to always be 64, possibly will be extended. *Isn't
     * this more of a set than a get?*
     */
    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    public boolean isBurning() {
        return burnTime > 0 && this.getEnergy() < this.getMaxEnergy();
    }

    @SideOnly(Side.CLIENT)
    public static boolean isBurning(IInventory inv) {
        return inv.getField(0) > 0;
    }

    /**
     * Updates the JList with a new model.
     */
    public void update() {

        boolean updated = false;

        //Если горит топливо
        if (burnFuel()) {
            updated = true;
        }

        if (chargeFromFuel()) {
            updated = true;
        }

        if (isChargeability(charge_slot1) && canStartCharge(charge_slot1)) {
            charge(charge_slot1);
            charge1 = true;
        }else{
            charge1 = false;
        }
        if (isChargeability(charge_slot2) && canStartCharge(charge_slot2)) {
            charge(charge_slot2);
            charge2 = true;
        }else{
            charge2 = false;
        }

        if (updated) { // если поменчено на обновление
            this.markDirty(); // обновляем
        }
    }

    private boolean burnFuel(){
        boolean updated = false;
        if (isBurning()) {
            if (burnTime>=getBurnPacketEnergy()) { // есть ли пакет в горение
                burnTime -= getBurnPacketEnergy(); // убавляем на пакет время горения
                this.addEnergy(getBurnPacketEnergy()); // прибавляем энергию на пакет
            }else{ // если нет, то просто
                this.addEnergy(burnTime); // прибавляем остатки
                burnTime = 0;  // и обнуляем горение
            }
            updated = true; // помечаем на обновление
            if (burnTime <= 0) { // если сгорело
                if (canBurn()) { // можно ли произвести сжеть ещё?
                    burn(); //сжигаем ещё
                }
            }
        } else {
            setFuelBurnTime(0); // время горения на 0
            if (canBurn()) { // можем ли сжечь топливо?
                updated = true; //помечаем на обновление
                burn(); //сжигаем топливо
            }
        }
        return updated;
    }

    private boolean chargeFromFuel() {
        if (isEnergyStorage()) {
            ItemStack item = getStackInSlot(fuel_slot);
            if (item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = (IEnergyCharges) item.getItem();
                if (charge.hasEnergy(item)
                        && this.getEnergy() <= (this.getMaxEnergy() - charge.packetEnergy(item))) {
                    this.addEnergy(charge.packetEnergy(item));
                    charge.subEnergy(item);
                    return true; // помечаем на обновление
                }
            }
        }
        return false;
    }

    private boolean isEnergyStorage() {
        ItemStack item = getStackInSlot(fuel_slot);
        if (item == null) return false;
        if (item.getItem() instanceof IEnergyCharges) {
            IEnergyCharges charge = (IEnergyCharges) item.getItem();
            return charge.currectEnergy(item) >= charge.packetEnergy(item);
        }
        return false;
    }

    private void charge(int charge_slot) {
        ItemStack item = getStackInSlot(charge_slot);
        IEnergyCharges charge = ((IEnergyCharges) item.getItem());
        charge.addEnergy(item, this.getPacketEnergy());
        this.subEnergy(this.getPacketEnergy());
    }

    private boolean canStartCharge(int charge_slot) {
        ItemStack item = getStackInSlot(charge_slot);
        IEnergyCharges charge = ((IEnergyCharges) item.getItem());
        return charge.currectEnergy(item) <= (charge.maxEnergy(item) - this.getPacketEnergy())
                && this.hasSubEnergy(this.getPacketEnergy());
    }


    private boolean isChargeability(int charge_slot) {
        ItemStack item = getStackInSlot(charge_slot);
        if (item == null) return false;
        if (item.getItem() instanceof IEnergyCharges) return true;
        return false;
    }

    public static int getItemBurnTime(ItemStack stack) {
        return Fuel.getInstance().getEnergy(stack);
    }

    public static boolean isItemFuel(ItemStack item) {
        return getItemBurnTime(item) > 0;
    }

    /**
     * Do not make give this method the name canInteractWith because it clashes with Container
     */
    public boolean isUseableByPlayer(EntityPlayer player) {
        return this.worldObj.getTileEntity(this.pos) == this
                && player.getDistanceSq((double) this.pos.getX() + 0.5D,
                (double) this.pos.getY() + 0.5D,
                (double) this.pos.getZ() + 0.5D) <= 64.0D;
    }

    public void openInventory(EntityPlayer player) {
    }

    public void closeInventory(EntityPlayer player) {
    }

    /**
     * Returns true if automation is allowed to insert the given stack (ignoring stack size) into the given slot.
     */
    public boolean isItemValidForSlot(int index, ItemStack stack) {
        if (index == fuel_slot && getItemBurnTime(stack) == 0) {
            return false;
        }
        if (index == charge_slot1 && isChargeability(charge_slot1)) {
            return true;
        }
        if (index == charge_slot2 && isChargeability(charge_slot2)) {
            return true;
        }
        return false;
    }

    public int[] getSlotsForFace(EnumFacing side) {
        switch (side) {
            case DOWN:
                return slotsBottom;
            case UP:
                return slotsTop;
            default:
                return slotsSides;
        }
    }

    /**
     * Returns true if automation can insert the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
        return this.isItemValidForSlot(index, itemStackIn);
    }

    /**
     * Returns true if automation can extract the given item in the given slot from the given side. Args: slot, item,
     * side
     */
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
        if (direction == EnumFacing.DOWN && index == 1) {
            Item item = stack.getItem();

            if (item != Items.water_bucket && item != Items.bucket) {
                return false;
            }
        }

        return true;
    }

    //todo abstract
    public String getGuiID() {
        return "nord:abstractEnegyMachina";
    }

    //todo abstract
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
        return new ContainerFurnace(playerInventory, this);
    }

    public int getField(int id) {
        switch (id) {
            case 0:
                return this.fuelBurnTime;
            case 1:
                return this.burnTime;
            default:
                return 0;
        }
    }

    public void setField(int id, int value) {
        switch (id) {
            case 0:
                this.fuelBurnTime = value;
                break;
            case 1:
                this.burnTime = value;
                break;
        }
    }

    public int getFieldCount() {
        return 4;
    }

    public void clear() {
        for (int i = 0; i < this.inventory.length; ++i) {
            this.setInventorySlotContents(i, null);
        }
    }

    public boolean canBurn() {
        ItemStack fuel = getStackInSlot(fuel_slot);
        return !(this.getEnergy() >= this.getMaxEnergy() ||
                fuel == null ||
                getItemBurnTime(fuel) == 0 ||
                burnTime > 0);
    }

    public void burn() {
        ItemStack fuel = getStackInSlot(fuel_slot);
        if (fuel != null) {

            this.burnTime = getItemBurnTime(fuel);
            this.fuelBurnTime = this.burnTime;
            setInventorySlotContents(fuel_slot, Fuel.getInstance().burn(fuel));
        }
    }

    @Override
    public int getBurnTime() {
        return burnTime;
    }

    @Override
    public void setBurnTime(int val) {
        burnTime = val;
    }

    @Override
    public void setFuelBurnTime(int val) {
        fuelBurnTime = val;
    }

    @Override
    public int getFuelBurnTime() {
        return fuelBurnTime;
    }

    @SideOnly(Side.CLIENT)
    public int getEnergyProgressScaled(int val) {
        return this.getEnergy() * val / this.getMaxEnergy();
    }
    @SideOnly(Side.CLIENT)
    public boolean getCharge(int charge_slot) {
        return charge_slot==1 ? charge1 :charge2;
    }
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int val) {
        return fuelBurnTime == 0 ? 0 : burnTime * val / fuelBurnTime;
    }
}
