package com.cs2tech.old.jshooter.gameCore.kernel;

import com.cs2tech.old.jshooter.components.GameConfig;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameStatus;

/**
 * <p>
 * Esta classe implementa a Thread central, responsavel pelo sincronismo das
 * aaaes de todos os elementos do jogo.
 * </p>
 * <p>
 * Esta thread implementa um recurso de intervalo variavel entre os pulsos de
 * clock, mantendo o tempo entre os frames o mais uniforme possavel.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GameThread extends Thread {

    /**
     * Intervalo de tempo de pausa.
     */
    private final long sleepInterval = (long) (1000 / GameConfig.getInstance()
        .getFrameRate());

    /**
     * Instante em que foi iniciado o processo de atualizaaao do kernel.
     */
    private long tStart;

    /**
     * Diferenaa entre o intervalo programado e o tempo gasto na atualizaaao.
     */
    private long delta;

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Thread#run()
     */
    @Override
    public void run() {
        while (GameStatus.getInstance()
            .isRunning()) {
            tStart = System.currentTimeMillis();
            GameFactory.getInstance()
                .getFrm()
                .update();
            delta = sleepInterval - (System.currentTimeMillis() - tStart);
            try {
                if (delta > 0) {
                    Thread.sleep(delta);
                }
            } catch (final InterruptedException e) {
                // do nothing
            }
        }
    }
}
