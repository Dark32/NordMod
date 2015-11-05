package ru.nord_deco.common.blocks;


import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.abstracts.BlockRotateble;
import ru.nord_deco.common.utils.SittableUtil;
import ru.nord_deco.common.utils.enums.EnumChairOther;


import java.util.List;

public class BlockChairOther extends BlockRotateble {
    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumChairOther.class);
    private final String[] names;
    private String unlocalizedName;

    public BlockChairOther(String modid, String[] names) {
        super(modid);
        this.names = names;
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
        int type = ((EnumChairOther) state.getValue(TYPE)).getMetadata();
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
                .withProperty(TYPE, EnumChairOther.values()[type]);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IBlockState getStateForEntityRender(IBlockState state) {
        return this.getDefaultState()
                .withProperty(FACING, EnumFacing.SOUTH)
                .withProperty(TYPE, ((EnumChairOther) state.getValue(TYPE)).getMetadata());
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
        EnumChairOther type = EnumChairOther.values()[meta & 3];
        return this.getDefaultState()
                .withProperty(FACING, face)
                .withProperty(TYPE,type);
    }
}