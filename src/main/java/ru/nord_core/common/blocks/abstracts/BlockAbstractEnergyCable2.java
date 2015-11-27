package ru.nord_core.common.blocks.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.Sys;
import ru.nord.common.utils.Version;
import ru.nord_core.common.blocks.BlockBase;
import ru.nord_core.common.blocks.interfaces.IWrenchable;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyCable2;
import ru.nord_core.common.tiles.abstracts.TileAbstractEnergyMachine;
import ru.nord_core.common.utils.enums.EnumCableState;
import ru.nord_core.common.utils.enums.EnumColors;

import java.util.List;

public abstract class BlockAbstractEnergyCable2 extends BlockBase implements IWrenchable,ITileEntityProvider {

    // the LINK properties are used to communicate to the ISmartBlockModel which of the links should be drawn
    public static final PropertyEnum CONNECT_UP = PropertyEnum.create("connect_up", EnumCableState.class);
    public static final PropertyEnum CONNECT_DOWN = PropertyEnum.create("connect_down", EnumCableState.class);
    public static final PropertyEnum CONNECT_WEST = PropertyEnum.create("connect_west", EnumCableState.class);
    public static final PropertyEnum CONNECT_EAST = PropertyEnum.create("connect_east", EnumCableState.class);
    public static final PropertyEnum CONNECT_NORTH = PropertyEnum.create("connect_north", EnumCableState.class);
    public static final PropertyEnum CONNECT_SOUTH = PropertyEnum.create("connect_south", EnumCableState.class);
//    public static final PropertyEnum COLOUR = PropertyEnum.create("colour", EnumColors.class);

    private final float pipeRadius; // in fraction of a block (aka meters)

    public BlockAbstractEnergyCable2() {
        super(Version.MODID,Material.iron);
        setHardness(2.0F);
        setResistance(5.0F);
        this.pipeRadius = 0.0625f * 2;
        this.setDefaultState(
                this.blockState.getBaseState()
                        .withProperty(CONNECT_UP, EnumCableState.UNDEFINED)
                        .withProperty(CONNECT_DOWN, EnumCableState.UNDEFINED)
                        .withProperty(CONNECT_WEST, EnumCableState.UNDEFINED)
                        .withProperty(CONNECT_EAST, EnumCableState.UNDEFINED)
                        .withProperty(CONNECT_NORTH, EnumCableState.UNDEFINED)
                        .withProperty(CONNECT_SOUTH, EnumCableState.UNDEFINED)
        );
    }

    @SideOnly(Side.CLIENT)
    @Override
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
        IProperty[] listedProperties = new IProperty[]{
                CONNECT_UP,
                CONNECT_DOWN,
                CONNECT_EAST,
                CONNECT_WEST,
                CONNECT_NORTH,
                CONNECT_SOUTH,
//                COLOUR
        };
        return new BlockState(this, listedProperties);
    }


    @Override
    public void setBlockBoundsBasedOnState(final IBlockAccess world, final BlockPos coord) {
        final EnumCableState connectNorth = this.getConnectState(world, coord, EnumFacing.NORTH);
        final EnumCableState connectSouth = this.getConnectState(world, coord, EnumFacing.SOUTH);
        final EnumCableState connectWest = this.getConnectState(world, coord, EnumFacing.WEST);
        final EnumCableState connectEast = this.getConnectState(world, coord, EnumFacing.EAST);
        final EnumCableState connectUp = this.getConnectState(world, coord, EnumFacing.UP);
        final EnumCableState connectDown = this.getConnectState(world, coord, EnumFacing.DOWN);

        float rminus = 0.5f - pipeRadius;
        float rplus = 0.5f + pipeRadius;

        float x1 = rminus;
        float x2 = rplus;
        float y1 = rminus;
        float y2 = rplus;
        float z1 = rminus;
        float z2 = rplus;

        if (connectNorth.getBound()) {
            z1 = 0.0f;
        }
        if (connectSouth.getBound()) {
            z2 = 1.0f;
        }
        if (connectWest.getBound()) {
            x1 = 0.0f;
        }
        if (connectEast.getBound()) {
            x2 = 1.0f;
        }
        if (connectDown.getBound()) {
            y1 = 0.0f;
        }
        if (connectUp.getBound()) {
            y2 = 1.0f;
        }


        this.setBlockBounds(x1, y1, z1, x2, y2, z2);
    }

    @Override
    public void addCollisionBoxesToList(final World world, final BlockPos coord,
                                        final IBlockState bs, final AxisAlignedBB box,
                                        final List collisionBoxList,
                                        final Entity entity) {
        final EnumCableState connectNorth = this.getConnectState(world, coord, EnumFacing.NORTH);
        final EnumCableState connectSouth = this.getConnectState(world, coord, EnumFacing.SOUTH);
        final EnumCableState connectWest = this.getConnectState(world, coord, EnumFacing.WEST);
        final EnumCableState connectEast = this.getConnectState(world, coord, EnumFacing.EAST);
        final EnumCableState connectUp = this.getConnectState(world, coord, EnumFacing.UP);
        final EnumCableState connectDown = this.getConnectState(world, coord, EnumFacing.DOWN);

        float rminus = 0.5f - pipeRadius;
        float rplus = 0.5f + pipeRadius;

        this.setBlockBounds(rminus, rminus, rminus, rplus, rplus, rplus);
        super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        if (connectUp.getBound()) {
            this.setBlockBounds(rminus, rminus, rminus, rplus, 1f, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectDown.getBound()) {
            this.setBlockBounds(rminus, 0f, rminus, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectEast.getBound()) {
            this.setBlockBounds(rminus, rminus, rminus, 1f, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectWest.getBound()) {
            this.setBlockBounds(0f, rminus, rminus, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectSouth.getBound()) {
            this.setBlockBounds(rminus, rminus, rminus, rplus, rplus, 1f);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }
        if (connectNorth.getBound()) {
            this.setBlockBounds(rminus, rminus, 0f, rplus, rplus, rplus);
            super.addCollisionBoxesToList(world, coord, bs, box, collisionBoxList, entity);
        }

    }

    @Override
    public boolean wrenche(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (playerIn.isSneaking()) {
            this.removedByPlayer(worldIn, pos, playerIn, true);
            this.dropBlockAsItem(worldIn, pos, state, 0);
            this.disconnectBreakBlock(worldIn, pos, EnumFacing.NORTH, pos.north());
            this.disconnectBreakBlock(worldIn, pos, EnumFacing.SOUTH, pos.south());
            this.disconnectBreakBlock(worldIn, pos, EnumFacing.WEST, pos.west());
            this.disconnectBreakBlock(worldIn, pos, EnumFacing.EAST, pos.east());
            this.disconnectBreakBlock(worldIn, pos, EnumFacing.UP, pos.up());
            this.disconnectBreakBlock(worldIn, pos, EnumFacing.DOWN, pos.down());
        } else {
            TileEntity tileEntity = worldIn.getTileEntity(pos);
            if (tileEntity == null) {
                return false;
            }
            if (tileEntity instanceof TileAbstractEnergyCable2) {
                    playerIn.addChatComponentMessage(new ChatComponentText(
                            " x="+hitX+
                            " z="+hitZ+
                            " y="+hitY
                    ));
                    worldIn.markBlockForUpdate(pos);
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

    @Override
    public void onBlockPlacedBy(final World world, final BlockPos coord,
                                final IBlockState bs, final EntityLivingBase player,
                                final ItemStack item) {
        super.onBlockPlacedBy(world, coord, bs, player, item);
        this.updteCableState(world, coord, bs);
    }

    @Override
    public void onNeighborBlockChange(World worldIn, BlockPos pos, IBlockState state, Block neighborBlock)
    {
        worldIn.markBlockForUpdate(pos);
        super.onNeighborBlockChange(worldIn, pos, state, neighborBlock);
    }

    private void updteCableState(final World worldIn, final BlockPos pos,
                                 final IBlockState state){
        TileAbstractEnergyCable2 tile = (TileAbstractEnergyCable2) worldIn.getTileEntity(pos);
        final EnumCableState connectNorth = this.getConnectStateToPlace(worldIn, pos, EnumFacing.NORTH, pos.north());
        final EnumCableState connectSouth = this.getConnectStateToPlace(worldIn, pos, EnumFacing.SOUTH, pos.south());
        final EnumCableState connectWest = this.getConnectStateToPlace(worldIn, pos, EnumFacing.WEST, pos.west());
        final EnumCableState connectEast = this.getConnectStateToPlace(worldIn, pos, EnumFacing.EAST, pos.east());
        final EnumCableState connectUp = this.getConnectStateToPlace(worldIn, pos, EnumFacing.UP, pos.up());
        final EnumCableState connectDown = this.getConnectStateToPlace(worldIn, pos, EnumFacing.DOWN, pos.down());
        tile.setCableStateOnFacing(EnumFacing.NORTH,connectNorth);
        tile.setCableStateOnFacing(EnumFacing.SOUTH,connectSouth);
        tile.setCableStateOnFacing(EnumFacing.WEST,connectWest);
        tile.setCableStateOnFacing(EnumFacing.EAST,connectEast);
        tile.setCableStateOnFacing(EnumFacing.UP,connectUp);
        tile.setCableStateOnFacing(EnumFacing.DOWN,connectDown);
    }

    private EnumCableState getConnectStateToPlace(IBlockAccess worldIn, BlockPos thisBlock, EnumFacing face, BlockPos otherBlock) {
        IBlockState otherState = worldIn.getBlockState(otherBlock);
        IBlockState thisState = worldIn.getBlockState(thisBlock);

        if (otherState.getBlock() instanceof BlockAbstractEnergyCable2) {
            TileAbstractEnergyCable2 otherTile = (TileAbstractEnergyCable2) worldIn.getTileEntity(otherBlock);
//            if (thisState.getValue(COLOUR) == otherState.getValue(COLOUR)) {
//                otherTile.setCableStateOnFacing(face.getOpposite(), EnumCableState.DISCONNECT);
//                return EnumCableState.DISCONNECT;
//            } else {
                otherTile.setCableStateOnFacing(face.getOpposite(), EnumCableState.CONNECT);
                return EnumCableState.CONNECT;
//            }
        }
        if (otherState.getBlock() instanceof BlockAbstractMachine) {
            return EnumCableState.OUTPUT;
        }
        return EnumCableState.UNDEFINED;
    }

    private EnumCableState getConnectState(IBlockAccess worldIn, BlockPos thisBlock, EnumFacing face) {
        TileAbstractEnergyCable2 tile = (TileAbstractEnergyCable2) worldIn.getTileEntity(thisBlock);
        return tile.getCableStateOnFacing(face);
    }
/*
    private EnumColors getColour(IBlockAccess worldIn, BlockPos thisBlock, EnumFacing face) {
        TileAbstractEnergyCable2 tile = (TileAbstractEnergyCable2) worldIn.getTileEntity(thisBlock);
        return tile.getColor();
    }
*/
    @Override
    public IBlockState getStateFromMeta(int meta){
        return this.getDefaultState()
//                .withProperty(COLOUR, EnumColors.byMetadata(meta))
                ;
    }
    @Override
    public int getMetaFromState(IBlockState state) {
//        return ((IMetadataEnum)state.getValue(COLOUR)).getMetadata();
        return 0;
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return this.getDefaultState()
                .withProperty(CONNECT_DOWN,getConnectState(worldIn,pos,EnumFacing.DOWN))
                .withProperty(CONNECT_UP,getConnectState(worldIn,pos,EnumFacing.UP))
                .withProperty(CONNECT_WEST,getConnectState(worldIn,pos,EnumFacing.WEST))
                .withProperty(CONNECT_EAST,getConnectState(worldIn,pos,EnumFacing.EAST))
                .withProperty(CONNECT_NORTH,getConnectState(worldIn,pos,EnumFacing.NORTH))
                .withProperty(CONNECT_SOUTH,getConnectState(worldIn,pos,EnumFacing.SOUTH))
                ;
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player)
    {
        super.onBlockHarvested(worldIn, pos, state, player);
    }

    private void disconnectBreakBlock(World worldIn, BlockPos thisBlock, EnumFacing face, BlockPos otherBlock) {
        IBlockState otherState = worldIn.getBlockState(otherBlock);
        if (otherState.getBlock() instanceof BlockAbstractEnergyCable2) {
            TileAbstractEnergyCable2 otherTile = (TileAbstractEnergyCable2) worldIn.getTileEntity(otherBlock);
            otherTile.setCableStateOnFacing(face.getOpposite(), EnumCableState.UNDEFINED);
            worldIn.markBlockForUpdate(otherBlock);
        }
    }
    @Override
    public void harvestBlock(World worldIn, EntityPlayer player, BlockPos pos, IBlockState state, TileEntity te){
        this.disconnectBreakBlock(worldIn, pos, EnumFacing.NORTH, pos.north());
        this.disconnectBreakBlock(worldIn, pos, EnumFacing.SOUTH, pos.south());
        this.disconnectBreakBlock(worldIn, pos, EnumFacing.WEST, pos.west());
        this.disconnectBreakBlock(worldIn, pos, EnumFacing.EAST, pos.east());
        this.disconnectBreakBlock(worldIn, pos, EnumFacing.UP, pos.up());
        this.disconnectBreakBlock(worldIn, pos, EnumFacing.DOWN, pos.down());
        super.harvestBlock(worldIn, player, pos, state, te);
    }
}
