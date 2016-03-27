package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockAbstractRoofLamp;
import ru.nord_core.common.utils.enums.EnumState;

import java.util.List;

public class BlockFloorLamp extends BlockAbstractRoofLamp {

    public static final PropertyEnum STATUS = PropertyEnum.create("type", EnumState.class);

    public BlockFloorLamp() {
        super(Version.MODID);
//        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F);
        setStepSound(SoundType.WOOD);
        setHardness(0.0F);
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        list.add(new ItemStack(itemIn, 1, 1));
        list.add(new ItemStack(itemIn, 1, 2));
    }

    @Override
    public PropertyEnum getVariant() {
        return STATUS;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumState.byMetadata(meta);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumState) state.getValue(STATUS)).getDrop();
    }

    private boolean canPlaceOn(World worldIn, BlockPos pos) {
        return worldIn.isSideSolid(pos, EnumFacing.UP);
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
        return this.canPlaceOn(worldIn, pos.down());
    }

    public int getLightValue(IBlockAccess world, BlockPos pos) {
        IBlockState state = world.getBlockState(pos);
        EnumState status = ((EnumState) state.getValue(STATUS));
        if (status == EnumState.ON || status == EnumState.INVERTED_ON) {
            return 15;
        } else {
            return 0;
        }
    }

    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock) {
        EnumState status = ((EnumState) state.getValue(STATUS));
        boolean power = worldIn.isBlockPowered(pos);
        if (!worldIn.isRemote) {

            if (power) {
                switch (status) {
                    case ON: {
                        worldIn.scheduleUpdate(pos, this, 4);
                        break;
                    }
                    case OFF: {
                        worldIn.setBlockState(pos, state.withProperty(STATUS, EnumState.ON), 3);
                        break;
                    }
                    case INVERTED_ON: {
                        worldIn.setBlockState(pos, state.withProperty(STATUS, EnumState.INVERTED_OFF), 3);
                        break;
                    }
                    case INVERTED_OFF: {
                        worldIn.scheduleUpdate(pos, this, 4);
                        break;
                    }
                }
            } else {
                switch (status) {
                    case ON: {
                        worldIn.setBlockState(pos, state.withProperty(STATUS, EnumState.OFF), 3);
                        break;
                    }
                    case OFF: {
                        worldIn.scheduleUpdate(pos, this, 4);
                        break;
                    }
                    case INVERTED_ON: {
                        worldIn.scheduleUpdate(pos, this, 4);
                        break;
                    }
                    case INVERTED_OFF: {
                        worldIn.setBlockState(pos, state.withProperty(STATUS, EnumState.INVERTED_ON), 3);
                        break;
                    }
                }
            }
        }
    }
}
