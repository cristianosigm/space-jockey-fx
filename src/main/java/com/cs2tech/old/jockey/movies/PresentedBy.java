package com.cs2tech.old.jockey.movies;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameElements.SMovie;

import java.awt.*;

/**
 * <p>
 * Anima��o de apresenta��o do projeto.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class PresentedBy extends SMovie {

    /**
     * �ndice da imagem usada na anima��o.
     */
    private static final byte IMG_LOGO = 3;

    /**
     * Dura��o da anima��o (em quadros).
     */
    private static final int LENGTH = IRenderable.gameUtils.getNFrames(5);

    /**
     * Espa�o de tempo em tela limpa ap�s a execu��o da anima��o (em quadros).
     */
    private static final int BLACK = PresentedBy.LENGTH - IRenderable.gameUtils.getNFrames(1);

    /**
     * <p>
     * Construtor padr�o.
     * </p>
     */
    public PresentedBy() {
        super();
        setLenght(PresentedBy.LENGTH);
        audio.playMusicOnce(Definitions.MUS_LOGO);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameAnimation#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        if (getFrame() < PresentedBy.BLACK) {
            gfx.drawImage(IRenderable.imageRepository.getImage(PresentedBy.IMG_LOGO), 0, 0, IRenderable.gameConfig.getGameResolution().width,
                IRenderable.gameConfig.getGameResolution().height + 2, cnv);
        } else {
            gfx.setColor(Color.BLACK);
            gfx.fillRect(0, 0, IRenderable.gameConfig.getGameResolution().width, IRenderable.gameConfig.getGameResolution().height);
        }
        super.draw(gfx, cnv);
    }
}
