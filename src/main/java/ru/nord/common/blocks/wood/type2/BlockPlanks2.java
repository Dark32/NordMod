package ru.nord.common.blocks.wood.type2;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.NordTabs;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.blocks.BlockMetadata;

public class BlockPlanks2 extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("variant", EnumNordPlank2.class);

    public BlockPlanks2(String[] names)
    {
        super(Material.wood,names, Version.MODID);
        this.setCreativeTab(NordTabs.tabWood);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank2.byMetadata(meta);
    }

}
