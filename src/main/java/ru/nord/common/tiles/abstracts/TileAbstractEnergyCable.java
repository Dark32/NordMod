package ru.nord.common.tiles.abstracts;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3i;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.lib.utils.enums.EnumNeighbors;
import ru.nord.common.tiles.interfaces.IAccumulator;
import ru.nord.common.tiles.interfaces.IEnergoCable;
import ru.nord.common.tiles.interfaces.IEnergyTile;

/**
 * @author andrew
 */
public abstract class TileAbstractEnergyCable extends TileAbstractEnergyBlock
        implements IEnergoCable {
    private static final int[] slotsTop = new int[]{0};
    private static final int[] slotsBottom = new int[]{0};
    private static final int[] slotsSides = new int[]{0};

    //    private Vec3i[] _neighbors = new Vec3i[]{
//            EnumFacing.DOWN.getDirectionVec(),  // 0
//            EnumFacing.UP.getDirectionVec(),    // 1
//            EnumFacing.NORTH.getDirectionVec(), // 2
//            EnumFacing.SOUTH.getDirectionVec(), // 3
//            EnumFacing.EAST.getDirectionVec(),  // 4
//            EnumFacing.WEST.getDirectionVec()   // 5
//    };
    private ItemStack[] inventory = new ItemStack[1];
    private final int upgrade = 0;
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
    public abstract String getName();

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
        int energy = compound.getInteger("energy");
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
        if (sendEnergy()) {
            updated = true;
        }
//        System.err.println(this.getEnergy());
        if (updated) { // если поменчено на обновление
            this.markDirty(); // обновляем
        }
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

        if (index == upgrade) {
            return false;
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
        return false;
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

    @SideOnly(Side.CLIENT)
    public int getEnergyProgressScaled(int val) {
        return this.getEnergy() * val / this.getMaxEnergy();
    }

    @Override
    public EnumNeighbors[] updateNeighbors() {
        EnumNeighbors[] neighbors = new EnumNeighbors[6];
        for (int i = 0; i <= 5; i++) {
            neighbors[i] = validateNeighbors(i);
        }
        return neighbors;
    }

    @Override
    public boolean sendEnergy() {
        EnumNeighbors[] __neighbors = updateNeighbors();
        boolean flag = false;
        for (int i = 0; i <= 5; i++) {
            EnumNeighbors __neighbor = __neighbors[i];
            if (__neighbor == EnumNeighbors.INVALID) { // если сосед не энерго тайл
                continue; // пропустим
            }
            BlockPos _pos = getNeighborACoordinate(i);
            IEnergyTile energyTile = (IEnergyTile) this.worldObj.getTileEntity(_pos);

            if (__neighbor == EnumNeighbors.DISPENSER) {
                sendDISPENSER(energyTile);
            } else if (__neighbor == EnumNeighbors.ACCEPTOR) {
                sendACCEPTOR(energyTile);

            } else if (__neighbor == EnumNeighbors.CABLE) {
                sendCABLE(energyTile);
            }else if (__neighbor == EnumNeighbors.ACCUMULATOR_ACCEPTOR) {
                IAccumulator accumulatorTile =  (IAccumulator)energyTile;
                sendACCUMULATOR_ACCEPTOR(accumulatorTile);

            }else if (__neighbor == EnumNeighbors.ACCUMULATOR_DISPENSER) {
                sendDISPENSER(energyTile);
            }
            flag = true;
        }
        return flag;
    }

    private void sendDISPENSER(IEnergyTile energyTile) {
        if (energyTile.hasSubEnergy(this.getPacketEnergy())
                && this.hasAddEnergy(this.getPacketEnergy())) {
            energyTile.subEnergy(this.getPacketEnergy());
            this.addEnergy(this.getPacketEnergy());
        }
    }
    private void sendACCUMULATOR_ACCEPTOR(IAccumulator accumulatorTile) {
        if (accumulatorTile.hasAddBonusEnergy(this.getPacketEnergy())
                && this.hasSubEnergy(this.getPacketEnergy())){
            accumulatorTile.addBonusEnergy(this.getPacketEnergy());
            this.subEnergy(this.getPacketEnergy());
        }else{
            sendACCEPTOR(accumulatorTile);
        }
    }
    private void sendACCEPTOR(IEnergyTile energyTile) {
        if (energyTile.hasAddEnergy(this.getPacketEnergy())
                && this.hasSubEnergy(this.getPacketEnergy())) {
            energyTile.addEnergy(this.getPacketEnergy());
            this.subEnergy(this.getPacketEnergy());
        }
    }

    private void sendCABLE(IEnergyTile energyTile) {
        if (energyTile.hasAddEnergy(this.getPacketEnergy())
                && this.hasSubEnergy(this.getPacketEnergy())
                && this.getEnergy() + this.getPacketEnergy() > energyTile.getEnergy()) {
            energyTile.addEnergy(this.getPacketEnergy());
            this.subEnergy(this.getPacketEnergy());
        }
    }

    @Override
    public EnumNeighbors validateNeighbors(int index) {
        BlockPos pos = new BlockPos(getNeighborACoordinate(index));
        TileEntity tile = this.worldObj.getTileEntity(pos);
        if (tile != null && tile instanceof IEnergyTile) {
            if (tile instanceof IAccumulator) {
                if (index == 0) {
                    return EnumNeighbors.ACCUMULATOR_DISPENSER;
                } else {
                    return EnumNeighbors.ACCUMULATOR_ACCEPTOR;
                }
            }
            if (tile instanceof IEnergoCable) return EnumNeighbors.CABLE;
            if (((IEnergyTile) tile).isAcepter()) return EnumNeighbors.ACCEPTOR;
            if (((IEnergyTile) tile).isDispenser()) return EnumNeighbors.DISPENSER;
        }
        return EnumNeighbors.INVALID;
    }

    @Override
    public BlockPos getNeighborACoordinate(int index) {
        if (index > EnumFacing.values().length) index = 0;
        Vec3i coord = EnumFacing.values()[index].getDirectionVec();
        return new BlockPos(
                pos.getX() + coord.getX(),
                pos.getY() + coord.getY(),
                pos.getZ() + coord.getZ()
        );
    }
}
