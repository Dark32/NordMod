package ru.nord.common.lib.recipes;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.NordBloks;
import ru.nord.NordItems;
import ru.nord.common.lib.utils.enums.EnumDust;
import ru.nord.common.lib.utils.enums.EnumMetal;
import ru.nord.common.lib.utils.enums.EnumOre;


/**
 * Created by nikit_000 on 06.07.2015.
 */
public class Recipe {
    public static void addAll(){
        addAllRecipeVanilla();
        addAllRecipeMacerator();
    }
    public static void addAllRecipeVanilla(){
        //GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock, 1), "xxx", "x x", "xxx", 'x', new ItemStack(Blocks.stone, 1));
        GameRegistry.addSmelting(new ItemStack(NordItems.itemDust, 1, EnumDust.IRON.getMetadata()), new ItemStack(Items.iron_ingot), 0);
        GameRegistry.addSmelting(new ItemStack(NordItems.itemDust, 1, EnumDust.GOLD.getMetadata()), new ItemStack(Items.gold_ingot), 0);
        GameRegistry.addRecipe(new ItemStack(NordItems.itemStick,2,EnumDust.IRON.getMetadata()),"x","x",
                'x',new ItemStack(Items.iron_ingot,1));
        GameRegistry.addRecipe(new ItemStack(NordItems.itemStick,2,EnumDust.GOLD.getMetadata()),"x","x",
                'x',new ItemStack(Items.gold_ingot,1));
        for (EnumMetal metal : EnumMetal.values()) {
            GameRegistry.addSmelting(
                    new ItemStack(NordItems.itemDust, 1, metal.getDust().getMetadata()),
                    new ItemStack(NordItems.itemIngot,1, metal.getMetadata()),
                    1);
            GameRegistry.addRecipe(new ItemStack(NordItems.itemStick,2,metal.getMetadata()),"x","x",
                    'x',new ItemStack(NordItems.itemIngot,2,metal.getMetadata()));
        }

        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(NordBloks.frame), 1, 1), "yxy", "x x", "yxy",
                'x', new ItemStack(Blocks.planks, 1),
                'y', new ItemStack(Items.stick));

        GameRegistry.addRecipe(new ItemStack(Item.getItemFromBlock(NordBloks.frame),1,0),"yxy", "xbx", "yxy",
                'x',new ItemStack(Items.iron_ingot, 1),
                'y',new ItemStack(NordItems.itemStick,1,0),
                'b',new ItemStack(NordBloks.frame,1,1));
        GameRegistry.addRecipe(new ItemStack(NordBloks.energyCableBlock,12),"xxx", "yyy", "xxx",
                'x',new ItemStack(Blocks.wool, 1),
                'y',new ItemStack(NordItems.itemIngot, 1,1));
        GameRegistry.addRecipe(new ItemStack(NordItems.itemBlades,1)," x ", "xyx", " x ",
                'x',new ItemStack(NordItems.itemStick, 1,0),
                'y',new ItemStack(Items.iron_ingot, 1));
        GameRegistry.addRecipe(new ItemStack(NordItems.energyStorageItem,1,16000),"yxy", "xsb", "yxy",
                'x',new ItemStack(Blocks.redstone_block,1),
                'y',new ItemStack(NordItems.itemStick,1,1),
                'b',new ItemStack(NordBloks.energyCableBlock,1),
                's',new ItemStack(Items.gold_ingot,1,0));
        GameRegistry.addRecipe(new ItemStack(NordBloks.flowingBlock),"yxy", "xsx", "yby",
                'x',new ItemStack(NordBloks.energyCableBlock,1),
                'y',new ItemStack(NordItems.itemStick,1,0),
                'b',new ItemStack(NordBloks.generatorBlock,1),
                's',new ItemStack(NordItems.itemBlades,1));
        GameRegistry.addRecipe(new ItemStack(NordBloks.generatorBlock),"yxy", "xsx", "yby",
                'x',new ItemStack(NordBloks.energyCableBlock,1),
                'y',new ItemStack(NordItems.itemStick,1,0),
                'b',new ItemStack(Blocks.furnace,1),
                's',new ItemStack(Blocks.redstone_block,1));
        GameRegistry.addRecipe(new ItemStack(NordBloks.accumulatorBlock,1),"yxy", "xsb", "yxy",
                'x',new ItemStack(NordItems.energyStorageItem, 1,16000),
                'y',new ItemStack(NordItems.itemStick,1,0),
                'b',new ItemStack(NordBloks.energyCableBlock,1),
                's',new ItemStack(NordBloks.frame,1,0));
        GameRegistry.addRecipe(new ItemStack(NordItems.wrench,1),"xx","yx"," x",
                'x',new ItemStack(NordItems.itemStick,1,0),
                'y',new ItemStack(Items.iron_ingot));
        for(int i = 0;i< EnumMetal.values().length;i++){
        GameRegistry.addRecipe(new ItemStack(NordBloks.metalBlock,1,i),"xxx","xxx","xxx",
                'x',new ItemStack(NordItems.itemIngot,1,i));
            GameRegistry.addSmelting(new ItemStack(NordItems.itemDust, 1, i+2), new ItemStack(NordItems.itemIngot,1,i), 0);
        }

    }
    public static void addAllRecipeMacerator(){
        //FlowingRecipes1I2O.addRecipe(ItemStack input, ItemStack output1,ItemStack output2, int needEnergy, float percent, float exp)
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.gold_ore),1),
                new ItemStack(NordItems.itemDust,2,1),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone),1)
                ,80,90,0);
        FlowingRecipes1I2O.addRecipe(new ItemStack(Item.getItemFromBlock(Blocks.iron_ore),1),
                new ItemStack(NordItems.itemDust,2,0),
                new ItemStack(Item.getItemFromBlock(Blocks.cobblestone),1)
                ,80,90,0);

        for (EnumOre ore : EnumOre.values()) {
            FlowingRecipes1I2O.addRecipe(
                    new ItemStack(NordBloks.metalOre,1,ore.getMetadata()),
                    new ItemStack(NordItems.itemDust,2,ore.getDust().getMetadata()),
                    new ItemStack(Item.getItemFromBlock(Blocks.cobblestone),1)
                    ,80,90,0);
        }
        for (EnumMetal metal : EnumMetal.values()) {
            FlowingRecipes1I2O.addRecipe(
                    new ItemStack(NordItems.itemIngot,1,metal.getMetadata()),
                    new ItemStack(NordItems.itemDust,1,metal.getDust().getMetadata()),
                    new ItemStack(Item.getItemFromBlock(Blocks.cobblestone),1)
                    ,80,0,0);
        }
    }
}
