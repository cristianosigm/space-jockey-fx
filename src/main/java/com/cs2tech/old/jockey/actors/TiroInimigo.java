package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.SShot;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa um tiro do advers�rio.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class TiroInimigo extends SShot {

    /**
     * Poder de destrui��o do tiro.
     */
    private static final byte BS_HIT_POWER = 1;

    /**
     * Velocidade do tiro.
     */
    private static final int BS_SPEED = 450;

    /**
     * Intervalo de tempo entre as trocas de sprites.
     */
    private static final byte SPRITE_INTERVAL = 0;

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {5};

    /**
     * Matriz de colis�o deste objeto.
     */
    private static final boolean[][] MATRIX = {{CollisionObject.X}};

    /**
     * <p>
     * Construtor Principal.
     * </p>
     *
     * @param initialPos
     *     Posi��o inicial do tiro.
     */
    public TiroInimigo(final Point initialPos) {
        super(initialPos, TiroInimigo.SPRITES, TiroInimigo.SPRITE_INTERVAL, TiroInimigo.BS_SPEED, TiroInimigo.MATRIX, true, Directions.DIR_LEFT, TiroInimigo.BS_HIT_POWER);
    }
}
