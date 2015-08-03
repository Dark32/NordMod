package ru.nord.common.lib.events;



import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class OreDropEvent {

    @SubscribeEvent(priority= EventPriority.NORMAL)
    public void onDrops(BlockEvent.BreakEvent event)
    {
        World world = event.world;
        BlockPos pos = event.pos;
        IBlockState state = event.state;
        FMLLog.warning(state.getBlock().toString());
        if (!event.getPlayer().capabilities.isCreativeMode){
            if (state.getBlock() == Blocks.iron_ore || state.getBlock() == Blocks.gold_ore){
                event.setCanceled(true);
                
            }
        }
    }

}
