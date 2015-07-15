package ru.nord.common.lib.recipes;

import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BrickFurnaceRecipes {
    private static final BrickFurnaceRecipes smelterBase = new BrickFurnaceRecipes();
    private final List<ItemStack> firstSlot = new ArrayList();
    private final List<ItemStack> output = new ArrayList();
    private final List<ItemStack> second_output = new ArrayList();
    private final List<Float> second_output_percent = new ArrayList();
    private final List<Float> experience_output = new ArrayList();

    public static BrickFurnaceRecipes crushing() {
        return smelterBase;
    }

    private BrickFurnaceRecipes() {
    }

    public void smeltItemStack(ItemStack input1, ItemStack output1,
                               ItemStack output2, float percent, float exp) {
        int index = getIndexResult(input1);
        if (index == -1) {
            firstSlot.add(input1);
            output.add(output1);
            second_output.add(output2);
            second_output_percent.add(percent);
            experience_output.add(exp);
        } else {
            System.out.println("Brick Furnace recipe #" + index + " owerwrite");
            output.set(index, output1);
            second_output.set(index, output2);
            second_output_percent.set(index, percent);
            experience_output.set(index, exp);
        }
    }

    public int getIndexResult(ItemStack iStack1) {
        if (iStack1 == null) {
            return -1;
        }

        for (int i = 0; i < firstSlot.size(); i++) {
            boolean match = compareSizeble(iStack1, firstSlot.get(i));
            if (match)
                return i;

        }
        return -1;
    }

    private boolean compare(ItemStack item1, ItemStack item2) {
        if (item1 == null || item2 == null) {
            return false;
        }
        boolean item = item1.getItem() == item2.getItem();
        boolean meta = item1.getItemDamage() == item2.getItemDamage();
        return item && meta;
    }

    private boolean compareSizeble(ItemStack currect, ItemStack need) {
        if (currect == null || need == null) {
            return false;
        }
        boolean size = currect.stackSize >= need.stackSize;
        boolean item = currect.getItem() == need.getItem();
        boolean meta = currect.getItemDamage() == need.getItemDamage();
        return size && item && meta;
    }

    public ItemStack getSmeltResult(ItemStack iStack1) {
        return getSmeltResult(getIndexResult(iStack1));
    }

    public ItemStack getSmeltResult(int index) {
        return index != -1 ? output.get(index) : null;
    }

    public ItemStack getSmeltResultSecond(ItemStack iStack1) {
        return getSmeltResultSecond(getIndexResult(iStack1));
    }

    public ItemStack getSmeltResultSecond(int index) {
        return index != -1 ? second_output.get(index) : null;
    }

    public float getSmeltResultSecondPercent(ItemStack iStack1) {
        return getSmeltResultSecondPercent(getIndexResult(iStack1));
    }

    public float getSmeltResultSecondPercent(int index) {
        return index != -1 ? second_output_percent.get(index) : 0;
    }

    public boolean canSmelt(ItemStack input, ItemStack output) {
        return output.getItem() == input.getItem()
                && (output.getItemDamage() == 32767 || output.getItemDamage() == input
                .getItemDamage());
    }

    public float getSmeltExp(ItemStack iStack1) {
        return getSmeltExp(getIndexResult(iStack1));
    }

    public float getSmeltExp(int index) {
        return index != -1 ? this.experience_output.get(index) : 0;
    }

    public int getQuantaty(int index, int input) {
        if (index == -1) {
            return 0;
        }
        if (input == 1) {
            return firstSlot.get(index).stackSize;
        }
        return 0;

    }
}