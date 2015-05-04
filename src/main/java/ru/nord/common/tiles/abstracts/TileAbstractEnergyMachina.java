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
import ru.nord.common.lib.recipes.Interfaces.IRecipe1I2O;
import ru.nord.common.lib.recipes.Interfaces.IRecipes1I2O;
import ru.nord.common.lib.utils.ChargeHelper;
import ru.nord.common.lib.utils.Fuel;
import ru.nord.common.tiles.interfaces.IMachine;

/**
 * @author andrew
 *         Абстрактная машина с 1 входом, 2 выходами и энергией
 */
public abstract class TileAbstractEnergyMachina extends TileAbstractEnergyBlock
        implements IMachine {

    private static final int[] slotsTop = new int[]{1};
    private static final int[] slotsBottom = new int[]{2, 3};
    private static final int[] slotsSides = new int[]{0, 1};
    private ItemStack[] inventory = new ItemStack[4];
    /**
     * inv[0] - fuel
     * inv[1] - item to work
     * inv[2] - result 1
     * inv[3] - result 2
     */
    private final int fuel_slot = 0;
    private final int input_slot = 1;
    private final int result_slot = 2;
    private final int second_result_slot = 3;

    protected int burnTime; // текущие время горения
    protected int fuelBurnTime; // время горения
    protected int currentItemEnergyProgress; // прогресс работы
    protected int currentItemEnergyNeed; // общая энергия требуемая

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

        if (index == input_slot && !flag) {
            //todo Выяснить значение этого действия
//            this.totalCookTime = 200;
//            this.cookTime = 0;
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            this.markDirty();
        }
    }

    /**
     * Gets the name of this command sender (usually username, but possibly "Rcon")
     */
    @Override
    abstract public String getName();
/*
*    {
*        return this.hasCustomName() ? this.machineCustomName : "nord.tile.abstractEnergyMachina";
*    }
*/

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
        currentItemEnergyProgress = compound.getShort("curEnergyProg");
        currentItemEnergyNeed = compound.getShort("curEnergyNeed");

        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
            this.machineCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setShort("energy", (short) this.getEnergy());
        compound.setShort("burnTime", (short) burnTime);
        compound.setShort("curBurnTime", (short) currentItemEnergyProgress);
        compound.setShort("curCookTime", (short) currentItemEnergyNeed);

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

        if(chargeFromFuel()){updated = true;}

        if (isWork() && canStartWorking()) { // работаем и можем ли работать ?
            currentItemEnergyProgress += getWorkPacketEnergy(); // прибавляем к прогрессу пакет работы
            this.subEnergy(getWorkPacketEnergy()); // отнимаем пакет энергии
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
        if (index == result_slot || index == second_result_slot) {
            return false;
        }

        IRecipe1I2O rec = getRecipe(stack);
        return rec != null;
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
            case 2:
                return this.currentItemEnergyProgress;
            case 3:
                return this.currentItemEnergyNeed;
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
            case 2:
                this.currentItemEnergyProgress = value;
                break;
            case 3:
                this.currentItemEnergyNeed = value;
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

    public boolean isWork() {
        return currentItemEnergyNeed > 0 && this.getEnergy() >= this.getPacketEnergy();
    }

    public boolean canStartWorking() {
        IRecipe1I2O rec = getRecipe(getStackInSlot(input_slot));
        if (rec == null) {
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            return false;
        }
        ItemStack input = getStackInSlot(input_slot);
        ItemStack result = getStackInSlot(result_slot);
        ItemStack secondResult = getStackInSlot(second_result_slot);
        ItemStack out1 = rec.getResult();
        ItemStack out2 = rec.getSecondResult();

        if (input.stackSize < rec.getInput().stackSize) { // не достаточно входных вещей
            return false;
        }

        if (result == null && secondResult == null) { // выходные слоты пусты
            return true;
        }

// в выходном слоте не наша вещь
        if ((result != null && out1 != null && !out1.isItemEqual(result)) ||
                (secondResult != null && out2 != null && !out2.isItemEqual(secondResult))) {
            return false;
        }
// хватает ли места
        int countResult = (result != null ? result.stackSize : 0) + (out1 != null ? out1.stackSize : 0);
        int countResult2 = (secondResult != null ? secondResult.stackSize : 0) + (out2 != null ? out2.stackSize : 0);

        boolean ret = countResult <= getInventoryStackLimit() &&
                countResult2 <= getInventoryStackLimit();

        if (!ret) {
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
        }

        return ret;
    }

    public boolean canWorkResult() {
        IRecipe1I2O rec = getRecipe(getStackInSlot(input_slot));
        if (rec == null) { // нет рецепта
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            return false;
        }

        return currentItemEnergyProgress >= rec.getEnergy() && canStartWorking();

    }

    /**
     * Обработать предмет
     */
    public void work() {
        IRecipe1I2O rec = getRecipe(getStackInSlot(input_slot));
        if (rec == null) {
            return;
        }

        ItemStack input = getStackInSlot(input_slot);
        ItemStack result = getStackInSlot(result_slot);
        ItemStack secondResult = getStackInSlot(second_result_slot);
        ItemStack out1 = rec.getResult();
        ItemStack out2 = rec.getSecondResult();

        boolean second = this.worldObj.rand.nextFloat() * 100 < rec.getPercent();
        if (out1 != null) {
            if (result != null) {
                result.stackSize += out1.stackSize;
            } else {
                result = out1.copy();
            }
            setInventorySlotContents(result_slot, result);
        }

        if (out2 != null && second) {
            if (secondResult != null) {
                secondResult.stackSize += out2.stackSize;
            } else {
                secondResult = out2.copy();
            }
            setInventorySlotContents(second_result_slot, secondResult);
        }

        input.stackSize -= rec.getInput().stackSize;

        if (input.stackSize == 0) {
            input = null;
        }
        setInventorySlotContents(input_slot, input);

        IRecipe1I2O recNext = getRecipe(input);
        currentItemEnergyNeed = recNext != null ? recNext.getEnergy() : 0;
        currentItemEnergyProgress = 0;
    }
    private boolean chargeFromFuel(){
        if (isEnergyStorage()) {
            ItemStack item = getStackInSlot(fuel_slot);
            if (item.getItem() instanceof IEnergyCharges) {
                IEnergyCharges charge = (IEnergyCharges) item.getItem();
                if (ChargeHelper.hasEnergy(item, charge.packetEnergy(item))
                        && this.getEnergy() <= (this.getMaxEnergy() - charge.packetEnergy(item))) {
                    this.addEnergy(charge.packetEnergy(item));
                    ChargeHelper.subEnergy(item, charge.packetEnergy(item));
                    return true; // помечаем на обновление
                }
            }
        }
        return false;
    }

    private boolean isEnergyStorage() {
        ItemStack item = getStackInSlot(fuel_slot);
        if(item == null) return false;
        if (item.getItem() instanceof IEnergyCharges) {
            IEnergyCharges charge = (IEnergyCharges) item.getItem();
            return charge.currectEnergy(item) >= charge.packetEnergy(item);
        }
        return false;
    }

    protected IRecipe1I2O getRecipe(ItemStack stack) {
        return ((IRecipes1I2O) getRecipes()).getRecipe(stack);
    }

    @Override
    public int getBurnTime() {
        return burnTime;
    }

    @Override
    public int getCurrentItemEnergyProgress() {
        return currentItemEnergyProgress;
    }

    @Override
    public int getCurrentItemEnergyNeed() {
        return currentItemEnergyNeed;
    }

    @Override
    public void setBurnTime(int val) {
        burnTime = val;
    }

    @Override
    public void setCurrentItemEnergyProgress(int val) {
        currentItemEnergyProgress = val;
    }

    @Override
    public void setCurrentItemEnergyNeed(int val) {
        currentItemEnergyNeed = val;
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
    public int getProgressScaled(int val) {
        return currentItemEnergyNeed == 0 ? 0 : currentItemEnergyProgress * val / currentItemEnergyNeed;
    }

    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int val) {
        return fuelBurnTime == 0 ? 0 : burnTime * val / fuelBurnTime;
    }
}
