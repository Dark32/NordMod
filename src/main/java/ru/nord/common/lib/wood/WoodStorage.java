package ru.nord.common.lib.wood;

import java.util.ArrayList;
import java.util.List;

public class WoodStorage {
    private final String name;
    private List<String> woods;
    private List<Float> weights;

    private String[] asValue;
    private Float[] asWeights;

    public WoodStorage(String name) {
        this.name = name;
        this.woods = new ArrayList<>(2);
        this.weights = new ArrayList<>(2);
    }

    public WoodStorage add(String wood, Float weight) {
        this.woods.add(wood);
        this.weights.add(weight);
        return this;
    }

    private WoodStorage add(String wood, int weight) {
        add(wood, (float) weight);
        return this;
    }

    public WoodStorage addDefault() {
        add("small", 100);
        add("some", 100);
        add("big", 100);
        return this;
    }


    public String getName() {
        return name;
    }

    public String[] getValues() {
        if (this.asValue == null) {
            this.asValue = new String[this.woods.size()];
            for (int i = 0; i < this.woods.size(); i++) {
                this.asValue[i] = this.name + "." + this.woods.get(i) + ".schematic";
            }

        }
        return this.asValue;
    }

    public Float[] getWeights() {
        if (this.asWeights == null) {
            this.asWeights = new Float[this.weights.size()];
            for (int i = 0; i < this.weights.size(); i++) {
                this.asWeights[i] = this.weights.get(i);
            }
        }
        return this.asWeights;
    }
}
