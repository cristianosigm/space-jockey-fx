package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class JetFighter extends EnemyShipSprite {
    public JetFighter() {
        super(getInitialPosition(),
                new ProportionalSize(11.72, 7.81), getSpeed(), List.of(20), new AnimationSettings(0));

        movingLeft(true);
    }

    private static Position getInitialPosition() {
        return new Position(
                GameElements.get()
                            .getGameResolution().width - 1, GameUtils.getRandomValueBetween(
                20, (int) (GameElements.get()
                                       .getGameResolution().height - 200)
        )
        );
    }

    private static Speed getSpeed() {
        var speed = GameUtils.getRandomValueBetween(2, 8);
        return new Speed(speed, 0, speed);
    }
}
