package com.cs2tech.jockey.main;

import com.cs2tech.framework.controllers.KeyboardController;
import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.graphics.GraphicsRenderer;
import com.cs2tech.jockey.scenes.LevelOne;
import com.cs2tech.jockey.sprites.House;
import com.cs2tech.jockey.sprites.JetFighter;
import com.cs2tech.jockey.sprites.PlayerOne;
import com.cs2tech.jockey.sprites.Tree;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JockeyApplication extends Application {
    final double windowWidth = GameElements.get().getGameResolution().width;
    final double windowHeight = GameElements.get().getGameResolution().height;
    private final Logger logger = LoggerFactory.getLogger(JockeyApplication.class);

    @Override
    public void start(final Stage stage) throws Exception {
        logger.info("Starting the game...");

        // TODO: set the game configuration into a proper config file
        final BorderPane pane = new BorderPane();
        final Scene scene = new Scene(pane, windowWidth, windowHeight);

        // adding controller listeners ------------------------------------------------------------------
        final KeyboardController keyboardController = new KeyboardController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, keyboardController::keyPressed);
        scene.addEventFilter(KeyEvent.KEY_RELEASED, keyboardController::keyReleased);

        // adding media players -------------------------------------------------------------------------
//        final MidiPlayer musicPlayer = new MidiPlayer();
//        musicPlayer.play(1);
//
//        final WavPlayer effects = new WavPlayer();
//        effects.play(1);

        // Adding a level -------------------------------------------------------------------------------
        GameElements.get().playLevel(new LevelOne());

        // adding a few sample enemies ------------------------------------------------------------------
        GameElements.get().getRenderables().add(new JetFighter(new Position(windowWidth - 1, getRandomYPosition())));
        GameElements.get().getRenderables().add(new Tree(windowWidth, windowHeight));
        GameElements.get().getRenderables().add(new House(windowWidth, windowHeight));

        // adding player --------------------------------------------------------------------------------
        GameElements.get().addPlayer(new PlayerOne(new Position(windowWidth / 2, windowHeight - 49)));

        // starting renderer ----------------------------------------------------------------------------
        new GraphicsRenderer(stage, scene, pane, new Dimension2D(windowWidth, windowHeight));
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    private int getRandomYPosition() {
        return (int) Math.ceil(Math.random() * (windowHeight - 40)) + 20;
    }
}
