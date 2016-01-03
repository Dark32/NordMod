package ru.nord_deco.common.blocks;

/**
 * Created by andrew on 19.12.15.
 * Block non contain TileEntity!
 */
public  class BlockDoubleHalfSlabTile extends BlockHalfSlabTile {
    public BlockDoubleHalfSlabTile() {
        super();
    }

    @Override
    public  boolean isDouble() {
        return true;
    }
}
