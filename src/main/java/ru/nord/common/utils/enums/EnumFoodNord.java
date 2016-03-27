package ru.nord.common.utils.enums;

import net.minecraft.init.MobEffects;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import ru.nord.NordItems;
import ru.nord_core.common.utils.enums.interfaces.IMetadataEnum;

public enum EnumFoodNord implements IMetadataEnum {

    a_lot_of_pancakes(0, "a_lot_of_pancakes", 8, 4),
    Pancake(1, "Pancake", 2, 1),
    Pancakeswithcottagecheese(2, "Pancakeswithcottagecheese", 4, 2),
    pancakeswithcurd(3, "pancakeswithcurd", 4, 2),
    pancakeswithfish(4, "pancakeswithfish", 6, 3),
    Pancakeswithjam(5, "Pancakeswithjam", 4, 2),
    Pancakeswithjam2(6, "Pancakeswithjam2", 5, 2),
    pancakeswithmeat(7, "pancakeswithmeat", 8, 4),
    pancakeswithonionsandeggs(8, "pancakeswithonionsandeggs", 5, 2),
    pancakeswithpotatoes(9, "pancakeswithpotatoes", 8, 4),
    pancakeswithsorrel(10, "pancakeswithsorrel", 4, 2),
    bun(11, "bun", 2, 2),
    bunjam(12, "bunjam", 2, 2),
    bunjam2(13, "bunjam2", 2, 2),
    cheesecake(14, "cheesecake", 2, 2),
    curd(15, "curd", 4, 4),
    dough(16, "dough", 1, 1, 10),
    apricotfruit(17, "apricotFruit", 1, 1, 10),
    berry1(18, "berry1", 1, 1, 10),
    berry2(19, "berry2", 1, 1, 10),
    coffeaFruit(20, "coffeaFruit", 1, 1, 10),
    coffeaGrain(21, "coffeaGrain", 1, 1, 10),
    lemonFruit(22, "lemonFruit", 1, 1, 10),
    oliveFruit(23, "oliveFruit", 1, 1, 10),
    orangeFruit(24, "orangeFruit", 1, 1, 10),
    peachFruit(25, "peachFruit", 1, 1, 10),
    pearFruit(26, "pearFruit", 1, 1, 10),
    punicaFruit(27, "punicaFruit", 1, 1, 10),
    sakuraFruit(28, "sakuraFruit", 1, 1, 10) {

        public PotionEffect[] getEffect() {
            return new PotionEffect[]{
                    new PotionEffect(MobEffects.poison, 10, 1),
                    new PotionEffect(MobEffects.blindness, 10, 1),
                    new PotionEffect(MobEffects.hunger, 10, 1),
            };
        }
    },
    dough2(29, "dough2", 1, 1, 20) {
        public PotionEffect[] getEffect() {
            return new PotionEffect[]{
                    new PotionEffect(MobEffects.poison, 40, 1),
                    new PotionEffect(MobEffects.blindness, 40, 1),
                    new PotionEffect(MobEffects.hunger, 40, 1),
            };
        }

        public ItemStack getContainer() {
            return new ItemStack(NordItems.itemGlassFood, 1, EnumGlassFood.PITCHER_EMPTY.getMetadata());
        }
    },
    fritters(30, "fritters", 2, 2),
    clabberfull(31, "clabberfull", 2, 6, 80) {
        public PotionEffect[] getEffect() {
            return new PotionEffect[]{
                    new PotionEffect(MobEffects.absorption, 100, 1),
            };
        }

        public ItemStack getContainer() {
            return new ItemStack(NordItems.itemGlassFood, 1, EnumGlassFood.CLABBEREMPTY.getMetadata());
        }
        public EnumAction getAnimation() {
            return EnumAction.DRINK;
        }
    },
    PitcherFull(32, "PitcherFull", 4, 8, 99) {
        public PotionEffect[] getEffect() {
            return new PotionEffect[]{
                    new PotionEffect(MobEffects.absorption, 200, 2),
            };
        }

        public ItemStack getContainer() {
            return new ItemStack(NordItems.itemGlassFood, 1, EnumGlassFood.PITCHER_EMPTY.getMetadata());
        }
        public EnumAction getAnimation() {
            return EnumAction.DRINK;
        }
    },
    onion(33, "onion", 2, 2),
    FishPie(34, "FishPie", 8, 4),
    JamPie(35, "JamPie", 8, 4),
    MeatPie(36, "MeatPie", 8, 4),
    Piewithonionsandeggs(37, "Piewithonionsandeggs", 8, 4),
    PotatoesPie(38, "PotatoesPie", 8, 4),
    SorrelPie(39, "SorrelPie", 8, 4),
    Sorrel(40, "Sorrel", 8, 4),;

    private final String name;
    private final int meta;
    private final int saturation;
    private final int heal;
    private final int effectPercent;

    EnumFoodNord(int _meta, String _name, int _saturation, int _heal) {
        this.name = _name;
        this.meta = _meta;
        this.saturation = _saturation;
        this.heal = _heal;
        this.effectPercent = 0;
    }

    EnumFoodNord(int _meta, String _name, int _saturation, int _heal, int _effectPercent) {
        this.name = _name;
        this.meta = _meta;
        this.saturation = _saturation;
        this.heal = _heal;
        this.effectPercent = _effectPercent;
    }

    public static EnumFoodNord byMetadata(int meta) {
        return meta < EnumFoodNord.values().length ? EnumFoodNord.values()[meta] : EnumFoodNord.values()[0];
    }

    public static String[] getNames() {
        String[] array = new String[EnumFoodNord.values().length];
        for (int i = 0; i < EnumFoodNord.values().length; i++) {
            array[i] = EnumFoodNord.values()[i].getName();
        }
        return array;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getMetadata() {
        return this.meta;
    }

    public int getSaturation() {
        return this.saturation;
    }

    public int getHeal() {
        return this.heal;
    }

    public int getEffectPercentl() {
        return this.effectPercent;
    }

    public PotionEffect[] getEffect() {
        return null;
    }

    public ItemStack getContainer() {
        return null;
    }
    public EnumAction getAnimation() {
        return EnumAction.EAT;
    }

}
