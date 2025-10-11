package com.cs2tech.framework.levels;

import com.cs2tech.framework.graphics.Renderable;
import javafx.scene.canvas.GraphicsContext;

public class GameLevel implements Renderable {

    private final Background bg;

    public GameLevel(Background bg) {
        this.bg = bg;
    }

    @Override
    public void draw(final GraphicsContext gc) {
        bg.draw(gc);
    }
}
