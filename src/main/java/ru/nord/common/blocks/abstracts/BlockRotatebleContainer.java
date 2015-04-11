package ru.nord.common.blocks.abstracts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class BlockRotatebleContainer extends BlockContainer {
        @SideOnly(Side.CLIENT)
        protected IIcon iconTop;
        @SideOnly(Side.CLIENT)
        protected IIcon iconSide;
        @SideOnly(Side.CLIENT)
        protected IIcon iconDown;
        @SideOnly(Side.CLIENT)
        protected IIcon iconFront;


        protected BlockRotatebleContainer(Material mat) {
                super(mat);
        }

        @SideOnly(Side.CLIENT)
        abstract public void registerBlockIcons(IIconRegister iconRegister);

        @SideOnly(Side.CLIENT)
        protected IIcon getFrontIcon(IBlockAccess world, int x, int y, int z) {
                return this.iconFront;
        }

        @SideOnly(Side.CLIENT)
        @Override
        public IIcon getIcon(IBlockAccess iWorld, int x, int y, int z, int side) {
                int metadata = iWorld.getBlockMetadata(x, y, z);

                if (metadata == 0 && side == 3) {
                        return getFrontIcon(iWorld, x, y, z);
                } else {
                        if (side == 0) {
                                return this.iconDown;
                        } else if (side == 1) {
                                return this.iconTop;
                        } else if (side == metadata) {
                                return getFrontIcon(iWorld, x, y, z);
                        } else {
                                return this.blockIcon;
                        }
                }
        }

        @Override
        public void onBlockAdded(World world, int x, int y, int z) {
                super.onBlockAdded(world, x, y, z);
                this.setDefaultDirection(world, x, y, z);
        }

        private void setDefaultDirection(World world, int x, int y, int z) {
                if (!world.isRemote) {
                        Block b1 = world.getBlock(x, y, z - 1);
                        Block b2 = world.getBlock(x, y, z + 1);
                        Block b3 = world.getBlock(x - 1, y, z);
                        Block b4 = world.getBlock(x + 1, y, z);
                        byte b0 = 3;
                        if (b1.func_149730_j() && !b2.func_149730_j()) {
                                b0 = 3;
                        }
                        if (b2.func_149730_j() && !b1.func_149730_j()) {
                                b0 = 2;
                        }
                        if (b3.func_149730_j() && !b4.func_149730_j()) {
                                b0 = 5;
                        }
                        if (b4.func_149730_j() && !b3.func_149730_j()) {
                                b0 = 4;
                        }
                        world.setBlockMetadataWithNotify(x, y, x, b0, 2);
                }
        }

        @Override
        public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityplayer, ItemStack itemstack) {
                int l = MathHelper.floor_double((double) (entityplayer.rotationYaw * 4.0F / 360.F) + 0.5D) & 3;

                if (l == 0) {
                        world.setBlockMetadataWithNotify(x, y, z, 2, 2);
                }
                if (l == 1) {
                        world.setBlockMetadataWithNotify(x, y, z, 5, 2);
                }
                if (l == 2) {
                        world.setBlockMetadataWithNotify(x, y, z, 3, 2);
                }
                if (l == 3) {
                        world.setBlockMetadataWithNotify(x, y, z, 4, 2);
                }

//		if (itemstack.hasDisplayName()) {
//			((TileEntitySmelter) world.getTileEntity(x, y, z))
//					.setGuiDisplayName(itemstack.getDisplayName());
//		}
        }

}
