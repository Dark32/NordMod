package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord.common.utils.enums.EnumEmpGlass;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockEmpGlass extends ItemBlockMetadata {
    public ItemBlockEmpGlass(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumEmpGlass.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumEmpGlass.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
