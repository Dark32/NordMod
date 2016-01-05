package ru.nord_deco.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import ru.nord_deco.common.utils.Version;

public class BlockAbstractStairs extends BlockStairs {
    private String unlocalizedName;
    public BlockAbstractStairs(IBlockState modelState) {
        super(modelState);
        this.useNeighborBrightness = true;
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." + Version.MODID + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
}
