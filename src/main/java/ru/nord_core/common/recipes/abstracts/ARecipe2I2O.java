package ru.nord_core.common.recipes.abstracts;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.interfaces.IAbstractRecipe;
import ru.nord_core.common.recipes.interfaces.IRecipe2I2O;

public class ARecipe2I2O implements IRecipe2I2O {

    private final boolean soft;
    private final ItemStack input;
    private final ItemStack secondInput;
    private final ItemStack result;
    private final ItemStack secondResult;
    private final int energy;
    private final float expirince;
    private final float percent;

    public ARecipe2I2O(ItemStack input, ItemStack secondInput,
                       ItemStack result,
                       ItemStack secondResult, int energy, float expirince, float exp, boolean soft) {
        this.input = input;
        this.secondInput = secondInput;
        this.result = result;
        this.secondResult = secondResult;
        this.energy = energy;
        this.expirince = expirince;
        this.percent = exp;
        this.soft = soft;

    }

    @Override
    public ItemStack getSecondInput() {
        return secondInput;
    }

    @Override

    public boolean getSoft() {
        return soft;
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

    @Override
    public TYPES getType() {
        return TYPES.TYPE_2I2O;
    }
}
