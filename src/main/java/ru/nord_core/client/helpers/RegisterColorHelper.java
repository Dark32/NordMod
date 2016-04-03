package ru.nord_core.client.helpers;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.FMLClientHandler;

/**
 * Created by andrew on 03.04.16.
 */
public class RegisterColorHelper extends ru.nord_core.common.helpers.RegisterColorHelper{
    public static final RegisterColorHelper INSTANCE = new RegisterColorHelper();

    public final BlockColors blockColor = FMLClientHandler.instance().getClient().getBlockColors();
    public final ItemColors itemColor = FMLClientHandler.instance().getClient().getItemColors();

    @Override
    public void registerBlockColorHandler(Block block){
        if (block instanceof IBlockColor){
            IBlockColor iBlockColor = (IBlockColor) block;
            blockColor.registerBlockColorHandler(iBlockColor,block);
        }
    }
    @Override
    public void registerItemColorHandler(Block block){
        Item item = Item.getItemFromBlock(block);
        if (item instanceof IItemColor){
            IItemColor iItemColor = (IItemColor)item;
            itemColor.registerItemColorHandler(iItemColor,block);
        }
    }
    @Override
    public void registerItemColorHandler(Item item){
        if (item instanceof IItemColor){
            IItemColor iItemColor = (IItemColor)item;
            itemColor.registerItemColorHandler(iItemColor,item);
        }
    }
}
