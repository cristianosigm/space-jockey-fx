package com.cs2tech.old.jshooter.gameCore.kernel.collision;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.GraphicObject;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Esta classe define todos os comportamentos e atributos compartilhados por
 * todas as classes de objetos que podem sofrer colisao.
 * </p>
 * <p>
 * O tratamento de colisao a feito automaticamente atravas desta classe;
 * portanto, os objetos passaveis de colisao precisam apenas herdar esta classe
 * e definir, atravas do construtor, qual o tipo de objeto de colisao deve ser
 * instanciado.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class CollisionObject extends GraphicObject implements IHitable {

    /**
     * Elementos auxiliares para a definiaao da Matriz de Colisao.
     */
    public static final boolean X = true, O = false;

    /**
     * A matriz de colisao deste objeto.
     */
    private boolean[][] matrix;

    /**
     * O tipo de colisao deste objeto.
     */
    private byte type;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     * <p>
     * Cria um objeto grafico padrao, capaz de tratar colisaes.
     * </p>
     * <p>
     * O construtor da classe final <b>DEVE</b> definir uma matriz de colisao,
     * caso contrario o objeto nunca vai colidir com nada.
     * </p>
     *
     * @param initialPos
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
     */
    public CollisionObject(final Point initialPos, final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final byte collisionType) {
        super(initialPos, new Dimension(matrix[0].length * GameFactory.getInstance()
            .getColision()
            .getCollisionBlockSize().width, matrix.length * GameFactory.getInstance()
            .getColision()
            .getCollisionBlockSize().height), sprites, spriteInterval, speed);
        this.matrix = matrix;
        type = collisionType;
        IRenderable.log.addDebug(
            "Novo objeto de colisao criado. Tamanho = " + getSize().width + "x" + getSize().height + "px. Posiaao inicial = (" + initialPos.x + "," + initialPos.y + ").", this);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.GraphicObject#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        super.draw(gfx, cnv);
        testCollision();
    }

    /**
     * <p>
     * Testa se ocorreu uma colisao com este objeto. Caso positivo, repassa a
     * informaaao de colisao para a instancia de <code>CollisionHandler</code>,
     * responsavel por tomar as providancias conforme a colisao ocorrida.
     * </p>
     */
    private void testCollision() {
        final byte colided = GameFactory.getInstance()
            .getColision()
            .getCollision(this);
        if (colided > 0) {
            GameFactory.getInstance()
                .getColHandler()
                .handleCollision(this, (CollisionObject) GameFactory.getInstance()
                    .getActors()
                    .get(colided));
        }
    }

    /**
     * @return O banus que o jogador deve receber por destruir este objeto.
     */
    public abstract int getBonus();

    /**
     * @return O valor atual de matrix
     */
    public boolean[][] getMatrix() {
        return matrix;
    }

    /**
     * @param matrix
     *     Redefine o valor de matrix para o valor do parametro.
     */
    public void setMatrix(final boolean[][] matrix) {
        this.matrix = matrix;
    }

    /**
     * @return O valor atual de type
     */
    public byte getType() {
        return type;
    }

    /**
     * @param type
     *     Redefine o valor de type para o valor do parametro.
     */
    public void setType(final byte type) {
        this.type = type;
    }
}
