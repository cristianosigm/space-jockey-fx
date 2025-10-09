package com.cs2tech.old.jshooter.components.control;

import com.cs2tech.old.jshooter.components.GameConfig;
import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.GameStatus;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * <p>
 * Esta classe implementa um controle para o jogo, baseado no teclado do
 * computador.
 * </p>
 * <p>
 * A configuraaao de teclas a carregada do arquivo <b>config.properties</b>, que
 * pode ser redefinida manualmente ou pelo utilitario de configuraaao.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class KeyboardController extends KeyAdapter {

    /**
     * Ponteiro para o configurador do jogo.
     */
    private final GameConfig cfg = GameConfig.getInstance();

    /**
     * Ponteiro para o logger do jogo.
     */
    private final GameLogger log = GameLogger.getInstance();

    /**
     * Parser de entrada de comandos.
     */
    private final InputParser parser = InputParser.getInstance();

    /* KEYBOARD COMMANDS */
    private int p1Up;
    private int p1Down;
    private int p1Left;
    private int p1Right;
    private int p1ActionA;
    private int p1ActionB;
    private int p1ActionC;
    private int p1ActionD;

    private int p2Up;
    private int p2Down;
    private int p2Left;
    private int p2Right;
    private int p2ActionA;
    private int p2ActionB;
    private int p2ActionC;
    private int p2ActionD;

    /* ------------------ */

    /**
     * <p>
     * Construtor padrao.
     * </p>
     * <p>
     * La as teclas atualmente configuradas do <b>Factory</b>.
     * </p>
     */
    public KeyboardController() {
        super();
        setP1ActionA(cfg.getP1ActionA());
        setP1ActionB(cfg.getP1ActionB());
        setP1ActionC(cfg.getP1ActionC());
        setP1ActionD(cfg.getP1ActionD());
        setP1Up(cfg.getP1Up());
        setP1Down(cfg.getP1Down());
        setP1Left(cfg.getP1Left());
        setP1Right(cfg.getP1Right());

        setP2ActionA(cfg.getP1ActionA());
        setP2ActionB(cfg.getP1ActionB());
        setP2ActionC(cfg.getP1ActionC());
        setP2ActionD(cfg.getP1ActionD());
        setP2Up(cfg.getP1Up());
        setP2Down(cfg.getP1Down());
        setP2Left(cfg.getP1Left());
        setP2Right(cfg.getP1Right());
    }

    /**
     * @return O valor atual de p2ActionA
     */
    public int getP2ActionA() {
        return p2ActionA;
    }

    /**
     * @param p2ActionA
     *     Redefine o valor de p2ActionA para o valor do parametro.
     */
    public void setP2ActionA(final int actionA) {
        p2ActionA = actionA;
    }

    /**
     * @return O valor atual de p2ActionB
     */
    public int getP2ActionB() {
        return p2ActionB;
    }

    /**
     * @param p2ActionB
     *     Redefine o valor de p2ActionB para o valor do parametro.
     */
    public void setP2ActionB(final int actionB) {
        p2ActionB = actionB;
    }

    /**
     * @return O valor atual de p2ActionC
     */
    public int getP2ActionC() {
        return p2ActionC;
    }

    /**
     * @param p2ActionC
     *     Redefine o valor de p2ActionC para o valor do parametro.
     */
    public void setP2ActionC(final int actionC) {
        p2ActionC = actionC;
    }

    /**
     * @return O valor atual de p2ActionD
     */
    public int getP2ActionD() {
        return p2ActionD;
    }

    /**
     * @param p2ActionD
     *     Redefine o valor de p2ActionD para o valor do parametro.
     */
    public void setP2ActionD(final int actionD) {
        p2ActionD = actionD;
    }

    /**
     * @return O valor atual de p2Down
     */
    public int getP2Down() {
        return p2Down;
    }

    /**
     * @param p2Down
     *     Redefine o valor de p2Down para o valor do parametro.
     */
    public void setP2Down(final int down) {
        p2Down = down;
    }

    /**
     * @return O valor atual de p2Left
     */
    public int getP2Left() {
        return p2Left;
    }

    /**
     * @param p2Left
     *     Redefine o valor de p2Left para o valor do parametro.
     */
    public void setP2Left(final int left) {
        p2Left = left;
    }

    /**
     * @return O valor atual de p2Right
     */
    public int getP2Right() {
        return p2Right;
    }

    /**
     * @param p2Right
     *     Redefine o valor de p2Right para o valor do parametro.
     */
    public void setP2Right(final int right) {
        p2Right = right;
    }

    /**
     * @return O valor atual de p2Up
     */
    public int getP2Up() {
        return p2Up;
    }

    /**
     * @param p2Up
     *     Redefine o valor de p2Up para o valor do parametro.
     */
    public void setP2Up(final int up) {
        p2Up = up;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.KeyAdapter#keyTyped(java.awt.event.KeyEvent)
     */
    @Override
    public void keyTyped(final KeyEvent e) {
        if (e.getKeyChar() == 27) {
            System.exit(0);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.KeyAdapter#keyPressed(java.awt.event.KeyEvent)
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        // JOGADOR 1
        if (GameStatus.getInstance()
            .isP1ControllerEnabled()) {
            if (e.getKeyCode() == getP1Left()) {
                log.addDebug("Movendo player 1 para a esquerda.", this);
                parser.left();
            } else if (e.getKeyCode() == getP1Right()) {
                log.addDebug("Movendo player 1 para a direita.", this);
                parser.right();
            } else if (e.getKeyCode() == getP1Up()) {
                log.addDebug("Movendo player 1 para cima.", this);
                parser.up();
            } else if (e.getKeyCode() == getP1Down()) {
                log.addDebug("Movendo player 1 para baixo.", this);
                parser.down();
            } else if (e.getKeyCode() == getP1ActionA()) {
                log.addDebug("Player 1 acionou o botao A.", this);
                parser.actA();
            } else if (e.getKeyCode() == getP1ActionB()) {
                log.addDebug("Player 1 acionou o botao B.", this);
                parser.actB();
            } else if (e.getKeyCode() == getP1ActionC()) {
                log.addDebug("Player 1 acionou o botao C.", this);
                parser.actC();
            } else if (e.getKeyCode() == getP1ActionD()) {
                log.addDebug("Player 1 acionou o botao D.", this);
                parser.actD();
            }
        }
        // JOGADOR 2
        if (GameStatus.getInstance()
            .isP2ControllerEnabled()) {
            // TODO: implementar 2 jogadores.
        }
    }

    /**
     * @return O valor atual de p1Left
     */
    public int getP1Left() {
        return p1Left;
    }

    /**
     * @param Redefine
     *     o valor de p1Left para o valor do parametro.
     */
    public void setP1Left(final int left) {
        p1Left = left;
    }

    /**
     * @return O valor atual de p1Right
     */
    public int getP1Right() {
        return p1Right;
    }

    /**
     * @param p1Right
     *     Redefine o valor de p1Right para o valor do parametro.
     */
    public void setP1Right(final int right) {
        p1Right = right;
    }

    /**
     * @return O valor atual de p1Up
     */
    public int getP1Up() {
        return p1Up;
    }

    /**
     * @return O valor atual de p1Down
     */
    public int getP1Down() {
        return p1Down;
    }

    /**
     * @return O valor atual de p1ActionA
     */
    public int getP1ActionA() {
        return p1ActionA;
    }

    /**
     * @param p1ActionA
     *     Redefine o valor de p1ActionA para o valor do parametro.
     */
    public void setP1ActionA(final int actionA) {
        p1ActionA = actionA;
    }

    /**
     * @return O valor atual de p1ActionB
     */
    public int getP1ActionB() {
        return p1ActionB;
    }

    /**
     * @param p1ActionB
     *     Redefine o valor de p1ActionB para o valor do parametro.
     */
    public void setP1ActionB(final int actionB) {
        p1ActionB = actionB;
    }

    /**
     * @return O valor atual de p1ActionC
     */
    public int getP1ActionC() {
        return p1ActionC;
    }

    /**
     * @param p1ActionC
     *     Redefine o valor de p1ActionC para o valor do parametro.
     */
    public void setP1ActionC(final int actionC) {
        p1ActionC = actionC;
    }

    /**
     * @return O valor atual de p1ActionD
     */
    public int getP1ActionD() {
        return p1ActionD;
    }

    /**
     * @param p1ActionD
     *     Redefine o valor de p1ActionD para o valor do parametro.
     */
    public void setP1ActionD(final int actionD) {
        p1ActionD = actionD;
    }

    /**
     * @param p1Down
     *     Redefine o valor de p1Down para o valor do parametro.
     */
    public void setP1Down(final int down) {
        p1Down = down;
    }

    /**
     * @param p1Up
     *     Redefine o valor de p1Up para o valor do parametro.
     */
    public void setP1Up(final int up) {
        p1Up = up;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.event.KeyAdapter#keyReleased(java.awt.event.KeyEvent)
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        // JOGADOR 1
        if (GameStatus.getInstance()
            .isP1ControllerEnabled()) {
            if (e.getKeyCode() == getP1Up()) {
                log.addDebug("Player 1 parou.", this);
                parser.cancelUp();
            } else if (e.getKeyCode() == getP1Down()) {
                log.addDebug("Player 1 parou.", this);
                parser.cancelDown();
            } else if (e.getKeyCode() == getP1Left()) {
                log.addDebug("Player 1 parou.", this);
                parser.cancelLeft();
            } else if (e.getKeyCode() == getP1Right()) {
                log.addDebug("Player 1 parou.", this);
                parser.cancelRight();
            } else if (e.getKeyCode() == getP1ActionA()) {
                log.addDebug("Player 1 soltou o botao A.", this);
                parser.cancelActA();
            } else if (e.getKeyCode() == getP1ActionB()) {
                log.addDebug("Player 1 soltou o botao B.", this);
                parser.cancelActB();
            } else if (e.getKeyCode() == getP1ActionC()) {
                log.addDebug("Player 1 soltou o botao C.", this);
                parser.cancelActC();
            } else if (e.getKeyCode() == getP1ActionD()) {
                log.addDebug("Player 1 soltou o botao D.", this);
                parser.cancelActD();
            }
        }
        // JOGADOR 2
        if (GameStatus.getInstance()
            .isP2ControllerEnabled()) {
            // TODO: implementar 2 jogadores.
        }
    }
}
