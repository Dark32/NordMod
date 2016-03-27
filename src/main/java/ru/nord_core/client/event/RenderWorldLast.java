package ru.nord_core.client.event;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord_core.common.items.interfaces.ISelectItem;

public class RenderWorldLast {

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onBlockHighlight(RenderWorldLastEvent event) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        double shift2 = 0.01d;
//        if (player.getHeldItem() != null && player.getHeldItem().getItem() == NordCoreItems.debugStickItemSchematickSave) {
        if (player.getHeldItem(EnumHand.MAIN_HAND) != null && player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ISelectItem) {
            ItemStack itemStack = player.getHeldItem(EnumHand.MAIN_HAND);
            ((ISelectItem)itemStack.getItem()).renderSelectArea(itemStack, player,event.getPartialTicks());

        }
    }


}
