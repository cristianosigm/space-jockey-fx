package com.cs2tech.old.jshooter.gameCore.kernel.collision;

/**
 * <p>
 * Interface que define o comportamento padrao para objetos que podem ser
 * atingidos por tiros, colisao, etc.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public interface IHitable {

    /**
     * Indica que o objeto foi destruado, fazendo com que ele seja eliminado da
     * memaria para liberar recursos, e disparando os efeitos especiais
     * necessarios (animaaaes, efeitos sonoros, etc).
     */
    void die();

    /**
     * Decrescenta a energia do objeto atingido.
     *
     * @param points
     *     A quantidade de pontos de energia que serao decrescidos do
     *     objeto atingido.
     */
    void takeHit(int hitPoints);

    /**
     * <p>
     * Decrescenta a energia do objeto atingido, informando se deve executar (
     * <code>noSound = <b>False</b></code>) ou nao (
     * <code>noSound = <b>True</b></code>) os efeitos sonoros correspondentes.
     * </p>
     * <p>
     * Este matodo a atil ao usar bombas, porque elas implementam seus praprios
     * efeitos sonoros.
     * </p>
     *
     * @param points
     *     A quantidade de pontos de energia que serao decrescidos do
     *     objeto atingido.
     * @param noSound
     *     <b>True</b> se nenhum som deve ser reproduzido pelo objeto
     *     atingido.
     */
    void takeHit(int hitPoints, boolean noSound);
}
