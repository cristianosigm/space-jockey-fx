package com.cs2tech.framework.core;

public class AnimationSettings {
    private int staticImageCycleInterval = 1;
    private int staticImageRequestCount = 0;

    public AnimationSettings(int staticImageCycleInterval) {
        this.staticImageCycleInterval = staticImageCycleInterval;
    }

    public boolean shouldCycleStaticImage() {
        // TODO: adjust this to consider frame rate.
        if (staticImageRequestCount < staticImageCycleInterval) {
            staticImageRequestCount++;
            return false;
        } else {
            staticImageRequestCount = 0;
            return true;
        }
    }
}
