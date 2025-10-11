package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.core.AnimationSettings;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.core.Size;
import com.cs2tech.framework.core.Speed;
import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.util.List;

public class Tree extends EnemyShipSprite {

    public Tree(int windowWidth, int windowHeight) {
        super(new Position(windowWidth - 1, windowHeight - 140), new Size(74, 100), new Speed(3, 0, 3), List.of(19), new AnimationSettings(0));

        movingLeft(true);
    }
}
