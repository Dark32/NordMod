package ru.nord.common.blocks.wood.type4;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.NordTabs;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.blocks.BlockMetadata;

public class BlockPlanks4 extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("variant", EnumNordPlank4.class);

    public BlockPlanks4(String[] names)
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
        return  EnumNordPlank4.byMetadata(meta);
    }

}
