package com.cs2tech.old.jockey.actors;

import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jockey.stages.Cenario;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.SPlayer;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa a nave controlada pelo jogador.
 * </p>
 * <p>
 * Neste jogo, somente s�o usados os movimentos verticais e o bot�o de tiro.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class MyPlayer extends SPlayer {

    /**
     * Transpar�ncia a ser aplicada para indicar invencibilidade.
     */
    private static final float TRANSPARENCY = 0.5f;
    /**
     * Vetor contendo os sprites deste objeto.
     */
    private static final int[] SPRITES = {10, 11, 12, 13};
    /**
     * Intervalo de tempo entre as trocas de sprites.
     */
    private static final byte SPRITES_INTERVAL = 10;
    /**
     * N�vel m�ximo que a arma do jogador pode alcan�ar.
     */
    private static final byte MAX_WEAPON_LEVEL = 1;
    /**
     * M�ximo de energia que o jogador pode acumular.
     */
    private static final byte MAX_ENERGY = 1;
    /**
     * N�mero de bombas que o jogador inicialmente carrega.
     */
    private static final byte BOMBS = 0;
    /**
     * Velocidade inicial da nave do jogador.
     */
    private static final int SPEED = 100;
    /**
     * Velocidade m�xima que a nave do jogador pode atingir.
     */
    private static final int MAX_SPEED = 100;
    /**
     * N�mero de n�veis de velocidade que esta nave tem (n�mero de etapas entre
     * o m�nimo e o m�ximo).
     */
    private static final byte SPEED_LEVELS = 1;
    /**
     * Matriz de Colis�o deste objeto.
     */
    private static final boolean[][] MATRIX = {{CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O}, {CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X}, {CollisionObject.O, CollisionObject.O, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.X, CollisionObject.O, CollisionObject.O}};

    /**
     * <p>
     * Construtor padr�o.
     * </p>
     */
    public MyPlayer() {
        super(new Point(IRenderable.gameConfig.getGameResolution().width / 20, IRenderable.gameConfig.getGameResolution().height / 2), MyPlayer.SPRITES, MyPlayer.SPRITES_INTERVAL,
            MyPlayer.SPEED, MyPlayer.MAX_SPEED, MyPlayer.SPEED_LEVELS, MyPlayer.MATRIX, MyPlayer.MAX_ENERGY, MyPlayer.MAX_WEAPON_LEVEL, MyPlayer.BOMBS);
        setLimitLR(new Point(20, Cenario.GROUND - MyPlayer.MATRIX.length * Cenario.BLOCK.height - 3));
        setLimitUL(new Point(20, 20));
        setTransparent(MyPlayer.TRANSPARENCY);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SPlayer#moveLeft()
     */
    @Override
    public void moveLeft() {
        // nao usado neste jogo
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SPlayer#moveLeft()
     */
    @Override
    public void moveRight() {
        // nao usado neste jogo
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SPlayer#throwBomb()
     */
    @Override
    protected void throwBomb() {
        // nao utilizado nesse jogo
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SPlayer#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        super.draw(gfx, cnv);
        if (isInvincible()) {
            setTransparent(MyPlayer.TRANSPARENCY);
        } else if (getAlpha() > 0) {
            setOpaque();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SPlayer#handleShot(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override
    protected void handleShot(final Graphics2D gfx, final Canvas cnv) {
        // criando um novo tiro
        GameFactory.getInstance().getActors()
            .add(new Tiro(new Point(getPosition().x + getSize().width, getPosition().y + getSize().height / 2 - 5)), GraphicObjectsList.LAYER_SHOTS);
        // executando o som de disparo
        audio.playSnd(Definitions.EFF_SHOT_P);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SPlayer#die()
     */
    @Override
    public void die() {
        // executando o som da explosao
        audio.playSnd(Definitions.EFF_EXPLOSION_PLAYER);
        super.die();
    }
}
