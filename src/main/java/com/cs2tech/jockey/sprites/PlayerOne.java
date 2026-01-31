package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.sprites.PlayerSprite;

import java.util.List;

public class PlayerOne extends PlayerSprite {

    public PlayerOne(final Position initialPosition) {
        super(
                initialPosition,
                new ProportionalSize(8.40, 3.65),
                new Speed(4, 2, 10),
                List.of(10, 11, 12, 13),
                new AnimationSettings(4)
        );
    }
}
