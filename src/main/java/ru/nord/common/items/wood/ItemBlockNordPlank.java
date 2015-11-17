package ru.nord.common.items.wood;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import ru.nord.common.blocks.wood.BlockNordLog;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockNordPlank extends ItemBlockMetadata {
    private final Block block;

    public ItemBlockNordPlank(Block block)
    {
        super(block);
        this.block = block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage | 4;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + EnumNordPlank.byMetadata(stack.getMetadata()).getName();
    }
}
