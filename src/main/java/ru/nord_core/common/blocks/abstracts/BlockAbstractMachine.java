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
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.interfaces.IWrenchable;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyBlock;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachine;
import ru.nord_core.common.utils.Constants;

import java.util.Random;

abstract public class BlockAbstractMachine extends BlockRotatebleContainer implements IWrenchable {

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
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
//        this.wrenche(worldIn,pos,state,playerIn,side,hitX,hitY,hitZ);
        return true;
    }

    protected boolean getWork(IBlockAccess world, BlockPos pos) {
        TileAbstractEnergyMachine tile = (TileAbstractEnergyMachine) world.getTileEntity(pos);
        return tile != null && tile.isWork();
    }


    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        boolean isActive = getWork(worldIn, pos);
        if (isActive) {
            EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
            double d2 = (double) pos.getZ() + 0.5D;
            double d3 = 0.52D;
            double d4 = rand.nextDouble() * 0.6D - 0.3D;

            switch (SwitchEnumFacing.FACING_LOOKUP[enumfacing.ordinal()]) {
                case 1:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case 2:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d3, d1, d2 + d4, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case 3:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    break;
                case 4:
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
                    worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + d3, 0.0D, 0.0D, 0.0D, new int[0]);
            }
        }
    }

    @Override
    public boolean wrenche(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        return false;
    }

    @Override
    public void onBlockPlacedBy(final World world, final BlockPos coord, final IBlockState bs, final EntityLivingBase player, final ItemStack item) {
        super.onBlockPlacedBy(world, coord, bs, player, item);
        if( item.hasTagCompound() && item.getTagCompound().hasKey("energy")){
            TileAbstractEnergyBlock st = (TileAbstractEnergyBlock)world.getTileEntity(coord);
             st.setEnergy(item.getTagCompound().getInteger("energy"));
        }
    }

    public ItemStack createItem(TileAbstractEnergyBlock te) {
        ItemStack item = new ItemStack(this, 1);
        NBTTagCompound dataTag = new NBTTagCompound();
        dataTag.setInteger("energy", te.getEnergy());
        dataTag.setInteger("maxEnergy", te.getMaxEnergy());
        item.setTagCompound(dataTag);
        displayInformation(item);
        return item;
    }

    public void doItemDrop(final World world, final BlockPos coord, TileEntity te ) {
        if (!world.isRemote && !world.restoringBlockSnapshots) {
            if(te instanceof TileAbstractEnergyBlock){
                ItemStack item = createItem((TileAbstractEnergyBlock)te);
                spawnAsEntity(world, coord, item);
            } else {
                ItemStack item = new ItemStack(this,1);
                spawnAsEntity(world, coord, item);
            }
        }
    }

    public void displayInformation(ItemStack stack){
        NBTTagCompound dataTag = !stack.hasTagCompound() ? new NBTTagCompound() : stack.getTagCompound();
        String message = dataTag.hasKey("energy") && dataTag.hasKey("maxEnergy") ? "Energy: " + EnumChatFormatting.RED + dataTag.getInteger("energy") / Constants.SHARE_MULTIPLE
                + "/" + dataTag.getInteger("maxEnergy") / Constants.SHARE_MULTIPLE + " " + Constants.ENERGY : "Energy: 0/0 " + Constants.ENERGY;

        NBTTagCompound displayTag;
        if(dataTag.hasKey("display")){
            displayTag = dataTag.getCompoundTag("display");
        } else {
            displayTag = new NBTTagCompound();
            dataTag.setTag("display", displayTag);
        }

        NBTTagList loreTag;
        if(dataTag.hasKey("Lore")){
            loreTag = displayTag.getTagList("Lore", net.minecraftforge.common.util.Constants.NBT.TAG_STRING);
        } else {
            loreTag = new NBTTagList();
            displayTag.setTag("Lore", loreTag);
        }
        loreTag.appendTag(new NBTTagString(message));
        if(!stack.hasTagCompound()){
            stack.setTagCompound(new NBTTagCompound());
        }
    }
}
