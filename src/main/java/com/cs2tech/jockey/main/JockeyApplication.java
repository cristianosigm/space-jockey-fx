package com.cs2tech.jockey.main;

import com.cs2tech.framework.controllers.KeyboardController;
import com.cs2tech.framework.core.GameElements;
import com.cs2tech.framework.graphics.GraphicsRenderer;
import com.cs2tech.framework.media.MidiPlayer;
import com.cs2tech.framework.media.WavPlayer;
import com.cs2tech.framework.sprites.EnemyShipSprite;
import com.cs2tech.jockey.sprites.PlayerOne;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Dimension2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class JockeyApplication extends Application {
    private final Logger logger = LoggerFactory.getLogger(JockeyApplication.class);

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;

    @Override
    public void start(final Stage stage) throws Exception {
        logger.info("Starting the game...");

        final Pane pane = new Pane();
        final Scene scene = new Scene(pane, WINDOW_WIDTH, WINDOW_HEIGHT);

        // adding controller listeners ------------------------------------------------------------------
        final KeyboardController keyboardController = new KeyboardController();
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> keyboardController.keyPressed(key));
        scene.addEventFilter(KeyEvent.KEY_RELEASED, key -> keyboardController.keyReleased(key));

        // adding media players -------------------------------------------------------------------------
        final MidiPlayer musicPlayer = new MidiPlayer();
        musicPlayer.play(1);

        final WavPlayer effects = new WavPlayer();
        effects.play(1);

        // adding one sample enemy
        GameElements.get().getRenderables().add(new EnemyShipSprite(new Point(getRandomXPosition(), 0)));

        // adding player --------------------------------------------------------------------------------
        GameElements.get().addPlayer(new PlayerOne(new Point(WINDOW_WIDTH / 2, WINDOW_HEIGHT - 49)));

        // starting renderer ----------------------------------------------------------------------------
        new GraphicsRenderer(stage, scene, pane, new Dimension2D(WINDOW_WIDTH, WINDOW_HEIGHT));
    }

    @Override
    public void stop() {
        Platform.exit();
        System.exit(0);
    }

    private int getRandomXPosition() {
        return (int) Math.ceil(Math.random() * WINDOW_WIDTH);
    }
}
