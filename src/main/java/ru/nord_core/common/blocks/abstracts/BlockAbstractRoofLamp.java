package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.BlockMetadata;

public abstract class BlockAbstractRoofLamp extends BlockMetadata {

    public BlockAbstractRoofLamp(String modid) {
        super(Material.circuits,modid);
        this.setBlockBounds(0.187F, 0.0F, 0.187F, 0.812F, 1.0F, 0.812F);
        setLightLevel(0.8375F);
        setStepSound(soundTypeWood);
        setHardness(0.0F);
    }

    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass)
    {
        return getRenderColor(worldIn.getBlockState(pos));
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    @Override
    public boolean isFullCube()
    {
        return false;
    }


    private boolean canPlaceOn(World worldIn, BlockPos pos)
    {
        if (World.doesBlockHaveSolidTopSurface(worldIn, pos) || worldIn.getBlockState(pos).getBlock() == this)
        {
            return true;
        }
        else
        {
            Block block = worldIn.getBlockState(pos).getBlock();
            return block.canPlaceTorchOnTop(worldIn, pos);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World worldIn, BlockPos pos)
    {
        return this.canPlaceOn(worldIn, pos.up()) ||  this.canPlaceOn(worldIn, pos.down());
    }
}
