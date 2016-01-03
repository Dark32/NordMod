package ru.nord_deco.common.blocks;

import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_deco.NordBloksDeco;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;
import ru.nord_deco.common.utils.enums.EnumTileType1;

import java.util.List;
import java.util.Random;

/**
 * Created by andrew on 19.12.15.
 * Block non contain TileEntity!
 */
public  class BlockHalfSlabTile extends BlockAbstractSlab {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumTileType1.class);
    public BlockHalfSlabTile() {
        super(Material.rock);
        IBlockState iblockstate = this.blockState.getBaseState();

        if (isDouble())
        {
            iblockstate = iblockstate.withProperty(SEAMLESS, false);
        }
        else
        {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
            this.setLightOpacity(1);
        }

        this.setDefaultState(iblockstate.withProperty(VARIANT, EnumTileType1.GRAY));
    }


    @Override
    public boolean isDouble(){
        return false;
    };
    @Override
    public IProperty getVariantProperty() {
        return VARIANT;
    }

    @Override
    public String getUnlocalizedName(int meta) {
        return super.getUnlocalizedName() + "." + EnumTileType1.byMetadata(meta).getName();
    }

    @Override
    public Object getVariant(ItemStack stack) {
        return EnumTileType1.byMetadata(stack.getMetadata() & 7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        if (itemIn != Item.getItemFromBlock(Blocks.double_stone_slab)) {
            EnumTileType1[] aenumtype = EnumTileType1.values();
            int i = aenumtype.length;

            for (int j = 0; j < i; ++j) {
                EnumTileType1 enumtype = aenumtype[j];
                list.add(new ItemStack(itemIn, 1, enumtype.getMetadata()));
            }
        }
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(VARIANT, EnumTileType1.byMetadata(meta & 7));

        if (this.isDouble()) {
            iblockstate = iblockstate.withProperty(SEAMLESS, (meta & 8) != 0);
        } else {
            iblockstate = iblockstate.withProperty(HALF, (meta & 8) == 0 ? BlockSlab.EnumBlockHalf.BOTTOM : BlockSlab.EnumBlockHalf.TOP);
        }

        return iblockstate;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((EnumTileType1) state.getValue(VARIANT)).getMetadata();

        if (this.isDouble()) {
            if ((Boolean) state.getValue(SEAMLESS)) {
                i |= 8;
            }
        } else if (state.getValue(HALF) == BlockSlab.EnumBlockHalf.TOP) {
            i |= 8;
        }

        return i;
    }

    @Override
    protected BlockState createBlockState() {
        return this.isDouble() ? new BlockState(this, SEAMLESS, VARIANT) : new BlockState(this, HALF, VARIANT);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumTileType1) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune){
        return Item.getItemFromBlock(NordBloksDeco.halfSlabTile1);
    }
    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos)
    {
        return Item.getItemFromBlock(NordBloksDeco.halfSlabTile1);
    }
}
