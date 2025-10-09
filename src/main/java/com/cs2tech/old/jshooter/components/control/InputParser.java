/**
 *
 */
package com.cs2tech.old.jshooter.components.control;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameStatus;

/**
 * <p>
 * Esta classe recebe um comando de um leitor de entrada (por exemplo, o
 * <code>KeyboardController</code>) e solicita a execuaao da aaao correspondente
 * no jogo.
 * </p>
 * <p>
 * Por exemplo. se o jShooter esta executando um estagio e o jogador aciona o
 * comando UP, esta classe solicitara ao objeto Nave controlado pelo jogador
 * para mover acima; poram, se o jShooter esta executando um menu, esta classe
 * selecionara a opaao anterior do menu.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class InputParser {

    /**
     * Instancia anica de <code>InputParser</code>.
     */
    private static InputParser instance = null;
    /**
     * Ponteiro para a maquina de estados do framework.
     */
    private final GameStatus status = GameStatus.getInstance();

    /**
     * <p>
     * Construtor Principal.
     * </p>
     */
    private InputParser() {

    }

    /**
     * @return A Instancia anica de <code>InputParser</code>.
     */
    public static InputParser getInstance() {
        if (InputParser.instance == null) {
            InputParser.instance = new InputParser();
        }
        return InputParser.instance;
    }

    /**
     * Interpreta o comando ACTION A e solicita a aaao correspondente.
     */
    public void actA() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .actionA();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .previousValue();
        } else if (status.isPlayingMovie()) {
            GameFactory.getInstance()
                .getCurrentMovie()
                .skip();
        }
    }

    /**
     * Interpreta o comando ACTION B e solicita a aaao correspondente.
     */
    public void actB() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .actionB();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .nextValue();
        } else if (status.isPlayingMovie()) {
            GameFactory.getInstance()
                .getCurrentMovie()
                .skip();
        }
    }

    /**
     * Interpreta o comando ACTION C e solicita a aaao correspondente.
     */
    public void actC() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .actionC();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .startAct();
        } else if (status.isPlayingMovie()) {
            GameFactory.getInstance()
                .getCurrentMovie()
                .skip();
        }
    }

    /**
     * Interpreta o comando ACTION D e solicita a aaao correspondente.
     */
    public void actD() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .actionD();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .stopAct();
        } else if (status.isPlayingMovie()) {
            GameFactory.getInstance()
                .getCurrentMovie()
                .skip();
        }
    }

    /**
     * Interpreta o comando ACTION A e solicita a aaao correspondente.
     */
    public void cancelActA() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopActionA();
        }
    }

    /**
     * Interpreta o comando ACTION B e solicita a aaao correspondente.
     */
    public void cancelActB() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopActionB();
        }
    }

    /**
     * Interpreta o comando ACTION C e solicita a aaao correspondente.
     */
    public void cancelActC() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopActionC();
        }
    }

    /**
     * Interpreta o comando ACTION D e solicita a aaao correspondente.
     */
    public void cancelActD() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopActionD();
        }
    }

    /**
     * Interpreta o comando DOWN e solicita a aaao correspondente.
     */
    public void cancelDown() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopMovingDown();
        }
    }

    /**
     * Interpreta o comando LEFT e solicita a aaao correspondente.
     */
    public void cancelLeft() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopMovingLeft();
        }
    }

    /**
     * Interpreta o comando RIGHT e solicita a aaao correspondente.
     */
    public void cancelRight() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopMovingRight();
        }
    }

    /**
     * Interpreta o comando UP e solicita a aaao correspondente.
     */
    public void cancelUp() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .stopMovingUp();
        }
    }

    /**
     * Interpreta o comando DOWN e solicita a aaao correspondente.
     */
    public void down() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .moveDown();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .next();
        }
    }

    /**
     * Interpreta o comando LEFT e solicita a aaao correspondente.
     */
    public void left() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .moveLeft();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .previousValue();
        }
    }

    /**
     * Interpreta o comando RIGHT e solicita a aaao correspondente.
     */
    public void right() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .moveRight();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .nextValue();
        }
    }

    /**
     * Interpreta o comando UP e solicita a aaao correspondente.
     */
    public void up() {
        if (status.isAtStage() && status.isP1ControllerEnabled()) {
            GameFactory.getInstance()
                .getPlayer1()
                .moveUp();
        } else if (status.isAtMenu()) {
            GameFactory.getInstance()
                .getCurrentMenu()
                .previous();
        }
    }
}
