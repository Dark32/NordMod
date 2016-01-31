package ru.nord_deco.common.blocks.abstracts;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.abstracts.BlockRotateble;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;
import ru.nord_deco.common.entity.EntitySittableBlock;
import ru.nord_deco.common.helpers.CollisionHelper;
import ru.nord_deco.common.utils.SittableUtil;

import java.util.Iterator;
import java.util.List;

public abstract class BlockAbstractChair extends BlockRotateble implements IVariantMetadata {

    private String unlocalizedName;
    public BlockAbstractChair(String modid,Material mat) {
        super(modid, mat);
    }


    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.CUTOUT_MIPPED;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }


    @Override
    public boolean onBlockActivated(World world, BlockPos pos,
                                    IBlockState state, EntityPlayer player,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if(SittableUtil.sitOnBlock(world, pos.getX(), pos.getY(), pos.getZ(), player, 7 * 0.0625))
        {
            world.updateComparatorOutputLevel(pos, this);
            return true;
        }
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int face = state.getValue(FACING).getIndex();
        int type = ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
        return (face << 2) + (type & 15);
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, FACING, getVariant());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        int face = meta >> 2;
        int type = meta & 3;
        EnumFacing enumfacing = EnumFacing.getFront(face);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }
        return this.getDefaultState()
                .withProperty(FACING, enumfacing)
                .withProperty(getVariant(), getEnumByMetadata(type));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.SOUTH)
                .withProperty(getVariant(), ((IMetadataEnum) state.getValue(getVariant())).getMetadata());
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
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        Iterator iterator = getVariant().getAllowedValues().iterator();
        while (iterator.hasNext())
        {
            IMetadataEnum oenum = (IMetadataEnum)iterator.next();
            list.add(new ItemStack(itemIn, 1, oenum.getMetadata()));
        }
    }


    @Override
    public IBlockState onBlockPlaced(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        IBlockState state = super.onBlockPlaced(world, pos, facing, hitX, hitY, hitZ, meta, placer);
        Comparable type = getEnumByMetadata(meta & 3);
        state = state.withProperty(FACING, placer.getHorizontalFacing()).withProperty(getVariant(),type);
        return state;
    }


    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        IBlockState state = world.getBlockState(pos);
        int type = ((IMetadataEnum) state.getValue(getVariant())).getMetadata();
        return new ItemStack(this,1,type & 3);
    }

    @Override
    public void addCollisionBoxesToList(World world, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
        if (!(collidingEntity instanceof EntitySittableBlock))
        {
            int metadata = getMetaFromState(state);
            float[] data = CollisionHelper.fixRotation(metadata, 0.825F, 0.1F, 0.9F, 0.9F);
            setBlockBounds(data[0], 0.6F, data[1], data[2], 1.2F, data[3]);
            super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
            setBlockBounds(0.1F, 0.0F, 0.1F, 0.9F, 0.6F, 0.9F);
            super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
        }
        else
        {
            setBlockBounds(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
            super.addCollisionBoxesToList(world, pos, state, mask, list, collidingEntity);
        }
    }

    @Override
    public String getHarvestTool(IBlockState state)
    {
        return "axe";
    }

    @Override
    public int getComparatorInputOverride(World world, BlockPos pos)
    {
        return SittableUtil.isSomeoneSitting(world, pos.getX(), pos.getY(), pos.getZ()) ? 1 : 0;
    }
}