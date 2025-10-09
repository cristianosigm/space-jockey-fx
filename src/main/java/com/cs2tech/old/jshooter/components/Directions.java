/**
 *
 */
package com.cs2tech.old.jshooter.components;

/**
 * <p>
 * Esta classe armazena definiaaes de direaao, podendo ser acessada sem a
 * necessidade de uma instancia.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class Directions {

    /**
     * Direaao = BAIXO.
     */
    public static byte DIR_DOWN = 0;

    /**
     * Direaao = Diagonal BAIXO-ESQUERDA, 45 graus.
     */
    public static byte DIR_DOWNLEFT = 1;

    /**
     * Direaao = Diagonal BAIXO-ESQUERDA, 30 graus.
     */
    public static byte DIR_DOWNLEFT_NARROW = 2;

    /**
     * Direaao = Diagonal BAIXO-ESQUERDA, 60 graus.
     */
    public static byte DIR_DOWNLEFT_WIDE = 3;

    /**
     * Direaao = ESQUERDA.
     */
    public static byte DIR_LEFT = 4;

    /**
     * Direaao = Diagonal ESQUERDA-CIMA, 45 graus.
     */
    public static byte DIR_LEFTUP = 5;

    /**
     * Direaao = Diagonal ESQUERDA-CIMA, 30 graus.
     */
    public static byte DIR_LEFTUP_NARROW = 6;

    /**
     * Direaao = Diagonal ESQUERDA-CIMA, 60 graus.
     */
    public static byte DIR_LEFTUP_WIDE = 7;

    /**
     * Direaao = CIMA.
     */
    public static byte DIR_UP = 8;

    /**
     * Direaao = Diagonal CIMA-DIREITA, 45 graus.
     */
    public static byte DIR_UPRIGHT = 9;

    /**
     * Direaao = Diagonal CIMA-DIREITA, 30 graus.
     */
    public static byte DIR_UPRIGHT_NARROW = 10;

    /**
     * Direaao = Diagonal CIMA-DIREITA, 60 graus.
     */
    public static byte DIR_UPRIGHT_WIDE = 11;

    /**
     * Direaao = DIREITA.
     */
    public static byte DIR_RIGHT = 12;

    /**
     * Direaao = Diagonal DIREITA-BAIXO, 45 graus.
     */
    public static byte DIR_RIGHTDOWN = 13;

    /**
     * Direaao = Diagonal DIREITA-BAIXO, 30 graus.
     */
    public static byte DIR_RIGHTDOWN_NARROW = 14;

    /**
     * Direaao = Diagonal DIREITA-BAIXO, 60 graus.
     */
    public static byte DIR_RIGHTDOWN_WIDE = 15;

    /**
     * Direaao = Nenhuma
     */
    public static byte DIR_NONE = 127;
}
