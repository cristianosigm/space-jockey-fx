package com.cs2tech.framework.sprites;

import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.physics.Collidable;
import com.cs2tech.framework.physics.TransitoryCharacter;
import javafx.scene.canvas.GraphicsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public abstract class EnemyShipSprite extends Sprite implements TransitoryCharacter, Collidable {
    private final Logger logger = LoggerFactory.getLogger(EnemyShipSprite.class);

    protected int width = 40;
    protected int height = 80;

    public EnemyShipSprite(final Point initialPosition) {
        super(initialPosition);

        // overriding speed settings
        // TODO: read from configuration
        speedMaximum = 8;
        speedIncrement = 2;
        speed = 3;

        logger.debug("Enemy created! Position: {}, {}", initialPosition.x, initialPosition.y);
    }

    @Override
    public void draw(final GraphicsContext gc) {
        checkOutOfScreen(gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
        handleActions();

        //        gc.setFill(Color.YELLOW);
        //        gc.fillRect(getPosition().getX(), getPosition().getY(), width, height);

        gc.drawImage( GameElements.get().getImage(currentImageIndex), getPosition().x, getPosition().y, width, height);
    }

    @Override
    public void checkOutOfScreen(double windowWidth, double windowHeight) {
        if ((getPosition().getX() - width < 0) || (getPosition().getX() > windowWidth) || (getPosition().getY() + height < 0) ||
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
