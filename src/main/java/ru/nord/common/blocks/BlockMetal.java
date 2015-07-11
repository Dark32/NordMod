package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.items.ItemIngot;

import java.util.List;

/**
 * Created by nikit_000 on 08.07.2015.
 */
public class BlockMetal extends Block {
    public BlockMetal(){
        super(Material.iron);
    }
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + ItemIngot.nameIngot[stack.getMetadata()];
    }
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list)
    {
        for (int i = 0; i < ItemIngot.nameIngot.length; ++i)
        {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }
}
