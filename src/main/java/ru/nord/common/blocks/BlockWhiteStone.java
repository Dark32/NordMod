package ru.nord.common.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.enums.EnumWhiteStone;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public class BlockWhiteStone extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumWhiteStone.class);
    public BlockWhiteStone(String[] names) {
        super(Material.iron, names, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumWhiteStone.byMetadata(meta));
    }

    @Override
    public PropertyEnum getTypes() {
        return TYPE;
    }

}
