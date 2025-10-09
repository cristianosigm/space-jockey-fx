package com.cs2tech.old.jshooter.components;

/**
 * <p>
 * Esta classe implementa a maquina de estados basica do JShooter. Todos os
 * estados mais importantes ou compartihados do jogo sao controladas atravas
 * dela.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GameStatus {

    /**
     * Instancia anica de <code>GameStatus</code>.
     */
    private static GameStatus instance = null;
    /**
     * Indica se o jogo esta em execuaao.
     */
    private boolean running;

    /**
     * Indica se o controle do jogador 1 esta habilitado.
     */
    private boolean p1ControllerEnabled;

    /**
     * Indica se o controle do jogador 2 esta habilitado.
     */
    private boolean p2ControllerEnabled = false;

    /**
     * Indica se o jogo esta pausado.
     */
    private boolean paused;

    /**
     * Indica que o JShooter esta atualmente executando uma animaaao.
     */
    private boolean playingMovie;

    /**
     * Indica que o JShooter esta atualmente executando um menu.
     */
    private boolean atMenu;

    /**
     * Indica que o JShooter esta atualmente executando um estagio.
     */
    private boolean atStage;

    /**
     * Indica que o JShooter esta atualmente renderizando um chefe de fase.
     */
    private boolean atBoss;

    /**
     * Indica que o JShooter deve preparar o estagio para a entrada do chefe.
     */
    private boolean atWarning;

    /**
     * Indica que o jogador superou o estagio atual.
     */
    private boolean stageCleared;

    /**
     * Indica que o logo do JShooter esta em execuaao.
     */
    private boolean runningLogo;

    /**
     * <p>
     * Construtor Principal.
     * </p>
     */
    private GameStatus() {
        super();
    }

    /**
     * @return A instancia anica de <code>GameStatus</code>.
     */
    public static GameStatus getInstance() {
        if (GameStatus.instance == null) {
            GameStatus.instance = new GameStatus();
        }
        return GameStatus.instance;
    }

    /**
     * @return O valor atual de atBoss.
     */
    public boolean isAtBoss() {
        return atBoss;
    }

    /**
     * @param atBoss
     *     Redefine o valor de atBoss para o valor informado por
     *     parametro.
     */
    public void setAtBoss(final boolean atBoss) {
        this.atBoss = atBoss;
    }

    /**
     * @return O valor atual de atMenu.
     */
    public boolean isAtMenu() {
        return atMenu;
    }

    /**
     * @param atMenu
     *     Redefine o valor de atMenu para o valor informado por
     *     parametro.
     */
    public void setAtMenu(final boolean atMenu) {
        this.atMenu = atMenu;
        if (this.atMenu) {
            playingMovie = false;
            atStage = false;
        }
    }

    /**
     * @return O valor atual de atStage.
     */
    public boolean isAtStage() {
        return atStage;
    }

    /**
     * @param atStage
     *     Redefine o valor de atStage para o valor informado por
     *     parametro.
     */
    public void setAtStage(final boolean atStage) {
        this.atStage = atStage;
        if (this.atStage) {
            playingMovie = false;
            atMenu = false;
        }
    }

    /**
     * @return O valor atual de atWarning.
     */
    public boolean isAtWarning() {
        return atWarning;
    }

    /**
     * @param atWarning
     *     Redefine o valor de atWarning para o valor informado por
     *     parametro.
     */
    public void setAtWarning(final boolean atWarning) {
        this.atWarning = atWarning;
    }

    /**
     * @return O valor atual de p1ControllerEnabled.
     */
    public boolean isP1ControllerEnabled() {
        return p1ControllerEnabled;
    }

    /**
     * @param p1ControllerEnabled
     *     Redefine o valor de p1ControllerEnabled para o valor informado
     *     por parametro.
     */
    public void setP1ControllerEnabled(final boolean controllerEnabled) {
        p1ControllerEnabled = controllerEnabled;
    }

    /**
     * @return O valor atual de p2ControllerEnabled.
     */
    public boolean isP2ControllerEnabled() {
        return p2ControllerEnabled;
    }

    /**
     * @param p2ControllerEnabled
     *     Redefine o valor de p2ControllerEnabled para o valor informado
     *     por parametro.
     */
    public void setP2ControllerEnabled(final boolean controllerEnabled) {
        p2ControllerEnabled = controllerEnabled;
    }

    /**
     * @return O valor atual de paused.
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused
     *     Redefine o valor de paused para o valor informado por
     *     parametro.
     */
    public void setPaused(final boolean paused) {
        this.paused = paused;
    }

    /**
     * @return O valor atual de playingMovie.
     */
    public boolean isPlayingMovie() {
        return playingMovie;
    }

    /**
     * @param playingMovie
     *     Redefine o valor de playingMovie para o valor informado por
     *     parametro.
     */
    public void setPlayingMovie(final boolean playingMovie) {
        this.playingMovie = playingMovie;
        if (this.playingMovie) {
            atMenu = false;
            atStage = false;
        }
    }

    /**
     * @return O valor atual de running.
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running
     *     Redefine o valor de running para o valor informado por
     *     parametro.
     */
    public void setRunning(final boolean running) {
        this.running = running;
    }

    /**
     * @return O valor atual de runningLogo.
     */
    public boolean isRunningLogo() {
        return runningLogo;
    }

    /**
     * @param runningLogo
     *     Redefine o valor de runningLogo para o valor informado por
     *     parametro.
     */
    public void setRunningLogo(final boolean runningLogo) {
        this.runningLogo = runningLogo;
    }

    /**
     * @return O valor atual de stageCleared.
     */
    public boolean isStageCleared() {
        return stageCleared;
    }

    /**
     * @param stageCleared
     *     Redefine o valor de stageCleared para o valor informado por
     *     parametro.
     */
    public void setStageCleared(final boolean stageCleared) {
        this.stageCleared = stageCleared;
    }
}
