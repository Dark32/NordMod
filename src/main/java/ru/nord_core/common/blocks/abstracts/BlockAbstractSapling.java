package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;
import ru.nord_core.common.utils.enums.interfaces.IWorldGeneratorEnum;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class BlockAbstractSapling extends BlockBush implements IGrowable, IVariantMetadata {

    public static final PropertyInteger STAGE = PropertyInteger.create("stage", 0, 1);

    public abstract PropertyEnum getVariant();

    public abstract Comparable getEnumByMetadata(int meta);

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, pos, state, rand);
            }
        }
    }


    public void grow(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if ((Integer) state.getValue(STAGE) == 0) {
            worldIn.setBlockState(pos, state.cycleProperty(STAGE), 4);
        } else {
            this.generateTree(worldIn, pos, state, rand);
        }
    }

    public void generateTree(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(worldIn, rand, pos)) return;
        WorldGenerator tree = ((IWorldGeneratorEnum) state.getValue(getVariant())).generate(worldIn);
        if (tree == null) return;
        int i = 0;
        int j = 0;
        boolean flag = false;
        IBlockState iblockstate1 = Blocks.air.getDefaultState();
        worldIn.setBlockState(pos, iblockstate1, 4);
        if (!tree.generate(worldIn, rand, pos.add(i, 0, j))) {
            worldIn.setBlockState(pos, state, 4);
        }
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return (double) worldIn.rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, pos, state, rand);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(getVariant(), getEnumByMetadata(meta & 7)).withProperty(STAGE, (meta & 8) >> 3);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
        i |= (Integer) state.getValue(STAGE) << 3;
        return i;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getVariant(), STAGE);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        Iterator iterator = getVariant().getAllowedValues().iterator();
        while (iterator.hasNext())
        {
            IMetadataEnum oenum = (IMetadataEnum)iterator.next();
            list.add(new ItemStack(itemIn, 1, oenum.getMetadata()));
        }
    }
}
