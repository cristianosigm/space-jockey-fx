package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.GraphicObject;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa uma bomba, que basicamente a um ataque do jogador que
 * atinge todos os elementos destrutaveis da tela.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SBomb extends GraphicObject {

    /**
     * Poder destrutivo da bomba (quanta energia sera drenada de cada elemento
     * na tela quando a bomba explodir).
     */
    private int hitPower;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     *
     * @param position
     *     Posiaao inicial do objeto na tela.
     * @param size
     *     Tamanho do objeto.
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em frames) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em pixels/seg).
     * @param hitPoints
     *     Poder destrutivo da bomba (quanta energia sera drenada de cada
     *     elemento na tela quando a bomba explodir).
     */
    public SBomb(final Point position, final Dimension size, final int[] sprites, final byte spriteInterval, final int speed, final int hitPoints) {
        super(position, size, sprites, spriteInterval, speed);
        hitPower = hitPoints;
    }

    /**
     * Faz com que a bomba exploda, drenando energia de todos os elementos
     * atualmente na tela e eliminando ela prapria da lista de atores.
     */
    public void explode() {
        // dispara o efeito flash de luz
        GameFactory.getInstance()
            .createSpecialEffect(GameFactory.EFF_FLASHLIGHT, this);
        // drena energia de todos os elementos da tela
        GameFactory.getInstance()
            .getActors()
            .takeHit(getHitPower());
        // elimina da memaria a bomba apas a explosao
        IRenderable.log.addDebug("Bomba explodiu. Eliminando instancia.", this);
        terminate();
    }

    /**
     * @return valor atual de HitPower
     */
    public int getHitPower() {
        return hitPower;
    }

    /**
     * @param hitPower
     *     Novo valor para hitPower.
     */
    public void setHitPower(final int hitPower) {
        this.hitPower = hitPower;
    }
}
