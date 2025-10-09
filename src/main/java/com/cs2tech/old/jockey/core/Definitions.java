package com.cs2tech.old.jockey.core;

/**
 * <p>
 * Classe auxiliar para centralizar as defini��es compartilhadas por todo o jogo
 * - por exemplo, efeitos sonoros para eventos comuns, m�sicas, etc.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class Definitions {

    /**
     * Efeito sonoro para tiro do jogador.
     */
    public static final byte EFF_SHOT_P = 0;

    /**
     * Efeito sonoro para explos�o de uma nave comum.
     */
    public static final byte EFF_EXPLOSION_COMMON = 1;

    /**
     * Efeito sonoro para explos�o de um chefe.
     */
    public static final byte EFF_EXPLOSION_PLAYER = 2;

    /**
     * M�sica de fundo da exibi��o do logo.
     */
    public static final byte MUS_LOGO = 0;

    /**
     * M�sica de fundo da introdu��o.
     */
    public static final byte MUS_INTRO = 1;

    /**
     * M�sica de fundo do estagio 1.
     */
    public static final byte MUS_STAGE1 = 2;
}
