package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.AnimationSettings;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.core.Size;
import com.cs2tech.framework.core.Speed;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class JetFighter extends EnemyShipSprite {
    public JetFighter(final Position initialPosition) {
        super(initialPosition,
                new Size(60, 20), new Speed(10, 0, 10), List.of(20), new AnimationSettings(0));

        movingLeft(true);
    }
}
