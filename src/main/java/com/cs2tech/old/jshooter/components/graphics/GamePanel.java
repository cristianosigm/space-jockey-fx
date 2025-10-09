/**
 *
 */
package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.GameConfig;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameStatus;
import com.cs2tech.old.jshooter.gameElements.internal.MadeWithJShooter;
import javafx.scene.canvas.GraphicsContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.VolatileImage;

/**
 * <p>
 * Esta classe implementa a porta grafica central para o tratamento grafico do
 * jShooter. Executa todas as operaaaes graficas, e renderiza o resultado na
 * tela.
 * </p>
 * <p>
 * Implementa operaaaes graficas utilizando Graphics2D, operaaaes em buffer de
 * memaria, double buffering e antialiasing.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GamePanel extends Canvas {

    private static final long serialVersionUID = 4672625915646737691L;
    /**
     * Tamanho das caixas de indicaaao de vida, bomba e energia.
     */
    private static final int BOX_SIZE = 10;
    /**
     * Espaao entre as caixas de indicaaao de vida, bomba e energia.
     */
    private static final int BOX_SPACE = 2;
    /**
     * Margens das barras de indicaaao de vida, bomba e energia.
     */
    private static final int BOX_POSITION = 20;
    /**
     * Fonte usada para renderizar a pontuaaao.
     */
    private static final Font FNT_SCORE = new Font("Arial", Font.BOLD, (2 * BOX_SIZE));
    /**
     * andice da imagem para pausa.
     */
    private static final int IMG_PAUSE = 0; // fixo
    /**
     * andice da imagem para fim de estagio.
     */
    private static final int IMG_STAGECLEAR = 1; // fixo
    /**
     * andice da imagem para aviso de proximidade do chefe de fase.
     */
    private static final int IMG_BOSS = 2; // fixo
    /**
     * Gerenciador de logs.
     */
    private final Logger log = LoggerFactory.getLogger(GamePanel.class);
    /**
     * Repositorio de estados.
     */
    private final GameStatus status = GameStatus.getInstance();
    /**
     * Repositorio de configuraaaes.
     */
    private final GameConfig cfg = GameConfig.getInstance();
    /**
     * Ponteiro para imagem alocada em memaria de vadeo.
     */
    private final VolatileImage imgBuffer = null;
    /**
     * Ponteiro para porta grafica.
     */
    private final Graphics2D gfxVI = null;
    /**
     * Armazenamento de cadigo de validaaao da operaaao de alocaaao grafica.
     */
    private final int valCode = 0;
    /**
     * Buffer de operaaao grafica.
     */
    private final Graphics2D gfxBuffer = null;
    /**
     * Quadro atual.
     */
    private int frame = 0;
    /**
     * Chave para piscar elementos.
     */
    private boolean blinkOn = true;
    /**
     * Estratagia de operaaao de buffer.
     */
    private BufferStrategy strategy;
    /**
     * Logo de abertura do framework.
     */
    private MadeWithJShooter logo = null;

    /**
     * Construtor padrao.
     */
    public GamePanel() {
        setIgnoreRepaint(true);
        log.info("Game Panel created.", this);
    }

    /**
     * Matodo principal para renderizaaao.
     */
    public void draw() {
        final GameFactory fac = GameFactory.getInstance();

        try {
            //            if (gfxBuffer == null) {
            //                this.createBufferStrategy(4);
            //                strategy = getBufferStrategy();
            //                gfxBuffer = (Graphics2D) strategy.getDrawGraphics();
            //            }
            // ----------------------------------------------------------------------------------------------------------------------------
            // Volatile Buffer
            //            if (imgBuffer == null) {
            //                imgBuffer = createVolatileImage(getWidth(), getHeight());
            //            }
            //            if (gfxVI == null) {
            //                final GraphicsContext gc = canvas.getGraphicsContext2D();
            //                valCode = imgBuffer.validate(gc);
            //                if (valCode == VolatileImage.IMAGE_INCOMPATIBLE) {
            //                    imgBuffer = createVolatileImage(getWidth(), getHeight());
            //                }
            //                gfxVI = imgBuffer.createGraphics();
            //                final RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
            //                    RenderingHints.VALUE_ANTIALIAS_OFF);
            //                hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            //                hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            //                hints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            //                hints.put(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            //                gfxVI.setRenderingHints(hints);
            //            }
            // ----------------------------------------------------------------------------------------------------------------------------
            if ((!status.isPlayingMovie()) && (!status.isAtMenu()) && (!status.isPaused()) && (!status.isRunningLogo()) && (status.isAtStage())) {
                // renderiza o estagio
                fac.getCurrentStage()
                    .draw(gfxVI, this);
                // renderiza todo mundo
                fac.getActors()
                    .draw(gfxVI, this);
                // renderiza a matriz de colisao
                if (cfg.isRederCollision()) {
                    fac.getColision()
                        .draw(gfxVI);
                }
                // renderiza informaaaes na tela
                if (cfg.isRederOnScreen()) {
                    gfxVI.setColor(Color.YELLOW);
                    gfxVI.drawString("REFRESH RATE: " + cfg.getFrameRate(), 20, getHeight() - 30);
                    gfxVI.drawString("RESOLUCAO: " + getWidth() + "x" + getHeight(), 20, getHeight() - 10);
                }
                // renderiza as vidas e pontos do jogador
                drawGameInfo(fac);
                // exibe mensagem de estagio finalizado, caso esteja finalizado
                if (status.isStageCleared()) {
                    drawStageClear();
                }
            } else if ((!status.isPlayingMovie()) && (!status.isAtMenu()) && (!status.isRunningLogo()) && (status.isPaused())) {
                // game paused
                drawPause();
            } else if (status.isRunningLogo()) {
                // executando o logo
                if (logo == null) {
                    logo = new MadeWithJShooter();
                } else if (!logo.isFinished()) {
                    logo.draw(gfxVI, this);
                } else {
                    status.setRunningLogo(false);
                    logo.terminate();
                }
            } else if (status.isPlayingMovie()) {
                fac.getCurrentMovie()
                    .draw(gfxVI, this);
            } else if (status.isAtMenu()) {
                fac.getCurrentMenu()
                    .draw(gfxVI, this);
            } else {
                log.info(" --> OPaaO INVALIDA!", this);
            }
            if ((!status.isPlayingMovie()) && (!status.isAtMenu()) && (status.isAtWarning())) {
                // aviso de chefe
                drawWarning();
            }
            // renderizando do buffer pra tela
            gfxBuffer.drawImage(imgBuffer, 0, 0, this);
            strategy.show();
        } catch (final Exception e) {
            log.error("Erro ao tentar desenhar o frame atual: " + e.getMessage(), e, this);
        }
    }

    /**
     * Desenha as informaaaes do jogo atual (vidas, pontos, etc).
     */
    private void drawGameInfo(final GameFactory fac) {
        //__________________________________________________________________
        // VIDAS
        gfxVI.setColor(Color.white);
        for (byte i = 0; i < cfg.getNumberOfLives(); i++) {
            if (i >= fac.getPlayer1()
                .getLives()) {
                gfxVI.drawRect((i * (BOX_SIZE + BOX_SPACE)) + BOX_POSITION, BOX_POSITION, BOX_SIZE, BOX_SIZE);
            } else {
                gfxVI.fillRect((i * (BOX_SIZE + BOX_SPACE)) + BOX_POSITION, BOX_POSITION, BOX_SIZE, BOX_SIZE);
            }
        }
        //__________________________________________________________________
        // ENERGIA
        gfxVI.setColor(Color.green);
        for (byte i = 0; i < fac.getPlayer1()
            .getMaxEnergy(); i++) {
            if (i >= fac.getPlayer1()
                .getEnergy()) {
                gfxVI.drawRect((i * (BOX_SIZE + BOX_SPACE)) + BOX_POSITION, BOX_POSITION + BOX_SIZE + BOX_SPACE, BOX_SIZE, BOX_SIZE);
            } else {
                gfxVI.fillRect((i * (BOX_SIZE + BOX_SPACE)) + BOX_POSITION, BOX_POSITION + BOX_SIZE + BOX_SPACE, BOX_SIZE, BOX_SIZE);
            }
        }
        //__________________________________________________________________
        // BOMBAS
        gfxVI.setColor(Color.red);
        for (byte i = 0; i < fac.getPlayer1()
            .getBombs(); i++) {
            gfxVI.fillArc((i * (BOX_SIZE + BOX_SPACE)) + BOX_POSITION, BOX_POSITION + 2 * (BOX_SIZE + BOX_SPACE), BOX_SIZE, BOX_SIZE, 0, 360);
        }
        //__________________________________________________________________
        // PONTOS
        gfxVI.setColor(Color.white);
        gfxVI.setFont(FNT_SCORE);
        gfxVI.drawString("" + fac.getPlayer1()
            .getScore(), BOX_POSITION, BOX_POSITION + 4 * (BOX_SIZE + BOX_SPACE + BOX_SPACE));
        //__________________________________________________________________
    }

    /**
     * <p>
     * Desenha a mensagem de ESTaGIO COMPLETO.
     * </p>
     */
    private void drawStageClear() {
        // game paused
        gfxVI.drawImage(ImageRepository.getInstance()
            .getImage(GamePanel.IMG_STAGECLEAR), cfg.getGameResolution().width / 2 - (ImageRepository.getInstance()
            .getImage(GamePanel.IMG_STAGECLEAR)
            .getWidth() / 2), cfg.getGameResolution().height / 2 - (ImageRepository.getInstance()
            .getImage(GamePanel.IMG_STAGECLEAR)
            .getHeight() / 2), this);
    }

    /**
     * <p>
     * Desenha a mensagem de PAUSA.
     * </p>
     */
    private void drawPause() {
        // game paused
        gfxVI.drawImage(ImageRepository.getInstance()
            .getImage(GamePanel.IMG_PAUSE), cfg.getGameResolution().width / 2 - (ImageRepository.getInstance()
            .getImage(GamePanel.IMG_PAUSE)
            .getWidth() / 2), cfg.getGameResolution().height / 2 - (ImageRepository.getInstance()
            .getImage(GamePanel.IMG_PAUSE)
            .getHeight() / 2), this);
    }

    /**
     * <p>
     * Desenha a mensagem de ESTaGIO COMPLETO.
     * </p>
     */
    private void drawWarning() {
        // a cada quarto de segundo, uma posiaao no blink
        if (frame % (cfg.getFrameRate() / 2) == 0) {
            // alternando
            blinkOn = !blinkOn;
        }
        // renderizando aviso
        if (blinkOn) {
            gfxVI.drawImage(ImageRepository.getInstance()
                .getImage(GamePanel.IMG_BOSS), cfg.getGameResolution().width / 2 - (ImageRepository.getInstance()
                .getImage(GamePanel.IMG_BOSS)
                .getWidth() / 2), cfg.getGameResolution().height / 2 - (ImageRepository.getInstance()
                .getImage(GamePanel.IMG_BOSS)
                .getHeight() / 2), this);
        }
        // finalizando aviso
        if (frame > cfg.getFrameRate() * 5) {
            frame = -1;
            blinkOn = true;
            status.setAtWarning(false);
        }
        frame++;
    }

    /**
     * @return Buffer grafico.
     */
    public Graphics2D getGfxBuffer() {
        return gfxBuffer;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Canvas#paint(java.awt.Graphics)
     */
    @Override
    public void paint(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Canvas#update(java.awt.Graphics)
     */
    @Override
    public void update(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#print(java.awt.Graphics)
     */
    @Override
    public void print(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#paintAll(java.awt.Graphics)
     */
    @Override
    public void paintAll(final Graphics gfx) {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#repaint()
     */
    @Override
    public void repaint() {
    }

    /*
     * (non-Javadoc)
     *
     * @see java.awt.Component#printAll(java.awt.Graphics)
     */
    @Override
    public void printAll(final Graphics gfx) {
    }
}
