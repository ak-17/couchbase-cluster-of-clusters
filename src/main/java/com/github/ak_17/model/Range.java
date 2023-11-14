package com.github.ak_17.model;

public class Range {
    private int min; // inclusive
    private int max; // inclusive

    public Range(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
