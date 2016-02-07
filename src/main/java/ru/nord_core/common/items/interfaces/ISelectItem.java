package ru.nord_core.common.items.interfaces;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by andrew on 06.02.16.
 */
public interface ISelectItem {

    @SideOnly(Side.CLIENT)
    void renderSelectArea(ItemStack itemStack, EntityPlayer player, double partialTicks);
}
