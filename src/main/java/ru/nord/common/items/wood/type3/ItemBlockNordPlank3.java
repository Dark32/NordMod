package ru.nord.common.items.wood.type3;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockNordPlank3 extends ItemBlockMetadata {

    public ItemBlockNordPlank3(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumNordPlank3.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumNordPlank3.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
