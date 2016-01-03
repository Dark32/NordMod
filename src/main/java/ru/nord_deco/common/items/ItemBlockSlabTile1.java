package ru.nord_deco.common.items;

import net.minecraft.block.Block;
import ru.nord_deco.common.blocks.BlockDoubleHalfSlabTile;
import ru.nord_deco.common.blocks.BlockHalfSlabTile;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;
import ru.nord_deco.common.items.abstracts.ItemBlockAbstractSlab;

public class ItemBlockSlabTile1 extends ItemBlockAbstractSlab{
    public ItemBlockSlabTile1(Block block, BlockHalfSlabTile singleSlab, BlockDoubleHalfSlabTile doubleSlab, final Boolean stacked) {
        super(block, singleSlab, doubleSlab,stacked);
    }
}
