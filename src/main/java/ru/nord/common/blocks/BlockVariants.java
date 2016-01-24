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
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * A block with several variants.
 * <p>
 * Test for this thread:
 * http://www.minecraftforum.net/forums/mapping-and-modding/minecraft-mods/modification-development/2594064-metadata-blocks-dont-have-textures
 *
 * @author Choonster
 */
public class BlockVariants extends Block {
	public static final IProperty<EnumType> VARIANT = PropertyEnum.create("variant", EnumType.class);
    public static void setBlockName(Block block, String blockName) {
        block.setRegistryName(blockName);
        block.setUnlocalizedName(block.getRegistryName());
    }
	public BlockVariants(Material materialIn) {
		super(materialIn);
        setBlockName(this,"variants");
	}

	@Override
	protected BlockState createBlockState() {
		return new BlockState(this, VARIANT);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(VARIANT, EnumType.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).getMeta();
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
		worldIn.setBlockState(pos, state.cycleProperty(VARIANT));

		return true;
	}

	@Override
	public void getSubBlocks(Item itemIn, CreativeTabs tab, List<ItemStack> list) {
		for (EnumType enumType : EnumType.values()) {
			list.add(new ItemStack(this, 1, enumType.getMeta()));
		}
	}

	public enum EnumType implements IStringSerializable {
		VARIANT_A(0, "a"),
		VARIANT_B(1, "b");

		private static final EnumType[] META_LOOKUP = Stream.of(values()).sorted(Comparator.comparing(EnumType::getMeta)).toArray(EnumType[]::new);

		private final int meta;
		private final String name;

		EnumType(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		public int getMeta() {
			return meta;
		}

		@Override
		public String getName() {
			return name;
		}

		public static EnumType byMetadata(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}

			return META_LOOKUP[meta];
		}

		public static String[] getNames() {
			return Stream.of(META_LOOKUP).map(EnumType::getName).toArray(String[]::new);
		}
	}
}
