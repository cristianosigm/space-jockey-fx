package com.cs2tech.old.jockey.core;

import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.SShot;

/**
 * <p>
 * Implementa��o do componente de tratamento de colis�o.
 * </p>
 * <p>
 * Este componente � de fato bem simples: ele apenas define, para cada par de
 * objetos que colidiram, quais a��es devem ser tomadas. A detec��o de colis�o �
 * completamente feita pelo Framework.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class MyCollision extends CollisionHandler {

    /*
     * (non-Javadoc)
     *
     * @see
     * com.cs.com.cs2tech.old.collision.kernel.gameCore.jshooter.CollisionHandler#handleCollision(jShooter
     * .gameCore.kernel.collision.CollisionObject,
     * com.cs.com.cs2tech.old.collision.kernel.gameCore.jshooter.CollisionObject)
     */
    @Override
    public void handleCollision(final CollisionObject tested, final CollisionObject collided) {
        if ((tested.getType() == CollisionHandler.TYPE_PLAYER_SHOT) && (collided.getType() == CollisionHandler.TYPE_ENEMY)) {
            // tiro do jogador atingiu advers�rio
            tested.die();
            collided.takeHit(((SShot) tested).getHitPower());
        } else if ((tested.getType() == CollisionHandler.TYPE_ENEMY) && (collided.getType() == CollisionHandler.TYPE_PLAYER_SHOT)) {
            // tiro do jogador atingiu advers�rio
            tested.takeHit(((SShot) collided).getHitPower());
            collided.die();
        } else if ((tested.getType() == CollisionHandler.TYPE_PLAYER) && (collided.getType() == CollisionHandler.TYPE_ENEMY_SHOT)) {
            // jogador tomou um tiro
            tested.takeHit(((SShot) collided).getHitPower());
            collided.die();
        } else if ((tested.getType() == CollisionHandler.TYPE_ENEMY_SHOT) && (collided.getType() == CollisionHandler.TYPE_PLAYER)) {
            // jogador tomou um tiro
            tested.die();
            collided.takeHit(((SShot) tested).getHitPower());
        } else if ((tested.getType() == CollisionHandler.TYPE_PLAYER) && (collided.getType() == CollisionHandler.TYPE_ENEMY)) {
            // jogador e advers�rio colidiram.
            collided.die();
            (tested).takeHit((byte) 127);
        } else if ((tested.getType() == CollisionHandler.TYPE_ENEMY) && (collided.getType() == CollisionHandler.TYPE_PLAYER)) {
            // jogador e advers�rio colidiram.
            (collided).takeHit((byte) 127);
            tested.die();
        } else {
            // colisao ignorada
        }
    }
}
