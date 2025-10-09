/**
 *
 */
package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.BasicGraphics;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Superclasse para todos os filmes do jogo. Implementa as operaaaes basicas de
 * filmes, como iniciar, pular e manipular a sequancia de frames.
 * </p>
 * <p>
 * Para criar um filme, basta herdar esta classe, criar um construtor basico
 * para definir duraaao do filme e reimplementar o matodo <code>draw()</code>
 * para gerenciar as animaaaes.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SMovie extends BasicGraphics {

    /**
     * Frame de animaaao atual, usado para controlar o fluxo de animaaao.
     */
    private int frame;
    /**
     * Duraaao do filme.
     */
    private int lenght;
    /**
     * Indica se o filme foi finalizado.
     */
    private boolean finished;

    /**
     * Construtor padrao.
     */
    public SMovie() {
        frame = 0;
        finished = false;
    }

    /**
     * Calcula um tamanho definido em blocos de colisao para o equivalente em
     * pixels, no eixo Y (altura).
     *
     * @param colBlocks
     *     Namero de blocos de colisao.
     *
     * @return Tamanho equivalente em pixels.
     */
    public static int colBlocksToPixels_Height(final float colBlocks) {
        return Math.round(colBlocks * GameFactory.getInstance()
            .getColision()
            .getCollisionBlockSize().height);
    }

    /**
     * Calcula um tamanho definido em blocos de colisao para o equivalente em
     * pixels, no eixo X (largura).
     *
     * @param colBlocks
     *     Namero de blocos de colisao.
     *
     * @return Tamanho equivalente em pixels.
     */
    public static int colBlocksToPixels_Width(final float colBlocks) {
        return Math.round(colBlocks * GameFactory.getInstance()
            .getColision()
            .getCollisionBlockSize().width);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        // proximo frame do filme
        frame++;
        if (frame > lenght) {
            finished = true;
        }
    }

    /**
     * @return O valor atual de frame.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * @param Redefine
     *     o valor de frame para o valor informado por parametro.
     */
    public void setFrame(final int frame) {
        this.frame = frame;
    }

    /**
     * @return O valor atual de finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * Volta ao estado inicial do filme.
     */
    public void reset() {
        frame = 0;
        finished = false;
    }

    /**
     * Pula o filme, movendo o frame corrente para o final.
     */
    public void skip() {
        setFrame(getLenght());
    }

    /**
     * @return O valor atual de lenght.
     */
    public int getLenght() {
        return lenght;
    }

    /**
     * @param Redefine
     *     o valor de lenght para o valor informado por parametro.
     */
    public void setLenght(final int lenght) {
        this.lenght = lenght;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#terminate()
     */
    @Override
    public void terminate() {
        try {
            //audio.stopMusic();
            finalize();
        } catch (final Throwable e) {
            IRenderable.log.addError("Erro ao tentar apagar o filme ID = " + getId() + ": " + e.getMessage(), new Exception(e), this);
        }
    }
}
