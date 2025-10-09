package com.cs2tech.old.jshooter.gameElements.effects;

import com.cs2tech.old.jshooter.components.graphics.GraphicObject;

import java.awt.*;

/**
 * <p>
 * Efeito de explosao comum, aplicavel a uma nave ou objeto de qualquer tipo.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class CommonExplosion extends SpecialEffect {

    /**
     * Velocidade de transiaao do efeito.
     */
    private static final float SPEED = 0.2f;

    /**
     * Tempo de cada passo.
     */
    private static final byte FRAME_DELAY = 0;

    /**
     * Transparancia.
     */
    private float alpha = 1.0f;

    /**
     * Tamanho do efeito (quadrado).
     */
    private int currWidth;

    /**
     * Construtor padrao.
     *
     * @param obj
     *     Objeto vinculado ao efeito.
     */
    public CommonExplosion(final GraphicObject obj) {
        super();
        setFrameDelay(CommonExplosion.FRAME_DELAY);
        setSize(new Dimension(obj.getSize().width, obj.getSize().width));
        setPosition(new Point(obj.getPosition()));
        currWidth = obj.getSize().width;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.effects.SpecialEffect#draw(java.awt
     * .Graphics2D, java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        if (getFrame() == getFrameDelay()) {
            alpha -= CommonExplosion.SPEED;
            if (alpha > 0) {
                setTransparent(alpha);
            } else {
                terminate();
            }
            setFrame(0);
        } else {
            nextFrame();
        }
        if (alpha < 1) {
            applyTransparency(gfx);
        }
        // circulo mais baixo
        gfx.setColor(Color.LIGHT_GRAY);
        gfx.fillOval(getPosition().x, getPosition().y, currWidth, currWidth);
        // circulo mais baixo
        gfx.setColor(Color.WHITE);
        gfx.fillOval(getPosition().x + currWidth / 8, getPosition().y + currWidth / 8, 6 * currWidth / 8, 6 * currWidth / 8);
        // circulo central
        gfx.setColor(Color.YELLOW);
        gfx.fillOval(getPosition().x + currWidth / 3, getPosition().y + currWidth / 3, currWidth / 3, currWidth / 3);
        if (alpha < 1) {
            applyOpaque(gfx);
        }
        // expandindo
        currWidth += 2;
        getPosition().x--;
        getPosition().y--;
    }
}
