package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.audio.AudioController;

import java.awt.*;

public abstract class BasicGraphics implements IRenderable {

    protected final AudioController audio = AudioController.getInstance();

    private byte id;

    private Dimension size;

    @Override
    public byte getId() {
        return id;
    }

    @Override
    public void setId(final byte id) {
        this.id = id;
    }

    @Override
    public void terminate() {
        GameFactory.getInstance()
            .getActors()
            .remove(id);
    }

    public Dimension getSize() {
        return size;
    }

    public void setSize(final Dimension size) {
        this.size = size;
    }
}
