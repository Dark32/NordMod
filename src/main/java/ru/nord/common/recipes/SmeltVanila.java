package ru.nord.common.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.NordItems;
import ru.nord.common.lib.recipes.ExtractorRecipes1I2O;
import ru.nord_core.common.utils.enums.EnumDust;
import ru.nord_core.common.utils.enums.EnumMetal;
import ru.nord_core.common.utils.enums.EnumOre;
import ru.nord_core.common.utils.enums.EnumOreDrop;

public class SmeltVanila {
    public static void postInit(){
        /*
        Порошок метала в слиток
         */
        GameRegistry.addSmelting(new ItemStack(NordItems.itemMetalDust, 1, EnumDust.IRON.getMetadata()), new ItemStack(Items.iron_ingot), 0);
        GameRegistry.addSmelting(new ItemStack(NordItems.itemMetalDust, 1, EnumDust.GOLD.getMetadata()), new ItemStack(Items.gold_ingot), 0);
        for (EnumDust ore : EnumDust.values()) {
            if (ore.getMetal() !=null){
                GameRegistry.addSmelting(
                        new ItemStack(NordItems.itemMetalDust, 1, ore.getMetadata()),
                        new ItemStack(NordItems.itemIngot,1,ore.getMetal().getMetadata()), 0);

            }
        }

    }
}
