package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameElements.SShip;

import java.awt.*;

public class Arvore extends SShip {

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {19};

    /**
     * Intervalo de tempo entre as trocas de sprites.
     */
    private static final byte SPRITES_INTERVAL = 0;

    /**
     * Energia desta nave.
     */
    private static final byte ENERGY = 1;

    /**
     * Pontua��o dada ao jogador quando esta nave � destru�da.
     */
    private static final int BONUS = 25;

    /**
     * Velocidade desta nave na tela.
     */
    private static final int SPEED = Cenario.BG_SPEED;

    /**
     * Matriz de Colis�o deste objeto.
     */
    private static final boolean[][] MATRIX = {{O, O, X, X, X, X, O, O}, {O, O, X, X, X, X, X, X}, {X, X, X, X, X, X, X, X}, {X, X, X, X, X, X, X, X}, {X, X, X, X, X, X, X, O}, {O, O, X, X, X, X, O, O}, {O, O, X, X, X, X, O, O}, {O, O, X, X, X, X, O, O}, {O, O, X, X, X, X, O, O}, {O, O, X, X, X, X, O, O}, {O, X, X, X, X, X, X, O}};

    public Arvore() {
        super(new Point(IRenderable.gameConfig.getGameResolution().width, (Cenario.GROUND - (Arvore.MATRIX.length * GameFactory.getInstance()
                .getColision()
                .getCollisionBlockSize().height) + 5)), Arvore.SPRITES, Arvore.SPRITES_INTERVAL, Arvore.SPEED, Arvore.MATRIX, CollisionHandler.TYPE_ENEMY, Directions.DIR_LEFT,
            Arvore.BONUS, Arvore.ENERGY, Definitions.EFF_EXPLOSION_COMMON);
        IRenderable.log.addDebug("NOVA ARVORE CRIADA. Pos = (" + getPosition().x + "," + getPosition().y + ").", this);
    }

    @Override
    public void handleActions() {
        // arvore nao faz nada.
    }
}
