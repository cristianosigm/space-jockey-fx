package com.cs2tech.framework.core;

import java.util.Random;

public abstract class GameUtils {

    private static final Random randomizer = new Random();

    public static int getRandomValueBetween(int min, int max) {
        return randomizer.nextInt((max - min) + 1) + min;
    }
}
