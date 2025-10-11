package com.cs2tech.framework.core;

public class Speed {
    private final int increment;
    private final int initial;
    private final int maximum;
    private int current;

    public Speed(int initial, int increment, int maximum) {
        this.initial = initial;
        this.current = initial;
        this.increment = increment;
        this.maximum = maximum;
    }

    public void increment() {
        if (current < maximum) {
            current += increment;
        }
    }

    public void reset() {
        this.current = initial;
    }

    public int getCurrent() {
        return current;
    }
}
