package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class Airplane extends EnemyShipSprite {
    // @formatter:off
    public Airplane() {
        super(new Position(
                        GameElements.get().getGameResolution().width - 1,
                        GameElements.get().getGameResolution().height - 140
                ),
                new ProportionalSize(12.5, 8.33),
                new Speed(3, 0, 3),
                List.of(21, 22),
                new AnimationSettings(0));

        movingLeft(true);
    }
    // @formatter:on
}