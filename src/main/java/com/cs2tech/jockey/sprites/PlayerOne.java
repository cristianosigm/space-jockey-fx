package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.AnimationSettings;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.core.Size;
import com.cs2tech.framework.core.Speed;
import com.cs2tech.framework.sprites.PlayerSprite;

import java.util.List;

public class PlayerOne extends PlayerSprite {

    public PlayerOne(final Position initialPosition) {
        super(
            initialPosition,
            new Size(86, 28),
            new Speed(4, 2, 10),
            List.of(10, 11, 12, 13),
            new AnimationSettings(4));
    }
}
