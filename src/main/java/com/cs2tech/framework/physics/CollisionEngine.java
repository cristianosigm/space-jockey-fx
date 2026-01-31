package com.cs2tech.framework.physics;

import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.graphics.Renderable;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class CollisionEngine {

    public void checkCollision() {
        // @formatter:off
        GameElements.get().getRenderables().forEach(this::checkOutOfScreen);

        // Getting all collidables
        final var collidables = GameElements.get()
                                            .getRenderables().stream()
                                            .filter(entry -> entry instanceof Collidable)
                                            .map(entry -> (Collidable) entry)
                                            .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));

        // Checking collision for each collidable
        collidables.forEach(
                col -> collidables.stream()
                                  .filter(another -> !another.equals(col))
                                  .forEach(another -> col.handleCollision(another)));
        // @formatter:on
    }

    private void checkOutOfScreen(Renderable sprite) {
        if (sprite instanceof TransitorySprite) {
            ((TransitorySprite) sprite).checkOutOfScreen();
        }
    }
}
