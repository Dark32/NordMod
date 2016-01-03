package ru.nord_deco.common.items.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;

public class ItemBlockAbstractSlab extends ItemSlab
{
    public ItemBlockAbstractSlab(Block block, BlockAbstractSlab singleSlab, BlockAbstractSlab doubleSlab , final boolean stacked)
    {
        super(block, singleSlab, doubleSlab);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

}