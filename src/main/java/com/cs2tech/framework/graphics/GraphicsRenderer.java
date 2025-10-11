package com.cs2tech.framework.graphics;

import com.cs2tech.framework.core.GameElements;
import javafx.animation.AnimationTimer;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.awt.*;

public class GraphicsRenderer {
    private final Pane pane;
    private final Canvas canvas;
    private final GraphicsContext gc;

    public GraphicsRenderer(final Stage stage, final Scene initialScene, final Pane pane, final Dimension2D screenSize) {
        this.pane = pane;
        canvas = new Canvas(screenSize.getWidth(), screenSize.getHeight());
        gc = canvas.getGraphicsContext2D();
        this.pane.getChildren().add(canvas);

        createAnimationTimer(gc);

        stage.setOnShown(e -> readRefreshRate(e, stage));
        stage.setScene(initialScene);
        stage.setTitle("Space Jockey v0.1");
        stage.show();
    }

    private void createAnimationTimer(GraphicsContext gc) {
        new AnimationTimer() {

            @Override
            public void handle(long now) {
                drawFrame(gc);
            }
        }.start();
    }

    private void drawFrame(GraphicsContext gc) {
        // rendering level
        GameElements.get().getCurrentLevel().draw(gc);

        // drawing sprites
        GameElements.get().getRenderables().forEach(entry -> entry.draw(gc));
        GameElements.get().getPlayer().draw(gc);
    }

    private void readRefreshRate(final WindowEvent e, final Stage stage) {
        final Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight()).get(0);
        final GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        final GraphicsDevice graphicsDevice = graphicsEnvironment.getScreenDevices()[Screen.getScreens().indexOf(screen)];

        GameElements.get().setScreenRefreshRate(graphicsDevice.getDisplayMode().getRefreshRate());
    }
}
