package ru.nord_core.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockBase extends Block {
    private String unlocalizedName;
    protected String modid;
    public BlockBase(String modid,Material mat) {
        super(mat);
        this.modid=modid;
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." +modid + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }
}