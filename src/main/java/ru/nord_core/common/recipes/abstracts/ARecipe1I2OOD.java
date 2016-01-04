package ru.nord_core.common.recipes.abstracts;

import net.minecraft.item.ItemStack;
import ru.nord_core.common.recipes.interfaces.IRecipe1I2O;
import ru.nord_core.common.recipes.interfaces.IRecipe1I2OOD;

public class ARecipe1I2OOD implements IRecipe1I2OOD {

    private final ItemStack input;
    private final String oreDictInput;
    private final int oreDictInputCount;
    private final ItemStack result;
    private final ItemStack secondResult;
    private final int energy;
    private final float expirince;
    private final float percent;
    private final boolean softOre;

    public ARecipe1I2OOD(ItemStack input, ItemStack result,
                         ItemStack secondResult, int energy, float percent, float expirince) {
        this.input = input;
        this.oreDictInputCount = -1;
        this.result = result;
        this.secondResult = secondResult;
        this.energy = energy;
        this.percent = percent;
        this.expirince = expirince;
        this.oreDictInput ="";
        this.softOre = false;
    }

    public ARecipe1I2OOD(String oreDictInput, int oreDictInputCount, ItemStack result,
                         ItemStack secondResult, int energy, float percent, float expirince) {
        this.input = null;
        this.oreDictInput =oreDictInput;
        this.oreDictInputCount = oreDictInputCount;
        this.result = result;
        this.secondResult = secondResult;
        this.energy = energy;
        this.percent = percent;
        this.expirince = expirince;
        this.softOre = true;
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
        return TYPES.TYPE_1I2OOD;
    }

    @Override
    public String getOreDictInput() {
        return oreDictInput;
    }

    @Override
    public boolean isSoftOre() {
        return softOre;
    }

    @Override
    public int getOreDictInputCount() {
        return oreDictInputCount;
    }
}
