package ru.nord_core.common.utils;

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
    public static final int NOT_FUEL = -1;      // 5

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

    public static void postInit() {
        Fuel.getInstance().addFuel(Blocks.wooden_slab, 150);
        Fuel.getInstance().addFuel(Blocks.coal_block, 16000);
        Fuel.getInstance().addFuel(Material.wood, 300);
        Fuel.getInstance().addFuel(Items.stick, 100);
        Fuel.getInstance().addFuel(Items.coal, 1600);
        Fuel.getInstance().addFuel(Items.lava_bucket, 20000);
        Fuel.getInstance().addFuel(Blocks.sapling, 100);
        Fuel.getInstance().addFuel(Items.blaze_rod, 2400);
//        Fuel.getInstance().addFuel(NordItems.energyStorageItem, 16, 16, true);

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
            }
        }
        itemFuelMetadata.add(new FuelMetadata(itemStack.getItem(),
                itemStack.getItemDamage(), energy));
        return true;

    }

    /**
     * Добавляем повреждаемое топливо
     *
     * @param item   - повреждаемое топливо
     * @param energy - энергия заряда
     * @param last   - сгорает до конца
     * @return истина, если новое, ложь, если перезапись
     */
    public boolean addFuel(Item item, int energy, int packet, boolean last) {
        for (int i = 0; i < itemFuelDamagable.size(); i++) {
            FuelDamagable fuel = itemFuelDamagable.get(i);
            boolean check = fuel.item == item;
            if (check) {
                itemFuelDamagable.set(i, fuel.setEnergy(energy).setLast(last).setPacket(packet));
                return false;
            }
        }
        itemFuelDamagable.add(new FuelDamagable(item, energy, packet, last));
        return true;

    }

    /**
     * Получаем индекс топлива
     *
     * @param itemStack кандидат на топливо
     * @return индекс оплива
     */
    public int isFuelCode(ItemStack itemStack) {
        if (itemStack == null) return NOT_FUEL;
        Item item = itemStack.getItem();
        int metadata = itemStack.getItemDamage();
        if (getFuelDamagable(itemStack) != null) return DAMAGABLE;
        for (FuelMetadata fuel : itemFuelMetadata) {
            if (fuel.item == item && fuel.metadata == metadata) {
                return METADATA;
            }
        }
        if (itemFuel.containsKey(item)) {
            return SIMPLE;
        }
        if (item instanceof ItemBlock
                && Block.getBlockFromItem(item) != Blocks.air) {
            Block block = Block.getBlockFromItem(item);
            Material material = block.getMaterial(block.getStateFromMeta(itemStack.getMetadata()));
            if (itemFuelMaterial.containsKey(material)) {
                return MATERIAL;
            }
        }
        return NOT_FUEL;
    }

    /**
     * Является ли предмет поливо
     *
     * @param itemStack - предмет
     * @return истина - является, ложь - не является
     */
    public boolean isFuel(ItemStack itemStack) {
        return isFuelCode(itemStack) != NOT_FUEL;
    }

    private boolean getDamagableFuelFullBurn(ItemStack itemStack) {
        FuelDamagable fuel = getFuelDamagable(itemStack);
        return fuel != null && fuel.last;
    }

    /**
     * Получаем повреждаемое топливо
     *
     * @param itemStack предмет
     * @return топливо
     */
    private FuelDamagable getFuelDamagable(ItemStack itemStack) {
        if (itemStack == null) return null;
        for (FuelDamagable fuel : itemFuelDamagable) {
            if (fuel.item == itemStack.getItem()) {
                return fuel;
            }
        }
        return null;
    }

    /**
     * Может ли топливо гореть
     *
     * @param itemStack - топливо
     * @return истини - может, ложь - не может
     */
    public boolean hasBurn(ItemStack itemStack) {
        if (isFuel(itemStack)) {
            if (isFuelCode(itemStack) == DAMAGABLE) {
                if (getDamagableFuelFullBurn(itemStack)) {
                    return true;
                } else {
                    return itemStack.getItemDamage() > 0;
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
        if (isFuelCode(itemStack) == DAMAGABLE && hasBurn(itemStack)) {
            FuelDamagable fuel = getFuelDamagable(itemStack);
            burn(itemStack, fuel);
            //itemStack.setItemDamage(itemStack.getItemDamage() + fuel.packet);
        } else {
            itemStack.stackSize--;
            if (itemStack.stackSize == 0) {
                itemStack = null;
            }
        }
        return itemStack;
    }

    /**
     * Сжигаем повреждаемое топливо
     */
    public ItemStack burn(ItemStack itemStack, FuelDamagable fuel) {
        if (itemStack.getItemDamage() > fuel.packet) {
            itemStack.setItemDamage(itemStack.getItemDamage() + fuel.packet);
        } else {
            itemStack.setItemDamage(itemStack.getMaxDamage());
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
        if (itemStack == null) return 0;
        Item item = itemStack.getItem();
        int metadata = itemStack.getItemDamage();

        FuelDamagable fuel_d = getFuelDamagable(itemStack);
        if (fuel_d != null) return fuel_d.energy;

        for (FuelMetadata fuel : itemFuelMetadata) {
            if (fuel.item == item && fuel.metadata == metadata) {
                return fuel.energy;
            }
        }
        if (itemFuel.containsKey(item)) {
            return itemFuel.get(item);
        }

        if (item instanceof ItemBlock
                && Block.getBlockFromItem(item) != Blocks.air) {
            Block block = Block.getBlockFromItem(item);
            Material material = block.getMaterial(block.getStateFromMeta(itemStack.getMetadata()));
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
        public int packet;
        public boolean last;

        public FuelDamagable(Item item, int energy, int packet, boolean last) {
            this.item = item;
            this.energy = energy;
            this.packet = packet;
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

        /**
         * @param packet сколько урона наносить предмету за тик сгорания
         * @return себя же
         */
        public FuelDamagable setPacket(int packet) {
            this.packet = packet;
            return this;
        }
    }

}
