package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord.common.utils.enums.EnumWhiteStone;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockWhiteStone extends ItemBlockMetadata {
    public ItemBlockWhiteStone(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumWhiteStone.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumWhiteStone.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
