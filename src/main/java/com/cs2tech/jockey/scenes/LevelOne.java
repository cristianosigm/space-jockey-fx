package com.cs2tech.jockey.scenes;

import com.cs2tech.framework.core.Direction;
import com.cs2tech.framework.levels.Background;
import com.cs2tech.framework.levels.BackgroundLayer;
import com.cs2tech.framework.levels.GameLevel;

import java.util.List;

public class LevelOne extends GameLevel {

    public LevelOne() {
        super(
            new Background(
                List.of(
                    new BackgroundLayer(6, 6, 1, Direction.LEFT),
                    new BackgroundLayer(7, 7, 2, Direction.LEFT),
                    new BackgroundLayer(8, 8, 4, Direction.LEFT),
                    new BackgroundLayer(9, 9, 5, Direction.LEFT)
                )
            )
        );
    }
}
