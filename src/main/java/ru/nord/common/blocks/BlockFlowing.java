package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.nord.Nord;
import ru.nord_core.common.blocks.abstracts.BlockAbstractMachine;
import ru.nord.common.tiles.TileFlowing;

public class BlockFlowing extends BlockAbstractMachine {

        public BlockFlowing() {
                super(Material.rock);
                setHardness(2.0F);
                setResistance(5.0F);
        }

        @Override
        public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
        {
            super.onBlockActivated(worldIn, pos, state, playerIn, side, hitX, hitY, hitZ);
            TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (tileEntity == null || playerIn.isSneaking()) {
                        return false;
                }
            playerIn.openGui(Nord.instance, 2, worldIn, pos.getX(), pos.getY(),pos.getZ());
                return true;
        }

        @Override
        public TileEntity createNewTileEntity(World world, int meta) {
                return new TileFlowing();
        }

}
