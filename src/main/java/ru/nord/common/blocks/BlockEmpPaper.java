package ru.nord.common.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumPaperEmp;
import ru.nord_core.common.blocks.BlockMetadata;

public class BlockEmpPaper extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumPaperEmp.class);

    public BlockEmpPaper(String[] names) {
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
        return EnumPaperEmp.byMetadata(meta);
    }


}
