package com.cs2tech.old.jshooter.gameCore.kernel.collision;

import com.cs2tech.old.jshooter.components.GameLogger;

/**
 * <p>
 * Esta classe a responsavel por manipular as colisaes do jogo.
 * </p>
 * <p>
 * Cada jogo implementa suas praprias aaaes no caso de uma colisao, por isto
 * estas aaaes sao definidas pelo programador de jogos (e nao pelo framework),
 * dentro do matodo <code>handleCollision()</code>.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class CollisionHandler {

    /**
     * Tipo de objeto de colisao = player ship.
     */
    public static byte TYPE_PLAYER = 0;
    /**
     * Tipo de objeto de colisao = player shot.
     */
    public static byte TYPE_PLAYER_SHOT = 1;
    /**
     * Tipo de objeto de colisao = enemy ship.
     */
    public static byte TYPE_ENEMY = 2;
    /**
     * Tipo de objeto de colisao = enemy shot.
     */
    public static byte TYPE_ENEMY_SHOT = 3;
    /**
     * Tipo de objeto de colisao = collidible object (objeto que pode colidir,
     * mas nao pode ser destruado).
     */
    public static byte TYPE_OBJECT = 4;
    /**
     * Tipo de objeto de colisao = destructive object.
     */
    public static byte TYPE_DESTRUCTIVE_OBJECT = 5;
    /**
     * Tipo de objeto de colisao = item.
     */
    public static byte TYPE_ITEM = 6;
    /**
     * Tipo de objeto de colisao = CHEFE.
     */
    public static byte TYPE_BOSS = 7;
    /**
     * Gerenciador de logs.
     */
    protected GameLogger log = GameLogger.getInstance();

    /**
     * <p>
     * Gerencia as colisaes, decidindo que aaaes tomar para cada evento
     * particular de colisao.
     * </p>
     *
     * @param tested
     *     Instancia que chamou o gerenciador de colisao.
     * @param collided
     *     Instancia que colidiu com a que chamou o gerenciador.
     */
    public abstract void handleCollision(CollisionObject tested, CollisionObject collided);
}
