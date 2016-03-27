package ru.nord.common.items.wood.type2;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.ColorizerFoliage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import ru.nord.common.blocks.wood.type1.BlockNordLeaves;
import ru.nord.common.blocks.wood.type2.BlockNordLeaves2;
import ru.nord.common.utils.enums.EnumNordPlank2;
import ru.nord_core.common.items.abstracts.ItemBlockMetadata;
import ru.nord_core.common.utils.enums.EnumMetal;

public class ItemBlockNordLeaves2 extends ItemBlockMetadata implements IItemColor {
    private final BlockNordLeaves2 leaves;

    public ItemBlockNordLeaves2(Block block)
    {
        super(block);
        this.leaves = (BlockNordLeaves2)block;
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
        if (meta < EnumNordPlank2.getNames().length) {
            return super.getUnlocalizedName() + "." + EnumNordPlank2.byMetadata(stack.getMetadata()).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }
}
