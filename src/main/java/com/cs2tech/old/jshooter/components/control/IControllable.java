package com.cs2tech.old.jshooter.components.control;

/**
 * <p>
 * Esta interface define o padrao de comportamento para todas as classes do
 * jShooter capazes de receber comandos.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public interface IControllable {

    /**
     * Executa a aaao A.
     */
    void actionA();

    /**
     * Executa a aaao B.
     */
    void actionB();

    /**
     * Executa a aaao C.
     */
    void actionC();

    /**
     * Executa a aaao D.
     */
    void actionD();

    /**
     * Move o personagem do jogador para baixo.
     */
    void moveDown();

    /**
     * Move o personagem do jogador para esquerda.
     */
    void moveLeft();

    /**
     * Move o personagem do jogador para direita.
     */
    void moveRight();

    /**
     * Move o personagem do jogador para cima.
     */
    void moveUp();

    /**
     * Interrompe a execuaao da aaao A.
     */
    void stopActionA();

    /**
     * Interrompe a execuaao da aaao B.
     */
    void stopActionB();

    /**
     * Interrompe a execuaao da aaao C.
     */
    void stopActionC();

    /**
     * Interrompe a execuaao da aaao D.
     */
    void stopActionD();

    /**
     * Interrompe o movimento do personagem para baixo.
     */
    void stopMovingDown();

    /**
     * Interrompe o movimento do personagem para esquerda.
     */
    void stopMovingLeft();

    /**
     * Interrompe o movimento do personagem para direita.
     */
    void stopMovingRight();

    /**
     * Interrompe o movimento do personagem para cima.
     */
    void stopMovingUp();
}