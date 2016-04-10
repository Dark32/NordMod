package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import ru.nord.Nord;
import ru.nord.common.tiles.TileWasher;
import ru.nord_core.common.blocks.abstracts.BlockAbstractMachine;
import ru.nord_core.common.helpers.FluidHelper;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachine;
import ru.nord_core.common.tiles.interfaces.IFluidTankBlock;

public class BlockWasher extends BlockAbstractMachine {

    public BlockWasher() {
        super(Material.rock);
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {

        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity == null || playerIn.isSneaking()) {
            return false;
        }

        if (worldIn.isRemote) {
            return true;
        }

        ItemStack item = playerIn.getHeldItemMainhand();
        if (item != null && FluidContainerRegistry.isContainer(item)) {
            IFluidTankBlock te = (IFluidTankBlock) tileEntity;
            FluidStack fluid = FluidContainerRegistry.getFluidForFilledItem(item);
            if (fluid != null && fluid.getFluid() == FluidRegistry.WATER) {
                if (te.getTank().getFluidAmount() <= 0 && te.getTank().getCapacity() >= fluid.amount) {
                    // В пустой танк
                    te.getTank().setFluid(fluid);
                    FluidHelper.bucktDrain(playerIn, item, worldIn);
                    return true;
                } else if (te.getTank().getFluid().getFluid().equals(fluid.getFluid())
                        && (te.getTank().getFluidAmount() + fluid.amount) <= te.getTank().getCapacity()) {
                    //В не пустой танк
                    te.getTank().fill(fluid, true);
                    FluidHelper.bucktDrain(playerIn, item, worldIn);
                    tileEntity.markDirty();
                    return true;
                } else {
                    return false;
                }
            }
        }
        playerIn.openGui(Nord.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileWasher();
    }

    @Override
    public boolean hasWorking(IBlockAccess world, BlockPos pos) {
        TileAbstractEnergyMachine tile = (TileAbstractEnergyMachine) world.getTileEntity(pos);
        return tile != null && tile.isWork();
    }
}
