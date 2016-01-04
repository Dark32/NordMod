package ru.nord_core.common.recipes.abstracts;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.oredict.OreDictionary;
import ru.nord_core.common.recipes.interfaces.IAbstractRecipe;
import ru.nord_core.common.recipes.interfaces.IRecipe1I2OOD;
import ru.nord_core.common.recipes.interfaces.IRecipes1I2OOD;

import java.util.ArrayList;
import java.util.List;

abstract public class ARecipes1I2OOD extends ARecipes implements IRecipes1I2OOD {
    private final List<ARecipe1I2OOD> recipes = new ArrayList<ARecipe1I2OOD>(64);

    @Override
    public List<ARecipe1I2OOD> getRecipes() {
        return recipes;
    }

    @Override
    public IRecipe1I2OOD getRecipe(ItemStack item) {
        return getRecipe(getIndexRecipe(item));
    }

    @Override
    public IRecipe1I2OOD getRecipe(int index) {
        if (index != NOT_FOUND)
            return recipes.get(index);
        else
            return null;
    }

    @Override
    public TYPES getType() {
        return TYPES.TYPE_1I2OOD;
    }

    @Override
    public int getIndexRecipe(ItemStack item) {
        if (item == null) {
            return NOT_FOUND;
        }
        boolean check;
        IRecipe1I2OOD recipe;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            if (recipe.isSoftOre()){
                check = hardCompare(item,recipe.getInput());
            }else{
                check = softCompare(item,recipe.getOreDictInput(),recipe.getOreDictInputCount());
            }
            if (check) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private int checkIndexRecipeForAdd(ItemStack item) {
        if (item == null) {
            return NOT_FOUND;
        }
        boolean check;
        IRecipe1I2OOD recipe;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            check = hardCompare(item,recipe.getInput());
            if (check) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    private int checkIndexRecipeForAdd(String item, int count) {
        if (item == null) {
            return NOT_FOUND;
        }
        boolean check;
        IRecipe1I2OOD recipe;
        for (int i = 0; i < recipes.size(); i++) {
            recipe = recipes.get(i);
            check =item.equals(recipe.getOreDictInput()) && recipe.getOreDictInputCount()<=count;
            if (check) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    @Override
    public void add(IAbstractRecipe recipe) {
        IRecipe1I2OOD recipe1I2OOD = (IRecipe1I2OOD) recipe;
        int indx;
        if (recipe1I2OOD.isSoftOre()){
            indx = this.checkIndexRecipeForAdd(recipe1I2OOD.getOreDictInput(),recipe1I2OOD.getOreDictInputCount());
        }else{
            indx = this.checkIndexRecipeForAdd(recipe1I2OOD.getInput());
        }
        if (indx == NOT_FOUND) {
            this.getRecipes().add((ARecipe1I2OOD) recipe);
        } else {
            FMLLog.info("[NORD] Recipe override " + indx);
            this.getRecipes().add(indx, (ARecipe1I2OOD) recipe);
        }
    }

    private boolean hardCompare(ItemStack input, ItemStack recepie) {
        if (input == null || recepie == null) { // если чего-то не хватает, что в принципе невозможно, то вещи не равны
            return false;
        }
        boolean check_item = input.getItem() == recepie.getItem() // совпадают ли вещи
                && (input.getItemDamage() == recepie.getItemDamage() // повреждения совпадают
                || recepie.getItemDamage() == OreDictionary.WILDCARD_VALUE); // или у входа стоит флаг "любое повреждение"
        boolean chesk_size = input.stackSize >= recepie.stackSize; // достаточно ли предметов
        return check_item && chesk_size;
    }

    private boolean softCompare(ItemStack input, String recepie, int count) {
        if (recepie.isEmpty() || input == null || count<1) { // если чего-то не хватает, что в принципе невозможно, то вещи не равны
            return false;
        }
        boolean check_item = checkInputOreDictoary(recepie,input); // входит ли предмет в словарь
        boolean chesk_size = count >= input.stackSize; // достаточно ли предметов
        return check_item && chesk_size;
    }

    public final boolean checkInputOreDictoary(String recepies, ItemStack input) {
        int iid = OreDictionary.getOreID(recepies);
        int[] recepies_ids = OreDictionary.getOreIDs(input);
            for (int rid : recepies_ids) {
                if (iid == rid) {
                    return true;
                }
            }
        return false;
    }
}
