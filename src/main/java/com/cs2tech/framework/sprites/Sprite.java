package com.cs2tech.framework.sprites;

import com.cs2tech.framework.graphics.Renderable;

import java.awt.*;

public abstract class Sprite implements Renderable {

    private final Point initialPosition;
    private final Point position;

    protected int speedIncrement;
    protected int speedMaximum;
    protected int speed;

    protected boolean isMovingUp = false;
    protected boolean isMovingDown = false;
    protected boolean isMovingLeft = false;
    protected boolean isMovingRight = false;
    protected boolean isShooting = false;

    protected int currentImageIndex = 0;

    public Sprite(Point initialPosition) {
        this.initialPosition = initialPosition;
        this.position = initialPosition;

        // initial and maximum speed
        // TODO: read from configuration file
        speedMaximum = 10;
        speed = 2;
        speedIncrement = 2;
    }

    public Point getPosition() {
        return position;
    }

    // controller actions

    public void movingUp(boolean value) {
        isMovingUp = value;
    }

    public void movingDown(boolean value) {
        isMovingDown = value;
    }

    public void movingLeft(boolean value) {
        isMovingLeft = value;
    }

    public void movingRight(boolean value) {
        isMovingRight = value;
    }

    public void shooting(boolean value) {
        isShooting = value;
    }

    public void incrementSpeed() {
        speed += speedIncrement;
    }

    // TODO: implement remaining actions
    public void handleActions() {
        if (isMovingUp) {
            getPosition().y -= speed;
        }
        if (isMovingDown) {
            getPosition().y += speed;
        }
        if (isMovingLeft) {
            getPosition().x -= speed;
        }
        if (isMovingRight) {
            getPosition().x += speed;
        }
    }
}
