/**
 *
 */
package com.cs2tech.old.jshooter.gameCore;

import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.graphics.TransitoryGraphicObject;
import com.cs2tech.old.jshooter.gameElements.SSeeker;

/**
 * <p>
 * Esta classe condensa os calculos matematicos necessarios para simular a
 * fasica do jogo.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Phisics {

    /**
     * Instancia anica de <code>Phisics</code>.
     */
    private static final Phisics instance = new Phisics();
    /**
     * Instancia do gerenciador de logs.
     */
    private final GameLogger log = GameLogger.getInstance();
    /**
     * Elemento do calculo de movimento tipo Seeker (eixo X).
     */
    private double deltaX;
    /**
     * Elemento do calculo de movimento tipo Seeker (eixo Y).
     */
    private double deltaY;
    /**
     * Elemento do calculo de movimento tipo Seeker (fator de deslocamento no
     * eixo X).
     */
    private double pcX;
    /**
     * Elemento do calculo de movimento tipo Seeker (fator de deslocamento no
     * eixo Y).
     */
    private double pcY;
    /**
     * Elemento do calculo de movimento tipo Seeker (distancia percorrida).
     */
    private double movementLenth;
    /**
     * Elemento do calculo de movimento tipo Seeker (deslocamento calculado no
     * eixo X).
     */
    private double dX;
    /**
     * Elemento do calculo de movimento tipo Seeker (deslocamento calculado no
     * eixo Y).
     */
    private double dY;

    /**
     * @return A instancia anica de <code>Phisics</code>.
     */
    public static Phisics getInstance() {
        return Phisics.instance;
    }

    /**
     * Calcula o movimento linear de objetos maveis.
     *
     * @param object
     *     Instancia do objeto que executara o movimento.
     */
    public void linearMovement(final TransitoryGraphicObject object) {
        if (object.getDirection() == Directions.DIR_DOWN) {
            object.getPosition().y += object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_UP) {
            object.getPosition().y -= object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_DOWNLEFT) {
            object.getPosition().y += object.getPixelsPerFrame();
            object.getPosition().x -= object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_DOWNLEFT_NARROW) {
            object.getPosition().y += object.getPixelsPerFrame();
            object.getPosition().x -= object.getPixelsPerFrame() / 2;
        } else if (object.getDirection() == Directions.DIR_DOWNLEFT_WIDE) {
            object.getPosition().y += object.getPixelsPerFrame();
            object.getPosition().x -= object.getPixelsPerFrame() * 1.5;
        } else if (object.getDirection() == Directions.DIR_RIGHTDOWN) {
            object.getPosition().y += object.getPixelsPerFrame();
            object.getPosition().x += object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_RIGHTDOWN_NARROW) {
            object.getPosition().y += object.getPixelsPerFrame();
            object.getPosition().x += object.getPixelsPerFrame() / 2;
        } else if (object.getDirection() == Directions.DIR_RIGHTDOWN_WIDE) {
            object.getPosition().y += object.getPixelsPerFrame();
            object.getPosition().x += object.getPixelsPerFrame() * 1.5;
        } else if (object.getDirection() == Directions.DIR_LEFT) {
            object.getPosition().x -= object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_LEFTUP) {
            object.getPosition().y -= object.getPixelsPerFrame();
            object.getPosition().x -= object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_LEFTUP_NARROW) {
            object.getPosition().y -= object.getPixelsPerFrame();
            object.getPosition().x -= object.getPixelsPerFrame() / 2;
        } else if (object.getDirection() == Directions.DIR_LEFTUP_WIDE) {
            object.getPosition().y -= object.getPixelsPerFrame();
            object.getPosition().x -= object.getPixelsPerFrame() * 1.5;
        } else if (object.getDirection() == Directions.DIR_UPRIGHT) {
            object.getPosition().y -= object.getPixelsPerFrame();
            object.getPosition().x += object.getPixelsPerFrame();
        } else if (object.getDirection() == Directions.DIR_UPRIGHT_NARROW) {
            object.getPosition().y -= object.getPixelsPerFrame();
            object.getPosition().x += object.getPixelsPerFrame() / 2;
        } else if (object.getDirection() == Directions.DIR_UPRIGHT_WIDE) {
            object.getPosition().y -= object.getPixelsPerFrame();
            object.getPosition().x += object.getPixelsPerFrame() * 1.5;
        } else if (object.getDirection() == Directions.DIR_RIGHT) {
            object.getPosition().x += object.getPixelsPerFrame();
        } else {
            log.addWarning(
                "Tentativa de executar um movimento invalido para um objeto da classe " + object.getClass() + ". Direaao: " + object.getDirection() + ". EXECUTANDO MOVIMENTO PADRAO (para baixo).",
                null);
            object.getPosition().y += object.getPixelsPerFrame();
        }
    }

    /**
     * Calcula o movimento de objetos teleguiados.
     *
     * @param seek
     *     Instancia do objeto que executara o movimento.
     */
    public void seekMovement(final SSeeker seek) {
        log.addDebug("Movimento teleguiado. Posiaao original = (" + seek.getPosition() + ")", this);
        // deltas
        deltaX = Math.abs(seek.getPosition().x - seek.getTarget()
            .getPosition().x);
        deltaY = Math.abs(seek.getPosition().y - seek.getTarget()
            .getPosition().y);
        // pcs
        pcX = deltaX / (deltaX + deltaY);
        pcY = deltaY / (deltaX + deltaY);
        // tamanho do movimento
        movementLenth = 2 * seek.getPixelsPerFrame();
        // calculo dos deslocamentos
        dX = Math.round(pcX * movementLenth);
        dY = Math.round(pcY * movementLenth);
        // teste de direcao
        if ((dX > 0) && seek.isMovingLeft()) {
            dX = -dX;
        }
        if ((dY > 0) && seek.isMovingUp()) {
            dY = -dY;
        }
        seek.getPosition().x += dX;
        seek.getPosition().y += dY;
        log.addDebug(
            "---------------------\n" + " -> deltaX = " + deltaX + ";\n" + " -> deltaY = " + deltaY + ";\n" + " -> pcX = " + pcX + ";\n" + " -> pcY = " + pcY + ";\n" + " -> movementLenth = " + movementLenth + ";\n" + " -> dX = " + dX + ";\n" + " -> dY = " + dY + ";\n" + "---------------------",
            this);
        log.addDebug(" * Nova posiaao = (" + seek.getPosition() + ")", this);
    }
}
