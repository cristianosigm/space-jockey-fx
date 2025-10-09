package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameElements.SShip;

import java.awt.*;

/**
 * <p>
 * Classe que define o advers�rio do tipo Casa (o que � profundamente
 * question�vel do ponto de vista moral, mas, mais uma vez, isto estava no jogo
 * original.)
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Casa extends SShip {

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {23};

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
    private static final boolean[][] MATRIX = {{O, O, X, X, X, X, X, X, O, O}, {X, X, X, X, X, X, X, X, X, O}, {X, X, X, X, X, X, X, X, X, X}, {X, X, X, X, X, X, X, X, X, X}, {X, X, X, X, X, X, X, X, X, X}, {X, X, X, X, X, X, X, X, X, X}, {X, X, X, X, X, X, X, X, X, X}};

    /**
     * <p>
     * Construtor Principal.
     * </p>
     */
    public Casa() {
        super(new Point(IRenderable.gameConfig.getGameResolution().width, (Cenario.GROUND - (Casa.MATRIX.length * GameFactory.getInstance()
                .getColision()
                .getCollisionBlockSize().height) + 5)), Casa.SPRITES, Casa.SPRITES_INTERVAL, Casa.SPEED, Casa.MATRIX, CollisionHandler.TYPE_ENEMY, Directions.DIR_LEFT, Casa.BONUS,
            Casa.ENERGY, Definitions.EFF_EXPLOSION_COMMON);
        IRenderable.log.addDebug("NOVA CASA CRIADA. Pos = (" + getPosition().x + "," + getPosition().y + ").", this);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.internal.IAutomatedActor#handleActions()
     */
    @Override
    public void handleActions() {
        // arvore nao faz nada.
    }
}
