package ru.nord_deco.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_deco.common.utils.enums.EnumChairType;

public class ItemBlockChair extends ItemBlockMetadata {
    public ItemBlockChair(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + EnumChairType.byMetadata(stack.getMetadata()).getName();
    }

}