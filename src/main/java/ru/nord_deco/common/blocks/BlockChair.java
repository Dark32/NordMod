package ru.nord_deco.common.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractChair;
import ru.nord_deco.common.utils.enums.EnumChairType;

public class BlockChair extends BlockAbstractChair {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumChairType.class);

    public BlockChair(String modid) {
        super(modid, Material.cloth);
        setHardness(1.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumChairType.byMetadata(meta);
    }


}