package ru.nord_core.common.items.interfaces;

import net.minecraft.item.ItemStack;

public interface IEnergyTool extends IEnergyCharges {
    enum TOOLS {
        PICKAXE(1, "pickaxe"),
        SHOVEL(2, "shovel"),
        AXE(3, "axe"),
        ;

        int id;
        String name;

        TOOLS(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }

    /**
     * Энергии на использование
     * @param stack инструмент
     * @return значение
     */
    int powerOnUse(ItemStack stack);

    /**
     * Энергии на удар
     * @param stack инструмент
     * @return значение
     */
    int powerOnHit(ItemStack stack);

    /**
     * Тип инструмента
     * @return тип
     */
    TOOLS getHarvestType();
}
