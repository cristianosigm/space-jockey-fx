package com.cs2tech.framework.core;

import com.cs2tech.framework.graphics.Renderable;
import com.cs2tech.framework.levels.GameLevel;
import com.cs2tech.framework.sprites.PlayerSprite;
import javafx.scene.image.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;

public class GameElements {
    private static final GameElements instance = new GameElements();
    private final Logger logger = LoggerFactory.getLogger(GameElements.class);
    private final LinkedList<Renderable> renderables = new LinkedList<>();
    private final LinkedList<Image> images = new LinkedList<>();
    private final Size gameResolution = new Size(800, 600);
    private PlayerSprite player;
    private GameLevel currentLevel;

    private GameElements() {
        loadImages();
    }

    public static GameElements get() {
        return instance;
    }

    public LinkedList<Renderable> getRenderables() {
        return renderables;
    }

    public void addPlayer(PlayerSprite player) {
        this.player = player;
    }

    public PlayerSprite getPlayer() {
        return player;
    }

    public Image getImage(int index) {
        return images.get(index);
    }

    public Size getGameResolution() {
        return gameResolution;
    }

    public GameLevel getCurrentLevel() {
        return currentLevel;
    }

    public void playLevel(GameLevel level) {
        currentLevel = level;
    }

    private void loadImages() {
        final ClassLoader loader = GameElements.class.getClassLoader();

        // TODO: join all properties loaders in an utility class.
        try (InputStream input = loader.getResourceAsStream("images.properties")) {
            logger.debug("Loading list of image files from images.properties...");

            if (input == null) {
                logger.error("Failed to load the list of images.");
                throw new Exception("Invalid images configuration file. Aborting startup. ");
            }

            final Properties properties = new Properties();
            properties.load(input);

            final int listSize = Integer.parseInt(properties.getProperty("QTY"));

            for (int i = 0; i < listSize; i++) {
                images.add(new Image(loader.getResourceAsStream(properties.getProperty("IMG" + i))));
            }

            logger.debug("List of images loaded successfully.");
        } catch (Exception e) {
            logger.error("Failed to load images used by sprites. Aborting game startup.", e);
        }
    }
}
