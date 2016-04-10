package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.interfaces.IDropItemEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class BlockAbstractLeaves extends BlockLeaves implements IVariantMetadata {


    public abstract PropertyEnum getVariant();

    public abstract Comparable getEnumByMetadata(int meta);

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {

        IDropItemEnum apple = ((IDropItemEnum) state.getValue(getVariant()));
        if (apple.dropItem() != null && worldIn.rand.nextInt(chance) == 0)
            spawnAsEntity(worldIn, pos, apple.dropItem());

    }

    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return super.getSaplingDropChance(state);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }


    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((IMetadataEnum) state.getValue(getVariant())).getMetadata());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()
                .withProperty(getVariant(), this.getWoodType2(meta))
                .withProperty(DECAYABLE, (meta & 4) == 0)
                .withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
        if (!state.getValue(DECAYABLE)) {
            i |= 4;
        }
        if (state.getValue(CHECK_DECAY)) {
            i |= 8;
        }
        return i;
    }


    public Comparable getWoodType2(int meta) {
        return getEnumByMetadata((meta & 3));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getVariant(), CHECK_DECAY, DECAYABLE);
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
    }


    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(this, 1, ((IMetadataEnum) state.getValue(getVariant())).getMetadata())));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer() {
        return Blocks.leaves.getBlockLayer();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        Iterator iterator = getVariant().getAllowedValues().iterator();
        while (iterator.hasNext()) {
            IMetadataEnum oenum = (IMetadataEnum) iterator.next();
            list.add(new ItemStack(itemIn, 1, oenum.getMetadata()));
        }
    }
}
