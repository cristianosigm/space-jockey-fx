package com.cs2tech.framework.sprites;

import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.physics.Collidable;
import com.cs2tech.framework.physics.TransitoryCharacter;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class EnemyShipSprite extends Sprite implements TransitoryCharacter, Collidable {
    private final Logger logger = LoggerFactory.getLogger(EnemyShipSprite.class);

    private final int size = 40;

    public EnemyShipSprite(final Point initialPosition) {
        super(initialPosition);

        // overriding speed settings
        // TODO: read from configuration
        speedMaximum = 8;
        speedIncrement = 2;
        speed = 3;

        // default: move down
        isMovingDown = true;

        logger.debug("Enemy created! Position: {}, {}", initialPosition.x, initialPosition.y);
    }

    @Override
    public void draw(final GraphicsContext gc) {
        checkOutOfScreen(gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        handleActions();

        gc.setFill(Color.YELLOW);
        gc.fillRect(getPosition().getX(), getPosition().getY(), size, size);
    }

    @Override
    public void checkOutOfScreen(double windowWidth, double windowHeight) {
        if ((getPosition().getX() - size < 0) || (getPosition().getX() > windowWidth) || (getPosition().getY() + size < 0) ||
            (getPosition().getY() > windowHeight)) {
            if (!die()) {
                logger.error("Failed to kill an enemy that went out of the screen.");
            }
        }
    }

    @Override
    public boolean die() {
        logger.debug("Enemy ship died! Removing from renderables...");
        return GameElements.get().getRenderables().remove(this);
    }

    @Override
    public void takeHit(final int hitPoints) {

    }

    @Override
    public void takeHit(final int hitPoints, final boolean playSoundEffect) {

    }
}
