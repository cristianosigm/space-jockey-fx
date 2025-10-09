package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.IWeaponItem;
import com.cs2tech.old.jshooter.gameElements.SItem;

/**
 * <p>
 * Item do tipo arma padr�o, usada apenas na inicializa��o (n�o � renderizada).
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class DefaultWeapon extends SItem implements IWeaponItem {

    /**
     * Efeito sonoro executado quando o jogador captura o item.
     */
    private static final byte SND_EFF = 0;

    /**
     * Matriz de colis�o do item.
     */
    private static final boolean[][] MATRIX = {{CollisionObject.X}};

    /**
     * N�mero de pontos adicionais dados ao jogador quando ele captura este
     * item.
     */
    private static final int BONUS = 0;

    /**
     * Valor do item para tratamento interno.
     */
    private static final byte VALUE = 1;

    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] IMAGES = {0};

    /**
     * Intervalo de tempo entre as trocas de sprites.
     */
    private static final byte SPRITE_INTERVAL = 0;

    /**
     * Velocidade desta nave na tela.
     */
    private static final int SPEED = 200;

    /**
     * Intervalo m�nimo entre os tiros para esta arma.
     */
    private static final int MIN_MILIS_SHOT_INTERVAL = 500;

    /**
     * Intervalo m�ximo entre os tiros para esta arma.
     */
    private static final int MAX_MILIS_SHOT_INTERVAL = 600;

    /**
     * Construtor padr�o.
     */
    public DefaultWeapon() {
        super(DefaultWeapon.IMAGES, DefaultWeapon.SPRITE_INTERVAL, DefaultWeapon.SPEED, DefaultWeapon.MATRIX, Directions.DIR_DOWN, DefaultWeapon.BONUS, SItem.TYPE_WEAPON_M,
            DefaultWeapon.VALUE, DefaultWeapon.SND_EFF);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.IWeaponItem#getMaxMilisShotInterval()
     */
    @Override
    public int getMaxMilisShotInterval() {
        return DefaultWeapon.MAX_MILIS_SHOT_INTERVAL;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.IWeaponItem#getMinMilisShotInterval()
     */
    @Override
    public int getMinMilisShotInterval() {
        return DefaultWeapon.MIN_MILIS_SHOT_INTERVAL;
    }
}
