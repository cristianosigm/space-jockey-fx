package com.cs2tech.framework.levels;

import com.cs2tech.framework.core.Direction;
import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.core.Size;
import com.cs2tech.framework.graphics.Renderable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BackgroundLayer implements Renderable {
    private final int scrollingSpeed;
    private final Position currentImagePosition = new Position();
    private final Position nextImagePosition = new Position();
    private final Direction direction;
    private final Size currentImageSize = new Size();
    private final Size nextImageSize = new Size();
    private boolean setupComplete = false;
    private int currentImageIndex;
    private int nextImageIndex;

    public BackgroundLayer(int currentImageIndex, int nextImageIndex, int scrollingSpeed, Direction direction) {
        this.currentImageIndex = currentImageIndex;
        this.nextImageIndex = nextImageIndex;
        this.scrollingSpeed = scrollingSpeed;
        this.direction = direction;
    }

    public void setNextImageIndex(int index) {
        nextImageIndex = index;
    }

    @Override
    public void draw(final GraphicsContext gc) {
        final Image cur = GameElements.get()
                                      .getImage(currentImageIndex);
        final Image next = GameElements.get()
                                       .getImage(nextImageIndex);

        setup(cur, next);
        scroll();

        gc.drawImage(cur, currentImagePosition.x, currentImagePosition.y, GameElements.get().getGameResolution().width,
                GameElements.get().getGameResolution().height);
        gc.drawImage(next, nextImagePosition.x, nextImagePosition.y, GameElements.get().getGameResolution().width,
                GameElements.get().getGameResolution().height);
    }

    private void setup(final Image cur, final Image next) {
        if (!setupComplete) {
            // TODO: handle BG images in sizes different than the screen
            currentImageSize.width = GameElements.get()
                                                 .getGameResolution().width;
            currentImageSize.height = GameElements.get()
                                                  .getGameResolution().height;

            nextImageSize.width = GameElements.get()
                                              .getGameResolution().width;
            nextImageSize.height = GameElements.get()
                                               .getGameResolution().height;

            switch (direction) {
                case UP:
                    nextImagePosition.y = currentImageSize.height;
                    break;
                case DOWN:
                    currentImagePosition.y = GameElements.get()
                                                         .getGameResolution().height - currentImageSize.height;
                    nextImagePosition.y = currentImagePosition.y - nextImageSize.height;
                    break;
                case LEFT:
                    nextImagePosition.x = currentImageSize.width;
                    break;
                case RIGHT:
                    currentImagePosition.x = GameElements.get()
                                                         .getGameResolution().width - currentImageSize.width;
                    nextImagePosition.x = currentImagePosition.x - nextImageSize.width;
            }
            setupComplete = true;
        }
    }

    private void scroll() {
        if (scrollingSpeed > 0) {
            switch (direction) {
                case UP -> scrollUp();
                case DOWN -> scrollDown();
                case LEFT -> scrollLeft();
                case RIGHT -> scrollRight();
            }
        }
    }

    private void scrollLeft() {
        currentImagePosition.x -= scrollingSpeed;
        nextImagePosition.x -= scrollingSpeed;
        if (currentImagePosition.x + currentImageSize.width < 0) {
            currentImageIndex = nextImageIndex;
            currentImagePosition.x = 0;
            nextImagePosition.x = currentImageSize.width;
        }
    }

    private void scrollRight() {
        currentImagePosition.x += scrollingSpeed;
        nextImagePosition.x += scrollingSpeed;
        if (currentImagePosition.x > GameElements.get()
                                                 .getGameResolution().width) {
            currentImageIndex = nextImageIndex;
            currentImagePosition.x = GameElements.get()
                                                 .getGameResolution().width - currentImageSize.width;
            nextImagePosition.x = currentImagePosition.x - nextImageSize.width;
        }
    }

    private void scrollUp() {
        currentImagePosition.y -= scrollingSpeed;
        nextImagePosition.y -= scrollingSpeed;
        if (currentImagePosition.y + currentImageSize.height < 0) {
            currentImageIndex = nextImageIndex;
            currentImagePosition.y = 0;
            nextImagePosition.y = currentImageSize.height;
        }
    }

    private void scrollDown() {
        currentImagePosition.y += scrollingSpeed;
        nextImagePosition.y += scrollingSpeed;
        if (currentImagePosition.y > currentImageSize.height) {
            currentImageIndex = nextImageIndex;
            currentImagePosition.y = GameElements.get()
                                                 .getGameResolution().height - currentImageSize.height;
            nextImagePosition.y = currentImagePosition.y - nextImageSize.height;
        }
    }
}
