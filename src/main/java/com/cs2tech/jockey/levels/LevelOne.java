package com.cs2tech.jockey.levels;

import com.cs2tech.framework.core.Direction;
import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.core.GameUtils;
import com.cs2tech.framework.levels.Background;
import com.cs2tech.framework.levels.BackgroundLayer;
import com.cs2tech.framework.levels.GameLevel;
import com.cs2tech.framework.sprites.Sprite;
import com.cs2tech.jockey.sprites.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public class LevelOne extends GameLevel {

    private long currentFrame = 0;
    private long nextLaunch;

    public LevelOne() {
        super(new Background(List.of(
                new BackgroundLayer(6, 6, 1, Direction.LEFT),
                new BackgroundLayer(7, 7, 2, Direction.LEFT),
                new BackgroundLayer(8, 8, 3, Direction.LEFT),
                new BackgroundLayer(9, 9, 4, Direction.LEFT)
        )));

    // TODO: create enemy slots to avoid launching two enemies in the same level.

        // playing music
        GameElements.get()
                    .getMusicPlayer()
                    .play(2);

        nextLaunch = nextIntervalToLaunch();
    }

    @Override
    public void draw(final GraphicsContext gc) {
        super.draw(gc);

        launchMoreEnemies();
    }

    private void launchMoreEnemies() {
        if (currentFrame > nextLaunch) {
            // launch a random sprite
            addSprite(0, getRandomEnemySprite());
            currentFrame = 0;
            nextLaunch = nextIntervalToLaunch();
        } else {
            currentFrame++;
        }
    }

    private long nextIntervalToLaunch() {
        return GameUtils.getRandomValueBetween(1, 4) * GameElements.get()
                                                                   .getScreenRefreshRate();
    }

    private Sprite getRandomEnemySprite() {
        final int rnd = GameUtils.getRandomValueBetween(1, 7);

        return switch (rnd) {
            case 1 -> new House();
            case 2 -> new Tree();
            case 3 -> new JetFighter();
            case 4 -> new Airplane();
            case 5 -> new Baloon();
            case 6 -> new Tank();
            default -> new Helicopter();
        };

    }
}
