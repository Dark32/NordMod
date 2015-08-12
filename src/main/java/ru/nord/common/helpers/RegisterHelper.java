package ru.nord.common.helpers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import ru.nord.NordConfig;
import ru.nord.common.utils.Version;
import ru.nord.common.utils.generator.NordOre;
import ru.nord.common.utils.generator.Ore;

public class RegisterHelper {


    public static void registerSingleBlock(Block block, String name) {
        ru.nord_core.common.helpers.RegisterHelper.registerSingleBlock(block, name, name, Version.MODID);
    }

    public static void registerSingleBlock(Block block, String name, String model) {
        ru.nord_core.common.helpers.RegisterHelper.registerSingleBlock(block,name,model,Version.MODID);
    }

    public static void registerSingleItem(Item item, String name) {
        registerSingleItem(item, name, name);
    }

    public static void registerSingleItem(Item item, String name, String model) {
        ru.nord_core.common.helpers.RegisterHelper.registerSingleItem(item, name, model, Version.MODID);
    }

    public static void registerMetadataBlock(Block block, Class<? extends ItemBlock> itemBlock,
                                             String name, String[] additionals) {
        registerMetadataBlock(block, itemBlock, name, name, additionals);
    }

    public static void registerMetadataBlock(Block block, Class<? extends ItemBlock> itemBlock,
                                             String name,int count) {
        registerMetadataBlock(block, itemBlock, name, name, count);
    }

    public static void registerMetadataBlock(
            Block block,
            Class<? extends ItemBlock> itemBlock,
            String name,
            String model,
            String[] additionals
    ) {
        ru.nord_core.common.helpers.RegisterHelper.registerMetadataBlock(block,itemBlock,name,model,additionals,Version.MODID);
    }
    public static void registerMetadataItem(
            Item itemVar,
            String name,
            String model,
            String[] additionals
    ){
        ru.nord_core.common.helpers.RegisterHelper.registerMetadataItem(itemVar,name,model,additionals,Version.MODID);
    }

    public static void registerMetadataBlock(
            Block block,
            Class<? extends ItemBlock> itemBlock,
            String name,
            String model,
            int count) {
        ru.nord_core.common.helpers.RegisterHelper.registerMetadataBlock(block,itemBlock,name,model,count,Version.MODID);
    }

    public static void registerOre(boolean enableOre,int minY,int maxY,int veinSize,int frequencyOre,IBlockState block, int dimId){
        if(enableOre){
            NordOre.listOre.add(new Ore(minY,maxY,block,frequencyOre, dimId,veinSize));
        }
    }
    public static void registerOreInOverWithString(String ore,IBlockState block){
        registerOre(NordConfig.getEnableOre(ore),NordConfig.getMinY(ore),NordConfig.getMaxY(ore),NordConfig.getVeinSize(ore),NordConfig.getVeinSize(ore),block,0);

    }

}