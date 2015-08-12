package ru.nord_core.common.items;


import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.nord_core.common.blocks.interfaces.IWrenchable;
import ru.nord_core.common.items.ItemBase;
import ru.nord_core.common.items.interfaces.IWrench;

public class ItemWrench extends ItemBase implements IWrench {
    public ItemWrench(String modid) {
        super(modid);
    }
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block!=null && !worldIn.isRemote && block instanceof IWrenchable){
            return ((IWrenchable)block).wrenche(worldIn,pos,worldIn.getBlockState(pos),playerIn,side,hitX,hitY,hitZ);
        }else{
            return false;
        }
    }
}