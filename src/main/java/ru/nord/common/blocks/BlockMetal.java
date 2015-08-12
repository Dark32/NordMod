package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumMetal;

public class BlockMetal extends BlockMetadata {
    public BlockMetal(String[] names) {
        super(Material.iron, names, EnumMetal.class, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumMetal.byMetadata(meta));
    }

}
