package ru.nord.common.lib.recipes.abstracts;

import net.minecraft.item.ItemStack;
import ru.nord.common.lib.recipes.Interfaces.IRecipe1I2O;

public class Recipe1I2O implements IRecipe1I2O {

    private final ItemStack input;
    private final ItemStack result;
    private final ItemStack secondResult;
    private final int energy;
    private final float expirince;
    private final float percent;

    public Recipe1I2O(ItemStack input, ItemStack result,
                      ItemStack secondResult, int energy, float expirince, float exp) {
        super();
        this.input = input;
        this.result = result;
        this.secondResult = secondResult;
        this.energy = energy;
        this.expirince = expirince;
        this.percent = exp;
    }

    @Override
    public ItemStack getInput() {
        return input;
    }

    @Override
    public ItemStack getResult() {
        return result;
    }

    @Override
    public ItemStack getSecondResult() {
        return secondResult;
    }

    @Override
    public int getEnergy() {
        return energy > 0 ? energy : 0;
    }

    @Override
    public float getExpirince() {
        return expirince > 0 ? expirince : 0;
    }

    @Override
    public float getPercent() {
        return percent < 100 ? percent : 100;
    }


}
