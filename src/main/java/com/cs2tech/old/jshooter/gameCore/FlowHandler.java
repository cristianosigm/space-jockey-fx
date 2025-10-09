package com.cs2tech.old.jshooter.gameCore;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.GameStatus;

/**
 * <p>
 * Esta classe define os comportamentos e atributos compartilhados por todos os
 * gerenciadores de fluxo de jogo.
 * </p>
 * <p>
 * Esta classe deve ser herdada uma anica vez para cada jogo, e deve centralizar
 * o fluxo de eventos e a sequancia principal do jogo.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class FlowHandler {

    /**
     * Repositario de status do framework.
     */
    protected GameStatus status = GameStatus.getInstance();

    /**
     * Gerenciador de logs.
     */
    protected GameLogger log = GameLogger.getInstance();

    /**
     * Prepara o gerenciador de fluxo para um novo jogo.
     */
    public void reset() {
        // reseting Status
        status.setP1ControllerEnabled(true);
        status.setPaused(false);
        status.setRunning(true);
        // limpando todos os objetos na lista de atores
        GameFactory.getInstance()
            .getActors()
            .reset();
        // reiniciando pontos e vidas do jogador
        GameFactory.getInstance()
            .getPlayer1()
            .reset();
    }

    /**
     * <p>
     * Atualiza o fluxo do jogo de acordo com o frame atual.
     * </p>
     * <p>
     * Este matodo descreve a sequancia do jogo em si: as sequancias de
     * animaaaes, os menus, o inacio e finalizaaao de cada estagio, etc.
     * </p>
     */
    public abstract void update();
}