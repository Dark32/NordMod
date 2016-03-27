package ru.nord.common.items.wood.type3;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerFoliage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.blocks.wood.type3.BlockNordLeaves3;
import ru.nord.common.utils.enums.EnumNordPlank3;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;

public class ItemBlockNordLeaves3 extends ItemBlockMetadata implements IItemColor {
    private final BlockNordLeaves3 leaves;

    public ItemBlockNordLeaves3(Block block)
    {
        super(block);
        this.leaves = (BlockNordLeaves3)block;
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getColorFromItemstack(ItemStack stack, int tintIndex) {
        return ColorizerFoliage.getFoliageColorBasic();
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
