package ru.nord_deco.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import ru.nord_deco.common.utils.Version;

public abstract class BlockAbstractSlab extends BlockSlab {

    public static final PropertyBool SEAMLESS = PropertyBool.create("seamless");
    private String unlocalizedName;

    public BlockAbstractSlab(Material materialIn) {
        super(materialIn);


    }

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

}
