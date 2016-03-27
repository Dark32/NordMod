package ru.nord_core.common.items.abstracts;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
//import net.minecraft.network.play.client.C07PacketPlayerDigging;
//import net.minecraft.network.play.server.S23PacketBlockChange;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import ru.nord_core.common.helpers.ToolHelper;
import ru.nord_core.common.items.abstracts.AEnergyToolNBT;
import ru.nord_core.common.utils.Constants;


public abstract class AItemDrill extends AEnergyToolNBT {
    int breakRadius = 1, breakDepth = 0;

    public AItemDrill(float attackDamage, ToolMaterial material,
                      int energyByUse, int energyByHit) {
        super(attackDamage, material, energyByUse, energyByHit);
    }
/*
    @Override
    public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
        RayTraceResult mop = ToolHelper.raytraceFromEntity(player.worldObj, player, false, 4.5d);
        if (mop == null)
            return false;
        EnumFacing sideHit = mop.sideHit;
        World world = player.worldObj;
        int xRange = breakRadius;
        int yRange = breakRadius;
        int zRange = breakDepth;
        switch (sideHit) {
            case DOWN:
            case UP:
                yRange = breakDepth;
                zRange = breakRadius;
                break;
            case NORTH:
            case SOUTH:
                xRange = breakRadius;
                zRange = breakDepth;
                break;
            case WEST:
            case EAST:
                xRange = breakDepth;
                zRange = breakRadius;
                break;
        }
        int X = pos.getX(), Y = pos.getY(), Z = pos.getZ();
        for (int xPos = X - xRange; xPos <= X + xRange; xPos++)
            for (int yPos = Y - yRange; yPos <= Y + yRange; yPos++)
                for (int zPos = Z - zRange; zPos <= Z + zRange; zPos++) {
                    // don't break the originally already broken block, duh
                    if (xPos == X && yPos == Y && zPos == Z)
                        continue;
                    BlockPos _pos = new BlockPos(xPos, yPos, zPos);
                    if (!super.onBlockStartBreak(itemstack, _pos, player))
                        breakExtraBlock(player.worldObj, _pos, sideHit, player, pos);
                }
        return super.onBlockStartBreak(itemstack, pos, player);
    }

*/
    /*
    //Right-click
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player,
                             World world, BlockPos pos, EnumFacing side,
                             float hitX, float hitY, float hitZ) {
        if (world.isRemote)
            return true;
        boolean used = false;
        int hotbarSlot = player.inventory.currentItem;
        int itemSlot = hotbarSlot == 0 ? 8 : hotbarSlot + 1;
        ItemStack nearbyStack = null;

        if (hotbarSlot < 8) {
            nearbyStack = player.inventory.getStackInSlot(itemSlot);
            if (nearbyStack != null) {
                Item item = nearbyStack.getItem();

                if (item instanceof ItemBlock) {
                    int posX = pos.getX();
                    int posY = pos.getY();
                    int posZ = pos.getZ();

                    switch (side) {
                        case DOWN:
                            --posY;
                            break;
                        case UP:
                            ++posY;
                            break;
                        case NORTH:
                            --posZ;
                            break;
                        case SOUTH:
                            ++posZ;
                            break;
                        case WEST:
                            --posX;
                            break;
                        case EAST:
                            ++posX;
                            break;
                    }

                    AxisAlignedBB blockBounds = new AxisAlignedBB(posX, posY, posZ, posX + 1, posY + 1, posZ + 1);
                    AxisAlignedBB playerBounds = player.getEntityBoundingBox();

                    if (item instanceof ItemBlock) {
                        Block blockToPlace = ((ItemBlock) item).block;
                        if (blockToPlace.getMaterial().blocksMovement()) {
                            if (playerBounds.intersectsWith(blockBounds))
                                return false;
                        }
                    }

                    int dmg = nearbyStack.getItemDamage();
                    int count = nearbyStack.stackSize;
                    used = item.onItemUse(nearbyStack, player, world, pos, side, hitX, hitY, hitZ);

                    // handle creative mode
                    if (player.capabilities.isCreativeMode) {
                        // fun fact: vanilla minecraft does it exactly the same way
                        nearbyStack.setItemDamage(dmg);
                        nearbyStack.stackSize = count;
                    }
                    if (nearbyStack.stackSize < 1) {
                        nearbyStack = null;
                        player.inventory.setInventorySlotContents(itemSlot, null);
                    }
                }
            }
        }
        return used;
    }

 */
    /*
    protected void breakExtraBlock(World world, BlockPos pos, EnumFacing facing, EntityPlayer playerEntity, BlockPos refPos) {
        // prevent calling that stuff for air blocks, could lead to unexpected behaviour since it fires events
        if (world.isAirBlock(pos))
            return;
        // what?
        if (!(playerEntity instanceof EntityPlayerMP))
            return;
        EntityPlayerMP player = (EntityPlayerMP) playerEntity;

        // check if the block can be broken, since extra block breaks shouldn't instantly break stuff like obsidian
        // or precious ores you can't harvest while mining stone
        IBlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
//        int meta = state.ge;

        // only effective materials
        if (!isEffective(state))
            return;
        IBlockState refBlock = world.getBlockState(refPos);
        float refStrength = ForgeHooks.blockStrength(refBlock, player, world, refPos);
        float strength = ForgeHooks.blockStrength(state, player, world, pos);

        // only harvestable blocks that aren't impossibly slow to harvest
        if (!ForgeHooks.canHarvestBlock(block, player, world, pos) || refStrength / strength > 10f)
            return;
        // send the blockbreak event
        int event = ForgeHooks.onBlockBreakEvent(world, player.theItemInWorldManager.getGameType(), player, pos);

        if (event == -1)
            return;

        if (player.capabilities.isCreativeMode) {
            block.onBlockHarvested(world, pos, state, player);
            if (block.removedByPlayer(world, pos, player, false))
                block.onBlockDestroyedByPlayer(world, pos, state);

            // send update to client
            if (!world.isRemote) {
                player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
            }
            return;
        }

        // callback to the tool the player uses. Called on both sides. This damages the tool n stuff.
        player.getCurrentEquippedItem().damageItem(1, player);
        // server sided handling
        if (!world.isRemote) {
            // serverside we reproduce ItemInWorldManager.tryHarvestBlock

            // ItemInWorldManager.removeBlock
            block.onBlockHarvested(world, pos, state, player);
            if (block.removedByPlayer(world, pos, player, true)) // boolean is if block can be harvested, checked above
            {
                block.onBlockDestroyedByPlayer(world, pos, state);
                TileEntity tile = world.getTileEntity(pos);
                block.harvestBlock(world, player, pos, state, tile);
                block.dropXpOnBlockBreak(world, pos, event);
            }

            // always send block update to client
            player.playerNetServerHandler.sendPacket(new S23PacketBlockChange(world, pos));
        }
        // client sided handling
        else {
            //PlayerControllerMP pcmp = Minecraft.getMinecraft().playerController;
            // clientside we do a "this clock has been clicked on long enough to be broken" call. This should not send any new packets
            // the code above, executed on the server, sends a block-updates that give us the correct state of the block we destroy.

            // following code can be found in {@link}PlayerControllerMP.onPlayerDestroyBlock
            world.playAuxSFX(2001, pos, Block.getStateId(state));
            if (block.removedByPlayer(world, pos, player, true)) {
                block.onBlockDestroyedByPlayer(world, pos, state);
            }

            // send an update to the server, so we get an update back
            Minecraft.getMinecraft().getNetHandler().addToSendQueue(new C07PacketPlayerDigging(C07PacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, pos, Minecraft.getMinecraft().objectMouseOver.sideHit));
        }
    }*/

    public boolean isEffective(IBlockState state) {
        if (this.getHarvestType().getName().equals(state.getBlock().getHarvestTool(state)))
            return true;
        else return isEffective(state.getBlock().getMaterial(state));
    }

    public boolean isEffective(Material material) {
        for (Material m : getEffectiveMaterials())
            if (m == material)
                return true;

        return false;
    }

    protected Material[] getEffectiveMaterials() {
        return materials;
    }

    static Material[] materials = new Material[]{Material.rock, Material.iron, Material.ice, Material.glass, Material.piston, Material.anvil};

}
