package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.Nord;
import ru.nord_core.common.blocks.abstracts.BlockAbstractMachine;
import ru.nord.common.tiles.TileAccumulator;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyAccumulator;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachine;
import ru.nord_core.common.tiles.interfaces.IEnergoCable;

public class BlockAccumulator extends BlockAbstractMachine {

    public BlockAccumulator() {
        super(Material.rock);
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    EntityPlayer playerIn, EnumHand hand, ItemStack heldItem,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(worldIn, pos, state, playerIn,hand,heldItem, side, hitX, hitY, hitZ);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity == null || playerIn.isSneaking()) {
            return false;
        }
        playerIn.openGui(Nord.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileAccumulator();
    }

    @Override
    protected boolean getWork(IBlockAccess world, BlockPos pos) {
        TileEntity otile = world.getTileEntity(pos);
        if (otile instanceof TileAbstractEnergyAccumulator ){
            TileAbstractEnergyAccumulator tile = (TileAbstractEnergyAccumulator) world.getTileEntity(pos);
            return tile != null && (tile.getCharge() || tile.getDisCharge());
        }
        return false;
    }
    public boolean isFullCube()
    {
        return false;
    }
    public boolean isOpaqueCube()
    {
        return false;
    }
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer()
    {
        return BlockRenderLayer.CUTOUT_MIPPED;
    }
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }


}
