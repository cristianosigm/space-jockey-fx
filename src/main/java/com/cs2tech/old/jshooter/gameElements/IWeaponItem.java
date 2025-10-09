package com.cs2tech.old.jshooter.gameElements;

/**
 * <p>
 * Interface que define os padraes de comportamento para os itens especaficos de
 * armas.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public interface IWeaponItem {

    /**
     * @return Intervalo maximo entre disparos, em milisegundos.
     */
    int getMaxMilisShotInterval();

    /**
     * @return Intervalo manimo entre disparos, em milisegundos.
     */
    int getMinMilisShotInterval();
}
