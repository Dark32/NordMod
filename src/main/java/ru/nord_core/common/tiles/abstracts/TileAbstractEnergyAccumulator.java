package ru.nord_core.common.tiles.abstracts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.items.interfaces.IEnergyCharges;
import ru.nord_core.common.utils.ChargeHelper;
import ru.nord_core.common.tiles.interfaces.IAccumulator;

/**
 * @author andrew
 *         Абстрактный генератор энергии
 */
public abstract class TileAbstractEnergyAccumulator extends TileAbstractEnergyBlock
        implements IAccumulator {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{1, 3};
    private static final int[] slotsSides = new int[]{2};

    private ItemStack[] inventory = new ItemStack[8];

    private final int charge_slot_in = 0;
    private final int charge_slot_out = 1;
    private final int dis_charge_slot_in = 2;
    private final int dis_charge_slot_out = 3;
    private final int[] upgrade = new int[]{4, 5, 6, 7};

    private boolean charge = false;
    private boolean discharge = false;

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
        this.setEnergy(compound.getInteger("energy"));
        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
            this.machineCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setInteger("energy", (short) this.getEnergy());
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

    /**
     * Updates the JList with a new model.
     */
    public void update() {

        boolean updated = false;
        if (isChargeability() && canStartCharge()) {
            charge();
            charge = true;
            updated = true;
        } else {
            charge = false;
        }
        if (isDisChargeability() && canStartDisCharge()) {
            discharge();
            discharge = true;
            updated = true;
        } else {
            discharge = false;
        }

        if (conserved()) {
            updated = true;
        }

        if (updated) { // если поменчено на обновление
            this.markDirty(); // обновляем
        }
    }

    public boolean getCharge() {
        return charge;
    }

    public boolean getDisCharge() {
        return discharge;
    }

    private void charge() {
        ItemStack item = getStackInSlot(charge_slot_in);
        ChargeHelper.addEnergy(item, this.getPacketEnergy());
        this.subEnergy(this.getPacketEnergy());
        if (!canStartCharge() && getStackInSlot(charge_slot_out) == null) {
            setInventorySlotContents(charge_slot_out, item);
            setInventorySlotContents(charge_slot_in, null);
        }
    }

    private boolean canStartCharge() {
        ItemStack item = getStackInSlot(charge_slot_in);
        return ChargeHelper.getDeficient(item) >= this.getPacketEnergy()
                && this.hasSubEnergy(this.getPacketEnergy());
    }

    private boolean isChargeability() {
        ItemStack item = getStackInSlot(charge_slot_in);
        if (item == null) return false;
        if (item.getItem() instanceof IEnergyCharges) return true;
        return false;
    }


    private void discharge() {
        ItemStack item = getStackInSlot(dis_charge_slot_in);
        ChargeHelper.subEnergy(item, this.getPacketEnergy());
        if (this.hasAddEnergy(this.getPacketEnergy())) {
            this.addEnergy(this.getPacketEnergy());
        } else {
            this.addBonusEnergy(this.getPacketEnergy());
        }
        if (!canStartDisCharge() && getStackInSlot(dis_charge_slot_out) == null) {
            setInventorySlotContents(dis_charge_slot_out, item);
            setInventorySlotContents(dis_charge_slot_in, null);
        }
    }

    private boolean canStartDisCharge() {
        ItemStack item = getStackInSlot(dis_charge_slot_in);
        IEnergyCharges charge = ((IEnergyCharges) item.getItem());
        return charge.currectEnergy(item) >= this.getPacketEnergy()
                && (this.hasAddEnergy(this.getPacketEnergy())
                || hasAddBonusEnergy(this.getPacketEnergy()));
    }


    private boolean isDisChargeability() {
        ItemStack item = getStackInSlot(dis_charge_slot_in);
        if (item == null) return false;
        if (item.getItem() instanceof IEnergyCharges) return true;
        return false;
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

        if (index == charge_slot_in && isChargeability()) {
            return true;
        }
        if (index == dis_charge_slot_in && isDisChargeability()) {
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
        return 0;
    }

    public void setField(int id, int value) {
    }

    public int getFieldCount() {
        return 0;
    }

    public void clear() {
        for (int i = 0; i < this.inventory.length; ++i) {
            this.setInventorySlotContents(i, null);
        }
    }

    @Override
    public boolean isAcepter() {
        return true;
    }

    @Override
    public boolean isDispenser() {
        return true;
    }

    @Override
    public int getUpgradeEnergy(int slot) {
        ItemStack item = getStackInSlot(slot);
        IEnergyCharges charge = ((IEnergyCharges) item.getItem());
        int _energy = charge.currectEnergy(item);
        return _energy;
    }

    @Override
    public int getEnergyByLine() {
        return getMaxEnergy() / getLineOnEnergy();
    }

    @Override
    public int setBonusEnergy(int energy) {
        if (energy > getBonusMaxEnergy()) energy = getBonusMaxEnergy();
        for (int anUpgrade : upgrade) {
            ItemStack item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = ((IEnergyCharges) item.getItem());
                charge.setEnergy(item, charge.maxEnergy(item));
                energy -= charge.maxEnergy(item);
            }
        }
        return getEnergy() + getBonusEnergy();
    }

    @Override
    public int getBonusMaxEnergy() {
        int energy = 0;
        ItemStack item;
        for (int anUpgrade : upgrade) {
            item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = ((IEnergyCharges) item.getItem());
                energy += charge.maxEnergy(item);
            }
        }
        return energy;
    }

    @Override
    public int getBonusEnergy() {
        int energy = 0;
        ItemStack item;
        for (int anUpgrade : upgrade) {
            item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = ((IEnergyCharges) item.getItem());
                energy += charge.currectEnergy(item);
            }
        }
        return energy;
    }

    @Override
    public int addBonusEnergy(int energy) {
        ItemStack item;
        for (int anUpgrade : upgrade) {
            item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                int _energyDeficient = ChargeHelper.getDeficient(item);
                if (energy >= _energyDeficient) {
                    ChargeHelper.addEnergy(item, _energyDeficient);
                    energy -= _energyDeficient;
                } else {
                    ChargeHelper.addEnergy(item, energy);
                    break;
                }
            }
        }
        return getEnergy() + getBonusEnergy();
    }

    @Override
    public int subBonusEnergy(int energy) {
        ItemStack item;
        for (int anUpgrade : upgrade) {
            item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = ((IEnergyCharges) item.getItem());
                int _energyProficit = charge.currectEnergy(item);
                if (energy >= _energyProficit) {
                    ChargeHelper.subEnergy(item, _energyProficit);
                    energy -= _energyProficit;
                } else {
                    ChargeHelper.subEnergy(item, energy);
                    break;
                }
            }
        }
        return getEnergy() + getBonusEnergy();
    }

    @Override
    public boolean hasSubBonusEnergy(int energy) {
        ItemStack item;
        for (int anUpgrade : upgrade) {
            item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = ((IEnergyCharges) item.getItem());
                int _energyProficit = charge.currectEnergy(item);
                if (energy >= _energyProficit) {
                    energy -= _energyProficit;
                } else {
                    return true;
                }
            }
        }
        return energy == 0;
    }

    @Override
    public boolean hasAddBonusEnergy(int energy) {
        ItemStack item;
        for (int anUpgrade : upgrade) {
            item = getStackInSlot(anUpgrade);
            if (item != null && item.getItem() instanceof IEnergyCharges) {
                int _energyDeficient = ChargeHelper.getDeficient(item);
                if (energy >= _energyDeficient) {
                    energy -= _energyDeficient;
                } else {
                    return true;
                }
            }
        }
        return energy == 0;
    }

    @Override
    public boolean conserved() {
        int bonus = getBonusEnergy() > getPacketEnergy() ? getPacketEnergy() : getBonusEnergy();
        if (hasAddEnergy(bonus)) {
            addEnergy(bonus);
            subBonusEnergy(bonus);
            return true;
        }
        return false;
    }

    @SideOnly(Side.CLIENT)
    public int getEnergyProgressScaled(int line, int val) {
        int energyInLine = this.getEnergy()-this.getEnergyByLine()*(line-1);
        energyInLine = energyInLine > 0 ? energyInLine : 0;
        energyInLine = energyInLine < this.getEnergyByLine() ? energyInLine : this.getEnergyByLine();
        return energyInLine * val / this.getEnergyByLine();
    }
}
