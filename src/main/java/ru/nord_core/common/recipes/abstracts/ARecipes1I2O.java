package ru.nord_core.common.recipes.abstracts;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import ru.nord_core.common.recipes.interfaces.IRecipe1I2O;
import ru.nord_core.common.recipes.interfaces.IRecipes1I2O;

import java.util.ArrayList;
import java.util.List;

abstract public class ARecipes1I2O extends ARecipes implements IRecipes1I2O {
    protected final List<ARecipe1I2O> recipes = new ArrayList<ARecipe1I2O>(64);

    @Override
    public List<ARecipe1I2O> getRecipes() {
        return recipes;
    }

    @Override
    public IRecipe1I2O getRecipe(ItemStack item) {
        return getRecipe(getIndexRecipe(item));
    }

    @Override
    public IRecipe1I2O getRecipe(int index) {
        if (index > -1)
            return recipes.get(index);
        else
            return null;
    }


    @Override
    public TYPES getType() {
        return TYPES.TYPE_1I2O;
    }

    @Override
    public int getIndexRecipe(ItemStack item) {
        if (item == null) {
            return -1;
        }
        boolean check;
        IRecipe1I2O recipe;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            check = compare(item, recipe.getInput());
            if (check) {
                return i;
            }
        }
        return -1;
    }
}
