package ru.nord_deco.common.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;

import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_deco.common.utils.Version;
import ru.nord_deco.common.utils.enums.EnumAbomination;

public class BlockAbomination extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumAbomination.class);
    public BlockAbomination(String[] names){
        super(Material.cloth,names, Version.MODID);
        this.setHardness(3F);
        setStepSound(SLIME_SOUND);
    }


    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumAbomination.byMetadata(meta));
    }

    @Override
    public PropertyEnum getTypes() {
        return TYPE;
    }
}