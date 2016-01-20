package ru.nord.common.recipes.minetweaker.helpers;

import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

/**
 * Created by nikit on 10.09.2015.
 */
public class ItemStackHelper {
    public static ItemStack toStack(IItemStack iStack) {
        if (iStack == null) {
            return null;
        } else {
            Object internal = iStack.getInternal();
            if (!(internal instanceof ItemStack)) {
                MineTweakerAPI.logError("Not a valid item stack: " + iStack);
            }

            return (ItemStack) internal;
        }
    }

    public static IItemStack[] toStacks(IIngredient[] iIngredient) {
        ArrayList<IItemStack> stacks = new ArrayList<IItemStack>();

        for (IIngredient ing : iIngredient) {
            for (IItemStack stack : ing.getItems()) {
                stacks.add(stack);
            }
        }

        return stacks.toArray(new IItemStack[stacks.size()]);
    }
}
