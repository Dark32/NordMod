package ru.nord.common.blocks.wood.type2;


import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import ru.nord.NordBloks;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLeaves;

import java.util.Random;

public class BlockNordLeaves2 extends BlockAbstractLeaves {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank2.class);

    public BlockNordLeaves2() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank2.MULBERRY)
                        .withProperty(CHECK_DECAY, true)
                        .withProperty(DECAYABLE, true));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank2.byMetadata(meta);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(NordBloks.nordSapling2);
    }
}
