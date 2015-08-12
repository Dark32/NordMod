package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.NordItems;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.abstracts.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.EnumOre;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

import java.util.List;
import java.util.Random;

public class BlockMetalOre extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumOre.class);
    public BlockMetalOre(String[] names) {
        super(Material.iron,names, EnumOre.class,Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(TYPE, EnumOre.byMetadata(meta));
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        if (((EnumOre) state.getValue(TYPE)).getNugget() != null) {
            return NordItems.itemOreNugget;
        } else {
            return NordItems.itemOreDrop;
        }
    }

    @Override
    public int quantityDropped(Random random) {
        return 3 + random.nextInt(3);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped((IBlockState) this.getBlockState().getValidStates().iterator().next(), random, fortune)) {
            int j = random.nextInt(fortune + 1) + 1;
            return this.quantityDropped(random) * j;
        } else {
            return this.quantityDropped(random);
        }
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
    }

    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, BlockPos pos, EntityPlayer player) {
        return new ItemStack(this, 1, ((EnumOre) world.getBlockState(pos).getValue(TYPE)).getMetadata());
    }

    @Override
    public int getDamageValue(World worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public int damageDropped(IBlockState state) {
        EnumOre status = (EnumOre) state.getValue(TYPE);
        if (status.getNugget() != null) {
            return status.getNugget().getMetadata();
        } else {
            return status.getOreDrop().getMetadata();
        }
    }

    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[]{TYPE});
    }

   @Override
    public int getMetaFromState(IBlockState state) {
        return ((IMetadataEnum)state.getValue(TYPE)).getMetadata();
    }

}
