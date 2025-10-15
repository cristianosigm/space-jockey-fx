package com.cs2tech.framework.sprites;

import com.cs2tech.framework.core.*;
import com.cs2tech.framework.physics.Collidable;
import com.cs2tech.framework.physics.TransitorySprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.Rectangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class EnemyShipSprite extends Sprite implements TransitorySprite, Collidable {
    private final Logger logger = LoggerFactory.getLogger(EnemyShipSprite.class);
    
    private final Rectangle collisionBox;

    public EnemyShipSprite(
            final Position position, final ProportionalSize proportionalSize, final Speed speed,
            final List<Integer> imageIndexes, final AnimationSettings animationSettings
    ) {
        super(position, proportionalSize.getSize(), speed, imageIndexes, animationSettings);
        collisionBox = new Rectangle(position.x, position.y, size.width, size.height);
        logger.debug("Enemy created (with proportional size)! Position: {}; size: {}.", position, size);
    }

    @Override
    public void draw(final GraphicsContext gc) {
        handleActions();

        gc.drawImage(GameElements.get().getImage(imageIndexes.next()), getPosition().x, getPosition().y, size.width, size.height);
    }

    @Override
    public void setPosition(final Position position) {
        super.setPosition(position);
        collisionBox.setX(position.x);
        collisionBox.setY(position.y);
    }

    @Override
    public void checkOutOfScreen() {
        // @formatter:off
        if ((getPosition().x - size.width < 0) ||
                (getPosition().x > GameElements.get().getGameResolution().width) ||
                (getPosition().y + size.height < 0) ||
                (getPosition().y > GameElements.get().getGameResolution().height)) {
            die();
        }
        // @formatter:on
    }

    @Override
    public void die() {
        logger.debug("Enemy ship died! Removing from renderables...");
        GameElements.get().getRenderables().remove(this);
    }

    @Override
    public Rectangle getCollisionBox() {
        return collisionBox;
    }

    @Override
    public void handleCollision(Collidable anotherCollidable) {

        final var boundsForThis = collisionBox.getBoundsInParent();
        final var boundsForAnother = anotherCollidable.getCollisionBox().getBoundsInParent();

        if (boundsForThis.intersects(boundsForAnother)) {
            logger.debug(
                    "Collision detected between {} and {}. Killing both!", this.getClass().getName(),
                    anotherCollidable.getClass().getName()
            );
            die();
            anotherCollidable.die();
        }
    }

    @Override
    public void takeHit(final int hitPoints) {

    }

    @Override
    public void takeHit(final int hitPoints, final boolean playSoundEffect) {

    }
}
