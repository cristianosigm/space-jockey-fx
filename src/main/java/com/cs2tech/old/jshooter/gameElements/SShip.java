package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.TransitoryGraphicObject;
import com.cs2tech.old.jshooter.gameElements.internal.IAutomatedActor;

import java.awt.*;

public abstract class SShip extends TransitoryGraphicObject implements IAutomatedActor {

    /**
     * Pontuaaao dada ao jogador quando este item for capturado.
     */
    private final int bonus;
    /**
     * Efeito sonoro a ser executado quando a nave explodir.
     */
    private byte soundExplosion = -1;
    /**
     * Energia deste objeto. Se chegar a 0, o objeto sera destruado.
     */
    private int energy;

    /**
     * Construtor padrao.
     *
     * @param position
     *     Posiaao inicial do objeto na tela.
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em frames) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em pixels/seg).
     * @param matrix
     *     Matriz de colisao para este objeto.
     * @param collisionType
     *     Tipo de colisao para este objeto (utilize os atributos
     *     estaticos <b>CollisionHandler.<i>TYPE_*</i></b> para
     *     selecionar um valor valido).
     * @param direction
     *     Direaao de movimento deste objeto (utilize os atributos
     *     estaticos <b>GameFactory.<i>DIR_*</i></b> para selecionar um
     *     valor valido).
     * @param bonus
     *     Pontuaaao dada ao jogador se ele destruir este objeto.
     * @param energy
     *     Energia deste objeto.
     */
    public SShip(final Point position, final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final byte collisionType, final byte direction,
                 final int bonus, final int energy, final byte soundExplosion) {
        super(position, sprites, spriteInterval, speed, matrix, collisionType, direction);
        this.bonus = bonus;
        this.energy = energy;
        this.soundExplosion = soundExplosion;
    }

    /**
     * <p>
     * Informa que o objeto foi destruado, poram permite o controle sobre a
     * execuaao da explosao e do efeito sonoro.
     * </p>
     * <p>
     * Isto a necessario, por exemplo, quando o objeto foi destruado por uma
     * bomba.
     * </p>
     *
     * @param renderExplosion
     *     <b>True</b> para solicitar a renderizaaao da explosao, ou
     *     <b>False</b> para excluir o objeto sem explodi-lo.
     */
    public void die(final boolean renderExplosion) {
        if (renderExplosion) {
            GameFactory.getInstance()
                .createSpecialEffect(GameFactory.EFF_EXPLOSION, this);
        }
        terminate();
    }    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#die()
     */
    @Override
    public void die() {
        GameFactory.getInstance()
            .createSpecialEffect(GameFactory.EFF_EXPLOSION, this);
        terminate();
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
        super.draw(gfx, cnv);
        nextFrame();
        handleActions();
    }

    /**
     * @return O valor atual de energy.
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @return O valor atual de soundExplosion
     */
    protected byte getSoundExplosion() {
        return soundExplosion;
    }    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.TransitoryGraphicObject#getBonus()
     */
    @Override
    public int getBonus() {
        return bonus;
    }





    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(int)
     */
    @Override
    public void takeHit(final int hitPoints) {
        takeHit(hitPoints, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(int)
     */
    @Override
    public void takeHit(final int hitPoints, final boolean muted) {
        energy -= hitPoints;
        if (energy <= 0) {
            // executando efeito sonoro
            if (!muted && (soundExplosion > -1)) {
                audio.playSnd(soundExplosion);
            }
            // pontos de bonus para o jogador
            GameFactory.getInstance()
                .getPlayer1()
                .addScore(getBonus());
            // dying
            die();
        }
    }
}
