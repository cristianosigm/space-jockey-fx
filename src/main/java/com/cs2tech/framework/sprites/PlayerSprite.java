package com.cs2tech.framework.sprites;

import java.awt.*;

public abstract class PlayerSprite extends Sprite {

    protected boolean isThrowingBomb = false;
    protected boolean isPressingSelect = false;
    protected boolean isPressingStart = false;

    public PlayerSprite(final Point initialPosition) {
        super(initialPosition);
    }

    public void throwingBomb(boolean value) {
        isThrowingBomb = value;
    }

    public void pressingSelect(boolean value) {
        isPressingSelect = value;
    }

    public void pressingStart(boolean value) {
        isPressingStart = value;
    }
}
