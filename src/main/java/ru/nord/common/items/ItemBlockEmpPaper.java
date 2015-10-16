package ru.nord.common.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumCrystal;
import ru.nord_core.common.utils.enums.EnumMetal;

public class ItemBlockEmpPaper extends ItemBlockMetadata {
    public ItemBlockEmpPaper(Block block)
    {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumPaperEmp.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumPaperEmp.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
