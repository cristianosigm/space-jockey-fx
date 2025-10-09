package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameElements.SShip;

import java.awt.*;

/**
 * <p>
 * Classe que define o advers�rio do tipo Helic�ptero.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Helicoptero extends SShip {

    /**
     * Intervalo de tempo entre os disparos desta nave.
     */
    private static final int FRAMES_TO_SHOT = IRenderable.gameUtils.getNFrames(1);

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {17, 18};

    /**
     * Intervalo de tempo entre as trocas de sprites.
     */
    private static final byte SPRITES_INTERVAL = 1;

    /**
     * Energia desta nave.
     */
    private static final byte ENERGY = 1;

    /**
     * Pontua��o dada ao jogador quando esta nave � destru�da.
     */
    private static final int BONUS = 50;

    /**
     * Velocidade desta nave na tela.
     */
    private static final int SPEED = Cenario.BG_SPEED;

    /**
     * Matriz de Colis�o deste objeto.
     */
    private static final boolean[][] MATRIX = {{X, X, X, X, X, X, X, X, X, X, X, X}, {O, O, X, X, X, X, X, X, X, X, X, X}, {O, O, X, X, X, X, X, X, O, O, O, O}, {O, X, X, X, X, X, X, O, O, O, O, O}, {O, X, X, X, X, X, X, O, O, O, O, O}, {O, X, X, X, X, X, X, O, O, O, O, O}};

    /**
     * <p>
     * Construtor padr�o.
     * </p>
     *
     * @param posY
     *     Posi��o do objeto no eixo Y.
     */
    public Helicoptero(final int posY) {
        super(new Point(IRenderable.gameConfig.getGameResolution().width, posY - (Helicoptero.MATRIX.length * Cenario.BLOCK.height / 2)), Helicoptero.SPRITES,
            Helicoptero.SPRITES_INTERVAL, IRenderable.gameUtils.getRandomInt(3) * Helicoptero.SPEED, Helicoptero.MATRIX, CollisionHandler.TYPE_ENEMY, Directions.DIR_LEFT,
            Helicoptero.BONUS, Helicoptero.ENERGY, Definitions.EFF_EXPLOSION_COMMON);
        IRenderable.log.addDebug("NOVO HELICOPTERO CRIADO!", this);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.internal.IAutomatedActor#handleActions()
     */
    @Override
    public void handleActions() {
        if (getFrame() % Helicoptero.FRAMES_TO_SHOT == 0) {
            // dispara tiros simples a cada dois segundos
            GameFactory.getInstance()
                .getActors()
                .add(new TiroInimigo(new Point(getPosition().x, getPosition().y + getSize().height / 2 - 5)), GraphicObjectsList.LAYER_SHOTS);
        }
    }
}
