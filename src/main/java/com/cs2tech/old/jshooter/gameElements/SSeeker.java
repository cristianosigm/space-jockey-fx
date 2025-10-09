package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;
import com.cs2tech.old.jshooter.gameElements.internal.ISeeker;

import java.awt.*;

/**
 * <p>
 * Superclasse que implementa os comportamentos e atributos padrao para um
 * objeto capaz de perseguir automaticamente um alvo.
 * </p>
 * <p>
 * Para implementa-lo o programador deve herdar esta classe, e instancia-lo
 * conforme as instruaaes no construtor.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SSeeker extends SShot implements ISeeker {

    /**
     * Alvo deste objeto.
     */
    private CollisionObject target;

    /**
     * Identificador anico do alvo deste objeto.
     */
    private byte targetId = -1;

    /**
     * Indica que este objeto esta se movendo para baixo.
     */
    private boolean movingDown = false;

    /**
     * Indica que este objeto esta se movendo para cima.
     */
    private boolean movingUp = false;

    /**
     * Indica que este objeto esta se movendo para esquerda.
     */
    private boolean movingLeft = false;

    /**
     * Indica que este objeto esta se movendo para direita.
     */
    private boolean movingRight = false;

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
    public SSeeker(final Point position, final int[] sprites, final byte spriteInterval, final int speed, final boolean[][] matrix, final boolean enemyShot, final byte direction,
                   final byte hitPower) {
        super(position, sprites, spriteInterval, speed, matrix, enemyShot, direction, hitPower);
    }

    /**
     * @return O valor atual de target.
     */
    @Override
    public CollisionObject getTarget() {
        return target;
    }

    /**
     * @return O valor atual de movingLeft.
     */
    @Override
    public boolean isMovingLeft() {
        return movingLeft;
    }    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#die()
     */
    @Override
    public void die() {
        terminate();
    }

    /**
     * @return O valor atual de movingUp.
     */
    @Override
    public boolean isMovingUp() {
        return movingUp;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.TransitoryGraphicObject#move()
     */
    @Override
    public void move() {
        IRenderable.log.addDebug("Executando movimento de um SEEKER.", this);
        // *
        if (GameFactory.getInstance()
            .getActors()
            .get(targetId) == null) {
            // localizando um novo alvo
            IRenderable.log.addDebug("Procurando por um novo alvo.", this);
            seekTarget();
            // movimento linear
            linearMovement();
        } else {
            // alvo travado. Perseguindo-o.
            IRenderable.log.addDebug(
                "Alvo travado (id = " + target.getId() + ", tipo = " + target.getType() + "). Verificando praximo movimento (alvo esta em " + target.getPosition() + "; " + "SEEKER esta em " + getPosition() + ").",
                this);
            // --------------------------------
            // eixo X
            if (getPosition().x > target.getPosition().x) {
                // esquerda
                movingRight = false;
                movingLeft = true;
            } else if (getPosition().x < target.getPosition().x) {
                // direita
                movingRight = true;
                movingLeft = false;
            } else {
                // sem movimento
                movingRight = false;
                movingLeft = false;
            }
            // eixo Y
            if (getPosition().y > target.getPosition().y) {
                // cima
                movingDown = false;
                movingUp = true;
            } else if (getPosition().y < target.getPosition().y) {
                // baixo
                movingDown = true;
                movingUp = false;
            } else {
                // sem movimento
                movingDown = false;
                movingUp = false;
            }

            // direcao atual
            if (movingDown && movingLeft) {
                // DOWN-LEFT
                setDirection(Directions.DIR_DOWNLEFT);
                circularMovement();
            } else if (movingDown && movingRight) {
                // DOWN-RIGHT
                setDirection(Directions.DIR_RIGHTDOWN);
                circularMovement();
            } else if (movingDown) {
                // DOWN
                setDirection(Directions.DIR_DOWN);
                circularMovement();
            } else if (movingUp && movingLeft) {
                // UP-LEFT
                setDirection(Directions.DIR_LEFTUP);
                circularMovement();
            } else if (movingUp && movingRight) {
                // UP-RIGHT
                setDirection(Directions.DIR_UPRIGHT);
                circularMovement();
            } else if (movingUp) {
                // UP
                setDirection(Directions.DIR_UP);
                circularMovement();
            } else if (movingLeft) {
                // LEFT
                setDirection(Directions.DIR_LEFT);
                circularMovement();
            } else if (movingRight) {
                // LEFT
                setDirection(Directions.DIR_RIGHT);
                circularMovement();
            } else {
                // nenhum movimento
                IRenderable.log.addDebug("Nenhum alvo. Mantendo altima direaao.", this);
                linearMovement();
            }
        }
        // */
    }

    /**
     * Procura por um novo alvo para este objeto SEEKER.
     */
    private void seekTarget() {
        final GameFactory fac = GameFactory.getInstance();
        if (getType() == CollisionHandler.TYPE_PLAYER_SHOT) {
            // tiro do jogador
            // --------------------------------------------
            // limitando a busca para atores apenas
            fac.getActors()
                .setLimits(GraphicObjectsList.LAYER_ACTORS);
            // tentando localizar um inimigo
            for (byte i = fac.getActors()
                .getLayerFrom(); i < fac.getActors()
                .getLayerTo(); i++) {
                if ((fac.getActors()
                    .get(i) != null) && (((CollisionObject) fac.getActors()
                    .get(i)).getType() == CollisionHandler.TYPE_ENEMY)) {
                    // inimigo encontrado
                    target = (CollisionObject) fac.getActors()
                        .get(i);
                    targetId = i;
                    IRenderable.log.addDebug("Novo alvo encontrado para este SEEKER: " + target.getId(), this);
                    break;
                }
            }
        } else {
            // qualquer outra coisa ira perseguir o jogador.
            target = fac.getPlayer1();
            IRenderable.log.addDebug("Novo alvo encontrado para este SEEKER: PLAYER.", this);
        }
    }

    /**
     * Utiliza o calculo de movimento linear da classe <b>GameUtils</b> para
     * mover o objeto na direaao atual.
     */
    private void linearMovement() {
        super.move();
    }

    /**
     * Utiliza o calculo de movimento de perseguiaao (SEEK) da classe
     * <b>GameUtils</b> para perseguir o alvo atualmente travado.
     */
    private void circularMovement() {
        IRenderable.gamePhisics.seekMovement(this);
    }



    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(byte)
     */
    @Override
    public void takeHit(final int hitPoints) {
        die();
    }
}
