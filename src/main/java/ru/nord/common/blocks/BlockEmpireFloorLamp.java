package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.client.utils.IColorizeBlock;
import ru.nord_core.common.blocks.abstracts.BlockAbstractRoofLamp;
import ru.nord_core.common.utils.enums.EnumColors;

public class BlockEmpireFloorLamp extends BlockAbstractRoofLamp implements IColorizeBlock {

    public static final PropertyEnum COLOR = PropertyEnum.create("type", EnumColors.class);

    public BlockEmpireFloorLamp() {
        super(Version.MODID);
//        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F);
        setLightLevel(0.9375F);
        setStepSound(SoundType.WOOD);
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


    private boolean canPlaceOn(World worldIn, BlockPos pos)
    {
        return worldIn.isSideSolid( pos, EnumFacing.UP);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.canPlaceOn(worldIn, pos.down());
    }



    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex) {
        return ((EnumColors)(state.getValue(COLOR))).getSecondColor();
    }

    @Override
    public int getColorForStack(ItemStack stack, Block block) {
        return ((EnumColors)(block.getStateFromMeta(stack.getMetadata()).getValue(COLOR))).getSecondColor();
    }
}
