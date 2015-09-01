package ru.nord_core.common.tiles.abstracts;

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
import ru.nord_core.common.helpers.RecipesHelper;
import ru.nord_core.common.items.interfaces.IEnergyCharges;
import ru.nord_core.common.recipes.interfaces.IAbstractRecipes;
import ru.nord_core.common.recipes.interfaces.IRecipe2I2O;
import ru.nord_core.common.recipes.interfaces.IRecipes2I2O;
import ru.nord_core.common.tiles.interfaces.IMachine;
import ru.nord_core.common.utils.ChargeHelper;
import ru.nord_core.common.utils.Fuel;

/**
 * @author andrew
 *         Абстрактная машина с 2 входами, 2 выходами и энергией
 */
public abstract class TileAbstractEnergyMachineDoubleInput extends TileAbstractEnergyMachine
        implements IMachine {

    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{3, 4};
    private static final int[] slotsSides = new int[]{1, 2};
    private ItemStack[] inventory = new ItemStack[5];

    private final int fuel_slot = 0;
    private final int input_slot = 1;
    private final int input_slot2 = 2;
    private final int result_slot = 3;
    private final int second_result_slot = 4;

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

        if ((index == input_slot || index == input_slot2) && !flag) {
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


        this.setEnergy(compound.getInteger("energy"));
        burnTime = compound.getShort("burnTime");
        currentItemEnergyProgress = compound.getShort("curEnergyProg");
        currentItemEnergyNeed = compound.getShort("curEnergyNeed");
        recipeId = compound.getInteger("recipeId");

        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) {
            this.machineCustomName = compound.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);

        compound.setInteger("energy", (short) this.getEnergy());
        compound.setShort("burnTime", (short) burnTime);
        compound.setShort("curEnergyProg", (short) currentItemEnergyProgress);
        compound.setShort("curEnergyNeed", (short) currentItemEnergyNeed);
        compound.setInteger("recipeId", this.recipeId);

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

        if (isWork() && canStartWorking()) { // работаем и можем ли работать ?
            currentItemEnergyProgress += getWorkPacketEnergy(); // прибавляем к прогрессу пакет работы
            this.subEnergy(getWorkPacketEnergy()); // отнимаем пакет энергии
            updated = true; // помечаем на обновление
            if (canWorkResult()) { // можем ли получить результат работы
                work(); // работаем
            }
        } else if (canStartWorking()) { // можем ли начать работать
            IRecipe2I2O rec = getRecipe(this.recipeId); // получаем рецепт
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

    protected boolean burnFuel() {
        boolean updated = false;
        if (isBurning()) {
            if (burnTime >= getBurnPacketEnergy()) { // есть ли пакет в горение
                burnTime -= getBurnPacketEnergy(); // убавляем на пакет время горения
                this.addEnergy(getBurnPacketEnergy()); // прибавляем энергию на пакет
            } else { // если нет, то просто
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

        IRecipe2I2O rec = getPartRecipe(stack);
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
        if (!this.checkRecipe()) {
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            return false;
        }
        IRecipe2I2O rec = getRecipe(this.recipeId);
        ItemStack input = getStackInSlot(input_slot);
        ItemStack input2 = getStackInSlot(input_slot2);
        ItemStack result = getStackInSlot(result_slot);
        ItemStack secondResult = getStackInSlot(second_result_slot);
        ItemStack out1 = rec.getResult();
        ItemStack out2 = rec.getSecondResult();

        if (input.stackSize < rec.getInput().stackSize) { // не достаточно входных вещей
            return false;
        }
        if (input2.stackSize < rec.getSecondInput().stackSize) { // не достаточно входных вещей
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
        if (!this.checkRecipe()) { // нет рецепта
            currentItemEnergyProgress = 0;
            currentItemEnergyNeed = 0;
            return false;
        }
        IRecipe2I2O rec = getRecipe(this.recipeId);
        return currentItemEnergyProgress >= rec.getEnergy() && canStartWorking();

    }

    /**
     * Обработать предмет
     */
    public void work() {
        if (!this.checkRecipe()) {
            return;
        }
        IRecipe2I2O rec = getRecipe(this.recipeId);
        ItemStack input = getStackInSlot(input_slot);
        ItemStack input2 = getStackInSlot(input_slot2);
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
        input2.stackSize -= rec.getSecondInput().stackSize;

        if (input.stackSize == 0) {
            input = null;
        }
        if (input2.stackSize == 0) {
            input2 = null;
        }
        setInventorySlotContents(input_slot, input);
        setInventorySlotContents(input_slot2, input2);
        this.checkRecipe();
        IRecipe2I2O recNext = getRecipe(this.recipeId);
        currentItemEnergyNeed = recNext != null ? recNext.getEnergy() : 0;
        currentItemEnergyProgress = 0;
    }

    protected boolean chargeFromFuel() {
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
        if (item == null) return false;
        if (item.getItem() instanceof IEnergyCharges) {
            IEnergyCharges charge = (IEnergyCharges) item.getItem();
            return charge.currectEnergy(item) >= charge.packetEnergy(item);
        }
        return false;
    }

    private IRecipe2I2O getPartRecipe(ItemStack stack) {
        return ((IRecipes2I2O) getRecipes()).getPartRecipe(stack, 0);
    }

    protected boolean checkRecipe() {
        ItemStack stack = getStackInSlot(input_slot);
        ItemStack stack2 = getStackInSlot(input_slot2);
        if (stack == null) return false;
        if (this.recipeId != IAbstractRecipes.NOT_FOUND) {
            IRecipe2I2O rec = getRecipe(this.recipeId);
            if (RecipesHelper.compare(stack,rec.getInput(),
                    stack2,rec.getSecondInput(),
                    rec.getSoft(),false)) {
                return true;
            }
        }
        this.recipeId = ((IRecipes2I2O) getRecipes()).getIndexRecipe(stack, stack2);
        return this.recipeId != IAbstractRecipes.NOT_FOUND;

    }

    private IRecipe2I2O getRecipe(int index) {
        return ((IRecipes2I2O) getRecipes()).getRecipe(index);
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
