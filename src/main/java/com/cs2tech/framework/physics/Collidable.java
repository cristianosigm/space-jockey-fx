package com.cs2tech.framework.physics;

import javafx.scene.shape.Rectangle;

public interface Collidable {

    Rectangle getCollisionBox();

    void die();

    void handleCollision(Collidable anotherCollidable);

    void takeHit(int hitPoints);

    void takeHit(int hitPoints, boolean playSoundEffect);
}
