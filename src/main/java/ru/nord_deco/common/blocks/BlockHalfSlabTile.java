package ru.nord_deco.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.client.utils.IColorizeBlock;
import ru.nord_deco.NordBloksDeco;
import ru.nord_deco.common.blocks.abstracts.BlockAbstractSlab;
import ru.nord_deco.common.utils.enums.EnumTileType1;

import java.util.Random;

/**
 * Created by andrew on 19.12.15.
 * Block non contain TileEntity!
 */
public class BlockHalfSlabTile extends BlockAbstractSlab  {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumTileType1.class);

    public BlockHalfSlabTile() {
        super(Material.rock);
        IBlockState iblockstate = this.blockState.getBaseState();
        if (isDouble()) {
            iblockstate = iblockstate.withProperty(SEAMLESS, false);
        } else {
            iblockstate = iblockstate.withProperty(HALF, BlockSlab.EnumBlockHalf.BOTTOM);
            this.setLightOpacity(1);
        }
        this.setDefaultState(iblockstate.withProperty(VARIANT, EnumTileType1.GRAY));
    }

    @Override
    public PropertyEnum getVariant() {
        return VARIANT;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumTileType1.byMetadata(meta);
    }

    @Override
    public boolean isDouble() {
        return false;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(NordBloksDeco.halfSlabTile1);
    }

    @SideOnly(Side.CLIENT)
    public Item getItem(World worldIn, BlockPos pos) {
        return Item.getItemFromBlock(NordBloksDeco.halfSlabTile1);
    }

}
