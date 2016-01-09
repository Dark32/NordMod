package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumCrystal;

public class BlockCrystal extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumCrystal.class);

    public BlockCrystal(String[] names) {
        super(Material.iron, names, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumCrystal.byMetadata(meta);
    }

}
