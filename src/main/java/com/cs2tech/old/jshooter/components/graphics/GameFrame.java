/**
 *
 */
package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.*;
import com.cs2tech.old.jshooter.components.control.KeyboardController;
import com.cs2tech.old.jshooter.gameCore.FlowHandler;
import com.cs2tech.old.jshooter.gameCore.kernel.GameThread;

import javax.swing.*;
import java.awt.*;

/**
 * <p>
 * Esta classe implementa o Frame do jogo, responsavel por conter a instancia da
 * porta grafica e sincronizar os eventos dos objetos atravas da
 * <code>Thread</code> principal.
 * </p>
 * <p>
 * Todos os jogos devem implementar esta classe; poram o anico matodo que
 * precisa ser sobrescrito a o
 * <code>public static void main(String[] args)</code>.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class GameFrame extends JFrame {

    /**
     * Gerenciador de logs padrao da classe.
     */
    protected static final GameLogger log = GameLogger.getInstance();
    /**
     * Gerenciador de configuraaaes da classe.
     */
    protected static final GameConfig cfg = GameConfig.getInstance();
    /**
     * Gerenciador de status da classe.
     */
    protected static final GameStatus status = GameStatus.getInstance();
    /**
     * Gerenciador de status da classe.
     */
    protected static final GameUtils util = GameUtils.getInstance();
    private static final long serialVersionUID = -238465625501065968L;
    /**
     * Tamanho da tela quando em full-screen.
     */
    private static final Dimension FULL_SCREEN_SIZE = new Dimension(800, 600);
    /**
     * Flag para sinalizar o uso do MOUSE como controlador
     */
    public static byte USE_MOUSE_CONTROLLER = 0;
    /**
     * Flag para sinalizar o uso do TECLADO como controlador
     */
    public static byte USE_KEYBOARD_CONTROLLER = 1;
    /**
     * Flag para sinalizar o uso do JOYSTICK como controlador
     */
    public static byte USE_JOYSTICK_CONTROLLER = 2;
    /**
     * Dispositivo full screen.
     */
    private final GraphicsDevice device;
    /**
     * Porta grafica do jShooter.
     */
    private GamePanel canvas = null;
    /**
     * Tipo de controle usado para este jogo.
     */
    private byte controller = GameFrame.USE_KEYBOARD_CONTROLLER;
    /**
     * Instancia do controlador de fluxo do jogo.
     */
    private FlowHandler gameCore;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     */
    public GameFrame() {
        device = GraphicsEnvironment.getLocalGraphicsEnvironment()
            .getDefaultScreenDevice();
        // buscando um display mode igual a: 800 x 600 full screen
        final DisplayMode[] modes = device.getDisplayModes();
        DisplayMode dispMode = device.getDisplayMode();
        for (final DisplayMode mode : modes) {
            if ((mode.getBitDepth() == DisplayMode.BIT_DEPTH_MULTI) && (mode.getRefreshRate() == DisplayMode.REFRESH_RATE_UNKNOWN) && (mode.getWidth() == GameFrame.FULL_SCREEN_SIZE.getWidth())) {
                dispMode = mode;
                break;
            }
        }
        dispose();
        if (GameFrame.cfg.isFullScreen() && device.isFullScreenSupported()) {
            // testando full-screen
            GameFrame.log.addWarning(" >>>>> FULLSCREEN SUPORTADO: " + device.isFullScreenSupported(), this);
            GameFrame.log.addWarning(" >>>>> DISPLAY MODE CHANGE SUPORTADO: " + device.isDisplayChangeSupported(), this);
            // tentando instanciar uma janela fullscreen
            setUndecorated(true);
            device.setFullScreenWindow(this);
            device.setDisplayMode(dispMode);
            setSize(Toolkit.getDefaultToolkit()
                .getScreenSize());
        } else {
            // instanciando a janela comum, caso nao suportado
            setUndecorated(false);
            setLocationRelativeTo(null);
            setSize(GameFrame.cfg.getGameResolution());
            // centralizando a janela
            this.setLocation(new Point((Toolkit.getDefaultToolkit()
                .getScreenSize().width - this.getSize().width) / 2, (Toolkit.getDefaultToolkit()
                .getScreenSize().height - this.getSize().height) / 2));
        }
        getContentPane().setPreferredSize(new Dimension(getSize().width + 1, getSize().height + 1));
        getContentPane().setLayout(new GridLayout(1, 1));
        getContentPane().setIgnoreRepaint(true);

        // CANVAS
        canvas = new GamePanel();
        // forcar o foco na janela principal
        canvas.setFocusable(false);
        canvas.setBounds(new Rectangle(0, 0, getSize().width + 2, getSize().height + 2));
        canvas.setSize(getSize());
        getContentPane().add(canvas);
        pack();
        // selecionando o controle
        if (controller == GameFrame.USE_KEYBOARD_CONTROLLER) {
            addKeyListener(new KeyboardController());
        } else {
            // outros controladores nao implementados nesta versao
            // TODO: adicionar outros controladores, quando implementados.
        }
        // setando as demais propriedades da janela
        setIgnoreRepaint(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        validate();
        /* ---------------------------------------------------- */
        GameFrame.log.addDebug("Inicializando log. Tamanho da janela: " + getSize().width + "x" + getSize().height, this);
    }

    /**
     * @return Instancia do canvas usado neste jogo.
     */
    public GamePanel getCanvas() {
        return canvas;
    }

    /**
     * @return Controle usado neste jogo.
     */
    public byte getController() {
        return controller;
    }

    /**
     * @param controller
     *     Redefine o controle usado por este jogo.
     */
    public void setController(final byte controller) {
        this.controller = controller;
    }

    /**
     * @return Instancia de GameCore.
     */
    public FlowHandler getGameCore() {
        return gameCore;
    }

    /**
     * @param gameCore
     *     Redefine a instancia de GameCore.
     */
    public void setGameCore(final FlowHandler gameCore) {
        this.gameCore = gameCore;
        GameFrame.status.setRunningLogo(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Container#paint(java.awt.Graphics)
     */
    @Override
    public void paint(final Graphics g) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#paintAll(java.awt.Graphics)
     */
    @Override
    public void paintAll(final Graphics g) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#printAll(java.awt.Graphics)
     */
    @Override
    public void printAll(final Graphics g) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Container#print(java.awt.Graphics)
     */
    @Override
    public void print(final Graphics g) {
    }

    /**
     * <p>
     * Inicializa a thread central do jogo e executa todas as operaaaes
     * necessarias para iniciar o jogo.
     * </p>
     */
    public void start() {
        try {
            // iniciando a thread principal
            GameFrame.status.setRunning(true);
            final GameThread clock = new GameThread();
            clock.start();
        } catch (final Exception e) {
            GameFrame.log.addError("Erro ao tentar inicializar a thread central: " + e.getMessage(), e, this);
            JOptionPane.showMessageDialog(this, "ERRO AO TENTAR INICIAR O JOGO.\nPor favor, contacte o fabricante...");
            System.exit(0);
        }
    }

    /**
     * <p>
     * Atualiza o estado do kernel e, logo apas, solicita a renderizaaao de
     * todos os objetos graficos.
     * </p>
     * <p>
     * Este matodo a chamado apenas pela thread central (para sincronizaaao).
     * </p>
     */
    public void update() {
        if (!GameFrame.status.isPaused() && !GameFrame.status.isRunningLogo()) {
            // atualizando o fluxo do jogo
            gameCore.update();
            // preparando o centro de colisao
            if (GameFrame.status.isAtStage()) {
                GameFactory.getInstance()
                    .getColision()
                    .reset();
            }
        }
        canvas.draw();
    }

    /*
     * (non-Javadoc)
     *
     * @see javax.swing.JFrame#update(java.awt.Graphics)
     */
    @Override
    public void update(final Graphics g) {
    }
}
