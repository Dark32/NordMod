package ru.nord.common.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.NordItems;
import ru.nord_core.common.utils.enums.EnumDust;

/**
 * Created by andrew on 03.08.15.
 */
public class SmeltVanila {
    public static void postInit(){
        GameRegistry.addSmelting(new ItemStack(NordItems.itemOreDrop, 1, EnumDust.IRON.getMetadata()), new ItemStack(Items.iron_ingot), 0);
        GameRegistry.addSmelting(new ItemStack(NordItems.itemOreDrop, 1, EnumDust.GOLD.getMetadata()), new ItemStack(Items.gold_ingot), 0);

    }
}
