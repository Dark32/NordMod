package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nord.common.blocks.abstracts.BlockAbstractMachina;
import ru.nord.common.lib.utils.Constants;
import ru.nord.common.tiles.TileEnergyCable;
import ru.nord.common.tiles.interfaces.IEnergoCable;

public class BlockEnergoCable extends BlockAbstractMachina {

    public BlockEnergoCable() {
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

    @Override
    protected boolean getWork(IBlockAccess world, BlockPos pos) {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    private void updateNeighbors(World worldIn, BlockPos pos){
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof IEnergoCable){
            ((IEnergoCable)tileEntity).updateNeighbors();
        }
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        updateNeighbors(worldIn, pos);
    }
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return super.onBlockPlaced(worldIn,pos,facing,hitX,hitY,hitZ,meta,placer);
    }
}
