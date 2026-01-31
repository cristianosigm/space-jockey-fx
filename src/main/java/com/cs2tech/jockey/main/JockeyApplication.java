package com.cs2tech.jockey.main;

import com.cs2tech.framework.controllers.KeyboardController;
import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.graphics.GraphicsRenderer;
import com.cs2tech.jockey.levels.LevelOne;
import com.cs2tech.jockey.sprites.PlayerOne;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JockeyApplication extends Application {
    private final double windowWidth = GameElements.get()
                                                   .getGameResolution().width;
    private final double windowHeight = GameElements.get()
                                                    .getGameResolution().height;
    private final Logger logger = LoggerFactory.getLogger(JockeyApplication.class);

    @Override
    public void start(final Stage stage) throws Exception {
        logger.info("Starting the game...");

        // TODO: set the game configuration into a proper config file
        final StackPane pane = new StackPane();
        final Scene scene = new Scene(pane, windowWidth, windowHeight);

        // adding controller listeners ------------------------------------------------------------------
        final KeyboardController keyboardController = new KeyboardController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyboardController::keyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyboardController::keyReleased);

        // Adding a level -------------------------------------------------------------------------------
        GameElements.get()
                    .playLevel(new LevelOne());
        
        // adding player --------------------------------------------------------------------------------
        GameElements.get()
                    .addPlayer(new PlayerOne(new Position(windowWidth / 2, windowHeight - 49)));

        // starting renderer ----------------------------------------------------------------------------
        new GraphicsRenderer(stage, scene, pane);
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }
}
