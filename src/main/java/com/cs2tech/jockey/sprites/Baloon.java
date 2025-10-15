package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class Baloon extends EnemyShipSprite {

    public Baloon() {
        super(
                new Position(
                        GameElements.get()
                                    .getGameResolution().width - 1, GameElements.get()
                                                                                .getGameResolution().height - 140
                ),
                new ProportionalSize(5.86, 13.02), new Speed(3, 0, 3),
                List.of(24),
                new AnimationSettings(0)
        );

        movingLeft(true);
    }
}