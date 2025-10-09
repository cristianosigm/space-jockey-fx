package com.cs2tech.old.jshooter.gameElements.internal;

import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameElements.SMovie;

import java.awt.*;

/**
 * <p>
 * Animaaao inicial para apresentar o Framework.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class MadeWithJShooter extends SMovie {

    /**
     * Tempo de exibiaao da animaaao.
     */
    private static final int LENGTH = IRenderable.gameUtils.getNFrames(5);

    /**
     * Cabeaalho.
     */
    private final String TXT_HEADER = "Este jogo foi criado usando";

    /**
     * Rodapa.
     */
    private final String TXT_FOOTER = "f . r . a . m . e . w . o . r . k";

    /**
     * Posiaao da base.
     */
    private final int BASE_MAIN = SMovie.colBlocksToPixels_Height(32);

    /**
     * Centro da tela.
     */
    private final int SCREEN_HCENTER = IRenderable.gameConfig.getGameResolution().width / 2;

    /**
     * Tamanho do bloco de colisao.
     */
    private final int BLOCK_SIZE = SMovie.colBlocksToPixels_Height(9);

    /**
     * Fonte do cabeaalho.
     */
    private final Font fntHeader = new Font("Serif", Font.BOLD, SMovie.colBlocksToPixels_Height(3.6f));

    /**
     * Fonte do rodapa.
     */
    private final Font fntFooter = new Font("Serif", Font.PLAIN, SMovie.colBlocksToPixels_Height(2.4f));

    /**
     * Fonte do logotipo.
     */
    private final Font fntMain = new Font("Serif", Font.BOLD, SMovie.colBlocksToPixels_Height(14));

    /**
     * <p>
     * Construtor padrao.
     * </p>
     */
    public MadeWithJShooter() {
        setLenght(MadeWithJShooter.LENGTH);
    }

    /*
     * (non-Javadoc)
     * @see jShooter.gameElements.SMovie#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        // bkg
        gfx.setColor(Color.BLACK);
        gfx.fillRect(0, 0, IRenderable.gameConfig.getGameResolution().width, IRenderable.gameConfig.getGameResolution().width);
        // header
        gfx.setColor(Color.DARK_GRAY);
        gfx.setFont(fntHeader);
        gfx.drawString(TXT_HEADER, SCREEN_HCENTER - (int) (2.4 * BLOCK_SIZE), 50);
        // shadow
        gfx.setFont(fntMain);
        gfx.drawString("O", SCREEN_HCENTER - (int) (1.5 * BLOCK_SIZE), BASE_MAIN - (BLOCK_SIZE / 6) + 5);
        gfx.drawString("O", SCREEN_HCENTER - (BLOCK_SIZE / 3), BASE_MAIN + (BLOCK_SIZE / 6) + 5);

        // jshooter
        gfx.setColor(Color.GREEN);
        gfx.drawString("j", SCREEN_HCENTER - (4 * BLOCK_SIZE), BASE_MAIN + (BLOCK_SIZE / 2));
        gfx.setColor(Color.WHITE);
        gfx.drawString("SH", SCREEN_HCENTER - (int) (3.5 * BLOCK_SIZE), BASE_MAIN);

        gfx.setColor(Color.RED);
        gfx.drawString("O", SCREEN_HCENTER - (int) (1.5 * BLOCK_SIZE), BASE_MAIN - (BLOCK_SIZE / 6));
        gfx.setColor(Color.YELLOW);
        gfx.drawString("O", SCREEN_HCENTER - (BLOCK_SIZE / 3), BASE_MAIN + (BLOCK_SIZE / 6));

        gfx.setColor(Color.WHITE);
        gfx.drawString("TER", SCREEN_HCENTER + (8 * BLOCK_SIZE / 9), BASE_MAIN);
        // footer
        gfx.setColor(Color.MAGENTA);
        gfx.setFont(fntFooter);
        gfx.drawString(TXT_FOOTER, SCREEN_HCENTER - (int) (1.4 * BLOCK_SIZE), IRenderable.gameConfig.getGameResolution().height - 50);
        super.draw(gfx, cnv);
    }
}
