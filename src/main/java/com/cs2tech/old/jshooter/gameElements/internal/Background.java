package com.cs2tech.old.jshooter.gameElements.internal;

import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.graphics.BasicGraphics;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Classe que implementa as aaaes relacionadas ao background.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Background extends BasicGraphics {

    /**
     * Tamanho do buffer de imagens para carregar.
     */
    private static final int BUFFER_SIZE = 5;
    /**
     * Imagem a ser renderizada apas a praxima imagem ser transferida para a
     * posiaao atual.
     */
    private final int[] buffer = new int[Background.BUFFER_SIZE];
    /**
     * Posiaao atual do background na tela.
     */
    private final Point position;
    /**
     * andice atual da imagem de background.
     */
    private int current = -1;
    /**
     * Praxima imagem de background.
     */
    private int next = -1;
    /**
     * Contador para vasculhar o buffer.
     */
    private int pos = 0;
    /**
     * Direaao da movimentaaao do background.
     */
    private byte direction;
    /**
     * Velocidade de movimento do background.
     */
    private byte speed;
    /**
     * Intervalo de ajuste de framerate.
     */
    private byte intLength;
    /**
     * Contador do valor atual de intervalo.
     */
    private byte intValue;

    /**
     * Construtor padrao.
     *
     * @param imageIndex
     *     andice da imagem de background inicial.
     * @param direction
     *     Direaao do background.
     * @param speed
     *     Velocidade de movimento (em pixels/segundo).
     */
    public Background(final int imageIndex, final byte direction, final double speed) {
        IRenderable.log.addDebug("INICIALIZANDO UM BACKGROUND. IMAGEM: " + imageIndex, this);
        // inicializando o buffer
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = -1;
        }
        addImage(imageIndex);
        this.direction = direction;
        setSpeed(speed);
        position = new Point(0, 0);
    }

    /**
     * Adiciona uma nova imagem de background. A nova imagem sera carregada no
     * praximo ciclo de background.
     *
     * @param imageIndex
     *     andice da nova imagem.
     */
    public void addImage(final int imageIndex) {
        IRenderable.log.addWarning("ADICIONANDO UM BACKGROUND NA LISTA: " + imageIndex, this);
        if (current == -1) {
            // setando a primeira imagem
            current = imageIndex;
            next = imageIndex;
        } else {
            while (!(buffer[pos] == -1)) {
                pos++;
            }
            buffer[pos] = imageIndex;
            pos = 0;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        if (getSpeed() > 0) {
            // animando
            if (direction == Directions.DIR_UP) {
                position.y -= getSpeed();
            } else if (direction == Directions.DIR_LEFT) {
                position.x -= getSpeed();
            } else if (direction == Directions.DIR_DOWN) {
                position.y += getSpeed();
            } else if (direction == Directions.DIR_RIGHT) {
                position.x += getSpeed();
            }
            // reciclando imagens
            if (((direction == Directions.DIR_UP) && (position.y <= 0)) || ((direction == Directions.DIR_DOWN) && (position.y >= cnv.getSize().height)) || ((direction == Directions.DIR_LEFT) && (position.x <= 0)) || ((direction == Directions.DIR_RIGHT) && (position.x >= cnv.getSize().width))) {
                // trocando imagens
                current = next;
                if (buffer[0] > -1) {
                    next = buffer[0];
                    // movendo o buffer uma casa p. esquerda
                    while (!(buffer[pos] == -1) && (pos < buffer.length - 1)) {
                        buffer[pos] = buffer[pos + 1];
                        pos++;
                    }
                    pos = 0;
                }
                // reposicionando
                if (direction == Directions.DIR_UP) {
                    position.y = cnv.getSize().height;
                } else if (direction == Directions.DIR_LEFT) {
                    position.x = cnv.getSize().width;
                } else if (direction == Directions.DIR_DOWN) {
                    position.y = 0;
                } else if (direction == Directions.DIR_RIGHT) {
                    position.x = 0;
                }
            }
            // renderizando
            gfx.drawImage(IRenderable.imageRepository.getImage(next),
                ((direction == Directions.DIR_LEFT) || (direction == Directions.DIR_RIGHT) ? position.x - cnv.getSize().width : position.x),
                ((direction == Directions.DIR_UP) || (direction == Directions.DIR_DOWN) ? position.y - cnv.getSize().height : position.y), cnv.getSize().width,
                cnv.getSize().height, cnv);
            gfx.drawImage(IRenderable.imageRepository.getImage(current), position.x, position.y, cnv.getSize().width, cnv.getSize().height, cnv);
        } else {
            // background nao a animado
            gfx.drawImage(IRenderable.imageRepository.getImage(current), 0, 0, cnv.getSize().width, cnv.getSize().height, cnv);
        }
    }

    /**
     * @return Numero de pixels a deslocar o objeto para cada frame.
     */
    public byte getSpeed() {
        if (intValue == intLength) {
            intValue = 0;
            return (byte) (speed + 1);
        }
        intValue++;
        return speed;
    }

    /**
     * @param newSpeed
     *     Velocidade do objeto, em pixels/segundo.
     */
    public void setSpeed(final double newSpeed) {
        final double factor = newSpeed / IRenderable.gameConfig.getFrameRate();
        // salvando a velocidade atual
        speed = (byte) Math.floor(factor);
        // calculando o ajuste
        intLength = (byte) Math.round((1 / (factor - speed)));
        IRenderable.log.addDebug("Nova velocidade de background definida: " + "speed = " + speed + "px/f; ajuste = 1 pixel a cada " + intLength + " frames", this);
    }

    /**
     * @return Direaao atual do background.
     */
    public byte getDirection() {
        return direction;
    }

    /**
     * @param direction
     *     Muda a direaao atual do background.
     */
    public void setDirection(final byte direction) {
        this.direction = direction;
    }
}
