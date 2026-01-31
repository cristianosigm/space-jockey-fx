package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.EnemyShipSprite;
import com.cs2tech.jockey.main.JockeyUtils;

import java.util.List;

public class Tree extends EnemyShipSprite {

    public Tree() {
        super(new Position(), new ProportionalSize(7.23, 13.02), new Speed(4, 0, 4), List.of(19), new AnimationSettings(0));

        setPosition(new Position(
                GameElements.get()
                            .getGameResolution().width - 1, JockeyUtils.getGroundLevel(size.height)
        ));

        movingLeft(true);
    }
}
