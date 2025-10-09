/**
 *
 */
package com.cs2tech.old.jshooter.gameElements.effects;

import java.awt.*;

/**
 * <p>
 * Efeito especial que desenha um halo tapico de turbinas, dadas a posiaao e o
 * tamanho do halo.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class BoostEffect extends SpecialEffect {

    /**
     * Duraaao de cada pulso.
     */
    private static final int PULSE_LENGHT = 1;

    /**
     * Transparancia do efeito.
     */
    private static final float ALPHA = 0.3f;

    /**
     * Fator de calculo de tamanho.
     */
    private int factor;

    /**
     * Fator de alongamento do halo.
     */
    private int stretch = 0;

    /**
     * Chave para definir se o fator ira alongar ou encolher o halo.
     */
    private boolean inflate = false;

    /**
     * <p>
     * Construtor Principal.
     * </p>
     *
     * @param size
     *     Tamanho do halo.
     * @param position
     *     Posiaao do halo.
     */
    public BoostEffect(final Dimension size, final Point position) {
        super();
        setSize(size);
        setPosition(position);
        setTransparent(BoostEffect.ALPHA);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        // calculando o tamanho atual
        nextFrame();
        if (getFrame() % BoostEffect.PULSE_LENGHT == 0) {
            setFrame(0);
            // pular um passo
            if (inflate) {
                factor -= 1;
            } else {
                factor += 1;
            }
            // recalcular tamanho
            stretch = getSize().height / factor;
            // verificando ciclo
            if (factor < 5) {
                inflate = false;
            } else if (factor > 7) {
                inflate = true;
            }
        }
        // desenhando
        applyTransparency(gfx);
        gfx.setColor(Color.yellow);
        gfx.fillOval(getPosition().x, getPosition().y, getSize().width, getSize().height + stretch);
        gfx.setColor(Color.red);
        gfx.fillOval(getPosition().x + getSize().width / 4, getPosition().y, getSize().width / 2, getSize().height + stretch / 2);
        applyOpaque(gfx);
    }
}
