package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockMetadata;
import ru.nord_core.common.blocks.BlockMetadata2;
import ru.nord_core.common.utils.enums.EnumClearMetal;

public class BlockClearMetal extends BlockMetadata {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumClearMetal.class);

    public BlockClearMetal() {
        super(Material.iron,  Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumClearMetal.byMetadata(meta);
    }


}
