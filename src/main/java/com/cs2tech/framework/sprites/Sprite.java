package com.cs2tech.framework.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.graphics.Renderable;
import javafx.scene.image.ImageView;

import java.util.List;

public abstract class Sprite implements Renderable {
    public final Position position;
    public final Size size;
    public final Speed speed;

    protected final ImageIndexList imageIndexes;
    protected final AnimationSettings animationSettings;
    protected boolean isMovingUp = false;
    protected boolean isMovingDown = false;
    protected boolean isMovingLeft = false;
    protected boolean isMovingRight = false;
    protected boolean isShooting = false;

    protected List<ImageView> viewSet;

    public Sprite(final Position position, final Size size, final Speed speed, final List<Integer> imageIndexes, final AnimationSettings animationSettings) {
        this.position = position;
        this.size = size;
        this.speed = speed;
        this.imageIndexes = new ImageIndexList(imageIndexes);
        this.animationSettings = animationSettings;
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
        speed.increment();
    }

    // TODO: implement remaining actions
    public void handleActions() {
        if (isMovingUp) {
            position.y -= speed.getCurrent();
        }
        if (isMovingDown) {
            position.y += speed.getCurrent();
        }
        if (isMovingLeft) {
            position.x -= speed.getCurrent();
        }
        if (isMovingRight) {
            position.x += speed.getCurrent();
        }
    }
}
