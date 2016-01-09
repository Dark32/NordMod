package ru.nord.common.blocks;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockAbstractRoofLamp;
import ru.nord_core.common.utils.enums.EnumColors;


public class BlockEmperiaLamp extends BlockAbstractRoofLamp{
    public static final PropertyEnum COLOR = PropertyEnum.create("type", EnumColors.class);

    public BlockEmperiaLamp() {
        super(Version.MODID);
    }

    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state)
    {
        return ((EnumColors)(state.getValue(COLOR))).getSecondColor();
    }

    @Override
    public PropertyEnum getVariant() {
        return COLOR;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumColors.byMetadata(meta);
    }
}
