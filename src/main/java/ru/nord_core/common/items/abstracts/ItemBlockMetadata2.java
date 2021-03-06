package ru.nord_core.common.items.abstracts;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord_core.common.blocks.interfaces.IVariantMetadata;
import ru.nord_core.common.utils.enums.interfaces.IMetadata2Enum;

/**
 * Простой ItemBlock для большенства блоков с методатой
 * Требует от блока реализации {@link ru.nord_core.common.blocks.interfaces.IVariantMetadata}
 */
public class ItemBlockMetadata2 extends ItemBlock {


    private final Object[] enums;
    protected final Block block;

    public ItemBlockMetadata2(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.block = block;
        if (!(block instanceof IVariantMetadata)) {
            FMLLog.warning("[NORD CORE] " + this + " not implements IVariantMetadata, cause bug");
            this.enums = null;
        } else {
            this.enums = ((IVariantMetadata) block).getVariant().getAllowedValues().toArray();

        }

    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    public String getUnlocalizedName(ItemStack stack) {
        int meta = stack.getMetadata();
        if (meta < enums.length) {
            return super.getUnlocalizedName() + "." + ((IMetadata2Enum) ((IVariantMetadata) block).getEnumByMetadata(stack.getMetadata())).getName();
        } else {
            return super.getUnlocalizedName() + ".errorData";
        }
    }

}
