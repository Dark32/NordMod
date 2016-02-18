package ru.nord.common.utils;


import java.util.Arrays;
import java.util.Random;

public class WeightRandom<T> {
    private Random random;
    private final T[] values;
    private final Float[] weights;
    private float[] reWeights;
    private float totalWeights;

    public WeightRandom(Random random, Float[] weights, T[] values) {
        this.random = random;
        this.values = values;
        this.weights = weights;
        calculateTotalWeights();
    }

    public WeightRandom(Float[] weights, T[] values) {
        this.values = values;
        this.weights = weights;
        calculateTotalWeights();
    }

    public void setRandom(Random random) {
        this.random = random;
    }

    private void calculateTotalWeights() {
        this.reWeights = new float[this.weights.length];
        this.totalWeights = 0;
        for (int i = 0; i < weights.length; i++) {
            this.totalWeights += weights[i];
            this.reWeights[i] = this.totalWeights;
        }
    }

    public T getRandom() throws Exception {
        if (this.random == null) {
            throw new Exception("Random not set");
        }
        float rand = random.nextFloat() * this.totalWeights;
        int index =Arrays.binarySearch(this.reWeights,rand );
        if (index<0){
            index = -(index+1);
        }
        return values[index];
    }
}
