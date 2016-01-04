package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumClearMetal;

public class BlockClearMetal extends BlockMetadata {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumClearMetal.class);
    public BlockClearMetal(String[] names){
        super(Material.iron,names,Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState().withProperty(TYPE, EnumClearMetal.byMetadata(meta));
    }

    @Override
    public PropertyEnum getTypes() {
        return TYPE;
    }


}
