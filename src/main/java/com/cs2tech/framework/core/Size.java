package com.cs2tech.framework.core;

public class Size {
    public double width;
    public double height;

    public Size() {
        width = 0;
        height = 0;
    }

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return "width = " + width + ", height = " + height;
    }
}
