package com.cs2tech.old.jshooter.gameElements.internal;

import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.graphics.BasicGraphics;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Classe que implementa as aaaes relacionadas ao foreground.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Foreground extends BasicGraphics {

    /**
     * Tamanho do buffer de imagens para carregar.
     */
    private static final int BUFFER_SIZE = 10;
    /**
     * Composiaao de transparancia nula (opacidade = 100%).
     */
    private final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    /**
     * Imagem a ser renderizada apas a praxima imagem ser transferida para a
     * posiaao atual.
     */
    private final int[] buffer = new int[Foreground.BUFFER_SIZE];
    /**
     * Posiaao atual do foreground na tela.
     */
    private final Point position;
    /**
     * Opacidade do elemento.
     */
    private float alpha = 1.0f;
    /**
     * Composiaao de transparancia parcial ou total.
     */
    private AlphaComposite transparent;
    /**
     * andice atual da imagem de foreground.
     */
    private int current = -1;
    /**
     * Praxima imagem de foreground.
     */
    private int next = -1;
    /**
     * Contador para vasculhar o buffer.
     */
    private int pos = 0;
    /**
     * Direaao da movimentaaao do foreground.
     */
    private byte direction;
    /**
     * Velocidade de movimento do foreground.
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
     * Frame atual
     */
    private int frame;

    /**
     * Construtor padrao.
     *
     * @param imageIndex
     *     andice da imagem de foreground inicial.
     * @param direction
     *     Direaao do foreground.
     * @param speed
     *     Velocidade de movimento (em pixels/segundo).
     * @param opacity
     *     Opacidade do foreground.
     */
    public Foreground(final int imageIndex, final byte direction, final double speed, final float opacity) {
        IRenderable.log.addDebug("INICIALIZANDO UM FOREGROUND. IMAGEM: " + imageIndex, this);
        // inicializando o buffer
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = -1;
        }
        addImage(imageIndex);
        this.direction = direction;
        setSpeed(speed);
        position = new Point(0, 0);
        setTransparent(opacity);
    }

    /**
     * Adiciona uma nova imagem de foreground. A nova imagem sera carregada no
     * praximo ciclo de foreground.
     *
     * @param imageIndex
     *     andice da nova imagem.
     */
    public void addImage(final int imageIndex) {
        IRenderable.log.addWarning("ADICIONANDO UM FOREGROUND NA LISTA: " + imageIndex, this);
        if (current == -1) {
            // setting the first image
            current = imageIndex;
            next = imageIndex;
        } else {
            while (!(buffer[pos] < 0)) {
                pos++;
            }
            if (pos < buffer.length) {
                buffer[pos] = imageIndex;
            }
            pos = 0;
        }
    }

    /**
     * <p>
     * Define este objeto como transparente.
     * </p>
     *
     * @param alpha
     *     Navel de transparancia. <b>0.0f</b> significa totalmente
     *     transparente, <b>1.0f</b> significa totalmente opaco.
     */
    public void setTransparent(final float alpha) {
        this.alpha = alpha;
        transparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.IRenderable#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        frame++;
        // animating
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
                while (!(buffer[pos] < 0) && (pos < buffer.length - 1)) {
                    buffer[pos] = buffer[pos + 1];
                    pos++;
                }
                pos = 0;
                // reset do contador de frames
                frame = 0;
            } else {
                //next = -1;
                next = current;
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
        // manipulando transparencia
        if (alpha < 1) {
            gfx.setComposite(transparent);
        }
        // renderizando elementos
        if (next >= 0) {
            gfx.drawImage(IRenderable.imageRepository.getImage(next),
                ((direction == Directions.DIR_LEFT) || (direction == Directions.DIR_RIGHT) ? position.x - cnv.getSize().width : position.x),
                ((direction == Directions.DIR_UP) || (direction == Directions.DIR_DOWN) ? position.y - cnv.getSize().height : position.y), cnv.getSize().width,
                cnv.getSize().height, cnv);
        }
        if (current >= 0) {
            gfx.drawImage(IRenderable.imageRepository.getImage(current), position.x, position.y, cnv.getSize().width, cnv.getSize().height, cnv);
        }
        // voltando para a opacidade normal
        if (alpha < 1) {
            gfx.setComposite(opaque);
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
        IRenderable.log.addDebug("Nova velocidade de foreground definida: " + "speed = " + speed + "px/f; ajuste = 1 pixel a cada " + intLength + " frames", this);
    }

    /**
     * @return Direaao atual do foreground.
     */
    public byte getDirection() {
        return direction;
    }

    /**
     * @param direction
     *     Muda a direaao atual do foreground.
     */
    public void setDirection(final byte direction) {
        this.direction = direction;
    }
}
