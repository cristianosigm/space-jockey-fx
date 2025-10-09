package com.cs2tech.old.jockey.core;

import com.cs2tech.old.jockey.movies.Abertura;
import com.cs2tech.old.jockey.movies.PresentedBy;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.gameCore.FlowHandler;
import com.cs2tech.old.jshooter.gameElements.SMovie;
import com.cs2tech.old.jshooter.gameElements.SStage;

/**
 * <p>
 * Esta classe implementa o fluxo principal do jogo.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class MyFlow extends FlowHandler {

    /**
     * Fluxo de execu��o - Logotipo.
     */
    private static final byte CREATEDBY = 1;

    /**
     * Fluxo de execu��o - Abertura do jogo.
     */
    private static final byte ABERTURA = 2;

    /**
     * Fluxo de execu��o - Est�gio 1.
     */
    private static final byte STAGE_1 = 3;

    /**
     * Etapa do fluxo atualmente em execu��o.
     */
    private byte nowPlaying = 0;

    /**
     * Ponteiro para a anima��o atualmente em execu��o.
     */
    private SMovie currentMovie;

    /**
     * Ponteiro para a inst�ncia de est�gio atualmente em execu��o.
     */
    private SStage currentStage;

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.FlowHandler#update()
     */
    @Override
    public void update() {
        if (nowPlaying == 0) {
            // iniciando o fluxo
            status.setP1ControllerEnabled(true);
            currentMovie = new PresentedBy();
            GameFactory.getInstance()
                .setCurrentMovie(currentMovie);
            nowPlaying = MyFlow.CREATEDBY;
            log.addWarning("Executando PRESENTED_BY.", this);
        } else if (nowPlaying == MyFlow.CREATEDBY) {
            // executando presented by
            if (currentMovie.isFinished()) {
                currentMovie.terminate();
                currentMovie = new Abertura();
                GameFactory.getInstance()
                    .setCurrentMovie(currentMovie);
                nowPlaying = MyFlow.ABERTURA;
                log.addWarning("PRESENTED_BY terminado. Executando ABERTURA.", this);
            }
        } else if (nowPlaying == MyFlow.ABERTURA) {
            // executando presented by
            if (currentMovie.isFinished()) {
                currentMovie.terminate();
                currentStage = new Cenario();
                GameFactory.getInstance()
                    .setCurrentStage(currentStage);
                nowPlaying = MyFlow.STAGE_1;
                GameFactory.getInstance()
                    .getPlayer1()
                    .reset();
                log.addWarning("ABERTURA terminado. Executando STAGE_1.", this);
            }
        } else if (nowPlaying == MyFlow.STAGE_1) {
            // executando a animacao
            if (currentStage.isFinished()) {
                // jogador morreu
                nowPlaying = 0;
                currentStage.terminate();
                reset();
                log.addWarning("Jogador morreu.", this);
            }
        }
    }
}