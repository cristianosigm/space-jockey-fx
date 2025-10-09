package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.graphics.BasicGraphics;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.ImageRepository;
import com.cs2tech.old.jshooter.gameElements.internal.MenuOption;

import java.awt.*;

/**
 * <p>
 * Superclasse para os menus de opaaes do jogo.
 * </p>
 * <p>
 * Para criar um menu, o programador de jogos deve herdar esta classe, criar uma
 * lista de objetos da classe <code>MenuOptions</code> e passa-la ao construtor.
 * Cada opaao deve ter o tatulo da opaao (a ser exibido no menu) e seus valores
 * selecionaveis (veja <code>MenuOptions</code>).
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SMenu extends BasicGraphics {

    /**
     * Frames antes de exibir a tela efetivamente.
     */
    private static final int BLACKSCREEN_FRAMES = IRenderable.gameUtils.getNFrames(1);

    /**
     * Topo do tatulo do menu.
     */
    private static final int TOP_TITLE = (IRenderable.gameConfig.getGameResolution().width == 800 ? 80 : 70);

    /**
     * Base do item de saada do menu.
     */
    private static final int BASE_EXIT = (IRenderable.gameConfig.getGameResolution().width == 800 ? 65 : 40);

    /**
     * Base do quadro de destaque do item de saada do menu.
     */
    private static final int BASE_EXIT_DET = (IRenderable.gameConfig.getGameResolution().width == 800 ? 95 : 70);

    /**
     * Topo da primeira opaao de menu.
     */
    private static final int TOP_OPTION = (IRenderable.gameConfig.getGameResolution().width == 800 ? 120 : 100);

    /**
     * Espaao entre as opaaes de menu
     */
    private static final int SPACE_OPTION = (IRenderable.gameConfig.getGameResolution().width == 800 ? 18 : 12);

    /**
     * Imagem de background do menu.
     */
    private final int background;

    /**
     * Lista de opaaes disponaveis na tela.
     */
    private final MenuOption[] options;
    /**
     * Fonte usada para renderizar o tatulo.
     */
    private final Font fntTitle = new Font("Serif", Font.BOLD, 60);
    /**
     * Fonte usada para renderizar as opaaes.
     */
    private final Font fntOption = new Font("Serif", Font.BOLD, 30);
    /**
     * Fonte usada para renderizar os valores de cada opaao.
     */
    private final Font fntValue = new Font("Serif", Font.BOLD + Font.ITALIC, 25);
    /**
     * Tatulo do menu de opaaes.
     */
    private String menuTitle;
    /**
     * Armazena a opaao atualmente selecionada.
     */
    private byte selected;
    /**
     * Indica se o menu foi finalizado e, portanto, o gerenciador de fluxo pode
     * fecha-lo e passar ao praximo item do fluxo.
     */
    private boolean finished;
    /**
     * Indica que a opaao de saada do menu foi selecionada. Exibe uma tela em
     * branco por alguns frames e depois informa a finalizaaao do menu.
     */
    private boolean exitSelected;
    /**
     * Atributo de controle do tempo de espera de saada.
     */
    private int currentWaitingFrame;
    /**
     * Opaao extra para sair deste menu.
     */
    private byte optExit;
    /**
     * Espaao entre as opaaes de menu, em pixels.
     */
    private int spaceBTWOptions = 0;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     * <p>
     * Use o parametro options para informar um vetor de objetos da classe
     * <code>MenuOption</code>, contendo todas as opaaes e valores disponaveis.
     * O namero maximo de opaaes suportado nesta versao a <b>8</b> (se precisar
     * de mais de 8 opaaes, use submenus).
     * </p>
     * <p>
     * Cada objeto da classe <code>MenuOption</code> tem uma lista interna de
     * valores selecionaveis. O namero maximo de caracteres para cada opaao a
     * <b>25</b>, e o namero maximo de valores selecionaveis a <b>30</b>.
     * </p>
     *
     * @param options
     *     Vetor de opaaes.
     * @param imgBackgroundIndex
     *     andice da imagem de background.
     * @param title
     *     Tatulo do menu.
     */
    public SMenu(final MenuOption[] options, final int imgBackgroundIndex, final String title) {
        background = imgBackgroundIndex;
        this.options = options;
        menuTitle = title;
        if (this.options != null) {
            optExit = (byte) this.options.length;
            // calculando o espaco entre as opcoes do menu
            spaceBTWOptions = ((IRenderable.gameConfig.getGameResolution().height - SMenu.TOP_OPTION) / this.options.length) - SMenu.SPACE_OPTION;
        }
        // iniciando
        selected = 0;
        setFinished(false);
        exitSelected = false;
    }

    /**
     * Renderiza o objeto na porta grafica principal.
     *
     * @param gfx
     *     Buffer da porta grafica.
     * @param cnv
     *     Canvas principal.
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        if (!exitSelected) {
            // renderizando o background
            gfx.drawImage(ImageRepository.getInstance()
                .getImage(background), 0, 0, cnv.getSize().width, cnv.getSize().height, cnv);
            // tatulo do menu
            if (menuTitle.length() > 0) {
                gfx.setColor(Color.DARK_GRAY);
                gfx.setFont(fntTitle);
                gfx.drawString(menuTitle, IRenderable.gameConfig.getGameResolution().width / 2 - ((menuTitle.length() / 2) * 45) + 5, SMenu.TOP_TITLE + 5);
                gfx.setColor(Color.BLUE);
                gfx.setFont(fntTitle);
                gfx.drawString(menuTitle, (IRenderable.gameConfig.getGameResolution().width / 2 - (menuTitle.length() / 2) * 45), SMenu.TOP_TITLE);
            }

            // renderizando as opcoes
            for (byte x = 0; x < options.length; x++) {
                if ((selected < optExit) && (selected == x)) {
                    // destacando a opaao selecionada
                    gfx.setColor(Color.BLUE);
                    gfx.drawRect(50, SMenu.TOP_OPTION + (spaceBTWOptions * x), IRenderable.gameConfig.getGameResolution().width - 100, 40);
                    gfx.setColor(Color.WHITE);
                } else {
                    gfx.setColor(Color.GRAY);
                }
                // opaao atual
                gfx.setFont(fntOption);
                gfx.drawString(options[x].getTitle(), 100, SMenu.TOP_OPTION + 30 + (spaceBTWOptions * x));
                // valor atual
                gfx.setFont(fntValue);
                gfx.drawString("<< " + options[x].getSelected() + " >>", 400, SMenu.TOP_OPTION + 28 + (spaceBTWOptions * x));
            }

            // renderizando a opaao SAIR
            if (selected == optExit) {
                gfx.setColor(Color.RED);
                gfx.drawRect(IRenderable.gameConfig.getGameResolution().width / 2 - 50, IRenderable.gameConfig.getGameResolution().height - SMenu.BASE_EXIT_DET, 100, 40);
                gfx.setColor(Color.YELLOW);
            } else {
                gfx.setColor(Color.RED);
            }
            gfx.setFont(fntOption);
            gfx.drawString("SAIR", IRenderable.gameConfig.getGameResolution().width / 2 - 36, IRenderable.gameConfig.getGameResolution().height - SMenu.BASE_EXIT);
        } else {
            gfx.setColor(Color.BLACK);
            gfx.fillRect(0, 0, IRenderable.gameConfig.getGameResolution().width, IRenderable.gameConfig.getGameResolution().width);
            if (currentWaitingFrame < SMenu.BLACKSCREEN_FRAMES) {
                currentWaitingFrame++;
            } else {
                setFinished(true);
            }
        }
    }

    /**
     * @return O valor atual de options.
     */
    public MenuOption[] getOptions() {
        return options;
    }

    /**
     * @return O valor atual de selected.
     */
    public byte getSelected() {
        return selected;
    }

    /**
     * @param selected
     *     Redefine o valor de selected para o valor informado por
     *     parametro.
     */
    public void setSelected(final byte selected) {
        this.selected = selected;
    }

    /**
     * @return O valor atual de finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @param finished
     *     Redefine o valor de finished para o valor informado por
     *     parametro.
     */
    public void setFinished(final boolean finished) {
        this.finished = finished;
    }

    /**
     * Seleciona a praxima opaao de menu.
     */
    public void next() {
        if (selected < options.length) {
            selected++;
        } else {
            selected = 0;
        }
    }

    /**
     * Seleciona o praximo valor para a opaao atual.
     */
    public void nextValue() {
        if (selected < optExit) {
            options[selected].next();
        } else {
            // nada a fazer
        }
    }

    /**
     * Seleciona a opaao de menu anterior.
     */
    public void previous() {
        if (selected > 0) {
            selected--;
        } else {
            selected = (byte) options.length;
        }
    }

    /**
     * Seleciona o valor anterior para a opaao atual.
     */
    public void previousValue() {
        if (selected < optExit) {
            options[selected].previous();
        } else {
            // nada a fazer
        }
    }

    /**
     * @param menuTitle
     *     Redefine o valor de menuTitle para o valor informado por
     *     parametro.
     */
    public void setMenuTitle(final String menuTitle) {
        this.menuTitle = menuTitle;
    }

    /**
     * Botao de aaao pressionado (seleciona ou muda a opaao atual).
     */
    public void startAct() {
        if (selected < optExit) {
            // nada a fazer
        } else {
            save();
            exitSelected = true;
        }
    }

    /**
     * Salva o status atual do menu.
     */
    public abstract void save();

    /**
     * Botao de aaao liberado (<i>nao utilizado nesta versao</i>).
     */
    public void stopAct() {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#terminate()
     */
    @Override
    public void terminate() {
        // parando a musica de fundo
        audio.stopMusic();
        try {
            // finalizando a instancia
            finalize();
        } catch (final Throwable e) {
            GameLogger.getInstance()
                .addError("Erro ao tentar apagar o estagio ID = " + getId() + ": " + e.getMessage(), new Exception(e), this);
        }
    }
}
