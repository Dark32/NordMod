package ru.nord.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.nord.NordBloks;

/**
 * Created by lionzxy on 12.07.15.
 */
public class BlockDecoPlacer extends Block{
    public BlockDecoPlacer(){
        super(Material.iron);
    }
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        BlockPos newPos = new BlockPos(pos.getX()+1,pos.getY(),pos.getZ()+1);
        for(int arrPos=0;arrPos<16;arrPos++){
            for(int metaPos=0;metaPos<16;metaPos++){
        worldIn.setBlockState(newPos.add(arrPos,0,metaPos), NordBloks.decoStoneBlock[arrPos].getStateFromMeta(metaPos));}
            worldIn.setBlockState(newPos.add(arrPos,1,0), NordBloks.empireLamp1.getStateFromMeta(arrPos));
            worldIn.setBlockState(newPos.add(arrPos, 1, 1), NordBloks.empireFloorLamp1.getStateFromMeta(arrPos));
        }
        }
    public void onBlockDestroyedByPlayer(World worldIn, BlockPos pos, IBlockState state) {
        BlockPos newPos = new BlockPos(pos.getX()+1,pos.getY(),pos.getZ()+1);
        for(int arrPos=0;arrPos<16;arrPos++){
            for(int metaPos=0;metaPos<16;metaPos++){
        worldIn.destroyBlock(newPos.add(arrPos,0,metaPos),false);}
        worldIn.destroyBlock(newPos.add(arrPos, 1, 1),false);
            worldIn.destroyBlock(newPos.add(arrPos,1,0),false);
        }
    }
}
