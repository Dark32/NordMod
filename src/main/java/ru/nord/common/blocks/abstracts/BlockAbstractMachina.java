package ru.nord.common.blocks.abstracts;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import ru.nordwest.nord.common.tiles.abstracts.TileAbstractEnergyMachina;

import java.util.Random;

abstract public class BlockAbstractMachina extends BlockRotatebleContainer {

        //private boolean isActive;

        @SideOnly(Side.CLIENT)
        protected IIcon iconFrontWork;

        protected BlockAbstractMachina(Material mat) {
                super(mat);
        }

        private void dropItems(World world, int x, int y, int z) {
                Random rand = new Random();
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (!(tileEntity instanceof IInventory)) {
                        return;
                }
                IInventory inventory = (IInventory) tileEntity;

                for (int i = 0; i < inventory.getSizeInventory(); i++) {
                        ItemStack item = inventory.getStackInSlot(i);

                        if (item != null && item.stackSize > 0) {
                                float rx = rand.nextFloat() * 0.8F + 0.1F;
                                float ry = rand.nextFloat() * 0.8F + 0.1F;
                                float rz = rand.nextFloat() * 0.8F + 0.1F;

                                EntityItem entityItem = new EntityItem(world,
                                        x + rx, y + ry, z + rz,
                                        item.copy());

                                if (item.hasTagCompound()) {
                                        entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
                                }

                                float factor = 0.05F;
                                entityItem.motionX = rand.nextGaussian() * factor;
                                entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
                                entityItem.motionZ = rand.nextGaussian() * factor;
                                world.spawnEntityInWorld(entityItem);
                                item.stackSize = 0;
                        }
                }
        }

        @Override
        public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
                dropItems(world, x, y, z);
                super.breakBlock(world, x, y, z, par5, par6);
        }

        private boolean getWork(IBlockAccess world, int x, int y, int z) {
                TileAbstractEnergyMachina tile = (TileAbstractEnergyMachina) world.getTileEntity(x, y, z);
                return tile != null && tile.isWork();
        }

        @Override
        @SideOnly(Side.CLIENT)
        protected IIcon getFrontIcon(IBlockAccess world, int x, int y, int z) {
                return getWork(world, x, y, z) ? this.iconFrontWork : super.getFrontIcon(world, x, y, z);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public void randomDisplayTick(World world, int x, int y, int z, Random random) {
                boolean isActive = getWork(world, x, y, z);
                if (isActive) {
                        int direction = world.getBlockMetadata(x, y, z);
                        float x1 = (float) x + 0.5F;
                        float y1 = (float) y + random.nextFloat();
                        float z1 = (float) z + 0.5F;
                        float f = 0.52F;
                        float f1 = random.nextFloat() * 0.6F - 0.3F;

                        if (direction == 4) {
                                world.spawnParticle("smoke", (double) (x1 - f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                                world.spawnParticle("flame", (double) (x1 - f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                        }
                        if (direction == 5) {
                                world.spawnParticle("smoke", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                                world.spawnParticle("flame", (double) (x1 + f), (double) (y1), (double) (z1 + f1), 0D, 0D, 0D);
                        }
                        if (direction == 2) {
                                world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
                                world.spawnParticle("flame", (double) (x1 + f1), (double) (y1), (double) (z1 - f), 0D, 0D, 0D);
                        }
                        if (direction == 3) {
                                world.spawnParticle("smoke", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
                                world.spawnParticle("flame", (double) (x1 + f1), (double) (y1), (double) (z1 + f), 0D, 0D, 0D);
                        }

                }
        }
}
