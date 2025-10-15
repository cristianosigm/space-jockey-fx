package com.cs2tech.framework.graphics;

import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.physics.CollisionEngine;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GraphicsRenderer {
    private final CollisionEngine ce = new CollisionEngine();

    public GraphicsRenderer(final Stage stage, final Scene initialScene, final Pane pane) {
        Canvas canvas = new Canvas(
                GameElements.get()
                            .getGameResolution().width,
                GameElements.get()
                            .getGameResolution().height
        );
        GraphicsContext gc = canvas.getGraphicsContext2D();
        pane.getChildren()
            .add(canvas);

        createAnimationTimer(gc);

        stage.setScene(initialScene);
        stage.setTitle("Space Jockey v0.1");
        stage.show();
    }

    private void createAnimationTimer(GraphicsContext gc) {
        final long targetFrameTime = 1000 / GameElements.get()
                                                        .getScreenRefreshRate();

        new AnimationTimer() {
            long lastFrameTime = 0;

            @Override
            public void handle(long now) {
                if ((System.currentTimeMillis() - lastFrameTime) > targetFrameTime) {
                    ce.checkCollision();
                    drawFrame(gc);
                    lastFrameTime = System.currentTimeMillis();
                }
            }
        }.start();
    }

    private void drawFrame(GraphicsContext gc) {
        // rendering level
        GameElements.get()
                    .getCurrentLevel()
                    .draw(gc);

        // drawing sprites
        GameElements.get()
                    .getRenderables()
                    .forEach(entry -> entry.draw(gc));
        GameElements.get()
                    .getPlayer()
                    .draw(gc);
    }

}
