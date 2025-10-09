/**
 *
 */
package com.cs2tech.old.jshooter.gameElements.effects;

import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Efeito especial "flash de luz".
 * </p>
 * <p>
 * Exibe um brilho na tela semelhante ao de uma explosao, com tempo variavel de
 * acordo com o valor de frameDelay informado no construtor. Quanto maior este
 * valor, mais demorado a o flash de luz.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Flashlight extends SpecialEffect {

    /**
     * Velocidade de transiaao da transparancia.
     */
    private static final float SPEED = 0.05f;

    /**
     * Transparancia.
     */
    private float alpha = 1.0f;

    /**
     * Construtor padrao.
     *
     * @param frameDelay
     *     Intervalo entre os quadros de animaaao no efeito especial.
     *     Quanto maior o valor, mais lento o efeito sera.
     */
    public Flashlight(final byte frameDelay) {
        setFrameDelay(frameDelay);
        setSize(IRenderable.gameConfig.getGameResolution());
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.effects.SpecialEffect#draw(java.awt
     * .Graphics2D, java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        // proximo valor de transparencia
        if (getFrame() == getFrameDelay()) {
            // reduzindo transparencia
            alpha -= Flashlight.SPEED;
            if (alpha > 0) {
                // aplicando transparencia atual
                setTransparent(alpha);
            } else {
                // finalizando efeito
                terminate();
            }
            setFrame(0);
        } else {
            nextFrame();
        }
        // aplicando transparencia
        if (alpha < 1) {
            applyTransparency(gfx);
        }
        // renderizando
        gfx.setColor(Color.WHITE);
        gfx.fillRect(0, 0, getSize().width, getSize().height);
        // voltando ao opaco
        if (alpha < 1) {
            applyOpaque(gfx);
        }
    }
}
