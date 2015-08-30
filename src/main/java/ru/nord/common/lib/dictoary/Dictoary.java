package ru.nord.common.lib.dictoary;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import org.apache.commons.lang3.text.WordUtils;
import ru.nord.NordBloks;
import ru.nord.NordItems;
import ru.nord_core.common.utils.enums.*;

public class Dictoary {
    public static void postInit() {
        for (EnumDust ore : EnumDust.values()) {
            OreDictionary.registerOre("dust" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordItems.itemMetalDust, 1, ore.getMetadata()));

            OreDictionary.registerOre("stick" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordItems.itemStick, 1, ore.getMetadata()));
        }

        for (EnumOreDrop ore : EnumOreDrop.values()) {
            OreDictionary.registerOre("dust" + WordUtils.capitalize(ore.getMetal()),
                    new ItemStack(NordItems.itemOreClearPowder, 1, ore.getMetadata()));

            OreDictionary.registerOre("powder" + WordUtils.capitalize(ore.getMetal()),
                    new ItemStack(NordItems.itemOreDirtyPowder, 1, ore.getMetadata()));

            OreDictionary.registerOre("drop" + WordUtils.capitalize(ore.getMetal()),
                    new ItemStack(NordItems.itemOreDrop, 1, ore.getMetadata()));
        }

        for (EnumNugget ore : EnumNugget.values()) {
            OreDictionary.registerOre("nugget" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordItems.itemOreNugget, 1, ore.getMetadata()));
        }

        for (EnumCrystal ore : EnumCrystal.values()) {
            OreDictionary.registerOre("gem" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordItems.itemOreCrystal, 1, ore.getMetadata()));

            OreDictionary.registerOre("block" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordBloks.metalCrystal, 1, ore.getMetadata()));
        }

        for (EnumMetal ore : EnumMetal.values()) {
            OreDictionary.registerOre("ingot" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordItems.itemIngot, 1, ore.getMetadata()));

            OreDictionary.registerOre("block" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordBloks.metalBlock, 1, ore.getMetadata()));
        }

        for (EnumClearMetal ore : EnumClearMetal.values()) {
            OreDictionary.registerOre("ingotClear" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordItems.itemClearIngot, 1, ore.getMetadata()));

            OreDictionary.registerOre("blockClear" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordBloks.metalClearBlock, 1, ore.getMetadata()));
        }

        for (EnumOre ore : EnumOre.values()) {
            OreDictionary.registerOre("ore" + WordUtils.capitalize(ore.getName()),
                    new ItemStack(NordBloks.metalOre, 1, ore.getMetadata()));
        }
    }
}
