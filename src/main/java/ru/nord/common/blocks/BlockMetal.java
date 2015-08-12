package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumMetal;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public class BlockMetal extends BlockMetadata {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumMetal.class);
    public BlockMetal(String[] names) {
        super(Material.iron, names, EnumMetal.class, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumMetal.byMetadata(meta));
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
