package com.cs2tech.old.jshooter.components;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * <p>
 * Esta classe implementa um gerenciador de logs, criado para trabalhar sem a
 * necessidade adicional de nenhum JAR ou plugin.
 * </p>
 * <p>
 * Este gerenciador de log foi desenhado para ser usado em aplicaaaes que
 * enfatizam performance, por ser extremamente leve e simples, e utilizar apenas
 * um anico ponteiro de arquivo, sem acessos a disco durante a escrita (apenas
 * na inicializaaao e finalizaaao da aplicaaao).
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GameLogger {

    /**
     * Instancia anica de <code>GameLogger</code>.
     */
    private static GameLogger instance = null;
    /**
     * Calendario, para geraaao de data e hora.
     */
    private final Calendar calendar = Calendar.getInstance();
    /**
     * Buffer de texto para entrada de dados.
     */
    private final StringBuffer bfr = new StringBuffer();
    /**
     * Navel de log.
     */
    private byte logLevel;
    /**
     * Chave liga-desliga para o gerador de logs. Se for setado como
     * <b>False</b>, todos o matodos deixam de funcionar, com pouquassimo
     * consumo de ciclos de computador para cada chamada anulada.
     */
    private boolean on = true;
    /**
     * Chave lia-desliga para o visualizador de logs na saada padrao
     * (System.out).
     */
    private boolean visible;
    /**
     * Ponteiro de arquivo fasico (onde o log sera armazenado).
     */
    private File logFile = null;
    /**
     * Manipulador de escrita no arquivo.
     */
    private FileWriter writer;
    /**
     * Manipulador de armazenamento no arquivo.
     */
    private PrintWriter printer;

    /**
     * Construtor padrao.
     */
    private GameLogger() {
        try {
            visible = GameConfig.getInstance()
                .isLogVisible();
            logLevel = GameConfig.getInstance()
                .getLogLevel();
            if (logLevel == 0) {
                // log desligado
                on = false;
            }
            if (on) {
                calendar.setTimeInMillis(System.currentTimeMillis());
                logFile = new File(GameConfig.getInstance()
                    .getLogFile() + "_" + calendar.get(Calendar.YEAR) + ((calendar.get(Calendar.MONTH) > 8) ? (calendar.get(Calendar.MONTH) + 1) : ("0" + (calendar.get(
                    Calendar.MONTH) + 1))) + ((calendar.get(Calendar.DAY_OF_MONTH) > 8) ? (calendar.get(Calendar.DAY_OF_MONTH) + 1) : ("0" + (calendar.get(
                    Calendar.DAY_OF_MONTH) + 1))) + "_" + calendar.get(Calendar.HOUR_OF_DAY) + "h" + calendar.get(Calendar.MINUTE) + "min" + calendar.get(
                    Calendar.SECOND) + "sec" + ".log");
                if (!logFile.exists()) {
                    logFile.createNewFile();
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return A instancia anica de <code>GameLogger</code>.
     */
    public static GameLogger getInstance() {
        if (GameLogger.instance == null) {
            GameLogger.instance = new GameLogger();
        }
        return GameLogger.instance;
    }

    /**
     * <p>
     * Adiciona uma entrada de navel DEBUG (detalhe maximo).
     * </p>
     */
    public void addDebug(final String msg, final Object obj) {
        if ((on) && (logLevel >= 3)) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            bfr.append(getCurrentTimestamp() + ": " + msg + " (" + (obj != null ? obj.getClass() : "null") + ")");
            addMessage();
        }
    }

    /**
     * @return Data e hora atuais, convertidos para texto.
     */
    private String getCurrentTimestamp() {
        return calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR) + " " + calendar.get(
            Calendar.HOUR_OF_DAY) + "h" + calendar.get(Calendar.MINUTE) + "min" + calendar.get(Calendar.SECOND) + "sec" + calendar.get(Calendar.MILLISECOND) + "milis";
    }

    /**
     * <p>
     * Escreve o conteado do buffer em disco e limpa o buffer.
     * </p>
     */
    private void addMessage() {
        try {
            if (logFile != null) {
                // preparando o escritor
                writer = new FileWriter(logFile, true);
                printer = new PrintWriter(writer);
                // escrevendo a mensagem
                printer.println(bfr);
                // fechando as ferramentas
                printer.close();
                writer.close();
            }
            // renderizando as mensagens na tela, quando solicitado
            if (visible) {
                System.out.println(bfr);
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        // limpando o buffer para a praxima mensagem
        bfr.delete(0, bfr.length());
    }

    /**
     * <p>
     * Adiciona uma entrada de navel ERROR (erros).
     * </p>
     */
    public void addError(final String msg, final Exception e, final Object obj) {
        if ((on) && (logLevel >= 1)) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            bfr.append(" --> " + getCurrentTimestamp() + " >>[ERROR]: " + msg + " (" + (obj != null ? obj.getClass() : "null") + ")" + e.getMessage() + "\n");
            for (int i = 0; i < e.getStackTrace().length; i++) {
                bfr.append(" . . . AT " + e.getStackTrace()[i].toString() + "\n");
            }
            addMessage();
        }
    }

    /**
     * <p>
     * Adiciona uma entrada de navel WARNING (detalhe manimo).
     * </p>
     */
    public void addWarning(final String msg, final Object obj) {
        if ((on) && (logLevel >= 2)) {
            calendar.setTimeInMillis(System.currentTimeMillis());
            bfr.append(getCurrentTimestamp() + " [WARNING!]: " + msg + " (" + (obj != null ? obj.getClass() : "null") + ")");
            addMessage();
        }
    }
}
