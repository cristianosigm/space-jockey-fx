/**
 *
 */
package com.cs2tech.old.jshooter.components.graphics.lists;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.IHitable;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa a lista de atores do jShooter.
 * </p>
 * <p>
 * Esta lista contam todos os atores do jogo, incluindo tiros, efeitos especiais
 * e elementos animados de cenario.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class ActorsList extends GraphicObjectsList {

    /*
     * (non-Javadoc)
     *
     * @seejShooter.components.graphics.lists.GraphicObjectsList#draw(java.awt.
     * Graphics2D, java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        for (byte i = 0; i <= getLastElement(); i++) {
            if (get(i) != null) {
                // renderizando o objeto
                get(i).draw(gfx, cnv);
            }
        }
    }

    /**
     * <p>
     * Quando uma bomba explode, todos os objetos atualmente instanciados devem
     * ser atingidos.
     * </p>
     * <p>
     * Este matodo distribui o dano causado por uma bomba para todos os atores
     * do jogo que podem ser atingidos.
     * </p>
     *
     * @param hitPower
     *     Poder destrutivo da bomba.
     */
    public void takeHit(final int hitPower) {
        GameFactory.getInstance()
            .getActors()
            .setLimits(GraphicObjectsList.LAYER_ACTORS);
        final byte lFrom = GameFactory.getInstance()
            .getActors()
            .getLayerFrom();
        final byte lTo = GameFactory.getInstance()
            .getActors()
            .getLayerTo();
        GameLogger.getInstance()
            .addWarning(" >>> Varrendo todos os atores e distribuindo impacto da bomba. " + "Limites: de " + lFrom + " ate " + lTo + ".", this);
        for (byte i = lFrom; i < lTo; i++) {
            GameLogger.getInstance()
                .addDebug("testando ator na pos. " + i + ".", this);
            if (get(i) != null) {
                if ((((CollisionObject) get(i)).getType() == CollisionHandler.TYPE_ENEMY) || (((CollisionObject) get(i)).getType() == CollisionHandler.TYPE_BOSS)) {
                    GameLogger.getInstance()
                        .addDebug("ator n.o: " + get(i).getId() + " selecionado para perder " + hitPower + " pontos.", this);
                    // drenando energia de todos os inimigos da tela
                    ((IHitable) get(i)).takeHit(hitPower, true);
                } else {
                    GameLogger.getInstance()
                        .addDebug("ator na pos. " + i + " nao a um inimigo (tipo = " + ((CollisionObject) get(i)).getType() + ").", this);
                }
            } else {
                GameLogger.getInstance()
                    .addDebug("ator na pos. " + i + " a nulo.", this);
            }
        }
        GameLogger.getInstance()
            .addWarning(" >>> Varredura terminada com sucesso.", this);
    }
}
