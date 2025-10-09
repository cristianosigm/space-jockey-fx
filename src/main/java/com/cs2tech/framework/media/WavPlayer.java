package com.cs2tech.framework.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.sampled.*;
import java.io.IOException;

public class WavPlayer extends CommonPlayer {
    private final Logger logger = LoggerFactory.getLogger(WavPlayer.class);

    private AudioInputStream audioInputStream = null;
    private AudioFormat audioInputFormat;
    private Clip currentAudioClip;

    public WavPlayer() {
        super("effects.properties");
    }

    @Override
    protected void loadTrack() {
        if (isPlayerEnabled()) {
            try {
                audioInputStream = AudioSystem.getAudioInputStream(getCurrentFile());
                audioInputFormat = audioInputStream.getFormat();
                final DataLine.Info inputDescription = new DataLine.Info(Clip.class, audioInputFormat);
                if (AudioSystem.isLineSupported(inputDescription)) {
                    currentAudioClip = (Clip) AudioSystem.getLine(inputDescription);
                    currentAudioClip.open(audioInputStream);

                    logger.debug("Track {} successfully loaded.", getCurrentTrack());
                } else {
                    logger.error("Unsupported audio input format: {}", inputDescription);
                }
            } catch (final UnsupportedAudioFileException e) {
                logger.error("Unsupported audio format when trying to load file {}. Reason: {}", getCurrentFile(), e.getMessage(), e);
            } catch (final IOException e) {
                logger.error("General I/O exception when trying to load file {}. Reason: {}", getCurrentFile(), e.getMessage(), e);
            } catch (final LineUnavailableException e) {
                logger.error("Line unavailable when loading track {}. Reason: {}", getCurrentFile(), e.getMessage(), e);
            } catch (final Exception e) {
                logger.error("Unhandled exception when trying to load track {}. Reason: {}", getCurrentFile(), e.getMessage(), e);
            }
        }
    }

    @Override
    public void pause() {
        if (isPlayerEnabled() && (currentAudioClip != null) && (currentAudioClip.isRunning())) {
            currentAudioClip.stop();
        }
    }

    @Override
    public void play() {
        if (isPlayerEnabled()) {
            loadTrack();
            if (currentAudioClip != null) {
                currentAudioClip.start();
            }
        }
    }

    private void rewind() {
        if (isPlayerEnabled() && (currentAudioClip != null) && (!currentAudioClip.isRunning())) {
            currentAudioClip.setMicrosecondPosition(0);
        }
    }

    @Override
    public void stop() {
        pause();
        rewind();
    }
}
