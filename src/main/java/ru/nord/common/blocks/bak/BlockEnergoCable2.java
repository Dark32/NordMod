package ru.nord.common.blocks.bak;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.nord.common.tiles.TileEnergyCable2;
import ru.nord_core.common.blocks.abstracts.bak.BlockAbstractEnergyCable2;

public  class BlockEnergoCable2 extends BlockAbstractEnergyCable2 {

    public BlockEnergoCable2() {
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEnergyCable2();
    }


    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.onBlockPlaced(worldIn,pos,facing,hitX,hitY,hitZ,meta,placer);
    }


    @Override
    public void doItemDrop(World world, BlockPos pos, TileEntity tile) {

    }
}
