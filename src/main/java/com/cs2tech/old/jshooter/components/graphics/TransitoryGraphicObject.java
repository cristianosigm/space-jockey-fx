package com.cs2tech.old.jshooter.components.graphics;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;

import java.awt.*;

/**
 * <p>
 * Esta subclasse especializa um <code>CollisionObject</code> para todos os
 * objetos que suportam colisao e sao transitarios (ou seja, que deixam de
 * existir ao saarem da tela).
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class TransitoryGraphicObject extends CollisionObject {

    /**
     * Margens para geraaao de elementos na tela.
     */
    private static final byte GENERATION_MARGINS = 100;

    /**
     * <p>
     * Construtor alternativo.
     * </p>
     * <p>
     * Gera automaticamente sua prapria posiaao inicial, no topo da tela.
     * </p>
     *
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em <i>frames</i>) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em <i>pixels/seg</i>).
     * @param matrix
     *     Matriz de colisao para este objeto.
     * @param collisionType
     *     Tipo de colisao para este objeto (utilize os atributos
     *     estaticos <b>CollisionHandler.<i>TYPE_*</i></b> para
     *     selecionar um valor valido).
     * @param direction
     *     Direaao de movimento deste objeto (utilize os atributos
     *     estaticos <b>GameFactory.<i>DIR_*</i></b> para selecionar um
     *     valor valido).
     */
    public TransitoryGraphicObject(final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final byte collisionType, final byte direction) {
        super(TransitoryGraphicObject.getRandomInitialPoint(), sprites, spriteInterval, speed, matrix, collisionType);
        setDirection(direction);
    }

    /**
     * @return Uma posiaao inicial randamica.
     */
    private static Point getRandomInitialPoint() {
        return new Point(IRenderable.gameUtils.getRandomXPosition(TransitoryGraphicObject.GENERATION_MARGINS,
            IRenderable.gameConfig.getGameResolution().width - TransitoryGraphicObject.GENERATION_MARGINS), -GameFactory.getInstance()
            .getColision()
            .getCollisionBlockSize().height + 1);
    }

    /**
     * Construtor padrao.
     *
     * @param position
     *     Posiaao inicial do objeto na tela.
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em frames) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em pixels/seg).
     * @param matrix
     *     Matriz de colisao para este objeto.
     * @param collisionType
     *     Tipo de colisao para este objeto (utilize os atributos
     *     estaticos <b>CollisionHandler.<i>TYPE_*</i></b> para
     *     selecionar um valor valido).
     * @param direction
     *     Direaao de movimento deste objeto (utilize os atributos
     *     estaticos <b>GameFactory.<i>DIR_*</i></b> para selecionar um
     *     valor valido).
     */
    public TransitoryGraphicObject(final Point position, final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final byte collisionType,
                                   final byte direction) {
        super(position, sprites, spriteInterval, speed, matrix, collisionType);
        setDirection(direction);
    }

    /**
     * Verifica se o objeto esta fora da tela. Se estiver, ele sera destruado
     * automaticamente.
     */
    public void checkOutOfScreen() {
        if ((getPosition().y > IRenderable.gameConfig.getGameResolution().height) || (getPosition().y + getSize().height < 0) || (getPosition().x + getSize().width < 0) || (getPosition().x > IRenderable.gameConfig.getGameResolution().width)) {
            // actor is out of screen
            try {
                die();
            } catch (final Throwable e) {
                IRenderable.log.addError("Erro ao tentar destruir uma instancia de TransitoryGraphicObject: " + e.getMessage(), new Exception(e), this);
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.CollisionObject#getBonus()
     */
    @Override
    public int getBonus() {
        return 0;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.GraphicObject#move()
     */
    @Override
    protected void move() {
        IRenderable.gamePhisics.linearMovement(this);
    }
}
