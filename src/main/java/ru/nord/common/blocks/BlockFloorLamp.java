package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
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
        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 0.1F, 0.812F);
//        setLightLevel(0.9375F);
        setStepSound(soundTypeWood);
        setHardness(0.0F);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(STATUS, EnumState.byMetadata(meta));
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    @Override
    public int getMetaFromState(IBlockState state) {

        return ((EnumState) state.getValue(STATUS)).getMetadata();
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{STATUS});
    }

    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        list.add(new ItemStack(itemIn, 1, 1));
        list.add(new ItemStack(itemIn, 1, 2));
    }

    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumState) state.getValue(STATUS)).getDrop();
    }


    private boolean canPlaceOn(World worldIn, BlockPos pos) {
        return World.doesBlockHaveSolidTopSurface(worldIn, pos);
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
