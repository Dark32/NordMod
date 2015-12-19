package ru.nord_deco.common.items;

import net.minecraft.block.Block;
import ru.nord_deco.common.blocks.BlockHalfSlabTile;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;
import ru.nord_deco.common.items.abstracts.ItemBlockAbstractSlab;

/**
 * Created by andrew on 19.12.15.
 */
public class ItemBlockSlabTile1 extends ItemBlockAbstractSlab{
    public ItemBlockSlabTile1(Block block, BlockHalfSlabTile singleSlab, BlockHalfSlabTile doubleSlab) {
        super(block, singleSlab, doubleSlab);
    }
}
