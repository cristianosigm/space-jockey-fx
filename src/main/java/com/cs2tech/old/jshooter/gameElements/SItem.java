package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.TransitoryGraphicObject;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;

import java.awt.*;

/**
 * <p>
 * Superclasse que implementa os comportamentos e atributos comuns aos itens do
 * jogo, como armas, velocidade e vidas.
 * </p>
 * <p>
 * <b>NOTA:</b> Os itens de Armas precisam implementar a interface
 * <b><i>IWeaponItem</i></b>.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SItem extends TransitoryGraphicObject {

    /**
     * Arma Machine Gun
     */
    public static byte TYPE_WEAPON_M = 1;

    /**
     * Arma Fireball
     */
    public static byte TYPE_WEAPON_F = 2;

    /**
     * Arma Wide Spread
     */
    public static byte TYPE_WEAPON_W = 3;

    /**
     * Arma Laser
     */
    public static byte TYPE_WEAPON_L = 4;

    /**
     * Arma Spread
     */
    public static byte TYPE_WEAPON_S = 5;

    /**
     * Arma Seeker
     */
    public static byte TYPE_WEAPON_K = 6;

    /**
     * Acrascimo de velocidade
     */
    public static byte TYPE_SPEED = 7;

    /**
     * Acrascimo de velocidade
     */
    public static byte TYPE_BOMB = 8;

    /**
     * Efeito sonoro executado quando um item a capturado.
     */
    private final byte effect;

    /**
     * Pontuaaao dada ao jogador quando este item for capturado.
     */
    private final int bonus;

    /**
     * Tipo de Item
     */
    private final byte itemType;

    /**
     * Valor do Item
     */
    private final byte value;

    /**
     * Efeito de brilho.
     */
    private boolean glow = false;

    /**
     * Construtor padrao.
     *
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em <i>frames</i>) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em <i>pixels/seg</i>).
     * @param matrix
     *     Matriz de colisao para este objeto.
     * @param direction
     *     Direaao de movimento deste objeto (utilize os atributos
     *     estaticos <b>GameFactory.<i>DIR_*</i></b> para selecionar um
     *     valor valido).
     * @param bonus
     *     Pontuaaao dada ao jogador quando este item for capturado.
     * @param itemType
     *     Tipo de item que sera instanciado.
     * @param value
     *     Valor do item.
     * @param effect
     *     andice do efeito sonoro a ser executado quando um item a
     *     capturado.
     */
    public SItem(final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final byte direction, final int bonus, final byte itemType,
                 final byte value, final byte effect) {
        super(sprites, spriteInterval, speed, matrix, CollisionHandler.TYPE_ITEM, direction);
        this.bonus = bonus;
        this.itemType = itemType;
        this.value = value;
        this.effect = effect;
        setTransparent(1.0f);
        if (spriteInterval == 0) {
            setSpriteInterval((byte) 1);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * jShooter.gameCore.kernel.collision.CollisionObject#draw(java.awt.Graphics2D
     * , java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        checkOutOfScreen();
        nextFrame();
        if (glow && (getFrame() % getSpriteInterval() == 0)) {
            if (getAlpha() < 1.0f) {
                setTransparent(getAlpha() + 0.1f);
            } else {
                glow = false;
            }
        } else if (getFrame() % getSpriteInterval() == 0) {
            if (getAlpha() > 0.4f) {
                setTransparent(getAlpha() - 0.1f);
            } else {
                glow = true;
            }
        }
        super.draw(gfx, cnv);
    }    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#die()
     */
    @Override
    public void die() {
        IRenderable.log.addDebug(" > Item capturado ou fora da tela. Auto-destruiaao acionada.", this);
        terminate();
    }

    /**
     * @return O valor atual de itemType.
     */
    public byte getItemType() {
        return itemType;
    }

    /**
     * @return O valor atual de value.
     */
    public byte getValue() {
        return value;
    }    /**
     * @return O valor atual de bonus.
     */
    @Override
    public int getBonus() {
        return bonus;
    }





    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(byte)
     */
    @Override
    public void takeHit(final int hitPower) {
        takeHit(hitPower, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(byte)
     */
    @Override
    public void takeHit(final int hitPower, final boolean noSound) {
        // playing effect
        if (!noSound) {
            audio.playSnd(effect);
        }
        // bonus points to the player
        GameFactory.getInstance()
            .getPlayer1()
            .addScore(getBonus());
        die();
    }
}
