package ru.nord.common.items.wood.type4;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.EnumMetal;

public class ItemBlockNordSapling4 extends ItemBlockMetadata {
    public ItemBlockNordSapling4(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumNordPlank4.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumNordPlank4.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }

}
