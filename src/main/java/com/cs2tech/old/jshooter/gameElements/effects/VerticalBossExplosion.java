/**
 *
 */
package com.cs2tech.old.jshooter.gameElements.effects;

import com.cs2tech.old.jshooter.components.graphics.GraphicObject;

import java.awt.*;

/**
 * @author Cristiano Souza
 */
public class VerticalBossExplosion extends SpecialEffect {

    /**
     * Velocidade de transiaao do efeito de transparancia.
     */
    private static final float SPEED = 0.2f;

    /**
     * Transparancia.
     */
    private float alpha = 1.0f;

    /**
     * Tamanho atual da explosao (comprimento).
     */
    private int currWidth;

    /**
     * Tamanho atual da explosao (altura).
     */
    private int currHeight;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     *
     * @param obj
     *     Elemento que ira gerar a explosao.
     * @param frameDelay
     *     Intervalo entre os quadros de animaaao no efeito especial.
     *     Quanto maior o valor, mais lento o efeito sera.
     */
    public VerticalBossExplosion(final GraphicObject obj, final byte frameDelay) {
        super();
        setFrameDelay(frameDelay);
        setSize(new Dimension(obj.getSize().width, obj.getSize().width / 2));
        setPosition(new Point(obj.getPosition().x, 6 * (obj.getPosition().y / 4)));
        currWidth = obj.getSize().width;
        currHeight = currWidth / 2;
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
            alpha -= VerticalBossExplosion.SPEED;
            if (alpha > 0) {
                // aplicando transparencia atual
                setTransparent(alpha);
            } else {
                // finalizando o efeito
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
        // circulo mais baixo
        gfx.setColor(new Color(200, 200, 50));
        gfx.fillOval(getPosition().x, getPosition().y, currWidth, currHeight);
        // circulo do meio
        gfx.setColor(new Color(255, 255, 100));
        gfx.fillOval(getPosition().x + currWidth / 10, getPosition().y + currHeight / 10, 4 * currWidth / 5, 4 * currHeight / 5);
        // circulo superior
        gfx.setColor(Color.WHITE);
        gfx.fillOval(getPosition().x + currWidth / 5, getPosition().y + currHeight / 5, 3 * currWidth / 5, 3 * currHeight / 5);
        // voltando ao opaco
        if (alpha < 1) {
            applyOpaque(gfx);
        }
        // expandindo
        currWidth += 6;
        currHeight += 6;
        getPosition().x -= 3;
        getPosition().y -= 3;
    }
}
