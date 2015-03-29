package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nord.common.lib.utils.Version;

public class BlockBase extends Block {
    private String unlocalizedName;

    public BlockBase() {
        super(Material.rock);
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