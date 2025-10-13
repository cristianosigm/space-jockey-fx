package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.AnimationSettings;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.core.Size;
import com.cs2tech.framework.core.Speed;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class House extends EnemyShipSprite {
    public House(double windowWidth, double windowHeight) {
        super(new Position(windowWidth - 1, windowHeight - 140), new Size(81, 59), new Speed(2, 0, 2), List.of(23), new AnimationSettings(0));

        movingLeft(true);
    }
}
