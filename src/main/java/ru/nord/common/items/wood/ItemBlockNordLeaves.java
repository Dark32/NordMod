package ru.nord.common.items.wood;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.blocks.wood.BlockNordLeaves;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockNordLeaves extends ItemBlockMetadata {
    private final BlockNordLeaves leaves;

    public ItemBlockNordLeaves(Block block)
    {
        super(block);
        this.leaves = (BlockNordLeaves)block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage)
    {
        return damage | 4;
    }
    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        return this.leaves.getRenderColor(this.leaves.getStateFromMeta(stack.getMetadata()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "." + this.leaves.getWoodType2(stack.getMetadata()).getName();
    }
}
