package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.interfaces.IWorkable;
import ru.nord_core.common.blocks.interfaces.IWrenchable;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyBlock;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachine;
import ru.nord_core.common.utils.Constants;

import java.util.Random;

abstract public class BlockAbstractMachine extends BlockRotatebleContainer
        implements IWrenchable, IWorkable {

    protected BlockAbstractMachine(Material mat) {
        super(mat, Version.MODID);
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntity tileentity = worldIn.getTileEntity(pos);

        if (tileentity instanceof TileAbstractEnergyMachine) {
            InventoryHelper.dropInventoryItems(worldIn, pos, (TileAbstractEnergyMachine) tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
        }
        super.breakBlock(worldIn, pos, state);
    }

    @Override
    public boolean hasWorking(IBlockAccess world, BlockPos pos) {
        TileAbstractEnergyMachine tile = (TileAbstractEnergyMachine) world.getTileEntity(pos);
        return tile != null && tile.isWork();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        boolean isActive = hasWorking(worldIn, pos);
        if (isActive) {
            EnumFacing enumfacing = state.getValue(FACING);
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            switch (SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()]) {
                case 1:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case 2:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case 3:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D);
                    break;
                case 4:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public void onBlockPlacedBy(final World world, final BlockPos coord, final IBlockState bs, final EntityLivingBase player, final ItemStack item) {
        super.onBlockPlacedBy(world, coord, bs, player, item);
        if (item.hasTagCompound() && item.getTagCompound().hasKey("energy")) {
            TileAbstractEnergyBlock st = (TileAbstractEnergyBlock) world.getTileEntity(coord);
            st.setEnergy(item.getTagCompound().getInteger("energy"));
        }
    }

    /**
     * Создаём предмет с NBT. вызывается в {@link #doItemDrop}
     *
     * @param te тайл
     * @return стак
     */
    private ItemStack createItem(TileAbstractEnergyBlock te) {
        ItemStack item = new ItemStack(this, 1);
        NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setInteger("energy", te.getEnergy());
        dataTag.setInteger("maxEnergy", te.getMaxEnergy());
        item.setTagCompound(dataTag);
        displayInformation(item);
        return item;
    }

    @Override
    public void doItemDrop(final World world, final BlockPos pos, TileEntity tile) {
        if (!world.isRemote && !world.restoringBlockSnapshots) {
            if (tile instanceof TileAbstractEnergyBlock) {
                ItemStack item = createItem((TileAbstractEnergyBlock) tile);
                spawnAsEntity(world, pos, item);
            } else {
                ItemStack item = new ItemStack(this, 1);
                spawnAsEntity(world, pos, item);
            }
        }
    }

    /**
     * Информация для отображения, вызывается в {@link #createItem}
     *
     * @param stack стак
     */
    private void displayInformation(ItemStack stack) {
        NBTTagCompound dataTag = !stack.hasTagCompound() ? new NBTTagCompound() : stack.getTagCompound();
        String message = dataTag.hasKey("energy") && dataTag.hasKey("maxEnergy") ? "Energy: " + TextFormatting.RED + dataTag.getInteger("energy") / Constants.SHARE_MULTIPLE
                + "/" + dataTag.getInteger("maxEnergy") / Constants.SHARE_MULTIPLE + " " + Constants.ENERGY : "Energy: 0/0 " + Constants.ENERGY;

        NBTTagCompound displayTag;
        if (dataTag.hasKey("display")) {
            displayTag = dataTag.getCompoundTag("display");
        } else {
            displayTag = new NBTTagCompound();
            dataTag.setTag("display", displayTag);
        }

        NBTTagList loreTag;
        if (dataTag.hasKey("Lore")) {
            loreTag = displayTag.getTagList("Lore", net.minecraftforge.common.util.Constants.NBT.TAG_STRING);
        } else {
            loreTag = new NBTTagList();
            displayTag.setTag("Lore", loreTag);
        }
        loreTag.appendTag(new NBTTagString(message));
        if (!stack.hasTagCompound()) {
            stack.setTagCompound(new NBTTagCompound());
        }
    }

    @Override
    public EnumActionResult wrenche(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) {
            this.harvesters.set(playerIn);
            TileAbstractEnergyBlock tile = (TileAbstractEnergyBlock) worldIn.getTileEntity(pos);
            InventoryHelper.dropInventoryItems(worldIn, pos, tile);
            this.doItemDrop(worldIn, pos, tile);
            this.removedByPlayer(state, worldIn, pos, playerIn, true);
            this.harvesters.set(null);
            return EnumActionResult.SUCCESS;
        } else {
            worldIn.setBlockState(pos, state.withProperty(FACING, side));
            return EnumActionResult.FAIL;
        }
    }
}
