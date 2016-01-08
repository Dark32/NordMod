package ru.nord.common.blocks.wood.type4;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import ru.nord.NordBloks;
import ru.nord.common.utils.enums.EnumNordPlank4;
import ru.nord_core.common.blocks.abstracts.BlockAbstractLeaves;

import java.util.Random;

public class BlockNordLeaves4 extends BlockAbstractLeaves {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank4.class);

    public BlockNordLeaves4() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank4.APRICOT)
                        .withProperty(CHECK_DECAY, true)
                        .withProperty(DECAYABLE, true));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumNordPlank4.byMetadata(meta);
    }


    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(NordBloks.nordSapling4);
    }
}
