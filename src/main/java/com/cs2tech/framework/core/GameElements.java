package com.cs2tech.framework.core;

import com.cs2tech.framework.graphics.Renderable;
import com.cs2tech.framework.sprites.PlayerSprite;

import java.util.LinkedList;

public class GameElements {

    private static final GameElements instance = new GameElements();

    private PlayerSprite player;

    private final LinkedList<Renderable> renderables = new LinkedList<>();

    private GameElements() {
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
}
