package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.EnemyShipSprite;
import com.cs2tech.jockey.main.JockeyUtils;

import java.util.List;

public class Tank extends EnemyShipSprite {
    public Tank() {
        super(new Position(), new ProportionalSize(10.74, 6.51), new Speed(5, 0, 5), List.of(14, 15, 16), new AnimationSettings(0));

        setPosition(new Position(
                GameElements.get()
                            .getGameResolution().width - 1, JockeyUtils.getGroundLevel(size.height)
        ));

        movingLeft(true);
    }
}
