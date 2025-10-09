/**
 *
 */
package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameElements.internal.Background;

import java.awt.*;

/**
 * <p>
 * Superclasse que implementa atributos e comportamentos de um menu especial,
 * com apenas duas opaaes: <b>INICIAR</b> (que inicia o jogo) e <b>OPaaES</b>
 * (que abre o principal menu de opaaes).
 * </p>
 * <p>
 * Esta classe manipula o gerenciamento de frames, o controle de troca de opaaes
 * e as aaaes. Tudo o que a classe filha precisa implementar sao as animaaaes
 * especaficas (se necessario); para isto, o programador deve sobrescrever o
 * matodo <code>draw()</code>, fazendo uma chamada para
 * <code>super.draw()</code> para acionar os comportamentos padrao.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SMainMenu extends SMenu {

    /**
     * Primeira opaao: iniciar o jogo.
     */
    public static byte OPT_START = 0;
    /**
     * Segunda opaao: abrir o menu principal.
     */
    public static byte OPT_OPTIONS = 1;
    /**
     * Largura da tela.
     */
    private final int WIDTH = IRenderable.gameConfig.getGameResolution().width;
    /**
     * Altura da tela.
     */
    private final int HEIGHT = IRenderable.gameConfig.getGameResolution().height;
    /**
     * Gerenciador de background.
     */
    private final Background bkg;
    /**
     * Fonte usada para renderizar as opaaes de menu.
     */
    private final Font fntOptions = new Font("Serif", Font.BOLD, 40);
    /**
     * Indica se o background deve ser renderizado.
     */
    private boolean enableBackground = true;
    /**
     * Frame atual.
     */
    private int frame = 0;
    /**
     * Duraaao da animaaao (quando aplicavel).
     */
    private int length = 0;

    /**
     * Construtor padrao.
     *
     * @param bgImage
     *     andice da imagem de background inicial.
     * @param bgDirection
     *     Direaao do background.
     * @param speed
     *     Velocidade de movimento (em pixels/segundo).
     */
    public SMainMenu(final int bgImage, final byte bgDirection, final byte bgSpeed) {
        super(null, bgImage, "");
        bkg = new Background(bgImage, bgDirection, bgSpeed);
        setSelected(SMainMenu.OPT_START);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        if (enableBackground) {
            // animando background
            bkg.draw(gfx, cnv);
            // renderizando opcoes
            gfx.setFont(fntOptions);
            if (getSelected() == SMainMenu.OPT_START) {
                gfx.setColor(Color.BLUE);
                gfx.drawRect(WIDTH / 3, HEIGHT - 233, WIDTH / 3, 38);
                gfx.setColor(Color.WHITE);
            } else {
                gfx.setColor(Color.GRAY);
            }
            gfx.drawString("INICIO", WIDTH / 2 - 65, HEIGHT - 200);

            if (getSelected() == SMainMenu.OPT_OPTIONS) {
                gfx.setColor(Color.BLUE);
                gfx.drawRect(WIDTH / 3, HEIGHT - 183, WIDTH / 3, 38);
                gfx.setColor(Color.WHITE);
            } else {
                gfx.setColor(Color.GRAY);
            }
            gfx.drawString("OPCOES", WIDTH / 2 - 88, HEIGHT - 150);
        }
        frame++;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#next()
     */
    @Override
    public void next() {
        changeSelected();
    }

    /**
     * Muda a opaao atualmente selecionada.
     */
    private void changeSelected() {
        if (getSelected() == SMainMenu.OPT_START) {
            setSelected(SMainMenu.OPT_OPTIONS);
        } else {
            setSelected(SMainMenu.OPT_START);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#nextValue()
     */
    @Override
    public void nextValue() {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#previous()
     */
    @Override
    public void previous() {
        changeSelected();
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#nextValue()
     */
    @Override
    public void previousValue() {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#startAct()
     */
    @Override
    public void startAct() {
        finish();
    }

    /**
     * Encerra a execuaao deste menu.
     */
    private void finish() {
        setFinished(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#save()
     */
    @Override
    public void save() {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameMenu#startAct()
     */
    @Override
    public void stopAct() {
        finish();
    }

    /**
     * @return O valor atual de frame.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * @param frame
     *     Redefine o valor de frame para o valor informado por
     *     parametro.
     */
    public void setFrame(final int frame) {
        this.frame = frame;
    }

    /**
     * @return O valor atual de length.
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length
     *     Redefine o valor de length para o valor informado por
     *     parametro.
     */
    public void setLength(final int length) {
        this.length = length;
    }

    /**
     * @return O valor atual de enableBackground.
     */
    public boolean isEnableBackground() {
        return enableBackground;
    }

    /**
     * @param enableBackground
     *     Redefine o valor de enableBackground para o valor informado
     *     por parametro.
     */
    public void setEnableBackground(final boolean enableBackground) {
        this.enableBackground = enableBackground;
    }
}
