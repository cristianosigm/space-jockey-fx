package com.cs2tech.jockey.main;

import com.cs2tech.framework.controllers.KeyboardController;
import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.core.Position;
import com.cs2tech.framework.graphics.GraphicsRenderer;
import com.cs2tech.framework.media.MidiPlayer;
import com.cs2tech.framework.media.WavPlayer;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JockeyApplication extends Application {
    private final Logger logger = LoggerFactory.getLogger(JockeyApplication.class);

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    @Override
    public void start(final Stage stage) throws Exception {
        logger.info("Starting the game...");

        final BorderPane pane = new BorderPane();
        final Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        // adding controller listeners ------------------------------------------------------------------
        final KeyboardController keyboardController = new KeyboardController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> keyboardController.keyPressed(key));
        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> keyboardController.keyReleased(key));

        // adding media players -------------------------------------------------------------------------
//        final MidiPlayer musicPlayer = new MidiPlayer();
//        musicPlayer.play(1);
//
//        final WavPlayer effects = new WavPlayer();
//        effects.play(1);

        // Adding a level -------------------------------------------------------------------------------
        GameElements.get().playLevel(new LevelOne());

        // adding a few sample enemies ------------------------------------------------------------------
        GameElements.get().getRenderables().add(new JetFighter(new Position(WINDOW_WIDTH - 1, getRandomYPosition())));
        GameElements.get().getRenderables().add(new Tree(WINDOW_WIDTH, WINDOW_HEIGHT));
        GameElements.get().getRenderables().add(new House(WINDOW_WIDTH, WINDOW_HEIGHT));

        // adding player --------------------------------------------------------------------------------
        GameElements.get().addPlayer(new PlayerOne(new Position(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 49)));

        // starting renderer ----------------------------------------------------------------------------
        new GraphicsRenderer(stage, scene, pane, new Dimension2D(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    private int getRandomYPosition() {
        return (int) Math.ceil(Math.random() * (WINDOW_HEIGHT - 40)) + 20;
    }
}
