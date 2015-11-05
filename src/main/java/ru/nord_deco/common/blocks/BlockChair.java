package ru.nord_deco.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
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
import ru.nord_deco.common.entity.EntitySittableBlock;
import ru.nord_deco.common.helpers.CollisionHelper;
import ru.nord_deco.common.utils.SittableUtil;
import ru.nord_deco.common.utils.enums.EnumChairType;

import java.util.List;

public class BlockChair extends BlockRotateble {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumChairType.class);
    private final String[] names;
    private String unlocalizedName;

    public BlockChair(String modid,String[] names) {
        super(modid, Material.cloth);
        this.names = names;
        setHardness(1.0F);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.5F, 1.0F);

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
    public boolean onBlockActivated(World worldIn, BlockPos pos,
                                    IBlockState state, EntityPlayer playerIn,
                                    EnumFacing side, float hitX, float hitY, float hitZ) {
        if (SittableUtil.sitOnBlock(worldIn, pos.getX(), pos.getY(), pos.getZ(), playerIn, 7 * 0.0625)) {
            worldIn.updateComparatorOutputLevel(pos, this);
            return true;
        }
        return false;
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int face = ((EnumFacing) state.getValue(FACING)).getIndex();
        int type = ((EnumChairType) state.getValue(TYPE)).getMetadata();
        return (face << 2) + (type & 15);
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{FACING, TYPE});
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
                .withProperty(TYPE, EnumChairType.values()[type]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.SOUTH)
                .withProperty(TYPE, ((EnumChairType) state.getValue(TYPE)).getMetadata());
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
        for (int i = 0; i < names.length; ++i) {
            list.add(new ItemStack(itemIn, 1, i & 3));
        }
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        EnumFacing face = placer.getHorizontalFacing().getOpposite();
        EnumChairType type = EnumChairType.values()[meta & 3];
        return this.getDefaultState()
                .withProperty(FACING, face)
                .withProperty(TYPE,type);
    }


    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player)
    {
        IBlockState state = world.getBlockState(pos);
        int type = ((EnumChairType) state.getValue(TYPE)).getMetadata();
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

    public String getHarvestTool(IBlockState state)
    {
        return "axe";
    }

}