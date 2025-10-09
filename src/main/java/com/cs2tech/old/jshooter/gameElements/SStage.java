package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.graphics.BasicGraphics;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameElements.internal.Background;
import com.cs2tech.old.jshooter.gameElements.internal.Foreground;

import java.awt.*;

public abstract class SStage extends BasicGraphics {

    /**
     * Duraaao da animaaao de fim de estagio.
     */
    private static final int N_FRAMES_FINISHING = IRenderable.gameUtils.getNFrames(8);
    /**
     * Tempo que um background leva para ser completamente exibido (em segundos).
     */
    protected int INT_DURATION_BACKGROUND = 0;
    /**
     * Background animado para este estagio.
     */
    private Background[] bkg;

    /**
     * Efeitos de cenario.
     */
    private Foreground frg;

    /**
     * Indica se o jogador finalizou o estagio atual vivo, e se ele cumpriu os
     * desafios necessarios para passar ao praximo estagio.
     */
    private boolean victory;

    /**
     * Indica se o estagio esta finalizado.
     */
    private boolean finished;

    /**
     * Indica que o estagio terminou, e solicita a animaaao correspondente.
     */
    private boolean finishing;

    /**
     * Frame atual.
     */
    private int frame;

    /**
     * Frame do final de estagio.
     */
    private int frameFinishing = 0;

    /**
     * Construtor padrao.
     *
     * @param imgBackground
     *     Imagem de background inicial a ser adicionada no inacio do
     *     estagio.
     * @param direction
     *     Direaao de movimento do background.
     * @param speed
     *     Velocidade do background (em pixels/segundo).
     */
    public SStage(final int imgBackground, final byte direction, final double speed) {
        bkg = new Background[1];
        bkg[0] = new Background(imgBackground, direction, speed);
        frg = null;
        victory = false;
        finished = false;
        IRenderable.gameStatus.setAtBoss(false);
        IRenderable.gameStatus.setAtMenu(false);
        IRenderable.gameStatus.setAtStage(true);
        IRenderable.gameStatus.setAtWarning(false);
        IRenderable.gameStatus.setPlayingMovie(false);
        IRenderable.gameStatus.setStageCleared(false);
        frame = 0;
        GameFactory.getInstance()
            .getPlayer1()
            .setEnergy(GameFactory.getInstance()
                .getPlayer1()
                .getMaxEnergy());
        if (GameFactory.getInstance()
            .isTwoPlayerGame()) {
            GameFactory.getInstance()
                .getPlayer2()
                .setEnergy(GameFactory.getInstance()
                    .getPlayer2()
                    .getMaxEnergy());
        }
        INT_DURATION_BACKGROUND = (int) (gameConfig.getGameResolution().height / speed);
    }

    /**
     * <p>
     * Adiciona mais uma camada ao conjunto de camadas de background atual.
     * </p>
     *
     * @param imageIndex
     *     Imagem da nova camada de background.
     * @param direction
     *     Direaao da camada de background.
     * @param speed
     *     Velocidade da camada de background.
     */
    public void addBgLayer(final int imageIndex, final byte direction, final double speed) {
        final Background bg = new Background(imageIndex, direction, speed);
        final Background[] bgt = new Background[getBkg().length + 1];
        int i = 0;
        for (i = 0; i < getBkg().length; i++) {
            bgt[i] = getBkg()[i];
        }
        bgt[i] = bg;
        bkg = bgt;
    }

    /**
     * @return O valor atual de bkg.
     */
    public Background[] getBkg() {
        return bkg;
    }

    /**
     * Cria um novo Foreground com as informaaaes recebidas.
     *
     * @param imgBackground
     *     andice da imagem usada no foreground.
     * @param direction
     *     Direaao de movimento do Foreground.
     * @param speed
     *     Velocidade de deslocamento da imagem (em px/seg).
     * @param opacity
     *     andice de opacidade da camada, sendo 1.0f totalmente opaco e
     *     0.0f totalmente transparente.
     */
    public void createForeground(final int imgBackground, final byte direction, final double speed, final float opacity) {
        frg = new Foreground(imgBackground, direction, speed, opacity);
        GameFactory.getInstance()
            .getActors()
            .add(frg, GraphicObjectsList.LAYER_TOP);
        frameFinishing = 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        // renderizando o background
        for (final Background element : bkg) {
            element.draw(gfx, cnv);
        }
        // proximo frame
        frame++;
        // se o estagio foi finalizado
        if (isFinishing()) {
            if (frameFinishing > SStage.N_FRAMES_FINISHING) {
                // acabou a animaaao de fim de estagio
                setFinished(true);
            } else {
                frameFinishing++;
            }
        }
    }

    /**
     * @return O valor atual de finishing
     */
    public boolean isFinishing() {
        return finishing;
    }

    /**
     * @param finishing
     *     Redefine o valor de finishing para o valor informado por
     *     parametro.
     */
    public void setFinishing(final boolean finishing) {
        // parando a musica de fundo
        audio.stopMusic();
        // informando o nucleo que o estagio acabou
        this.finishing = finishing;
    }

    /**
     * @return O valor atual de frame.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * @return O valor atual de frg
     */
    protected Foreground getFrg() {
        return frg;
    }

    /**
     * @param frg
     *     Redefine o valor de frg para o valor informado por parametro.
     */
    protected void setFrg(final Foreground frg) {
        this.frg = frg;
    }

    /**
     * @return O valor atual de finished.
     */
    public boolean isFinished() {
        return finished;
    }

    /**
     * @param finished
     *     Redefine o valor de finished para o valor informado por
     *     parametro.
     */
    private void setFinished(final boolean finished) {
        this.finished = finished;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#terminate()
     */
    @Override
    public void terminate() {
        try {
            // eliminando o backgrond
            for (int i = 0; i < bkg.length; i++) {
                bkg[i].terminate();
                bkg[i] = null;
            }
            // eliminando o foreground
            frg.terminate();
            // se o jogo acabou, elimina o player da memoria
            if (!isVictory()) {
                GameLogger.getInstance()
                    .addWarning("Estagio finalizado mas nao vencido. Destruindo os jogadores.", this);
                GameFactory.getInstance()
                    .getPlayer1()
                    .terminate();
                if (GameFactory.getInstance()
                    .getPlayer2() != null) {
                    GameFactory.getInstance()
                        .getPlayer2()
                        .terminate();
                }
            }
            // finalizando a instancia
            finalize();
        } catch (final Throwable e) {
            GameLogger.getInstance()
                .addError("Erro ao tentar apagar o estagio ID = " + getId() + ": " + e.getMessage(), new Exception(e), this);
        }
    }

    /**
     * @return O valor atual de victory.
     */
    public boolean isVictory() {
        return victory;
    }

    /**
     * @param victory
     *     Redefine o valor de victory para o valor informado por
     *     parametro.
     */
    public void setVictory(final boolean victory) {
        this.victory = victory;
        executeVictory();
        // informando o kernel que o estagio foi concluido
        IRenderable.gameStatus.setStageCleared(victory);
    }

    /**
     * Executa as aaaes requeridas quando o estagio a vencido (por exemplo, executar
     * uma masica ou animaaao).
     */
    public abstract void executeVictory();
}