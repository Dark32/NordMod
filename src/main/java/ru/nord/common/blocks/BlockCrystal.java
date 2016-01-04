package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.EnumCrystal;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public class BlockCrystal extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumCrystal.class);
    public BlockCrystal(String[] names) {
        super(Material.iron, names, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumCrystal.byMetadata(meta));
    }

    @Override
    public PropertyEnum getTypes() {
        return TYPE;
    }

}
