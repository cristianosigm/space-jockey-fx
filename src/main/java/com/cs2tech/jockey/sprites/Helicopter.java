package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class Helicopter extends EnemyShipSprite {
    public Helicopter() {
        super(
                new Position(
                        GameElements.get().getGameResolution().width - 1,
                        GameElements.get().getGameResolution().height - 140
                ),
                new ProportionalSize(12.7, 6.51), new Speed(2, 0, 2),
                List.of(17, 18),
                new AnimationSettings(0)
        );

        movingLeft(true);
    }
}
