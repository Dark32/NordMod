package ru.nord.common.blocks;


import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import ru.nord.NordItems;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockMetadata;
import ru.nord_core.common.utils.enums.EnumOre;
import java.util.Random;

public class BlockMetalOre extends BlockMetadata {

    public static final PropertyEnum TYPE = PropertyEnum.create("type", EnumOre.class);
    public BlockMetalOre(String[] names) {
        super(Material.iron,names, Version.MODID);
        this.setHardness(3F);
        this.setHarvestLevel("pickaxe", 1);
    }

    @Override
    public PropertyEnum getVariant() {
        return TYPE;
    }

    @Override
    public Comparable getEnumByMetadata(int meta) {
        return EnumOre.byMetadata(meta);
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
    public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player){
        return new ItemStack(this, 1, ((EnumOre) world.getBlockState(pos).getValue(TYPE)).getMetadata());
    }
    @Override
    public boolean canSilkHarvest(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
        return true;
    }

}
