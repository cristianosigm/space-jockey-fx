package com.cs2tech.framework.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class CommonPlayer implements IAudioPlayer {
    private final Logger logger = LoggerFactory.getLogger(CommonPlayer.class);

    private List<InputStream> list;
    private int currentTrack = 1;
    private boolean playerEnabled = true;

    public CommonPlayer(final String propertiesFile) {
        loadList(propertiesFile);
    }

    private void loadList(final String configurationFilePath) {
        try (
                InputStream input = CommonPlayer.class.getClassLoader()
                                                      .getResourceAsStream(configurationFilePath)
        ) {
            logger.debug("Loading list of media files from: {}.", configurationFilePath);

            if (input == null) {
                logger.warn("Failed to load the list of media files.");
                playerEnabled = false;
                return;
            }

            final Properties properties = new Properties();
            properties.load(input);
            loadFiles(properties);

            logger.debug("List of media files loaded successfully.");
        } catch (final FileNotFoundException e) {
            logger.error("File not found when trying to load media files {}. Reason: ", configurationFilePath, e);
        } catch (final IOException e) {
            logger.error("General I/O exception when trying to load media files {}. Reason: ", configurationFilePath, e);
        } catch (Exception e) {
            logger.error("Unhandled exception when trying to load media files {}. Reason: ", configurationFilePath, e);
        } finally {
            if (list == null || list.isEmpty()) {
                setPlayerEnabled(false);
            }
        }
    }

    private void loadFiles(final Properties properties) throws IOException {
        final int listSize = Integer.parseInt(properties.getProperty("QTY"));
        list = new ArrayList<>();

        for (int i = 0; i < listSize; i++) {
            list.add(CommonPlayer.class.getClassLoader()
                                       .getResourceAsStream(properties.getProperty("F" + i)));
        }
    }

    public InputStream getCurrentFile() {
        return list.get(currentTrack);
    }

    public int getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(final int currentTrack) {
        this.currentTrack = currentTrack;
    }

    @Override
    public void playNextTrack() {
        if (currentTrack < list.size() - 1) {
            currentTrack++;
        }
        loadTrack();
        this.play();
    }

    @Override
    public void play(final int index) {
        if (isPlayerEnabled() && (index >= 0) && (index < list.size())) {
            currentTrack = index;
            this.play();
        }
    }

    public boolean isPlayerEnabled() {
        return playerEnabled;
    }

    public void setPlayerEnabled(final boolean playerEnabled) {
        if (list != null && !list.isEmpty()) {
            this.playerEnabled = playerEnabled;
        } else {
            this.playerEnabled = false;
        }
    }

    @Override
    public void previous() {
        if (isPlayerEnabled() && currentTrack > 0) {
            currentTrack--;
        }
        loadTrack();
    }

    @Override
    public void reset() {
        if (isPlayerEnabled()) {
            this.stop();
            setCurrentTrack(0);
        }
    }

    protected abstract void loadTrack();
}
