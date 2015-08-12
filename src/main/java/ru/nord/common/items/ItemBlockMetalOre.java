package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.utils.enums.EnumOre;

import java.util.List;

public class ItemBlockMetalOre extends ItemBlock {
    public ItemBlockMetalOre(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumOre.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumOre.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add(EnumOre.byMetadata(stack.getMetadata()).getMetal());
    }

}
