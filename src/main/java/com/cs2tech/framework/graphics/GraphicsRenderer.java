package com.cs2tech.framework.graphics;

import com.cs2tech.framework.core.*;
import javafx.animation.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.canvas.*;
import javafx.scene.layout.*;
import javafx.stage.*;

public class GraphicsRenderer {
    // TODO: get the refresh rate as the samle below:

    //    stage.setOnShown(e -> {
    //        Screen screen = Screen.getScreensForRectangle(stage.getX(), stage.getY(), stage.getWidth(), stage.getHeight())
    //                .get(0);
    //        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    //        GraphicsDevice d = ge.getScreenDevices()[Screen.getScreens().indexOf(screen)];
    //
    //        int r = d.getDisplayMode().getRefreshRate();
    //        System.out.println("Screen refresh rate : " + r);
    //        //Calculate frame duration in nanoseconds
    //        this.frameNs = 1_000_000_000L / refreshRate; //Store it as it better suits you
    //    });

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

            @Override
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
        // rendering level
        GameElements.get().getCurrentLevel().draw(gc);

        // drawing sprites
        GameElements.get().getRenderables().forEach(entry -> entry.draw(gc));
        GameElements.get().getPlayer().draw(gc);
    }
}
