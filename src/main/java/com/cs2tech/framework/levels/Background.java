package com.cs2tech.framework.levels;

import com.cs2tech.framework.graphics.Renderable;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class Background implements Renderable {

    private final List<BackgroundLayer> layers;

    protected double currentFrame;
    protected boolean complete;

    public Background(List<BackgroundLayer> backgroundLayers) {
        layers = backgroundLayers;
    }

    @Override
    public void draw(final GraphicsContext gc) {
        layers.forEach(layer -> layer.draw(gc));
    }
}
