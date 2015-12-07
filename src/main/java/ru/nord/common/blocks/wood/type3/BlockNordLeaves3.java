package ru.nord.common.blocks.wood.type3;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.utils.enums.EnumNordPlank3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlockNordLeaves3 extends BlockLeaves {
    public static final PropertyEnum VARIANT = PropertyEnum.create("variant", EnumNordPlank3.class);

    public BlockNordLeaves3() {
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(VARIANT, EnumNordPlank3.COFFEA)
                        .withProperty(CHECK_DECAY, true)
                        .withProperty(DECAYABLE, true));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(IBlockState state) {
        return super.getRenderColor(state);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess worldIn, BlockPos pos, int renderPass) {
        if (((EnumNordPlank3) worldIn.getBlockState(pos).getValue(VARIANT)).getColorize()) {
            return super.colorMultiplier(worldIn, pos, renderPass);
        } else {
            return 0xffffff;
        }
    }

    @Override
    protected void dropApple(World worldIn, BlockPos pos, IBlockState state, int chance) {

        EnumNordPlank3 apple = ((EnumNordPlank3) state.getValue(VARIANT));
        if (apple.dropFruit() != null && worldIn.rand.nextInt(chance) == 0)
            spawnAsEntity(worldIn, pos, apple.dropFruit());

    }

    @Override
    protected int getSaplingDropChance(IBlockState state) {
        return super.getSaplingDropChance(state);
    }

    @Override
    public BlockPlanks.EnumType getWoodType(int meta) {
        return null;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item itemIn, CreativeTabs tab, List list) {
        for (int i = 0; i < EnumNordPlank3.values().length; i++) {
            list.add(new ItemStack(itemIn, 1, EnumNordPlank3.values()[i].getMetadata()));
        }
    }


    protected ItemStack createStackedBlock(IBlockState state) {
        return new ItemStack(Item.getItemFromBlock(this), 1, ((EnumNordPlank3) state.getValue(VARIANT)).getMetadata());
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState()
                .withProperty(VARIANT, this.getWoodType2(meta))
                .withProperty(DECAYABLE, (meta & 4) == 0)
                .withProperty(CHECK_DECAY, (meta & 8) > 0);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        byte b0 = 0;
        int i = b0 | ((EnumNordPlank3) state.getValue(VARIANT)).getMetadata();

        if (!(Boolean) state.getValue(DECAYABLE)) {
            i |= 4;
        }

        if ((Boolean) state.getValue(CHECK_DECAY)) {
            i |= 8;
        }

        return i;
    }


    public EnumNordPlank3 getWoodType2(int meta) {
        return EnumNordPlank3.byMetadata((meta & 3));
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{VARIANT, CHECK_DECAY, DECAYABLE});
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
    /**
     * Get the damage value that this Block should drop
     */
    @Override
    public int damageDropped(IBlockState state) {
        return ((EnumNordPlank3) state.getValue(VARIANT)).getMetadata();
    }

    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te) {
        if (!worldIn.isRemote && player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.shears) {
            player.triggerAchievement(StatList.mineBlockStatArray[Block.getIdFromBlock(this)]);
        } else {
            super.harvestBlock(worldIn, player, pos, state, te);
        }
    }

    @Override
    public List<ItemStack> onSheared(ItemStack item, IBlockAccess world, BlockPos pos, int fortune) {
        IBlockState state = world.getBlockState(pos);
        return new ArrayList<ItemStack>(Arrays.asList(new ItemStack(this, 1, ((EnumNordPlank3) state.getValue(VARIANT)).getMetadata())));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return Blocks.leaves.getBlockLayer();
    }
}
