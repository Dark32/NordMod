package ru.nord_core.common.recipes.abstracts;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.interfaces.IRecipe2I2O;
import ru.nord_core.common.recipes.interfaces.IRecipes2I2O;

import java.util.ArrayList;
import java.util.List;

abstract public class ARecipes2I2O extends ARecipes implements IRecipes2I2O {
    protected static final List<ARecipe2I2O> recipes = new ArrayList<ARecipe2I2O>(64);

    @Override
    public List<ARecipe2I2O> getRecipes() {
        return recipes;
    }

    @Override
    public TYPES getType() {
        return TYPES.TYPE_2I2O;
    }

    @Override
    public IRecipe2I2O getPartRecipe(ItemStack item, int slot) {
        return getPartRecipe(getIndexPartRecipe(item, slot), slot);
    }

    @Override
    public IRecipe2I2O getPartRecipe(int index, int slot) {
        if (index > -1 && (slot == 1 || slot == 2))
            return recipes.get(index);
        else
            return null;
    }

    @Override
    public int getIndexPartRecipe(ItemStack item, int slot) {
        if (item == null || (slot != 1 && slot != 2)) {
            return -1;
        }

        boolean check = true;
        IRecipe2I2O recipe;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            ItemStack input;
            if (slot == 1) {
                input = recipe.getInput();
            } else {
                input = recipe.getSecondInput();
            }
            check &= (input.getItem() == item.getItem());
            check &= (input.getItemDamage() == item.getItemDamage());
            check &= (input.stackSize <= item.stackSize);
            if (check) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public IRecipe2I2O getRecipe(int index) {
        if (index > -1)
            return recipes.get(index);
        else
            return null;
    }

    @Override
    public IRecipe2I2O getRecipe(ItemStack item, ItemStack item2) {
        return getRecipe(getIndexRecipe(item, item2));
    }

    @Override
    public int getIndexRecipe(ItemStack item, ItemStack item2) {
        if (item == null || item2 == null) {
            return -1;
        }

        boolean check;
        IRecipe2I2O recipe;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            ItemStack input = recipe.getInput();
            ItemStack input2 = recipe.getSecondInput();
            boolean soft = recipe.getSoft();
            check = compare(item, input) && compare(item2, input2);
            if (soft) {
                check |= compare(item2, input) && compare(item, input2);
            }
            if (check) {
                return i;
            }
        }
        return -1;
    }


}
