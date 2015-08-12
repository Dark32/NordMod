package ru.nord.common.events;

import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import ru.nord.NordItems;
import ru.nord_core.common.utils.enums.EnumOreDrop;
public class OreDropEvent {


    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onHarvest(BlockEvent.HarvestDropsEvent event) {
        if (event.isCanceled()) {
            return;
        }
        IBlockState state = event.state;
        int fortune = EnchantmentHelper.getFortuneModifier(event.harvester);
        int quantity = event.harvester.worldObj.rand.nextInt(fortune + 1) + 1;
        if (!event.harvester.capabilities.isCreativeMode && !event.isSilkTouching) {
            if (state.getBlock() == Blocks.iron_ore || state.getBlock() == Blocks.gold_ore) {
                event.drops.clear();
                if (state.getBlock() == Blocks.iron_ore) {
                    quantity *= event.harvester.worldObj.rand.nextInt(3) + 3;
                    int meta = EnumOreDrop.MAGNETITE.getMetadata();
                    event.drops.add(new ItemStack(NordItems.itemOreDrop, quantity, meta));
                } else {
                    quantity *= event.harvester.worldObj.rand.nextInt(5) + 6;
                    event.drops.add(new ItemStack(Items.gold_nugget, quantity));
                }
            }
        }
    }


    /*
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public void onDrops(BlockEvent.BreakEvent event) {
        FMLLog.warning(event.state.getBlock().toString());
        if (event.isCanceled()) {
            return;
        }
        BlockPos pos = event.pos;
        IBlockState state = event.state;
        EntityPlayer player = event.getPlayer();
        Random random = player.worldObj.rand;
        boolean silky = EnchantmentHelper.getSilkTouchModifier(player);
        int fortune = EnchantmentHelper.getFortuneModifier(player);
        if (!player.capabilities.isCreativeMode &&
                player.getHeldItem().canHarvestBlock(state.getBlock()) &&
                !silky) {
            if (state.getBlock() == Blocks.iron_ore || state.getBlock() == Blocks.gold_ore) {
                event.setCanceled(true);
                ItemStack itemStacck = null;
                int quantity = random.nextInt(fortune + 1) + 1;
                if (state.getBlock() == Blocks.iron_ore) {
                    quantity *= random.nextInt(3) + 3;
                    int meta = EnumOreDrop.MAGNETITE.getMetadata();
                    itemStacck = new ItemStack(NordItems.itemOreDrop, quantity, meta);
                } else {
                    quantity *= random.nextInt(5) + 6;
                    itemStacck = new ItemStack(Items.gold_nugget, quantity);
                }
                EntityItem entityitem = new EntityItem(player.worldObj,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        itemStacck);
                entityitem.setDefaultPickupDelay();
                player.worldObj.spawnEntityInWorld(entityitem);
            }
        }
    }
    */
}
