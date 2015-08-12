package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.EnumMetal;

public class ItemBlockClearMetal extends ItemBlock {
    public ItemBlockClearMetal(Block block)
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
            return super.getUnlocalizedName() + "." + EnumClearMetal.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
