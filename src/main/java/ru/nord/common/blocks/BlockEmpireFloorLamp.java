package ru.nord.common.blocks;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockAbstractRoofLamp;
import ru.nord_core.common.utils.enums.EnumColors;

public class BlockEmpireFloorLamp extends BlockAbstractRoofLamp {

    public static final PropertyEnum COLOR = PropertyEnum.create("type", EnumColors.class);

    public BlockEmpireFloorLamp() {
        super(Version.MODID);
        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F);
        setLightLevel(0.9375F);
        setStepSound(soundTypeWood);
        setHardness(0.0F);
    }


    @Override
    public PropertyEnum getVariant() {
        return COLOR;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumColors.byMetadata(meta);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return ((EnumColors)(state.getValue(COLOR))).getSecondColor();
    }

    private boolean canPlaceOn(World worldIn, BlockPos pos)
    {
        return World.doesBlockHaveSolidTopSurface(worldIn, pos);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.canPlaceOn(worldIn, pos.down());
    }

}
