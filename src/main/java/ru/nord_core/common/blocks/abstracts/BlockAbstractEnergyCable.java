package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.property.ExtendedBlockState;
import net.minecraftforge.common.property.IExtendedBlockState;
import net.minecraftforge.common.property.IUnlistedProperty;
import net.minecraftforge.common.property.Properties;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.blocks.interfaces.IWrenchable;
import ru.nord_core.common.tiles.interfaces.IEnergoCable;

import java.util.List;

public abstract class BlockAbstractEnergyCable extends BlockAbstractContainer implements IWrenchable {

    // the LINK properties are used to communicate to the ISmartBlockModel which of the links should be drawn
    public static final IUnlistedProperty<Boolean> LINK_UP = new Properties.PropertyAdapter<Boolean>(PropertyBool.create("link_up"));
    public static final IUnlistedProperty<Boolean> LINK_DOWN = new Properties.PropertyAdapter<Boolean>(PropertyBool.create("link_down"));
    public static final IUnlistedProperty<Boolean> LINK_WEST = new Properties.PropertyAdapter<Boolean>(PropertyBool.create("link_west"));
    public static final IUnlistedProperty<Boolean> LINK_EAST = new Properties.PropertyAdapter<Boolean>(PropertyBool.create("link_east"));
    public static final IUnlistedProperty<Boolean> LINK_NORTH = new Properties.PropertyAdapter<Boolean>(PropertyBool.create("link_north"));
    public static final IUnlistedProperty<Boolean> LINK_SOUTH = new Properties.PropertyAdapter<Boolean>(PropertyBool.create("link_south"));

    private final float pipeRadius; // in fraction of a block (aka meters)

    public BlockAbstractEnergyCable() {
        super(Material.iron);
        setHardness(2.0F);
        setResistance(5.0F);
        this.pipeRadius = 0.0625f * 2;
    }

    @SideOnly(Side.CLIENT)
    public EnumWorldBlockLayer getBlockLayer() {
        return EnumWorldBlockLayer.SOLID;
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean isFullCube() {
        return false;
    }

    @Override
    public int getRenderType() {
        return 3;
    }

    @Override
    protected BlockState createBlockState() {
        IProperty[] listedProperties = new IProperty[0]; // no listed properties
        IUnlistedProperty[] unlistedProperties = new IUnlistedProperty[]{LINK_UP, LINK_DOWN, LINK_EAST, LINK_WEST, LINK_NORTH, LINK_SOUTH};
        return new ExtendedBlockState(this, listedProperties, unlistedProperties);
    }

    @Override
    public IBlockState getExtendedState(IBlockState state, IBlockAccess world, BlockPos pos) {
        if (state instanceof IExtendedBlockState) {  // avoid crash in case of mismatch
            IExtendedBlockState retval = (IExtendedBlockState) state;
            boolean linkup = canConnectTo(world, pos, EnumFacing.UP, pos.up());
            retval = retval.withProperty(LINK_UP, linkup);

            boolean linkdown = canConnectTo(world, pos, EnumFacing.DOWN, pos.down());
            retval = retval.withProperty(LINK_DOWN, linkdown);

            boolean linkeast = canConnectTo(world, pos, EnumFacing.EAST, pos.east());
            retval = retval.withProperty(LINK_EAST, linkeast);

            boolean linkwest = canConnectTo(world, pos, EnumFacing.WEST, pos.west());
            retval = retval.withProperty(LINK_WEST, linkwest);

            boolean linknorth = canConnectTo(world, pos, EnumFacing.NORTH, pos.north());
            retval = retval.withProperty(LINK_NORTH, linknorth);

            boolean linksouth = canConnectTo(world, pos, EnumFacing.NORTH, pos.south());
            retval = retval.withProperty(LINK_SOUTH, linksouth);

            return retval;
        }
        return state;
    }

    private boolean canConnectTo(IBlockAccess worldIn, BlockPos thisBlock, EnumFacing face, BlockPos otherBlock) {
        Block block = worldIn.getBlockState(otherBlock).getBlock();
        if (block == Blocks.barrier) return false;
        if (block instanceof BlockAbstractEnergyCable || block instanceof BlockAbstractMachina) {
            return true;
        }
        return false;
    }


    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess world, final BlockPos coord) {
        final boolean connectNorth = this.canConnectTo(world, coord, EnumFacing.NORTH, coord.north());
        final boolean connectSouth = this.canConnectTo(world, coord, EnumFacing.SOUTH, coord.south());
        final boolean connectWest = this.canConnectTo(world, coord, EnumFacing.WEST, coord.west());
        final boolean connectEast = this.canConnectTo(world, coord, EnumFacing.EAST, coord.east());
        final boolean connectUp = this.canConnectTo(world, coord, EnumFacing.UP, coord.up());
        final boolean connectDown = this.canConnectTo(world, coord, EnumFacing.DOWN, coord.down());

        float radius = pipeRadius;
        float rminus = 0.5f - radius;
        float rplus = 0.5f + radius;

        float x1 = rminus;
        float x2 = rplus;
        float y1 = rminus;
        float y2 = rplus;
        float z1 = rminus;
        float z2 = rplus;
        if (connectNorth) {
            z1 = 0.0f;
        }
        if (connectSouth) {
            z2 = 1.0f;
        }
        if (connectWest) {
            x1 = 0.0f;
        }
        if (connectEast) {
            x2 = 1.0f;
        }
        if (connectDown) {
            y1 = 0.0f;
        }
        if (connectUp) {
            y2 = 1.0f;
        }
        this.setBlockBounds(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public void addCollisionBoxesToList(final World world, final BlockPos coord,
                                        final IBlockState bs, final AxisAlignedBB box,
                                        final List collisionBoxList,
                                        final Entity entity) {
        final boolean connectNorth = this.canConnectTo(world, coord, EnumFacing.NORTH, coord.north());
        final boolean connectSouth = this.canConnectTo(world, coord, EnumFacing.SOUTH, coord.south());
        final boolean connectWest = this.canConnectTo(world, coord, EnumFacing.WEST, coord.west());
        final boolean connectEast = this.canConnectTo(world, coord, EnumFacing.EAST, coord.east());
        final boolean connectUp = this.canConnectTo(world, coord, EnumFacing.UP, coord.up());
        final boolean connectDown = this.canConnectTo(world, coord, EnumFacing.DOWN, coord.down());

        float radius = pipeRadius;
        float rminus = 0.5f - radius;
        float rplus = 0.5f + radius;

        this.setBlockBounds(rminus, rminus, rminus, rplus, rplus, rplus);
        super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);

        if (connectUp) {
            this.setBlockBounds(rminus, rminus, rminus, rplus, 1f, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectDown) {
            this.setBlockBounds(rminus, 0f, rminus, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectEast) {
            this.setBlockBounds(rminus, rminus, rminus, 1f, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectWest) {
            this.setBlockBounds(0f, rminus, rminus, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectSouth) {
            this.setBlockBounds(rminus, rminus, rminus, rplus, rplus, 1f);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectNorth) {
            this.setBlockBounds(rminus, rminus, 0f, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
    }

    @Override
    public boolean wrenche(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) {
            this.removedByPlayer(worldIn, pos, playerIn, true);
            this.dropBlockAsItem(worldIn, pos, state, 0);
        } else {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity == null) {
                return false;
            }
            if (tileEntity instanceof IEnergoCable) {
                int energy = ((IEnergoCable) tileEntity).getEnergy();
                int maxenergy = ((IEnergoCable) tileEntity).getMaxEnergy();
                int packet = ((IEnergoCable) tileEntity).getPacketEnergy();
                if (!worldIn.isRemote) {
                    playerIn.addChatComponentMessage(new ChatComponentTranslation("energy " + energy + "/" + maxenergy + "(" + packet + ")"));
                }
            }
            return true;
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(final IBlockAccess world, final BlockPos coord, final EnumFacing face) {
        return true;
    }
}
