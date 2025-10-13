package com.cs2tech.framework.physics;

import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.graphics.Renderable;

public class CollisionEngine {

    public void checkCollision() {
        GameElements.get().getRenderables().forEach(this::checkOutOfScreen);
    }

    private void checkOutOfScreen(Renderable sprite) {
        if (sprite instanceof TransitoryCharacter) {
            ((TransitoryCharacter) sprite).checkOutOfScreen();
        }
    }
}
