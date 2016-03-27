package ru.nord.common.blocks;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockAbstractRoofLamp;
import ru.nord_core.common.utils.enums.EnumOilLamp;

import java.util.Random;


public class BlockOilLamp extends BlockAbstractRoofLamp {
    public static final PropertyEnum OIL = PropertyEnum.create("type", EnumOilLamp.class);

    public BlockOilLamp() {
        super(Version.MODID);
//        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 1.0F, 0.812F);
    }

    @Override
    public void randomDisplayTick(IBlockState state , World worldIn , BlockPos pos, Random rand)
    {
        final double a = (double) pos.getX() + 0.5F;
        final double b = (double) pos.getY() + (rand.nextFloat() * 0.2F) + 0.3F;
        final double c = (double) pos.getZ() + 0.5F;
        final double d = 0.01D;
        final double e = 0.02D;

        worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, a, b, c,
                d - (rand.nextDouble() * e), 0.0D, d- (rand.nextDouble() * e));
        worldIn.spawnParticle(EnumParticleTypes.FLAME, a, b, c,
                d - (rand.nextDouble() * e), 0.0D, d - (rand.nextDouble() * e));
    }


    @Override
    public PropertyEnum getVariant() {
        return OIL;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumOilLamp.byMetadata(meta);
    }
}
