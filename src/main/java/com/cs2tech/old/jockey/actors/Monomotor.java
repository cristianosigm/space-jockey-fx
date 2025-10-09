package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.SShip;

import java.awt.*;

/**
 * <p>
 * Classe que define o advers�rio do tipo Avi�o Monomotor.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Monomotor extends SShip {

    /**
     * Intervalo de tempo entre os disparos desta nave.
     */
    private static final int FRAMES_TO_SHOT = IRenderable.gameUtils.getNFrames(1);

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {21, 22};

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
    private static final int BONUS = 100;

    /**
     * Velocidade desta nave na tela.
     */
    private static final int SPEED = Cenario.BG_SPEED;

    /**
     * Matriz de Colis�o deste objeto.
     */
    private static final boolean[][] MATRIX = {{CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X}, {CollisionObject.O, CollisionObject.O, CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O}};

    /**
     * <p>
     * Construtor padr�o.
     * </p>
     *
     * @param posY
     *     Posi��o do objeto no eixo Y.
     */
    public Monomotor(final int posY) {
        super(new Point(IRenderable.gameConfig.getGameResolution().width, posY - (Monomotor.MATRIX.length * Cenario.BLOCK.height / 2)), Monomotor.SPRITES,
            Monomotor.SPRITES_INTERVAL, IRenderable.gameUtils.getRandomInt(3) * Monomotor.SPEED, Monomotor.MATRIX, CollisionHandler.TYPE_ENEMY, Directions.DIR_LEFT,
            Monomotor.BONUS, Monomotor.ENERGY, Definitions.EFF_EXPLOSION_COMMON);
        IRenderable.log.addDebug("NOVO MONOMOTOR CRIADO!", this);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.internal.IAutomatedActor#handleActions()
     */
    @Override
    public void handleActions() {
        if (getFrame() % Monomotor.FRAMES_TO_SHOT == 0) {
            // dispara tiros simples a cada dois segundos
            GameFactory.getInstance()
                .getActors()
                .add(new TiroInimigo(new Point(getPosition().x, getPosition().y + getSize().height / 2 - 5)), GraphicObjectsList.LAYER_SHOTS);
        }
    }
}
