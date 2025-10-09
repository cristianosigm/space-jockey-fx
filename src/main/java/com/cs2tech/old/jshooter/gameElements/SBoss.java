package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa um chefe de fase.
 * </p>
 *
 * @author Cristiano
 * @version 1.0
 */
public abstract class SBoss extends SShip {

    /**
     * Duracao da animaaao (explosao) antes da destruicao do objeto.
     */
    private static final int DEATH_ANI_DURATION = IRenderable.gameUtils.getNFrames(5);
    /**
     * Som que indica tiro sem efeito.
     */
    private final int effInvincible;
    /**
     * Indica que o chefe esta invencivel no momento.
     */
    private boolean invincible;
    /**
     * Atributo de controle da animaaao de destruicao do chefe.
     */
    private int deadInterval = 0;
    /**
     * Chave para permitir ou nao que o chefe atire.
     */
    private boolean allowShoot = false;
    /**
     * Indica se o chefe esta ou nao morto.
     */
    private boolean dead = false;
    /**
     * <b>True</b>Indica que esse chefe a o altimo chefe do estagio onde ele foi
     * setado. <b>False</b> indica que ele a apenas um subchefe.
     */
    private boolean finalBoss = false;

    /**
     * <p>
     * Construtor principal.
     * </p>
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
     * @param finalBoss
     *     <b>True</b>Indica que esse chefe a o altimo chefe do estagio
     *     onde ele foi setado. <b>False</b> indica que ele a apenas um
     *     subchefe.
     * @param effInvincible
     *     Efeito sonoro executado quando o objeto a atingido por um
     *     tiro, poram esta invencavel.
     */
    public SBoss(final Point position, final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final byte direction, final int bonus,
                 final int energy, final byte soundExplosion, final boolean isFinalBoss, final byte effInvincible) {
        super(position, sprites, spriteInterval, speed, matrix, CollisionHandler.TYPE_BOSS, direction, bonus, energy, soundExplosion);
        IRenderable.gameStatus.setAtBoss(true);
        setFinalBoss(isFinalBoss);
        this.effInvincible = effInvincible;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SShip#die()
     */
    @Override
    public void die() {
        IRenderable.log.addWarning("Boss morreu! Executando animaaao...", this);
        setMovementEnabled(false);
        setAllowShoot(false);
        setInvincible(true);
        setDead(true);
        if (finalBoss) {
            // chefe de fase morreu. Paralisa o player e seta o estagio como
            // finalizado.
            GameFactory.getInstance()
                .getCurrentStage()
                .setFinishing(true);
            GameFactory.getInstance()
                .getCurrentStage()
                .setVictory(true);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SShip#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        super.draw(gfx, cnv);
        if (isDead() && (deadInterval < (SBoss.DEATH_ANI_DURATION - IRenderable.gameUtils.getNFrames(1)))) {
            setInvincible(true);
            // para evitar que ele morra de novo; continua renderizando a nave
            // parada
            deadInterval++;
            IRenderable.log.addWarning("animaaao da morte do boss. Frame = " + deadInterval, this);
        } else if (isDead() && (deadInterval == (SBoss.DEATH_ANI_DURATION - IRenderable.gameUtils.getNFrames(1)))) {
            // criando a explosao
            GameFactory.getInstance()
                .createSpecialEffect(GameFactory.EFF_BOSS_EXPLOSION, this);
            // som
            audio.playSnd(getSoundExplosion());
            deadInterval++;
        } else if (isDead() && (deadInterval < SBoss.DEATH_ANI_DURATION)) {
            deadInterval++;
        } else if (isDead()) {
            // animaaao acabou. Flash na tela e apaga a instancia
            IRenderable.log.addWarning("animaaao da morte do boss terminou. Eliminando o dito cujo.", this);
            // GameFactory.getInstance().createSpecialEffect(GameFactory.EFF_FLASHLIGHT,
            // this);
            IRenderable.gameStatus.setAtBoss(false);
            super.die();
        }
    }

    /**
     * @return the dead
     */
    protected boolean isDead() {
        return dead;
    }

    /**
     * @param dead
     *     the dead to set
     */
    protected void setDead(final boolean dead) {
        this.dead = dead;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(int)
     */
    @Override
    public void takeHit(final int hitPoints) {
        if (isInvincible()) {
            // executar o som padrao para nave invencivel
            audio.playSnd(effInvincible);
        } else {
            super.takeHit(hitPoints, true);
        }
    }

    /**
     * @return the invincible
     */
    protected boolean isInvincible() {
        return invincible;
    }

    /**
     * @param invincible
     *     the invincible to set
     */
    protected void setInvincible(final boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * @return the allowShoot
     */
    protected boolean isAllowShoot() {
        return allowShoot;
    }

    /**
     * @param allowShoot
     *     the allowShoot to set
     */
    protected void setAllowShoot(final boolean allowShoot) {
        this.allowShoot = allowShoot;
    }

    /**
     * @return the finalBoss
     */
    protected boolean isFinalBoss() {
        return finalBoss;
    }

    /**
     * @param finalBoss
     *     the value to be set.
     */
    protected void setFinalBoss(final boolean isFinalBoss) {
        finalBoss = isFinalBoss;
    }
}
