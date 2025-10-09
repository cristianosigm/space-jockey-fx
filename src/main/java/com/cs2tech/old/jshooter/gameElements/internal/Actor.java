/**
 * <p>
 * The Elements package contains all basic game element superclasses.
 * </p>
 * <p>
 * All classes in this package are abstract, so the game programmer cannot
 * instantiate them directly; the programmer need to inherit the class and
 * adjust it according to your needs.
 * </p>
 */
package com.cs2tech.old.jshooter.gameElements.internal;

import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;

import java.awt.*;

/**
 * <p>
 * Esta superclasse implementa todos os comportamentos e atributos
 * compartilhados ou define os padraes utilizados pelos atores do jogo, como
 * invencibilidade, movimentaaao, etc.
 * </P>
 * <p>
 * Adicionalmente, os limites de movimentaaao do ator podem ser definidos
 * atravas dos matodos <b>setLimitUL(Point)</b> e <b>setLimitLR(Point)</b>. Se
 * isto for feito, os movimentos do ator serao executados somente enquanto ele
 * estiver dentro do retangulo formado pelos dois pontos informados. Caso nao
 * sejam informados, o objeto nao tera nenhuma limitaaao de movimento.
 * </p>
 * <p>
 * Esta superclasse herda <b>CollisionObject</b>, habilitando todos os
 * comportamentos de colisao e foraando automaticamente a todas as classes
 * filhas a implementar a interface <b>IHitable</b>.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class Actor extends CollisionObject {

    /**
     * Energia do ator. Se chegar a 0, o ator sera destruado.
     */
    private int energy;

    /**
     * Define o ator como invencavel.
     */
    private boolean invincible = false;

    /**
     * Limite de movimentaaao - canto superior esquerdo.
     */
    private Point limitUL = null;

    /**
     * Limite de movimentaaao - canto inferior.
     */
    private Point limitLR = null;

    /**
     * Informa que o jogador esta se movendo para CIMA
     */
    private boolean movingUp;

    /**
     * Informa que o jogador esta se movendo para BAIXO
     */
    private boolean movingDown;

    /**
     * Informa que o jogador esta se movendo para ESQUERDA
     */
    private boolean movingLeft;

    /**
     * Informa que o jogador esta se movendo para DIREITA
     */
    private boolean movingRight;

    /**
     * Informa que o jogador esta executando a aaao A
     */
    private boolean actingA;

    /**
     * Informa que o jogador esta executando a aaao B
     */
    private boolean actingB;

    /**
     * Informa que o jogador esta executando a aaao C
     */
    private boolean actingC;

    /**
     * Informa que o jogador esta executando a aaao D
     */
    private boolean actingD;

    /**
     * Construtor padrao.
     *
     * @param initialPos
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
     * @param energy
     *     Energia (padrao) da nave do jogador.
     */
    public Actor(final Point position, final int[] sprites, final byte spriteInterval, final byte speed, final boolean[][] collisionMatrix, final byte collisionType,
                 final byte energy) {
        super(position, sprites, spriteInterval, speed, collisionMatrix, collisionType);

        this.energy = energy;
    }

    /**
     * @return O valor atual de energy
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * @param energy
     *     Redefine o valor de energy para o valor do parametro.
     */
    public void setEnergy(final int energy) {
        this.energy = energy;
    }

    /**
     * @return O valor atual de limitLR
     */
    public Point getLimitLR() {
        return limitLR;
    }

    /**
     * @param limitLR
     *     Redefine o valor de limitLR para o valor do parametro.
     */
    public void setLimitLR(final Point limitLR) {
        this.limitLR = limitLR;
    }

    /**
     * @return O valor atual de limitUL
     */
    public Point getLimitUL() {
        return limitUL;
    }

    /**
     * @param limitUL
     *     Redefine o valor de limitUL para o valor do parametro.
     */
    public void setLimitUL(final Point limitUL) {
        this.limitUL = limitUL;
    }

    /**
     * @return O valor atual de actingA
     */
    public boolean isActingA() {
        return actingA;
    }

    /**
     * @param actingA
     *     Redefine o valor de actingA para o valor do parametro.
     */
    public void setActingA(final boolean actingA) {
        this.actingA = actingA;
    }

    /**
     * @return O valor atual de actingB
     */
    public boolean isActingB() {
        return actingB;
    }

    /**
     * @param actingB
     *     Redefine o valor de actingB para o valor do parametro.
     */
    public void setActingB(final boolean actingB) {
        this.actingB = actingB;
    }

    /**
     * @return O valor atual de actingC
     */
    public boolean isActingC() {
        return actingC;
    }

    /**
     * @param actingC
     *     Redefine o valor de actingC para o valor do parametro.
     */
    public void setActingC(final boolean actingC) {
        this.actingC = actingC;
    }

    /**
     * @return O valor atual de actingD
     */
    public boolean isActingD() {
        return actingD;
    }

    /**
     * @param actingD
     *     Redefine o valor de actingD para o valor do parametro.
     */
    public void setActingD(final boolean actingD) {
        this.actingD = actingD;
    }

    /**
     * @return O valor atual de invincible
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * @param invincible
     *     Redefine o valor de invincible para o valor do parametro.
     */
    public void setInvincible(final boolean invincible) {
        if (gameConfig.isInvincible()) {
            // cheat de invencibilidade
            this.invincible = true;
        } else {
            this.invincible = invincible;
        }
    }

    /**
     * @return O valor atual de movingDown
     */
    public boolean isMovingDown() {
        return movingDown;
    }

    /**
     * @param movingDown
     *     Redefine o valor de movingDown para o valor do parametro.
     */
    public void setMovingDown(final boolean movingDown) {
        this.movingDown = movingDown;
    }

    /**
     * @return O valor atual de movingLeft
     */
    public boolean isMovingLeft() {
        return movingLeft;
    }

    /**
     * @param movingLeft
     *     Redefine o valor de movingLeft para o valor do parametro.
     */
    public void setMovingLeft(final boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * @return O valor atual de movingRight
     */
    public boolean isMovingRight() {
        return movingRight;
    }

    /**
     * @param movingRight
     *     Redefine o valor de movingRight para o valor do parametro.
     */
    public void setMovingRight(final boolean movingRight) {
        this.movingRight = movingRight;
    }

    /**
     * @return O valor atual de movingUp
     */
    public boolean isMovingUp() {
        return movingUp;
    }

    /**
     * @param movingUp
     *     Redefine o valor de movingUp para o valor do parametro.
     */
    public void setMovingUp(final boolean movingUp) {
        this.movingUp = movingUp;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(byte)
     */
    @Override
    public void takeHit(final int points) {
        energy -= points;
        if (energy <= 0) {
            die();
        }
    }
}
