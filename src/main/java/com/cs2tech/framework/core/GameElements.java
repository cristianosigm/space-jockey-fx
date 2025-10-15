package com.cs2tech.framework.core;

import com.cs2tech.framework.graphics.Renderable;
import com.cs2tech.framework.levels.GameLevel;
import com.cs2tech.framework.media.MidiPlayer;
import com.cs2tech.framework.media.WavPlayer;
import com.cs2tech.framework.physics.Collidable;
import com.cs2tech.framework.sprites.PlayerSprite;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class GameElements {
    private static final GameElements instance = new GameElements();

    private final Logger logger = LoggerFactory.getLogger(GameElements.class);
    private final ConcurrentLinkedQueue<Renderable> renderables = new ConcurrentLinkedQueue<>();
    private final List<Image> images = new ArrayList<>();
    private final List<ImageView> imageViews = new ArrayList<>();
    private final Size gameResolution = new Size(800, 600);

    private PlayerSprite player;
    private GameLevel currentLevel;
    private final MidiPlayer musicPlayer;
    private final WavPlayer soundEffectsPlayer;

    private GameElements() {
        loadImages();
        musicPlayer = new MidiPlayer();
        soundEffectsPlayer = new WavPlayer();
    }

    public static GameElements get() {
        return instance;
    }

    public ConcurrentLinkedQueue<Renderable> getRenderables() {
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

    public ImageView getImageView(int index) {
        return imageViews.get(index);
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

    public MidiPlayer getMusicPlayer() {
        return musicPlayer;
    }

    public WavPlayer getSoundEffectsPlayer() {
        return soundEffectsPlayer;
    }

    public long getScreenRefreshRate() {
        return 60;
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
                InputStream imageStream = loader.getResourceAsStream(properties.getProperty("IMG" + i));
                if (imageStream != null && imageStream.available() > 0) {
                    images.add(new Image(imageStream));
                }
            }

            loadImageViews();

            logger.debug("List of images loaded successfully.");
        } catch (Exception e) {
            logger.error("Failed to load images used by sprites. Aborting game startup.", e);
        }
    }

    private void loadImageViews() {
        images.forEach(img -> imageViews.add(new ImageView(img)));
    }
}
