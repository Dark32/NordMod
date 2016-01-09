package ru.nord.common.blocks.wood.type3;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.NordTabs;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.blocks.BlockMetadata;

public class BlockPlanks3 extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("variant", EnumNordPlank3.class);

    public BlockPlanks3(String[] names)
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
        return  EnumNordPlank3.byMetadata(meta);
    }

}
