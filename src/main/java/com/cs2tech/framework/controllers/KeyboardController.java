package com.cs2tech.framework.controllers;

import com.cs2tech.framework.core.GameElements;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardController {

    public void keyPressed(KeyEvent key) {
        handleKey(key.getCode(), true);
    }

    public void keyReleased(KeyEvent key) {
        handleKey(key.getCode(), false);
    }

    private void handleKey(KeyCode code, boolean action) {
        switch (code) {
            case KeyCode.UP -> GameElements.get()
                                           .getPlayer()
                                           .movingUp(action);
            case KeyCode.DOWN -> GameElements.get()
                                             .getPlayer()
                                             .movingDown(action);
            case KeyCode.LEFT -> GameElements.get()
                                             .getPlayer()
                                             .movingLeft(action);
            case KeyCode.RIGHT -> GameElements.get()
                                              .getPlayer()
                                              .movingRight(action);
            default -> {
            }
        }
    }
}
