package ru.nord.common.blocks;


import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.abstracts.BlockRotateble;

public class BlockChair extends BlockRotateble {
    protected String modid;
    public BlockChair(String modid) {
        super(modid);
        this.modid=modid;
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);
    }
    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer()
    {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }
    @Override
    public boolean isFullCube()
    {
        return false;
    }
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    /*
    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos,
                                    IBlockState state, EntityPlayer playerIn,
                                    EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (worldIn.isRemote)
        {
            return true;
        }
        else
        {
            System.err.println(222);
            playerIn.setLocationAndAngles(pos.getX(), pos.getY()+0.5, pos.getZ(), playerIn.rotationYaw, playerIn.rotationPitch);
            return true;
        }
    }
    */

}