package com.cs2tech.framework.media;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sound.midi.*;
import java.io.IOException;

public class MidiPlayer extends CommonPlayer {
    private final Logger logger = LoggerFactory.getLogger(MidiPlayer.class);

    private Sequencer midiSequencer = null;
    private Sequence midiTrack;
    private boolean continuous = true;

    public MidiPlayer() {
        super("musics.properties");

        if(isPlayerEnabled()) {
            try {
                midiSequencer = MidiSystem.getSequencer(true);
                midiSequencer.open();
            } catch (final MidiUnavailableException e) {
                logger.error("Failed to instance a MIDI player. Reason: {}", e.getMessage(), e);
                setPlayerEnabled(false);
            }
        } else {
            logger.warn("Player is currently disabled. Skipping MIDI sequencer creation.");
        }
    }

    @Override
    public void pause() {
        if (isPlayerEnabled() && (midiSequencer != null) && (midiSequencer.isRunning())) {
            midiSequencer.stop();
        } else if (isPlayerEnabled() && (midiSequencer != null)) {
            midiSequencer.start();
        }
    }

    @Override
    public void play() {
        loadTrack();
        if (isPlayerEnabled() && (midiSequencer != null)) {
            midiSequencer.start();
        }
    }

    @Override
    protected void loadTrack() {
        if (isPlayerEnabled()) {
            logger.debug("MIDI player is enabled.");

            if ((midiSequencer != null) && midiSequencer.isRunning()) {
                stop();
            }

            try {
                midiTrack = MidiSystem.getSequence(getCurrentFile());
                midiSequencer.open();
                midiSequencer.setSequence(midiTrack);

                if (continuous) {
                    logger.debug("Playing MIDI track continuously.");
                    midiSequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
                }

                logger.debug("Track {} loaded successfully.", getCurrentTrack());
            } catch (final InvalidMidiDataException e) {
                logger.error("Invalid MIDI track.", e);
            } catch (final IOException e) {
                logger.error("General I/O exception.", e);
            } catch (final MidiUnavailableException e) {
                logger.error("MIDI sequencer is unavailable.", e);
            } catch (final Exception e) {
                logger.error("Unhandled exception when trying to play a MIDI track: {}", e.getMessage(), e);
            }
        }
    }

    private void rewind() {
        if (isPlayerEnabled() && (midiSequencer != null) && (!midiSequencer.isRunning())) {
            midiSequencer.setMicrosecondPosition(0);
        }
    }

    @Override
    public void stop() {
        if (isPlayerEnabled() && midiSequencer != null && midiSequencer.isRunning()) {
            midiSequencer.stop();
        }
        rewind();
    }

    public void setContinuous(final boolean continuous) {
        this.continuous = continuous;
    }
}
