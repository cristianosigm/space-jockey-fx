package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.sprites.PlayerSprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

public class PlayerOne extends PlayerSprite {

    public PlayerOne(final Point initialPosition) {
        super(initialPosition);
    }

    @Override
    public void draw(final GraphicsContext gc) {
        handleActions();

        gc.setFill(Color.ORANGERED);
        gc.fillRect(getPosition().getX(), getPosition().getY(), 20, 20);
    }
}
