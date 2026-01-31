package com.cs2tech.framework.sprites;

import com.cs2tech.framework.core.*;
import javafx.scene.canvas.GraphicsContext;

import java.util.List;

public abstract class PlayerSprite extends Sprite {

    protected boolean isThrowingBomb = false;
    protected boolean isPressingSelect = false;
    protected boolean isPressingStart = false;

    public PlayerSprite(
            final Position initialPosition, final Size size, final Speed speed, final List<Integer> imageIndexes,
            final AnimationSettings animationSettings
    ) {
        super(initialPosition, size, speed, imageIndexes, animationSettings);
    }

    public PlayerSprite(
            final Position initialPosition, final ProportionalSize proportionalSize, final Speed speed, final List<Integer> imageIndexes,
            final AnimationSettings animationSettings
    ) {
        super(initialPosition, proportionalSize.getSize(), speed, imageIndexes, animationSettings);
    }

    public void throwingBomb(boolean value) {
        isThrowingBomb = value;
    }

    public void pressingSelect(boolean value) {
        isPressingSelect = value;
    }

    public void pressingStart(boolean value) {
        isPressingStart = value;
    }

    @Override
    public void draw(final GraphicsContext gc) {
        handleActions();

        gc.drawImage(
                GameElements.get()
                            .getImage(getImageIndex()), getPosition().x, getPosition().y, size.width, size.height
        );
    }

    private int getImageIndex() {
        if (animationSettings.shouldCycleStaticImage()) {
            return imageIndexes.next();
        } else {
            return imageIndexes.current();
        }
    }
}
