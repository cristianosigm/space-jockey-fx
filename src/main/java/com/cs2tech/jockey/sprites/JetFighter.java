package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.awt.*;

public class JetFighter extends EnemyShipSprite {
    public JetFighter(final Point initialPosition) {
        super(initialPosition);

        width = 60;
        height = 20;

        // the jet uses only one static image
        currentImageIndex = 20;

        movingLeft(true);
    }
}
