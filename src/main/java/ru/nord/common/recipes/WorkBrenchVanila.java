package ru.nord.common.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.NordBloks;
import ru.nord.NordItems;
import ru.nord_core.common.utils.enums.EnumClearMetal;
import ru.nord_core.common.utils.enums.EnumDust;
import ru.nord_core.common.utils.enums.EnumMetal;
import ru.nord_core.common.utils.enums.EnumNugget;

/**
 * Created by andrew on 03.08.15.
 */
public class WorkBrenchVanila {
    public static void postInit() {
//GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock, 1), "xxx", "x x", "xxx", 'x', new ItemStack(Blocks.stone, 1));
        GameRegistry.addRecipe(new ItemStack(NordItems.itemStick, 2, EnumDust.IRON.getMetadata()), "x", "x",
                'x', new ItemStack(Items.iron_ingot, 1));
        GameRegistry.addRecipe(new ItemStack(NordItems.itemStick, 2, EnumDust.GOLD.getMetadata()), "x", "x",
                'x', new ItemStack(Items.gold_ingot, 1));
        for (EnumMetal metal : EnumMetal.values()) {
            GameRegistry.addRecipe(new ItemStack(NordItems.itemStick, 2, metal.getMetadata()), "x", "x",
                    'x', new ItemStack(NordItems.itemIngot, 2, metal.getMetadata()));
        }

        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(NordBloks.frame), 1, 1), "yxy", "x x", "yxy",
                'x', new ItemStack(Blocks.planks, 1),
                'y', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(NordBloks.frame), 1, 0), "yxy", "xbx", "yxy",
                'x', new ItemStack(Items.iron_ingot, 1),
                'y', new ItemStack(NordItems.itemStick, 1, 0),
                'b', new ItemStack(NordBloks.frame, 1, 1));
        GameRegistry.addRecipe(new ItemStack(NordBloks.energyCableBlock, 12), "xxx", "yyy", "xxx",
                'x', new ItemStack(Blocks.wool, 1),
                'y', new ItemStack(NordItems.itemIngot, 1, 1));
        GameRegistry.addRecipe(new ItemStack(NordItems.itemBlades, 1), " x ", "xyx", " x ",
                'x', new ItemStack(NordItems.itemStick, 1, 0),
                'y', new ItemStack(Items.iron_ingot, 1));
        GameRegistry.addRecipe(new ItemStack(NordItems.energyStorageItem, 1, 16000), "yxy", "xsb", "yxy",
                'x', new ItemStack(Blocks.redstone_block, 1),
                'y', new ItemStack(NordItems.itemStick, 1, 1),
                'b', new ItemStack(NordBloks.energyCableBlock, 1),
                's', new ItemStack(Items.gold_ingot, 1, 0));
        GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock), "yxy", "xsx", "yby",
                'x', new ItemStack(NordBloks.energyCableBlock, 1),
                'y', new ItemStack(NordItems.itemStick, 1, 0),
                'b', new ItemStack(NordBloks.generatorBlock, 1),
                's', new ItemStack(NordItems.itemBlades, 1));
        GameRegistry.addRecipe(new ItemStack(NordBloks.generatorBlock), "yxy", "xsx", "yby",
                'x', new ItemStack(NordBloks.energyCableBlock, 1),
                'y', new ItemStack(NordItems.itemStick, 1, 0),
                'b', new ItemStack(Blocks.furnace, 1),
                's', new ItemStack(Blocks.redstone_block, 1));
        GameRegistry.addRecipe(new ItemStack(NordBloks.accumulatorBlock, 1), "yxy", "xsb", "yxy",
                'x', new ItemStack(NordItems.energyStorageItem, 1, 16000),
                'y', new ItemStack(NordItems.itemStick, 1, 0),
                'b', new ItemStack(NordBloks.energyCableBlock, 1),
                's', new ItemStack(NordBloks.frame, 1, 0));
        GameRegistry.addRecipe(new ItemStack(NordItems.wrench, 1), "xx", "yx", " x",
                'x', new ItemStack(NordItems.itemStick, 1, 0),
                'y', new ItemStack(Items.iron_ingot));
        for (int i = 0; i < EnumMetal.values().length; i++) {
            GameRegistry.addRecipe(new ItemStack(NordBloks.metalBlock, 1, i), "xxx", "xxx", "xxx",
                    'x', new ItemStack(NordItems.itemIngot, 1, i));
        }
        GameRegistry.addRecipe(new ItemStack(NordBloks.oilLamp), "wfw", "sts", "www",
                'w', new ItemStack(Blocks.planks),
                's', new ItemStack(NordItems.itemStick, 1, 0),
                'f', new ItemStack(Items.flint_and_steel),
                't', new ItemStack(Items.string));
        GameRegistry.addRecipe(new ItemStack(NordBloks.floorLamp1), "xxx", "ysy",
                'x', new ItemStack(Blocks.glass),
                'y', new ItemStack(Blocks.planks),
                's', new ItemStack(Blocks.redstone_lamp));
        GameRegistry.addRecipe(new ItemStack(NordBloks.floorLamp1, 1, 1), "xy",
                'x', new ItemStack(NordBloks.floorLamp1, 1, 0),
                'y', new ItemStack(Blocks.redstone_torch));
        for (int i = 0; i < 15; i++) {
            GameRegistry.addRecipe(new ItemStack(NordBloks.empireLamp1, 1, i), "xyx", "ysy", "xyx",
                    'x', new ItemStack(NordItems.itemStick, 1, 0),
                    'y', new ItemStack(Blocks.stained_glass, 1, i),
                    's', new ItemStack(Blocks.torch)
            );
            GameRegistry.addRecipe(new ItemStack(NordBloks.empireFloorLamp1, 1, i), "xxx", "ysy",
                    'x', new ItemStack(Blocks.stained_glass, 1, i),
                    'y', new ItemStack(Blocks.planks),
                    's', new ItemStack(Blocks.torch));
        }

        for (EnumNugget nug : EnumNugget.values()) {
            if (nug.getMetal() != null) {
                ItemStack nugget = new ItemStack(NordItems.itemOreNugget, 1, nug.getMetadata());
                GameRegistry.addShapelessRecipe(new ItemStack(NordItems.itemIngot, 1, nug.getMetal().getMetadata()),
                        nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget, nugget);
                GameRegistry.addShapelessRecipe(new ItemStack(NordItems.itemOreNugget, 9, nug.getMetadata())
                        , new ItemStack(NordItems.itemIngot, 1, nug.getMetal().getMetadata()));
            }
        }
        for(EnumMetal ingot : EnumMetal.values()){
            ItemStack _ingot = new ItemStack(NordItems.itemIngot, 1, ingot.getMetadata());
            GameRegistry.addShapelessRecipe(new ItemStack(NordBloks.metalBlock, 1, ingot.getMetadata()),
                    _ingot, _ingot, _ingot, _ingot, _ingot, _ingot, _ingot, _ingot, _ingot);
            GameRegistry.addShapelessRecipe(new ItemStack(NordItems.itemIngot,9, ingot.getMetadata()),
                     new ItemStack(NordBloks.metalBlock, 1, ingot.getMetadata()));
        }

        for(EnumClearMetal ingot : EnumClearMetal.values()){
            ItemStack _ingot = new ItemStack(NordItems.itemClearIngot, 1, ingot.getMetadata());
            GameRegistry.addShapelessRecipe(new ItemStack(NordBloks.metalClearBlock, 1, ingot.getMetadata()),
                    _ingot, _ingot, _ingot, _ingot, _ingot, _ingot, _ingot, _ingot, _ingot);
            GameRegistry.addShapelessRecipe(new ItemStack(NordItems.itemClearIngot,9, ingot.getMetadata()),
                    new ItemStack(NordBloks.metalClearBlock, 1, ingot.getMetadata()));
        }
    }
}
