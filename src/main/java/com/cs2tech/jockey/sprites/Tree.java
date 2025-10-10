package com.cs2tech.jockey.sprites;

import com.cs2tech.framework.sprites.EnemyShipSprite;

import java.awt.*;

public class Tree extends EnemyShipSprite {

    public Tree(int windowWidth, int windowHeight) {
        super(new Point(windowWidth - 1, windowHeight - 140));

        width = 20;
        height = 40;

        // the tree uses only one static image
        currentImageIndex = 19;

        movingLeft(true);
    }
}
