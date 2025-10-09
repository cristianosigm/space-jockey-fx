/**
 *
 */
package com.cs2tech.old.jshooter.gameElements.effects;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.BasicGraphics;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Superclasse que implementa todos os comportamentos e atributos comuns aos
 * efeitos especiais.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SpecialEffect extends BasicGraphics {

    /**
     * Composiaao opaca.
     */
    private final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f);
    /**
     * Intervalo entre os quadros de animaaao.
     */
    private byte frameDelay;
    /**
     * Quadro de animaaao atual.
     */
    private int frame;
    /**
     * Tamanho do efeito.
     */
    private Dimension size;
    /**
     * Posiaao do efeito.
     */
    private Point position;
    /**
     * Composiaao de transparancia.
     */
    private AlphaComposite transparency = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    /**
     * andice de transparancia.
     */
    private float alpha = 0.5f;

    /**
     * Remove a transparancia atual.
     *
     * @param gfx
     *     Porta grafica em uso.
     */
    protected void applyOpaque(final Graphics2D gfx) {
        gfx.setComposite(opaque);
    }

    /**
     * Aplica a transparancia atual.
     *
     * @param gfx
     *     Porta grafica em uso.
     */
    protected void applyTransparency(final Graphics2D gfx) {
        transparency = transparency.derive(alpha);
        gfx.setComposite(transparency);
    }

    /**
     * @return O valor atual de frame.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * @param frame
     *     Redefine o valor de frame para o valor do padrao.
     */
    public void setFrame(final int frame) {
        this.frame = frame;
    }

    /**
     * @return Valor atual de frameDelay.
     */
    public byte getFrameDelay() {
        return frameDelay;
    }

    /**
     * @param frameDelay
     *     Redefine o valor de frameDelay para o valor do padrao.
     */
    public void setFrameDelay(final byte frameDelay) {
        this.frameDelay = (byte) (Math.round(frameDelay / (GameFactory.BASE_FRAMERATE / IRenderable.gameConfig.getFrameRate())));
    }

    /**
     * @return Valor atual de position
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position
     *     Redefine o valor de position para o valor do padrao.
     */
    public void setPosition(final Point position) {
        this.position = position;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.BasicGraphics#getSize()
     */
    @Override
    public Dimension getSize() {
        return size;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * jShooter.components.graphics.BasicGraphics#setSize(java.awt.Dimension)
     */
    @Override
    public void setSize(final Dimension size) {
        this.size = size;
    }

    /**
     * Praximo quadro de animaaao.
     */
    protected void nextFrame() {
        frame++;
    }

    /**
     * Remove a transparancia atual.
     *
     * @param gfx
     *     Ponteiro para o buffer grafico.
     */
    protected void removeTransparency(final Graphics2D gfx) {
        gfx.setComposite(opaque);
    }

    /**
     * Desliga a transparancia deste objeto.
     */
    protected void setOpaque() {
        alpha = 1.0f;
    }

    /**
     * Modifica o valor atual de transparancia.
     *
     * @param alpha
     *     Novo valor de transparancia, entre <b>0.0f</b> e <b>1.0f</b>,
     *     onde:
     *     <ul>
     *     <li>0.0f = Totalmente transparente; e</li>
     *     <li>1.0f = totalmente opaco.</li>
     *     </ul>
     */
    protected void setTransparent(final float alpha) {
        this.alpha = alpha;
    }
}
