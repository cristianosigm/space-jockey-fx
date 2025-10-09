/**
 *
 */
package com.cs2tech.old.jshooter.components;

import java.awt.*;
import java.util.Random;

/**
 * <p>
 * Esta classe contam algumas operaaaes mais complexas centralizadas e
 * disponibilizadas para uso em toda a aplicaaao.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GameUtils {

    /**
     * Gerador de nameros randamicos.
     */
    private static final Random rnd = new Random();
    /**
     * Caractere separador de paragrafo, para renderizar textos em tela inteira.
     */
    public static char CHR_NEWPARAGRAPH = '#';
    /**
     * Instancia anica de <code>GameUtils</code>.
     */
    private static GameUtils instance = null;
    /**
     * Ponteiro para a configuraaao central do jogo.
     */
    private final GameConfig cfg = GameConfig.getInstance();
    /**
     * Buffer de String utilizado para renderizar textos em tela inteira.
     */
    private final StringBuffer bfr = new StringBuffer();
    /**
     * Posiaao atual do texto
     */
    private int posText = 0;
    /**
     * Contador de paragrafos.
     */
    private int paragraphCounter = 0;

    /**
     * <p>
     * Construtor principal.
     * </p>
     */
    private GameUtils() {
    }

    /**
     * @return A instancia anica de <code>GameUtils</code>.
     */
    public static GameUtils getInstance() {
        if (GameUtils.instance == null) {
            GameUtils.instance = new GameUtils();
        }
        return GameUtils.instance;
    }

    /**
     * Retorna o manimo divisor comum entre dois valores.
     *
     * @param a
     *     O primeiro valor a ser analisado
     * @param b
     *     O segundo valor a ser analisado
     *
     * @return O manimo divisor comum entre eles, ou <b>1</b> se nao for
     * possavel determinar outro divisor comum.
     */
    public int getCommonDivisor(final long a, final long b) {
        int res = 1;
        long tA = a;
        long tB = b;
        boolean finished = false;

        while (!finished) {
            finished = true;
            for (long i = 2; ((i < tA) && (i < tB)); i++) {
                // localizando o menor divisor desta etapa
                if (((tA % i) == 0) && ((tB % i) == 0)) {
                    // divisor comum encontrado
                    finished = false;
                    tA /= i;
                    tB /= i;
                    res *= i;
                    break;
                }
            }
        }
        return res;
    }

    /**
     * <p>
     * Calcula e retorna o namero de quadros executados em um determinado
     * intervalo (em segundos).
     * </p>
     *
     * @param seconds
     *     Namero exato de segundos.
     *
     * @return Namero de quadros executados no intervalo informado.
     */
    public int getNFrames(final int seconds) {
        return (int) (seconds * cfg.getFrameRate());
    }

    /**
     * @param degrees
     *     O valor em graus que se deseja calcular
     *
     * @return O Valor correspondente em radianos.
     */
    public double getRad(final int degrees) {
        return (degrees * 3.14) / 180;
    }

    /**
     * <p>
     * Retorna um numero inteiro com manimo de 1 e valor maximo definido por
     * parametro, inclusive.
     * </p>
     *
     * @param max
     *     O valor maximo a ser retornado.
     *
     * @return um namero randamico inteiro, menor ou igual ao parametro
     * informado.
     */
    public int getRandomInt(final int max) {
        final double res = GameUtils.rnd.nextInt(100);
        final double space = 100 / max;
        final double result = res / space;
        return 1 + (int) Math.ceil(Math.round(result));
    }

    /**
     * <p>
     * Calcula e retorna uma posiaao randamica no eixo X correspondente a um
     * ponto coincidente com uma intersecaao entre dois blocos na Matriz de
     * Colisao.
     * </p>
     *
     * @param min
     *     Valor manimo
     * @param max
     *     Valor maximo
     *
     * @return Um namero randamico inteiro positivo, coincidente com uma
     * intersecaao entre dois blocos na Matriz de Colisao.
     */
    public int getRandomXPosition(final int min, final int max) {
        final GameFactory fac = GameFactory.getInstance();
        return (int) (Math.round(((Math.round(max / fac.getColision()
            .getCollisionBlockSize().width) - Math.round(min / fac.getColision()
            .getCollisionBlockSize().width)) * Math.random()) + Math.round(min / fac.getColision()
            .getCollisionBlockSize().width)) * fac.getColision()
            .getCollisionBlockSize().width);
    }

    /**
     * <p>
     * Calcula e retorna o ponto central da superfacie de renderizaaao do jogo,
     * como uma instancia de <code>Point</code>.
     * </p>
     *
     * @param cnv
     *     Instancia do canvas usada para renderizar o jogo.
     *
     * @return Uma instancia de <code>Point</code> que representa o ponto
     * central da superfacie grafica atualmente disponavel.
     */
    public Point getScreenCenter(final Canvas cnv) {
        final Point p = new Point();
        p.x = cnv.getWidth() / 2;
        p.y = cnv.getHeight() / 2;
        return p;
    }

    /**
     * <p>
     * Rederiza um texto, gerenciando automaticamente quebras de linha e
     * ajustando o texto ao espaao disponavel.
     * </p>
     *
     * @param text
     *     O texto a ser renderizado.
     * @param gfx
     *     Ponteiro para a porta grafica onde o texto sera renderizado.
     * @param posY
     *     Posiaao vertical do topo do texto na tela.
     * @param xMargins
     *     Margens esquerda e direita para o texto.
     */
    public void renderFullScreenText(String text, final Graphics2D gfx, final int posY, final int xMargins, final int textheight) {
        text = text.trim();
        posText = 0;
        paragraphCounter = 0;
        // lendo ate tratar o texto todo
        while (posText < text.length()) {
            // 1 - separando os paragrafos
            while ((posText < text.length()) && (text.charAt(posText) != GameUtils.CHR_NEWPARAGRAPH)) {
                bfr.append(text.charAt(posText));
                posText++;
            }
            // 2 - renderizando paragrafo
            gfx.drawString(bfr.toString(), xMargins, posY + (paragraphCounter * textheight));
            bfr.delete(0, bfr.length());
            paragraphCounter++;
            posText++;
        }
    }
}
