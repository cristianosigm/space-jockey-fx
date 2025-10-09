/**
 *
 */
package com.cs2tech.old.jshooter.gameElements.internal;

import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionObject;

import java.awt.*;

/**
 * <p>
 * Interface que define os padraes de comportamento de objetos teleguiados.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public interface ISeeker {

    /**
     * @return A posiaao atual do objeto.
     */
    Point getPosition();

    /**
     * @return O alvo atualmente selecionado pelo objeto
     */
    CollisionObject getTarget();

    /**
     * Indica que este objeto esta se movendo para esquerda.
     */
    boolean isMovingLeft();

    /**
     * Indica que este objeto esta se movendo para cima.
     */
    boolean isMovingUp();
}
