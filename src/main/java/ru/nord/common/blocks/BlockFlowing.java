package ru.nord.common.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import ru.nordwest.nord.Nord;
import ru.nordwest.nord.client.lib.tabs.TabsNord;
import ru.nordwest.nord.common.blocks.abstracts.BlockAbstractMachina;
import ru.nordwest.nord.common.tiles.TileFlowing;

public class BlockFlowing extends BlockAbstractMachina {

        public BlockFlowing() {
                super(Material.rock);
                setHardness(2.0F);
                setResistance(5.0F);
                setBlockName("Flowing");
                setCreativeTab(TabsNord.tabGeneral);
        }

        @Override
        public boolean onBlockActivated(World world, int x, int y, int z,
                                        EntityPlayer player, int metadata, float what, float these, float are) {
                TileEntity tileEntity = world.getTileEntity(x, y, z);
                if (tileEntity == null || player.isSneaking()) {
                        return false;
                }
                player.openGui(Nord.instance, Nord.guiIDFlowing, world, x, y, z);
                return true;
        }

        @Override
        public TileEntity createNewTileEntity(World world, int meta) {
                return new TileFlowing();
        }

        @Override
        public void registerBlockIcons(IIconRegister iconRegister) {

                this.blockIcon = iconRegister.registerIcon(Nord.MODID + ":flowing/side");
                this.iconFront = iconRegister.registerIcon(Nord.MODID + ":flowing/front");
                this.iconFrontWork = iconRegister.registerIcon(Nord.MODID + ":flowing/front_on");
                this.iconTop = iconRegister.registerIcon(Nord.MODID + ":flowing/top");
                this.iconDown = iconRegister.registerIcon(Nord.MODID + ":flowing/down");
        }

}
