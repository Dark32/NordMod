package ru.nord.common.blocks;

import com.google.common.base.Predicate;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockMetadata2;
import ru.nord_core.common.utils.metal.EnumOre;

public class BlockCrystal extends BlockMetadata2 {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumOre.class, new Predicate<EnumOre>()
    {
        @Override
        public boolean apply(EnumOre _enum)
        {
            return _enum.getCrystal();
        }
    });
    public BlockCrystal() {
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
        return EnumOre.byReIndexMetadata(getAllowedValues(), meta);
    }

}
