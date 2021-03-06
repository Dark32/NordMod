package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nord.Nord;
import ru.nord_core.common.blocks.abstracts.BlockAbstractMachine;
import ru.nord.common.tiles.TileGenerator;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyGenerator;

public class BlockGenerator extends BlockAbstractMachine {

    public BlockGenerator() {
        super(Material.rock);
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state,
                                    EntityPlayer playerIn, EnumHand hand, ItemStack heldItem,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        super.onBlockActivated(worldIn, pos, state, playerIn, hand,heldItem,side, hitX, hitY, hitZ);
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity == null || playerIn.isSneaking()) {
            return false;
        }
        playerIn.openGui(Nord.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileGenerator();
    }

    @Override
    public boolean hasWorking(IBlockAccess world, BlockPos pos) {
        TileAbstractEnergyGenerator tile = (TileAbstractEnergyGenerator) world.getTileEntity(pos);
        return tile != null && tile.isBurning();
    }

}
