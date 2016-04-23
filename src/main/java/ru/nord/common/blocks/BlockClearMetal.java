package ru.nord.common.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockMetadata;
import ru.nord_core.common.blocks.BlockMetadata2;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.metal.EnumMetal;

public class BlockClearMetal extends BlockMetadata2 {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumMetal.class, new Predicate<EnumMetal>()
    {
        @Override
        public boolean apply(EnumMetal _enum)
        {
            return _enum.getCleanable();
        }
    });
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
        return EnumMetal.byReIndexMetadata(getAllowedValues(),meta);
    }

}
