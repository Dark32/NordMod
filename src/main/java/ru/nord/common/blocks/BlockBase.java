package ru.nord.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.registry.GameRegistry;
import ru.nord.Nord;

public class BlockBase extends Block {
    private final String name = "tutorialBlock";

    public BlockBase() {
        super(Material.rock);
        GameRegistry.registerBlock(this, name);
        setUnlocalizedName(Nord.MODID + "_" + name);
        setCreativeTab(CreativeTabs.tabBlock);
    }

    public String getName() {
        return name;
    }
}