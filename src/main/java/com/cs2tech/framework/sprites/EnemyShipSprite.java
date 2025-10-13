package com.cs2tech.framework.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.physics.Collidable;
import com.cs2tech.framework.physics.TransitoryCharacter;
import javafx.scene.canvas.GraphicsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class EnemyShipSprite extends Sprite implements TransitoryCharacter, Collidable {
    private final Logger logger = LoggerFactory.getLogger(EnemyShipSprite.class);

    public EnemyShipSprite(final Position position, final Size size, final Speed speed, final List<Integer> imageIndexes, final AnimationSettings animationSettings) {
        super(position, size, speed, imageIndexes, animationSettings);
        logger.debug("Enemy created! Position: {}; size: {}.", position, size);
    }

    @Override
    public void draw(final GraphicsContext gc) {
        handleActions();

        gc.drawImage(GameElements.get().getImage(imageIndexes.next()), position.x, position.y, size.width, size.height);
    }

    @Override
    public void checkOutOfScreen() {
        if ((position.x - size.width < 0) || (position.x > GameElements.get().getGameResolution().width) || (position.y + size.height < 0) || (position.y > GameElements.get().getGameResolution().height)) {
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
