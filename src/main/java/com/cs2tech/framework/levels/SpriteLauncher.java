package com.cs2tech.framework.levels;

import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.sprites.Sprite;

public class SpriteLauncher {
    private final long framesToWait;
    private final Sprite sprite;
    private boolean launched = false;

    public SpriteLauncher(long framesToWait, Sprite sprite) {
        this.framesToWait = framesToWait;
        this.sprite = sprite;
    }

    public void launch(long currentFrame) {
        if (currentFrame > framesToWait) {
            GameElements.get()
                        .getRenderables()
                        .add(sprite);
            launched = true;
        }
    }

    public boolean launched() {
        return launched;
    }
}
