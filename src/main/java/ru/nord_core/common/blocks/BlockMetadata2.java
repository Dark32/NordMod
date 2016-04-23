package ru.nord_core.common.blocks;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata2;
import ru.nord_core.common.tiles.abstracts.TileMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadata2Enum;

import java.util.List;


public abstract class BlockMetadata2 extends Block implements IVariantMetadata2, ITileEntityProvider {
    private final ImmutableList allowedValues;
    private String unlocalizedName;
    protected String modid;
    private String[] harvestTool;
    private int[] harvestLevel;

    public BlockMetadata2(Material mat, String modid) {
        super(mat);
        this.modid = modid;
        this.allowedValues = ImmutableList.copyOf(getVariant().getAllowedValues());
        harvestTool = new String[this.allowedValues.size()];
        harvestLevel = new int[this.allowedValues.size()];
        for (int i = 0; i < this.allowedValues.size(); ++i) {
            harvestLevel[i] = -1;
        }
    }

    /*
     * Переопределение родных методов
     */
    @Override
    public void setHarvestLevel(String toolClass, int level) {
        for (IBlockState iBlockState : getBlockState().getValidStates()) {
            setHarvestLevel(toolClass, level, iBlockState);
        }
    }

    @Override
    public void setHarvestLevel(String toolClass, int level, IBlockState state) {
        int idx = this.getMetaFromState(state);
        this.harvestTool[idx] = toolClass;
        this.harvestLevel[idx] = level;
    }

    @Override
    public int getHarvestLevel(IBlockState state) {
        return harvestLevel[getMetaFromState(state)];
    }

    @Override
    public String getHarvestTool(IBlockState state) {
        return harvestTool[getMetaFromState(state)];
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < getVariant().getAllowedValues().toArray().length; ++i) {
            list.add(new ItemStack(itemIn, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName() {
        return "tile." + modid + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, getVariant());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getReIndexMeta(state);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return getReIndexMeta(state);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(getVariant(), getEnumByMetadata(meta));
    }


    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileMetadata tile = (TileMetadata) worldIn.getTileEntity(pos);
        int meta = tile.getMetadata();
        return this.getDefaultState()
                .withProperty(getVariant(), getEnumByMetadata(meta))
                ;
    }

    @Override
    public void onBlockPlacedBy(final World world, final BlockPos coord, final IBlockState bs,
                                final EntityLivingBase player, final ItemStack item) {
        super.onBlockPlacedBy(world, coord, bs, player, item);
        TileMetadata st = (TileMetadata) world.getTileEntity(coord);
        st.setMetadata(item.getMetadata());
    }

    /**
     * {@link ITileEntityProvider}
     */

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileMetadata();
    }

    /**
     * {@link IVariantMetadata2}
     */


    @Override
    public ImmutableList getAllowedValues() {
        return this.allowedValues;
    }

    @Override
    public int getReIndexMeta(IBlockState state) {
        return ((IMetadata2Enum) state.getValue(getVariant())).getReIndexMetadata(getAllowedValues());
    }

    /**
     * {@link ru.nord_core.common.blocks.interfaces.IVariantMetadata}
     */

    @Override
    public abstract PropertyEnum getVariant();


}
