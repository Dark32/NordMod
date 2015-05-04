package ru.nord.common.lib.utils;

import net.minecraft.item.ItemStack;
import ru.nord.common.items.interfaces.IEnergyCharges;

public class ChargeHelper {
    private static IEnergyCharges getCharge(ItemStack itemStack) {
        return ((IEnergyCharges) itemStack.getItem());
    }

    private static int currectEnergy(ItemStack itemStack) {
        return getCharge(itemStack).currectEnergy(itemStack);
    }

    private static int maxEnergy(ItemStack itemStack) {
        return getCharge(itemStack).maxEnergy(itemStack);
    }

    private static int setEnergy(ItemStack itemStack, int energy) {
        getCharge(itemStack).setEnergy(itemStack, energy);
        return currectEnergy(itemStack);
    }

    private static int packetEnergy(ItemStack itemStack) {
        return getCharge(itemStack).packetEnergy(itemStack);
    }

    /**
     * Добавить энергию
     *
     * @param itemStack предмет
     * @param add       сколько доббавить
     * @return новое значение
     */
    public static int addEnergy(ItemStack itemStack, int add) {
        if (currectEnergy(itemStack) <= (maxEnergy(itemStack)) - add)
            setEnergy(itemStack, currectEnergy(itemStack) + add);
        return currectEnergy(itemStack);
    }

    /**
     * Отнять энергию
     *
     * @param itemStack предмет
     * @param sub       сколько отнять
     * @return новое значение
     */
    public static int subEnergy(ItemStack itemStack, int sub) {
        if (currectEnergy(itemStack) >= sub)
            setEnergy(itemStack, currectEnergy(itemStack) - sub);
        return currectEnergy(itemStack);
    }

    /**
     * Добавить пакет энергии
     *
     * @param itemStack предмет
     * @return новое значение
     */

    public static int addEnergy(ItemStack itemStack) {
        return addEnergy(itemStack, packetEnergy(itemStack));
    }


    /**
     * Отнять пакет энергии
     *
     * @param itemStack предмет
     * @return новое значение
     */
    public static int subEnergy(ItemStack itemStack) {
        return subEnergy(itemStack, packetEnergy(itemStack));
    }

    /**
     * Есть ли столько энергии
     *
     * @param itemStack предмет
     * @param energy    энерия
     * @return есть ли
     */
    public static boolean hasEnergy(ItemStack itemStack, int energy) {
        return currectEnergy(itemStack) > energy;
    }

    /**
     * Есть ли пакет энергии
     *
     * @param itemStack предмет
     * @return есть ли
     */
    public static boolean hasEnergy(ItemStack itemStack) {
        return hasEnergy(itemStack, packetEnergy(itemStack));
    }

    /**
     * Сколько энергии не хватает для полного заполнения
     *
     * @param itemStack предмет
     * @return значение
     */
    public static int getDeficient(ItemStack itemStack) {
        return maxEnergy(itemStack) - currectEnergy(itemStack);
    }

}
