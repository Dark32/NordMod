package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nord.Nord;
import ru.nord.common.blocks.abstracts.BlockAbstractMachina;
import ru.nord.common.tiles.TileGenerator;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyGenerator;
import ru.nord.common.tiles.abstracts.TileAbstractEnergyMachina;

public class BlockGenerator extends BlockAbstractMachina {

    public BlockGenerator() {
        super(Material.rock);
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
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
    protected boolean getWork(IBlockAccess world, BlockPos pos) {
        TileAbstractEnergyGenerator tile = (TileAbstractEnergyGenerator) world.getTileEntity(pos);
        return tile != null && tile.isBurning();
    }
}
