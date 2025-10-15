package com.cs2tech.jockey.main;

import com.cs2tech.framework.core.GameElements;

public abstract class JockeyUtils {
    public static double getGroundLevel(double spriteHeight) {
        return GameElements.get().getGameResolution().height * 0.841 - spriteHeight;
    }
}
