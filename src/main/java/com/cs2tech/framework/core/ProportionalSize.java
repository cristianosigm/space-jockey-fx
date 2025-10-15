package com.cs2tech.framework.core;

public class ProportionalSize {

    private final Size size;

    public ProportionalSize(double percentWidth, double percentHeight) {

        this.size = new Size(
                GameElements.get().getGameResolution().width * percentWidth / 100,
                GameElements.get().getGameResolution().height * percentHeight / 100
        );
    }

    public Size getSize() {
        return size;
    }
}
