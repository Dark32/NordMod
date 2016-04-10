package ru.nord_core.common.items;


import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.nord_core.common.blocks.interfaces.IWrenchable;
import ru.nord_core.common.items.interfaces.IWrench;

public class ItemWrench extends ItemBase implements IWrench {
    public ItemWrench(String modid) {
        super(modid);
    }

    @Override
    public EnumActionResult onItemUse(ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        if (block!=null && !worldIn.isRemote && block instanceof IWrenchable){
            return ((IWrenchable)block).wrenche(worldIn,pos,worldIn.getBlockState(pos),playerIn,facing,hitX,hitY,hitZ);
        }else{
            return EnumActionResult.PASS;
        }
    }
}