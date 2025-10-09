package com.cs2tech.framework.controllers;

import com.cs2tech.framework.core.GameElements;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KeyboardController {
    private final Logger logger = LoggerFactory.getLogger(KeyboardController.class);

    public void keyPressed(KeyEvent key) {
        logger.debug(" --> Key Pressed: {}", key.getCode());
        handleKey(key.getCode(), true);
    }

    private void handleKey(KeyCode code, boolean action) {
        switch (code) {
            case KeyCode.UP -> GameElements.get().getPlayer().movingUp(action);
            case KeyCode.DOWN -> GameElements.get().getPlayer().movingDown(action);
            case KeyCode.LEFT -> GameElements.get().getPlayer().movingLeft(action);
            case KeyCode.RIGHT -> GameElements.get().getPlayer().movingRight(action);
        }
    }

    public void keyReleased(KeyEvent key) {
        logger.debug(" --> Key Released: {}", key.getCode());
        handleKey(key.getCode(), false);
    }
}
