package ru.nord_core.common.utils.metal;

import javafx.util.Pair;

import java.util.HashMap;

public class BindMetall {
    private static BindMetall instance;

    private HashMap<EnumOre, Drops> drops = new HashMap<EnumOre, Drops>(8);
    private HashMap<EnumOre, Crystals> crystals = new HashMap<EnumOre, Crystals>(8);
    private HashMap<EnumOre, Boolean> infusibility = new HashMap<EnumOre, Boolean>(8);
    private HashMap<EnumOre, Nuggents> nuggets = new HashMap<EnumOre, Nuggents>(8);
    private HashMap<EnumOre, Crushes> cushes = new HashMap<EnumOre, Crushes>(8);
    private HashMap<EnumOre, Washing> washing = new HashMap<EnumOre, Washing>(8);

    public static BindMetall INSTANCE() {
        if (instance == null) {
            instance = new BindMetall();
        }
        return instance;
    }

    private BindMetall() {
        // промежуточные
        setDrops(); // дропы
        setCrystals(); // кристаллы
        setInfusibilits(); // тугоплавкость
        setNuggents(); // самородки
        setCrushes(); // измельчённые
        setWashings(); // промытые
        // завершающие
//        setCentrefuges(); // центрефуженные
//        setSepotatedes() // разделённые
//        setElictrolizes() // разделённые

    }

    static class Drops {
        public EnumOre drop;
        public int smeltQuantaty;

        public Drops(EnumOre drop, int qu) {
            this.drop = drop;
            this.smeltQuantaty = qu;
        }

        public Drops(EnumOre drop) {
            this.drop = drop;
            this.smeltQuantaty = 7;
        }
    }


    private void setDrops() {
        setDrop(EnumOre.GALENA, new Drops(EnumOre.GALENA));
        setDrop(EnumOre.ZINCITE, new Drops(EnumOre.ZINCITE));
        setDrop(EnumOre.BAUXITE, new Drops(EnumOre.BAUXITE));
        setDrop(EnumOre.UVAROVITE, new Drops(EnumOre.UVAROVITE));
        setDrop(EnumOre.CROCOITE, new Drops(EnumOre.CROCOITE));
        setDrop(EnumOre.COPPER, new Drops(EnumOre.COPPER));
        setDrop(EnumOre.OLIVINE, new Drops(EnumOre.OLIVINE));
        setDrop(EnumOre.MAGNETITE, new Drops(EnumOre.MAGNETITE));
        setDrop(EnumOre.LIMONITE, new Drops(EnumOre.LIMONITE));
        setDrop(EnumOre.ALMANDINE, new Drops(EnumOre.ALMANDINE));
        setDrop(EnumOre.PENTLANDITE, new Drops(EnumOre.PENTLANDITE));
        setDrop(EnumOre.MILLERITE, new Drops(EnumOre.MILLERITE));
        setDrop(EnumOre.CINNABAR, new Drops(EnumOre.CINNABAR));
    }

    public void setDrop(EnumOre ore, Drops drop) {
        drops.put(ore, drop);
    }

    public Drops getDrop(EnumOre ore) {
        return (drops.containsKey(ore)) ? drops.get(ore) : null;
    }

    private static class Crystals {
        public EnumOre crystal;

        public Crystals(EnumOre crystal) {
            this.crystal = crystal;
        }
    }

    private void setCrystals() {
//        setCrystal(EnumOre.CROCOITE, new Crystals(EnumOre.CROCOITE));
//        setCrystal(EnumOre.UVAROVITE, new Crystals(EnumOre.UVAROVITE));
//        setCrystal(EnumOre.ALMANDINE, new Crystals(EnumOre.ALMANDINE));
//        setCrystal(EnumOre.OLIVINE, new Crystals(EnumOre.OLIVINE));
    }

    public void setCrystal(EnumOre ore, Crystals drop) {
        crystals.put(ore, drop);
    }

    public Crystals getCrystal(EnumOre ore) {
        return (crystals.containsKey(ore)) ? crystals.get(ore) : null;
    }

    private void setInfusibilits() {
//        setInfusibilit(EnumOre.BAUXITE, true);
//        setInfusibilit(EnumOre.ALMANDINE, true);
//        setInfusibilit(EnumOre.OLIVINE, true);
    }

    public void setInfusibilit(EnumOre ore, boolean infus) {
        infusibility.put(ore, infus);
    }

    public boolean getInfusibility(EnumOre ore) {
        return infusibility.containsKey(ore) ? infusibility.get(ore) : false;
    }

    private static class Nuggents {
        public EnumMetal nuggets;
        public int quantaty;

        public Nuggents(EnumMetal nuggets, int quantaty) {
            this.nuggets = nuggets;
            this.quantaty = quantaty;
        }
    }

    private void setNuggents() {
//        setNuggents(EnumOre.SILVER, new Nuggents(EnumMetal.SILVER, 9));
//        setNuggents(EnumOre.TIN, new Nuggents(EnumMetal.TIN, 9));
//        setNuggents(EnumOre.MERCURY, new Nuggents(EnumMetal.MERCURY, 9));
    }

    public void setNuggents(EnumOre ore, Nuggents nugget) {
        nuggets.put(ore, nugget);
    }

    public Nuggents getNuggents(EnumOre ore) {
        return (nuggets.containsKey(ore)) ? nuggets.get(ore) : null;
    }

    private static class Crushes {
        public EnumOre cruse;
        public int quantaty;

        public Crushes(EnumOre cruse, int quantaty) {
            this.cruse = cruse;
            this.quantaty = quantaty;
        }
    }

    private void setCrushes() {
        for (EnumOre e : EnumOre.values()) {
            setCrushe(e, new Crushes(e, 1));
        }
    }

    public void setCrushe(EnumOre ore, Crushes cruse) {
        cushes.put(ore, cruse);
    }

    public Crushes getCrushe(EnumOre ore) {
        return (cushes.containsKey(ore)) ? cushes.get(ore) : null;
    }

    private static class Washing {
        public Pair<EnumOre, Integer> first;
        public Pair<EnumMetal, Float> second;

        public Washing(Pair<EnumOre, Integer> first, Pair<EnumMetal, Float> second) {
            this.first = first;
            this.second = second;
        }
    }

    private void setWashings() {
        setWashing(EnumOre.ALMANDINE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.ALMANDINE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.ALUMINUM, 0.25f))
        );
//        setWashing(EnumOre.GALENA, new Washing(
//                        new Pair<EnumOre, Integer>(EnumOre.GALENA, 1),
//                        new Pair<EnumMetal, Float>(EnumMetal.SULFUR, 0.05f))
//        );
        setWashing(EnumOre.ZINCITE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.ZINCITE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.LEAD, 0.25f))
        );
//        setWashing(EnumOre.BAUXITE, new Washing(
//                        new Pair<EnumOre, Integer>(EnumOre.BAUXITE, 1),
//                        new Pair<EnumMetal, Float>(EnumMetal.FLINT, 0.05f))
//        );
//        setWashing(EnumOre.UVAROVITE, new Washing(
//                        new Pair<EnumOre, Integer>(EnumOre.UVAROVITE, 1),
//                        new Pair<EnumMetal, Float>(EnumMetal.FLINT, 1f))
//        );
        setWashing(EnumOre.CROCOITE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.CROCOITE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.CHROM, 0.5f))
        );
        setWashing(EnumOre.COPPER, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.COPPER, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.GOLD, 0.05f))
        );
//        setWashing(EnumOre.OLIVINE, new Washing(
//                        new Pair<EnumOre, Integer>(EnumOre.OLIVINE, 1),
//                        new Pair<EnumMetal, Float>(EnumMetal.FLINT, 0.25f))
//        );
        setWashing(EnumOre.MAGNETITE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.MAGNETITE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.NICEL, 0.05f))
        );
        setWashing(EnumOre.LIMONITE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.LIMONITE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.IRON, 0.25f))
        );
        setWashing(EnumOre.PENTLANDITE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.PENTLANDITE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.IRON, 0.25f))
        );
        setWashing(EnumOre.MILLERITE, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.MILLERITE, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.IRON, 0.05f))
        );
        setWashing(EnumOre.CINNABAR, new Washing(
                        new Pair<EnumOre, Integer>(EnumOre.CINNABAR, 1),
                        new Pair<EnumMetal, Float>(EnumMetal.MERCURY, 0.25f))
        );
    }

    public void setWashing(EnumOre ore, Washing washe) {
        washing.put(ore, washe);
    }

    public Washing getWashing(EnumOre ore) {
        return (washing.containsKey(ore)) ? washing.get(ore) : null;
    }

}
