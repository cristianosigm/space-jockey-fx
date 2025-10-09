package com.cs2tech.framework.physics;

public interface Collidable {

    boolean die();

    void takeHit(int hitPoints);

    void takeHit(int hitPoints, boolean playSoundEffect);
}
