package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.GameConfig;
import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.GameStatus;
import com.cs2tech.old.jshooter.components.GameUtils;
import com.cs2tech.old.jshooter.gameCore.Phisics;

import java.awt.*;

public interface IRenderable {

    GameLogger log = GameLogger.getInstance();

    ImageRepository imageRepository = ImageRepository.getInstance();

    GameConfig gameConfig = GameConfig.getInstance();

    GameUtils gameUtils = GameUtils.getInstance();

    Phisics gamePhisics = Phisics.getInstance();

    GameStatus gameStatus = GameStatus.getInstance();

    void draw(Graphics2D gfx, Canvas cnv);

    byte getId();

    void setId(byte id);

    void terminate();
}
