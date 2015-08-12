package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.Container;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import ru.nord.common.utils.Version;

public abstract class BlockAbstractContainer  extends BlockContainer {

    private String unlocalizedName;

    protected BlockAbstractContainer(Material mat) {
        super(mat);
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
    public boolean hasComparatorInputOverride()
    {
        return true;
    }

    public int getComparatorInputOverride(World worldIn, BlockPos pos)
    {
        return Container.calcRedstone(worldIn.getTileEntity(pos));
    }
}
