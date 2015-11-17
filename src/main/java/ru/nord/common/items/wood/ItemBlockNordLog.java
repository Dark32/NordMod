package ru.nord.common.items.wood;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.blocks.wood.BlockNordLeaves;
import ru.nord.common.utils.enums.EnumNordPlank;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumClearMetal;

public class ItemBlockNordLog extends ItemBlockMetadata {
    private final Block block;

    public ItemBlockNordLog(Block block)
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
