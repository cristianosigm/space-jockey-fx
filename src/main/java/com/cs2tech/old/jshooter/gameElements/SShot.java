package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.TransitoryGraphicObject;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;

import java.awt.*;

/**
 * <p>
 * Superclasse para todos os tiros do jogo.
 * </p>
 * <p>
 * Basicamente, o programador do jogo deve herdar esta classe e instanciar sua
 * classe de acordo com as instruaaes no matodo construtor.
 * </p>
 * <p>
 * Adicionalmente, o programador pode adicionar animaaao aos tiros,
 * sobrescrevendo o matodo <code>draw()</code> para manipular a troca de imagens
 * e chamar o matodo <code>super.draw()</code> para executar as operaaaes
 * basicas.
 * </p>
 * <p>
 * Para instancias que usam somente uma anica imagem, basta definir a anica
 * imagem passando o andice dela atravas do matodo
 * <code>setCurrentSpriteIndex(int)</code> dentro do construtor da classe filha.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SShot extends TransitoryGraphicObject {

    /**
     * Poder destrutivo do tiro (ou seja, quantos pontos de energia serao
     * tirados do objeto atingido, caso haja colisao).
     */
    private byte hitPower;

    /**
     * Construtor padrao.
     *
     * @param initialPos
     *     Posiaao inicial do objeto na tela.
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spritesInterval
     *     Intervalo (em frames) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em pixels/seg).
     * @param matrix
     *     Matriz de colisao para este objeto.
     * @param enemyShot
     *     Indica se o tiro foi disparado por um inimigo (<b>True</b>)ou
     *     pelo jogador (<b>False</b>).
     * @param direction
     *     Direaao de movimento deste objeto (utilize os atributos
     *     estaticos <b>GameFactory.<i>DIR_*</i></b> para selecionar um
     *     valor valido).
     * @param hitPower
     *     Poder destrutivo do tiro (ou seja, quantos pontos de energia
     *     serao tirados do objeto atingido, caso haja colisao).
     */
    public SShot(final Point initialPos, final int[] sprites, final byte spritesInterval, final int speed, final boolean[][] matrix, final boolean enemyShot, final byte direction,
                 final byte hitPower) {
        super(initialPos, sprites, spritesInterval, speed, matrix, (enemyShot ? CollisionHandler.TYPE_ENEMY_SHOT : CollisionHandler.TYPE_PLAYER_SHOT), direction);
        this.hitPower = hitPower;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#die()
     */
    @Override
    public void die() {
        IRenderable.log.addDebug("Tiro saiu da tela. Auto-destruiaao acionada.", this);
        terminate();
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(byte) (not used
     * for shots)
     */
    @Override
    public void takeHit(final int hitPower) {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(byte) (not used
     * for shots)
     */
    @Override
    public void takeHit(final int hitPower, final boolean noSound) {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooterOLD.kernel.elements.GameActor#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        super.draw(gfx, cnv);
        checkOutOfScreen();
    }

    /**
     * @return Namero de pontos de energia que este tiro decresce do objeto
     * atingido.
     */
    public byte getHitPower() {
        return hitPower;
    }

    /**
     * @param hitPower
     *     Redefine o namero de pontos de energia que este tiro decresce
     *     do objeto atingido.
     */
    public void setHitPower(final byte hitPower) {
        this.hitPower = hitPower;
    }
}
