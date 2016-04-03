package ru.nord_deco.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.client.utils.IColorizeBlock;
import ru.nord_core.common.utils.enums.interfaces.IColorizeEnum;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;
import ru.nord_deco.common.utils.Version;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public abstract class BlockAbstractSlab extends BlockSlab implements IColorizeBlock {

    public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
    private String unlocalizedName;

    public BlockAbstractSlab(Material materialIn) {
        super(materialIn);
    }

    public abstract PropertyEnum getVariant();

    public abstract Comparable getEnumByMetadata(int meta);

    @Override
    public abstract boolean isDouble();

    @Override
    public String getUnlocalizedName() {
        return "tile." + Version.MODID + "." + this.unlocalizedName;
    }

    @Override
    public Block setUnlocalizedName(String unlocalizedName) {
        this.unlocalizedName = unlocalizedName;
        return this;
    }

    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        IBlockState iblockstate = this.getStateFromMeta(meta);
        if (this.isDouble()) {
            return iblockstate;
        } else {
            if (facing != EnumFacing.DOWN && (facing == EnumFacing.UP || (double) hitY <= 0.5D)) {
                return iblockstate;
            } else {
                return iblockstate.withProperty(HALF, EnumBlockHalf.TOP);
            }
        }
    }


    @Override
    public String getUnlocalizedName(int meta) {
        return getUnlocalizedName() + "." + ((IMetadataEnum)getEnumByMetadata(meta)).getName();
    }

    @Override
    public Comparable<?> getTypeForItem(ItemStack stack) {
        return getEnumByMetadata(stack.getMetadata() & 7);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        Iterator iterator = getVariantProperty().getAllowedValues().iterator();
        while (iterator.hasNext())
        {
            IMetadataEnum oenum = (IMetadataEnum)iterator.next();
            list.add(new ItemStack(itemIn, 1, oenum.getMetadata()));
        }
    }
    @Override
    public IBlockState getStateFromMeta(int meta) {
        IBlockState iblockstate = this.getDefaultState().withProperty(getVariant(),getEnumByMetadata(meta & 7));

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
        int i = b0 | ((IMetadataEnum) state.getValue(getVariant())).getMetadata();

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
    protected BlockStateContainer createBlockState() {
        return this.isDouble() ? new BlockStateContainer(this, SEAMLESS, getVariant()) : new BlockStateContainer(this, HALF, getVariant());
    }

    @Override
    public int damageDropped(IBlockState state) {
        return ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
    }




//    @Override
//    @SideOnly(Side.CLIENT)
//    public int getRenderColor(IBlockState state) {
//        Comparable colorize = state.getValue(getVariant());
//        if (colorize instanceof IColorizeEnum) {
//            return ((IColorizeEnum) colorize).getColor();
//        } else {
//            return 0xffffff;
//        }
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
//        IBlockState state = worldIn.getBlockState(pos);
//        return this.getRenderColor(state);
//    }

    @Override
    public IProperty getVariantProperty() {
        return getVariant();
    }

    @Override
    public int getColorForStack(ItemStack stack, Block block) {
        IBlockState state = block.getStateFromMeta(stack.getMetadata());
        Comparable colorize = state.getValue(getVariant());
        if (colorize instanceof IColorizeEnum) {
            return ((IColorizeEnum) colorize).getColor();
        } else {
            return 0xffffff;
        }
    }

    @Override
    public int colorMultiplier(IBlockState state, IBlockAccess p_186720_2_, BlockPos pos, int tintIndex) {
        Comparable colorize = state.getValue(getVariant());
        if (colorize instanceof IColorizeEnum) {
            return ((IColorizeEnum) colorize).getColor();
        } else {
            return 0xffffff;
        }
    }
}
