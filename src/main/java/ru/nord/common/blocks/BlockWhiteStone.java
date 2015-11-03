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
        super(Material.iron, names, EnumWhiteStone.class, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumWhiteStone.byMetadata(meta));
    }
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{TYPE});
    }
    @Override
    public int damageDropped(IBlockState state)
    {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }

}
