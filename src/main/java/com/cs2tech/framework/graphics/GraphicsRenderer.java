package com.cs2tech.framework.graphics;

import com.cs2tech.framework.core.GameElements;
import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphicsRenderer {
    private final Logger logger = LoggerFactory.getLogger(GraphicsRenderer.class);

    // TODO: read from configuration file
    private final int FPS = 60;

    private final Pane pane;
    private final Canvas canvas;
    private final GraphicsContext gc;

    public GraphicsRenderer(final Stage stage, final Scene initialScene, final Pane pane, final Dimension2D screenSize) {
        this.pane = pane;
        canvas = new Canvas(screenSize.getWidth(), screenSize.getHeight());
        gc = canvas.getGraphicsContext2D();
        this.pane.getChildren().add(canvas);

        createAnimationTimer(gc);

        stage.setScene(initialScene);
        stage.setTitle("Space Jockey v0.1");
        stage.show();
    }

    private void createAnimationTimer(GraphicsContext gc) {
        new AnimationTimer() {
            long lastFrameTime = 0;

            public void handle(long now) {
                if (lastFrameTime == 0) {
                    lastFrameTime = now;
                    drawFrame(gc);
                    return;
                }

                if (now - lastFrameTime > 1000000000 / FPS) {
                    lastFrameTime = now;
                    drawFrame(gc);
                }
            }
        }.start();
    }

    private void drawFrame(GraphicsContext gc) {
        // paint background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // drawing sprites
        GameElements.get().getRenderables().forEach(entry -> entry.draw(gc));
        GameElements.get().getPlayer().draw(gc);
    }
}
