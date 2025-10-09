package com.cs2tech.old.jockey.movies;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameElements.SMovie;

import java.awt.*;

/**
 * <p>
 * Anima��o de abertura do jogo, exibida antes de iniciar o jogo em si.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public final class Abertura extends SMovie {

    /**
     * Comprimento da �rea de tela usada pela anima��o.
     */
    private static final int WIDTH = IRenderable.gameConfig.getGameResolution().width;
    /**
     * Altura da �rea de tela usada pela anima��o.
     */
    private static final int HEIGHT = IRenderable.gameConfig.getGameResolution().height;
    /**
     * Dura��o desta anima��o (em quadros).
     */
    private static final int LENGTH = IRenderable.gameUtils.getNFrames(130);
    /**
     * �ndice da imagem de background da abertura.
     */
    private static final int IMG_BG = 6;
    /**
     * �ndice do logotipo usado na anima��o.
     */
    private static final int IMG_JOCKEY = 25;
    /**
     * Posi��o do logotipo na tela.
     */
    private static final int POS_LOGO_LEFT = (gameConfig.getGameResolution().width - IRenderable.imageRepository.getImage(Abertura.IMG_JOCKEY)
        .getWidth()) / 2;
    /**
     * Posi��o atual do background animado no eixo X.
     */
    private int posX = Abertura.WIDTH;

    /**
     * Posi��o atual do logotipo animado no eixo Y.
     */
    private int posY_JOCKEY = Abertura.HEIGHT;

    /**
     * <p>
     * Construtor Principal.
     * </p>
     */
    public Abertura() {
        super();
        setLenght(Abertura.LENGTH);
        audio.playMusicOnce(Definitions.MUS_INTRO);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SMovie#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        super.draw(gfx, cnv);
        // tela de fundo
        gfx.drawImage(IRenderable.imageRepository.getImage(Abertura.IMG_BG), posX, 0, Abertura.WIDTH, Abertura.HEIGHT, cnv);
        gfx.drawImage(IRenderable.imageRepository.getImage(Abertura.IMG_BG), posX - Abertura.WIDTH, 0, Abertura.WIDTH, Abertura.HEIGHT, cnv);
        // JOCKEY
        gfx.drawImage(IRenderable.imageRepository.getImage(Abertura.IMG_JOCKEY), POS_LOGO_LEFT, posY_JOCKEY, IRenderable.imageRepository.getImage(Abertura.IMG_JOCKEY)
            .getWidth(), IRenderable.imageRepository.getImage(Abertura.IMG_JOCKEY)
            .getHeight(), cnv);
        // deslocando a imagem
        if (posX < 0) {
            posX = Abertura.WIDTH;
        } else {
            posX--;
        }
        if (posY_JOCKEY > 100) {
            posY_JOCKEY--;
        }
    }
}
