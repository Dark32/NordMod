package ru.nord.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class BlockAbstractCable extends BlockAbstractContainer  {
    /** radius of the pipe model, in meters (0.0625 per pixel) */
    private final float pipeRadius; // in fraction of a block (aka meters)
    protected BlockAbstractCable() {
        super(Material.rock);
        setHardness(2.0F);
        setResistance(5.0F);
        this.setDefaultState(this.blockState.getBaseState()
                .withProperty(WEST, false)
                .withProperty(DOWN, false)
                .withProperty(SOUTH, false)
                .withProperty(EAST, false)
                .withProperty(UP, false)
                .withProperty(NORTH, false));
        this.pipeRadius = 0.0625f*2;
    }
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }
    @Override
    protected BlockState createBlockState() {
        return new BlockState(this, new IProperty[] { WEST, DOWN, SOUTH, EAST, UP, NORTH });
    }

    /**
     * Sets the data values of a BlockState instance to represent this block
     */
    @Override
    public IBlockState getActualState(final IBlockState bs, final IBlockAccess world, final BlockPos coord) {
        return bs
                .withProperty(WEST, this.canConnectTo(world,coord, EnumFacing.WEST, coord.west()))
                .withProperty(DOWN, this.canConnectTo(world,coord,EnumFacing.DOWN, coord.down()))
                .withProperty(SOUTH, this.canConnectTo(world,coord,EnumFacing.SOUTH, coord.south()))
                .withProperty(EAST, this.canConnectTo(world,coord,EnumFacing.EAST, coord.east()))
                .withProperty(UP, this.canConnectTo(world,coord,EnumFacing.UP, coord.up()))
                .withProperty(NORTH, this.canConnectTo(world,coord,EnumFacing.NORTH, coord.north()));
    }
    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess world, final BlockPos coord) {
        final boolean connectNorth = this.canConnectTo(world,coord,EnumFacing.NORTH, coord.north());
        final boolean connectSouth = this.canConnectTo(world,coord,EnumFacing.SOUTH, coord.south());
        final boolean connectWest = this.canConnectTo(world,coord,EnumFacing.WEST, coord.west());
        final boolean connectEast = this.canConnectTo(world,coord,EnumFacing.EAST, coord.east());
        final boolean connectUp = this.canConnectTo(world,coord,EnumFacing.UP, coord.up());
        final boolean connectDown = this.canConnectTo(world,coord,EnumFacing.DOWN, coord.down());

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
        if(connectDown){
            y1 = 0.0f;
        }
        if(connectUp){
            y2 = 1.0f;
        }
        this.setBlockBounds(x1, y1, z1, x2, y2, z2);
    }

    /**
     * Calculates the collision boxes for this block.
     */
    @Override
    public void addCollisionBoxesToList(final World world, final BlockPos coord,
                                        final IBlockState bs, final AxisAlignedBB box,
                                        final List collisionBoxList,
                                        final Entity entity) {
        final boolean connectNorth = this.canConnectTo(world,coord,EnumFacing.NORTH, coord.north());
        final boolean connectSouth = this.canConnectTo(world,coord,EnumFacing.SOUTH, coord.south());
        final boolean connectWest = this.canConnectTo(world,coord,EnumFacing.WEST, coord.west());
        final boolean connectEast = this.canConnectTo(world,coord,EnumFacing.EAST, coord.east());
        final boolean connectUp = this.canConnectTo(world,coord,EnumFacing.UP, coord.up());
        final boolean connectDown = this.canConnectTo(world,coord,EnumFacing.DOWN, coord.down());

        float radius = pipeRadius;
        float rminus = 0.5f - radius;
        float rplus = 0.5f + radius;

        this.setBlockBounds(rminus, rminus, rminus, rplus, rplus, rplus);
        super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);

        if(connectUp){
            this.setBlockBounds(rminus, rminus, rminus, rplus, 1f, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if(connectDown){
            this.setBlockBounds(rminus, 0f, rminus, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if(connectEast){
            this.setBlockBounds(rminus, rminus, rminus, 1f, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if(connectWest){
            this.setBlockBounds(0f, rminus, rminus, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if(connectSouth){
            this.setBlockBounds(rminus, rminus, rminus, rplus, rplus, 1f);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if(connectNorth){
            this.setBlockBounds(rminus, rminus, 0f, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
    }

    /**
     * This method determines whether to connect to a neighboring block.
     * Override this method to change block connection behavior.
     * @param w World instance
     * @param thisBlock The block that is checking its neighbor
     * @param face The face on the first block through which the connection would happen
     * @param otherBlock Coordinate of neighboring block
     * @return Default implementation: true if the neighboring block implements
     * ITypedConductor and has the same energy type as this block. Overriding
     * the canConnectTo(ConductorType) method will change the results of this
     * method.
     */
    protected boolean canConnectTo(IBlockAccess w, BlockPos thisBlock, EnumFacing face, BlockPos otherBlock){
        Block other = w.getBlockState(otherBlock).getBlock();
        if(other instanceof BlockAbstractCable || other instanceof BlockAbstractMachina){
            return true;
        } else {
            return false;
        }
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
    public int getMetaFromState(final IBlockState bs) {
        return 0;
    }

    /**
     * Override of default block behavior
     */
    @SideOnly(Side.CLIENT)
    @Override
    public boolean shouldSideBeRendered(final IBlockAccess world, final BlockPos coord, final EnumFacing face) {
        return true;
    }

    @Override
    public int getRenderType()
    {
        return 3;
    }
}
