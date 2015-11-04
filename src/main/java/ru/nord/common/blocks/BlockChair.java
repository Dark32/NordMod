package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import ru.nord_core.common.blocks.abstracts.BlockRotateble;

public class BlockChair extends BlockRotateble {
    private String unlocalizedName;
    protected String modid;
    public BlockChair(String modid) {
        super("nord");
        this.modid=modid;
    }


}