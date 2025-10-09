/**
 *
 */
package com.cs2tech.old.jshooter.gameElements.effects;

import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Efeito especial "mascara negra".
 * </p>
 * <p>
 * Gradualmente apaga uma cortina totalmente escura, com tempo variavel de
 * acordo com o valor de frameDelay informado no construtor. Quanto maior este
 * valor, mais tempo leva para o fim do efeito.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class FadeFromBlack extends SpecialEffect {

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
    public FadeFromBlack(final byte frameDelay) {
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
        // definindo praximo valor
        if (getFrame() == getFrameDelay()) {
            // reduzindo transparencia
            alpha -= FadeFromBlack.SPEED;
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
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, getSize().width, getSize().height);
        // de volta ao opaco
        if (alpha < 1) {
            applyOpaque(gfx);
        }
    }
}
