package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.Directions;

import java.awt.*;

/**
 * <p>
 * Esta classe encapsula um objeto grafico genarico, implementado todos os
 * comportamentos comuns e definindo todos os atributos.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class GraphicObject extends BasicGraphics {

    /**
     * Composiaao de transparancia nula (opacidade = 100%)
     */
    private final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    /**
     * Direaao do movimento para este objeto.
     */
    private byte direction;
    /**
     * Frame atual.
     */
    private int frame;
    /**
     * Transparancia.
     */
    private float alpha = 1.0f;
    /**
     * Frame de animaaao atual.
     */
    private byte currentAnimationFrame = 0;
    /**
     * andice do sprite atualmente em uso.
     */
    private byte currentSprite = 0;
    /**
     * Posiaao do objeto grafico na tela.
     */
    private Point position;
    /**
     * Vetor de sprites para este objeto.
     */
    private int[] sprites;
    /**
     * Namero de frames para aguardar apas a troca de sprite ata a praxima
     * troca.
     */
    private byte spriteInterval = 0;
    /**
     * Velocidade de movimento deste objeto (em pixels/frame).
     */
    private int pixelsPerFrame;
    /**
     * Velocidade de movimento deste objeto (em pixels/segundo).
     */
    private int pixelsPerSecond;
    /**
     * Intervalo de ajuste de framerate.
     */
    private byte intLength;
    /**
     * Contador do valor atual de intervalo.
     */
    private byte intValue;
    /**
     * <b>True</b> se este objeto for animado (ou seja, se possuir mais de um
     * sprite).
     */
    private boolean animated = false;
    /**
     * habilita ou desabilita a movimentaaao do objeto.
     */
    private boolean movementEnabled = true;
    /**
     * Composiaao de transparancia parcial ou total.
     */
    private AlphaComposite transparent;
    /**
     * Contador de quadros do movimento senoidal.
     */
    private int sinFrame;

    /**
     * Tamanho do deslocamento (em pixels) do movimento senoidal.
     */
    private int sinMovementWidth = 0;

    /**
     * Intervalo entre cada atualizaaao na posiaao senoidal.
     */
    private int sinMovementInterval = 10;

    /**
     * Construtor padrao.
     *
     * @param position
     *     Posiaao inicial do objeto na tela.
     * @param size
     *     Tamanho do objeto.
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em frames) entre os sprites.
     * @param pixelsPerFrame
     *     Velocidade de movimento do objeto (em pixels/seg).
     */
    public GraphicObject(final Point position, final Dimension size, final int[] sprites, final byte spriteInterval, final int speed) {
        this.position = position;
        setSize(size);
        this.spriteInterval = spriteInterval;
        this.sprites = sprites;
        // Sincronizando o objeto grafico para o framerate atual.
        setPixelsPerSecond(speed);
        if (sprites.length > 1) {
            animated = true;
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
        if (isMovementEnabled()) {
            move();
            // movimento senoidal
            if (sinMovementWidth > 0) {
                if (sinFrame > 360) {
                    sinFrame = 0;
                }
                if (frame % sinMovementInterval == 0) {
                    sinFrame++;
                }
                if (direction == Directions.DIR_DOWN) {
                    position.x += (Math.cos(IRenderable.gameUtils.getRad(sinFrame)) * sinMovementWidth);
                } else if (direction == Directions.DIR_UP) {
                    position.x += (Math.cos(IRenderable.gameUtils.getRad(sinFrame)) * sinMovementWidth);
                } else if (direction == Directions.DIR_LEFT) {
                    position.y += (Math.cos(IRenderable.gameUtils.getRad(sinFrame)) * sinMovementWidth);
                } else if (direction == Directions.DIR_RIGHT) {
                    position.y += (Math.cos(IRenderable.gameUtils.getRad(sinFrame)) * sinMovementWidth);
                } else {
                    // nada a fazer
                }
            }
        }
        // animando o objeto
        if (animated) {
            if (currentAnimationFrame < spriteInterval) {
                // aguardando
                currentAnimationFrame++;
            } else {
                // intervalo alcanaado. Mudando a figura e reiniciando contador
                if (currentSprite < sprites.length - 1) {
                    currentSprite++;
                } else {
                    currentSprite = 0;
                }
                currentAnimationFrame = 0;
            }
        }
        // manipulando transparencia
        if (alpha < 1) {
            gfx.setComposite(transparent);
        }
        // renderizando
        gfx.drawImage(IRenderable.imageRepository.getImage(sprites[currentSprite]), position.x, position.y, getSize().width, getSize().height, cnv);
        // de volta ao opaco
        if (alpha < 1) {
            gfx.setComposite(opaque);
        }
    }

    /**
     * @return O valor atual de movementEnabled.
     */
    protected boolean isMovementEnabled() {
        return movementEnabled;
    }

    /**
     * @param movementEnabled
     *     Redefine o valor de movementEnabled para o valor do parametro.
     */
    protected void setMovementEnabled(final boolean movementEnabled) {
        this.movementEnabled = movementEnabled;
    }

    /**
     * Manipula a movimentaaao deste objeto.
     */
    protected abstract void move();

    /**
     * @return O valor atual de alpha.
     */
    public float getAlpha() {
        return alpha;
    }

    /**
     * @return Ponto central deste objeto.
     */
    public Dimension getCenter() {
        return new Dimension(position.x + (getSize().width / 2), position.y + (getSize().height / 2));
    }

    /**
     * @return O valor atual de direction.
     */
    public byte getDirection() {
        return direction;
    }

    /**
     * @param direction
     *     Redefine o valor de direction para o valor do parametro.
     */
    public void setDirection(final byte direction) {
        this.direction = direction;
    }

    /**
     * @return O valor atual de frame.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * <p>
     * Este metodo utiliza o ajuste definido pelo metodo setSpeed() para
     * adicionar um pixel a cada numero de frames definido no ajuste (ver
     * definicao em setSpeed())
     * </p>
     *
     * @return Numero de pixels a deslocar o objeto para cada frame.
     */
    public int getPixelsPerFrame() {
        if ((intValue > -1) && (intValue == intLength)) {
            intValue = 0;
            return (pixelsPerFrame + 1);
        } else if (intValue > -1) {
            intValue++;
            return pixelsPerFrame;
        } else {
            return pixelsPerFrame;
        }
    }

    /**
     * @return Numero de pixels por segundo setado pela ultima vez.
     */
    public int getPixelsPerSecond() {
        return pixelsPerSecond;
    }

    /**
     * <p>
     * Este metodo transforma o valor da velocidade informado em Pixels/Segundo
     * no seu valor correspondente em pixels/frame, para que seja armazenado
     * corretamente e utilizado pelo renderizador. Para isso, ele faz primeiro a
     * transformaaao de px/s para px/f e, em seguida, calcula o ajuste
     * necessario - um pixel adicional a cada X frames, representado pelo valor
     * intLength - para aproximar o maximo possivel do valor solicitado.
     * </p>
     *
     * @param newSpeed
     *     Velocidade do objeto, em pixels/segundo.
     */
    public void setPixelsPerSecond(final int newSpeed) {
        pixelsPerSecond = newSpeed;
        final double factor = newSpeed / IRenderable.gameConfig.getFrameRate();
        // salvando a velocidade atual
        pixelsPerFrame = (int) Math.floor(factor);
        // calculando o ajuste
        if ((factor - pixelsPerFrame) != 0) {
            intLength = (byte) Math.round((1 / (factor - pixelsPerFrame)));
        } else {
            intLength = -1;
        }
    }

    /**
     * @return O valor atual de position.
     */
    public Point getPosition() {
        return position;
    }

    /**
     * @param position
     *     Redefine o valor de position para o valor do parametro.
     */
    public void setPosition(final Point position) {
        this.position = position;
    }

    /**
     * @return O valor atual de sinMovementWidth.
     */
    protected int getSinMovementWidth() {
        return sinMovementWidth;
    }

    /**
     * @param sinMovementWidth
     *     Redefine o valor de sinMovementWidth para o valor do
     *     parametro.
     */
    protected void setSinMovementWidth(final int sinMovementWidth) {
        this.sinMovementWidth = sinMovementWidth;
    }

    /**
     * @return O valor atual de spriteInterval
     */
    public byte getSpriteInterval() {
        return spriteInterval;
    }

    /**
     * @param spriteInterval
     *     Redefine o valor de spriteInterval para o valor do parametro.
     */
    public void setSpriteInterval(final byte spriteInterval) {
        this.spriteInterval = spriteInterval;
    }

    /**
     * @return O valor atual de sprites
     */
    public int[] getSprites() {
        return sprites;
    }

    /**
     * @param sprites
     *     Redefine o valor de sprites para o valor do parametro.
     */
    public void setSprites(final int[] sprites) {
        this.sprites = sprites;
    }

    /**
     * Proximo frame.
     */
    protected void nextFrame() {
        frame++;
    }

    /**
     * Desliga a transparancia deste objeto.
     */
    public void setOpaque() {
        alpha = 1.0f;
    }

    /**
     * @param sinMovementInterval
     *     Redefine o valor de sinMovementInterval para o valor do
     *     parametro.
     */
    protected void setSinMovementInterval(final int sinMovementInterval) {
        this.sinMovementInterval = sinMovementInterval;
    }

    /**
     * <p>
     * Define este objeto como transparente.
     * </p>
     *
     * @param alpha
     *     Navel de transparancia entre <b>0.0</b> e <b>1.0</b>, onde:
     *     <ul>
     *     <li><b>0.0f</b> significa totalmente transparente; e</li>
     *     <li><b>1.0f</b> significa totalmente opaco.</li>
     *     </ul>
     */
    public void setTransparent(final float alpha) {
        this.alpha = alpha;
        transparent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha);
    }

    /**
     * Acelera o movimento senoidal.
     */
    protected void skipSinFrame(final int skipSize) {
        sinFrame += skipSize;
    }
}
