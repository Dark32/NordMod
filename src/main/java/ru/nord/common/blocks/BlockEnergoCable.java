package ru.nord.common.blocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.nord.common.blocks.abstracts.BlockAbstractCable;
import ru.nord.common.tiles.TileEnergyCable;
import ru.nord.common.tiles.interfaces.IEnergoCable;

public class BlockEnergoCable extends BlockAbstractCable {

    public BlockEnergoCable() {
        setHardness(2.0F);
        setResistance(5.0F);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity == null || playerIn.isSneaking()) {
            return false;
        }
        if (tileEntity instanceof IEnergoCable) {
            int energy = ((IEnergoCable) tileEntity).getEnergy();
            int maxenergy = ((IEnergoCable) tileEntity).getMaxEnergy();
            int packet = ((IEnergoCable) tileEntity).getPacketEnergy();
            if(!worldIn.isRemote) {
                playerIn.addChatComponentMessage(new ChatComponentTranslation("energy " + energy+"/"+maxenergy +"("+packet+")"));
            }
         }
            //playerIn.openGui(Nord.instance, 2, worldIn, pos.getX(), pos.getY(), pos.getZ());
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEnergyCable();
    }


    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.onBlockPlaced(worldIn,pos,facing,hitX,hitY,hitZ,meta,placer);
    }
}
