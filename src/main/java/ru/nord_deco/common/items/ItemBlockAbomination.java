package ru.nord_deco.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_deco.common.utils.enums.EnumAbomination;

public class ItemBlockAbomination extends ItemBlockMetadata {
    public ItemBlockAbomination(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumAbomination.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumAbomination.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
