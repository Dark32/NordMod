package ru.nord.common.items.wood.type3;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.blocks.wood.type3.BlockNordLeaves3;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockNordLeaves3 extends ItemBlockMetadata {
    private final BlockNordLeaves3 leaves;

    public ItemBlockNordLeaves3(Block block)
    {
        super(block);
        this.leaves = (BlockNordLeaves3)block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack stack, int renderPass)
    {
        return this.leaves.getRenderColor(this.leaves.getStateFromMeta(stack.getMetadata()));
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < EnumNordPlank3.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumNordPlank3.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
