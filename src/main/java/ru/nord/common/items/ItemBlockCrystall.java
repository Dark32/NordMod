package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.utils.enums.EnumCrystal;
import ru.nord_core.common.utils.enums.EnumMetal;

public class ItemBlockCrystall extends ItemBlock {
    public ItemBlockCrystall(Block block)
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
        if (meta < EnumMetal.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumCrystal.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
