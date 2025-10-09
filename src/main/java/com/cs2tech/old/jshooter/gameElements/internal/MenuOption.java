package com.cs2tech.old.jshooter.gameElements.internal;

import com.cs2tech.old.jshooter.components.GameLogger;

/**
 * <p>
 * Classe que implementa uma opaao de menu.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class MenuOption {

    /**
     * Tatulo da opaao.
     */
    private final String title;

    /**
     * Lista de valores selecionaveis para esta opaao.
     */
    private final String[] options;

    /**
     * andice do valor atualmente selecionado.
     */
    private byte selected;

    /**
     * Construtor padrao.
     *
     * @param title
     *     Tatulo da opaao.
     * @param options
     *     Lista de valores selecionaveis
     * @param selected
     *     andice do valor inicialmente selecionado.
     */
    public MenuOption(final String title, final String[] options, final byte selected) {
        this.title = title;
        this.options = options;
        this.selected = selected;
        GameLogger.getInstance()
            .addDebug(
                "Nova opcao de menu adicionada. valores:" + "\n Titulo: " + this.title + "\n Qtde de opcoes: " + this.options.length + "\n Selecionada: " + this.selected + "\n--------------------------------",
                this);
    }

    /**
     * @return Valor (conteado) atualmente selecionado.
     */
    public String getSelected() {
        // GameLogger.getInstance().addWarning(" ---> options: len=" +
        // options.length + "; value=" + options.toString(), this);
        return options[selected];
    }

    /**
     * @return andice do valor atualmente selecionado.
     */
    public byte getSelectedIndex() {
        return selected;
    }

    /**
     * @return Tatulo da opaao.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Seleciona o praximo valor disponavel.
     */
    public void next() {
        if (selected < (options.length - 1)) {
            selected++;
        } else {
            selected = 0;
        }
    }

    /**
     * Seleciona o valor anterior disponavel.
     */
    public void previous() {
        if (selected > 0) {
            selected--;
        } else {
            selected = (byte) (options.length - 1);
        }
    }
}
