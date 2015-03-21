package ru.nord.common.lib.utils;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Dark32 Класс, хранилище топлива, его условий и его применений.
 */
public class Fuel {

    private static Fuel INSTANCE;

    public static final int DAMAGABLE = 1; // 1
    public static final int METADATA = 2;  // 2
    public static final int SIMPLE = 3;    // 3
    public static final int MATERIAL = 4;  // 4
    public static final int NOT_METAL = -1;      // 5

    private final Map<Item, Integer> itemFuel = new HashMap<Item, Integer>();
    private final Map<Material, Integer> itemFuelMaterial = new HashMap<Material, Integer>();
    private final List<FuelMetadata> itemFuelMetadata = new ArrayList<FuelMetadata>();
    private final List<FuelDamagable> itemFuelDamagable = new ArrayList<FuelDamagable>();

    /**
     * Получаем экземпляр Словаря, лениво.
     *
     * @return Fuel
     */
    public static Fuel getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Fuel();
        }
        return INSTANCE;
    }

    public static void init() {
        Fuel.getInstance().addFuel(Blocks.wooden_slab, 150);
        Fuel.getInstance().addFuel(Blocks.coal_block, 16000);
        Fuel.getInstance().addFuel(Material.wood, 300);
        Fuel.getInstance().addFuel(Items.stick, 100);
        Fuel.getInstance().addFuel(Items.coal, 1600);
        Fuel.getInstance().addFuel(Items.lava_bucket, 20000);
        Fuel.getInstance().addFuel(Blocks.sapling, 100);
        Fuel.getInstance().addFuel(Items.blaze_rod, 2400);
    }

    /**
     * Добавляем обычное топливо
     *
     * @param item   вещь
     * @param energy энергия
     * @return истина, если новое, ложь, если перезапись
     */
    public boolean addFuel(Item item, int energy) {
        if (itemFuel.containsKey(item)) {
            itemFuel.put(item, energy);
            return false;
        } else {
            itemFuel.put(item, energy);
            return true;
        }
    }

    /**
     * Добавляем материал топливо
     *
     * @param material вещь
     * @param energy   энергия
     * @return истина, если новое, ложь, если перезапись
     */
    public boolean addFuel(Material material, int energy) {
        if (itemFuelMaterial.containsKey(material)) {
            itemFuelMaterial.put(material, energy);
            return false;
        } else {
            itemFuelMaterial.put(material, energy);
            return true;
        }
    }

    /**
     * Добавляем обычное топливо
     *
     * @param block  блок
     * @param energy энергия
     * @return истина, если новое, ложь, если перезапись
     */
    public boolean addFuel(Block block, int energy) {
        return addFuel(Item.getItemFromBlock(block), energy);
    }

    /**
     * Добавляем топливо с метадатой
     *
     * @param itemStack - вещь с методатой
     * @param energy    - энергия
     * @return истина, если новое, ложь, если перезапись
     */
    public boolean addFuel(ItemStack itemStack, int energy) {
        for (int i = 0; i < itemFuelMetadata.size(); i++) {
            FuelMetadata fuel = itemFuelMetadata.get(i);
            boolean check = fuel.item == itemStack.getItem();
            check &= fuel.metadata == itemStack.getItemDamage();
            if (check) {
                itemFuelMetadata.set(i, fuel.setEnergy(energy));
                return false;
            } else {
                itemFuelMetadata.add(new FuelMetadata(itemStack.getItem(),
                        itemStack.getItemDamage(), energy));
                return true;
            }
        }
        return false;
    }

    /**
     * Добавляем повреждаемое топливо
     *
     * @param itemStack - повреждаемое топливо
     * @param energy    - энергия заряда
     * @param last      - сгорает до конца
     * @return истина, если новое, ложь, если перезапись
     */
    public boolean addFuel(ItemStack itemStack, int energy, boolean last) {
        for (int i = 0; i < itemFuelMetadata.size(); i++) {
            FuelDamagable fuel = itemFuelDamagable.get(i);
            boolean check = fuel.item == itemStack.getItem();
            if (check) {
                itemFuelDamagable.set(i, fuel.setEnergy(energy).setLast(last));
                return false;
            } else {
                itemFuelDamagable.add(new FuelDamagable(itemStack.getItem(),
                        energy, last));
                return true;
            }
        }
        return false;
    }

    /**
     * Получаем индекс топлива
     *
     * @param itemStack кандидат на топливо
     * @return индекс оплива
     */
    public int isFuelCode(ItemStack itemStack) {
        Item item = itemStack.getItem();
        int metadata = itemStack.getItemDamage();
        for (int i = 0; i < itemFuelDamagable.size(); i++) {
            FuelDamagable fuel = itemFuelDamagable.get(i);
            if (fuel.item == item) {
                return DAMAGABLE;
            }

        }
        for (int i = 0; i < itemFuelMetadata.size(); i++) {
            FuelMetadata fuel = itemFuelMetadata.get(i);
            if (fuel.item == item && fuel.metadata == metadata) {
                return METADATA;
            }
        }
        if (itemFuel.containsKey(item)) {
            return SIMPLE;
        }
        if (item instanceof ItemBlock
                && Block.getBlockFromItem(item) != Blocks.air) {
            Material material = Block.getBlockFromItem(item).getMaterial();
            if (itemFuelMaterial.containsKey(material)) {
                return MATERIAL;
            }
        }
        return NOT_METAL;
    }

    /**
     * Является ли предмет поливо
     *
     * @param itemStack - предмет
     * @return истина - является, ложь - не является
     */
    public boolean isFuel(ItemStack itemStack) {
        return isFuelCode(itemStack) != NOT_METAL;
    }

    private boolean getDamagableFuelFullBurn(ItemStack itemStack) {
        for (int i = 0; i < itemFuelMetadata.size(); i++) {
            FuelDamagable fuel = itemFuelDamagable.get(i);
            if (fuel.item == itemStack.getItem()) {
                return fuel.last;
            }
        }
        return false;
    }

    /**
     * Может ли топливогореть
     *
     * @param itemStack - топливо
     * @return стини - может, ложь - не может
     */
    public boolean hasBurn(ItemStack itemStack) {
        if (isFuel(itemStack)) {
            if (isFuelCode(itemStack) == DAMAGABLE) {
                if (getDamagableFuelFullBurn(itemStack)) {
                    return true;
                } else {
                    return itemStack.getItemDamage() < itemStack.getMaxDamage();
                }
            }
            return true;
        } else {
            return false;
        }
    }

    /**
     * Сжигаем топливо.
     *
     * @param itemStack топливо
     * @return ItemStack
     */
    public ItemStack burn(ItemStack itemStack) {
        if (itemStack == null)
            return null;
        if (isFuelCode(itemStack) == DAMAGABLE) {
            itemStack.setItemDamage(itemStack.getItemDamage() + 1);
        } else {
            itemStack.stackSize--;
            if (itemStack.stackSize == 0) {
                itemStack = null;
            }
        }
        return itemStack;
    }

    /**
     * Энергия от сгорания
     *
     * @param itemStack топливо
     * @return сколько энергии выделится
     */
    public int getEnergy(ItemStack itemStack) {
        Item item = itemStack.getItem();
        int metadata = itemStack.getItemDamage();
        for (int i = 0; i < itemFuelDamagable.size(); i++) {
            FuelDamagable fuel = itemFuelDamagable.get(i);
            if (fuel.item == item) {
                return fuel.energy;
            }

        }
        for (int i = 0; i < itemFuelMetadata.size(); i++) {
            FuelMetadata fuel = itemFuelMetadata.get(i);
            if (fuel.item == item && fuel.metadata == metadata) {
                return fuel.energy;
            }
        }
        if (itemFuel.containsKey(item)) {
            return itemFuel.get(item);
        }
        if (item instanceof ItemBlock
                && Block.getBlockFromItem(item) != Blocks.air) {
            Material material = Block.getBlockFromItem(item).getMaterial();
            if (itemFuelMaterial.containsKey(material)) {
                return itemFuelMaterial.get(material);
            }
        }
        return 0;
    }


    /**
     * @author andrew Медата топливо
     */
    private static class FuelMetadata {
        public final Item item;
        public final int metadata;
        public int energy;

        public FuelMetadata(Item item, int metadata, int energy) {
            this.item = item;
            this.metadata = metadata;
            this.energy = energy;
        }

        /**
         * @param energy установить энергию
         * @return себя же
         */
        public FuelMetadata setEnergy(int energy) {
            this.energy = energy;
            return this;
        }
    }

    /**
     * @author andrew Повреждаемое топливо
     */
    private static class FuelDamagable {
        public final Item item;
        public int energy;
        public boolean last;

        public FuelDamagable(Item item, int energy, boolean last) {
            this.item = item;
            this.energy = energy;
            this.last = last;
        }

        /**
         * @param energy энергия
         * @return себя же
         */
        public FuelDamagable setEnergy(int energy) {
            this.energy = energy;
            return this;
        }

        /**
         * @param last сгорает до конца
         * @return себя же
         */
        public FuelDamagable setLast(boolean last) {
            this.last = last;
            return this;
        }
    }

}
