package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.SShip;

import java.awt.*;

/**
 * <p>
 * Classe que define o advers�rio do tipo Bal�o.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Balao extends SShip {

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {24};

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
    private static final int SPEED = Cenario.BG_SPEED - 15;

    /**
     * Matriz de Colis�o deste objeto.
     */
    private static final boolean[][] MATRIX = {{CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O}, {CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X}, {CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O}, {CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O}, {CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O}, {CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O}, {CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O}};

    /**
     * <p>
     * Construtor padr�o.
     * </p>
     *
     * @param posY
     *     Posi��o do objeto no eixo Y.
     */
    public Balao(final int posY) {
        super(new Point(IRenderable.gameConfig.getGameResolution().width, posY - (Balao.MATRIX.length * Cenario.BLOCK.height / 2)), Balao.SPRITES, Balao.SPRITES_INTERVAL,
            Balao.SPEED, Balao.MATRIX, CollisionHandler.TYPE_ENEMY, Directions.DIR_LEFT, Balao.BONUS, Balao.ENERGY, Definitions.EFF_EXPLOSION_COMMON);
        IRenderable.log.addDebug("NOVO BALAO CRIADO!", this);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.internal.IAutomatedActor#handleActions()
     */
    @Override
    public void handleActions() {
        // nao usado pelo balao
    }
}
