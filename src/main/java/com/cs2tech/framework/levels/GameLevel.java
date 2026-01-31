package com.cs2tech.framework.levels;

import com.cs2tech.framework.graphics.Renderable;
import com.cs2tech.framework.sprites.Sprite;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class GameLevel implements Renderable {

    private final Background bg;
    private final List<SpriteLauncher> spritesToLaunch = new ArrayList<>();
    private long frameCounter;

    public GameLevel(Background bg) {
        this.bg = bg;
    }

    public void addSprite(long framesToWait, Sprite sprite) {
        spritesToLaunch.add(new SpriteLauncher(framesToWait, sprite));
    }

    @Override
    public void draw(final GraphicsContext gc) {
        // launching each enemy as programmed
        spritesToLaunch.forEach(entry -> entry.launch(frameCounter));
        spritesToLaunch.removeIf(SpriteLauncher::launched);
        // draw the current background
        bg.draw(gc);
        // next frame
        frameCounter++;
    }
}
