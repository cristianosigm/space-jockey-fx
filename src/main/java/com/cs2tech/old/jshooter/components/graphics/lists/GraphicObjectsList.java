package com.cs2tech.old.jshooter.components.graphics.lists;

import com.cs2tech.old.jshooter.components.GameLogger;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa os comportamentos e atributos comuns a todas as listas
 * de objetos graficos.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class GraphicObjectsList {

    /**
     * altima posiaao selecionavel.
     */
    private static final byte LAST_ELEMENT = 126;
    /**
     * Camada de objetos de cenario.
     */
    public static byte LAYER_BGOBJECTS = 1;
    /**
     * Camada para tiros.
     */
    public static byte LAYER_SHOTS = 2;
    /**
     * Camada para atores, inclusive os jogadores.
     */
    public static byte LAYER_ACTORS = 3;
    /**
     * Camada superior. Use-a para efeitos especiais e objetos de animaaao, por
     * exemplo.
     */
    public static byte LAYER_TOP = 4;
    /**
     * Gerenciador de logs.
     */
    private final GameLogger log = GameLogger.getInstance();
    /**
     * Lista interna de objetos.
     */
    private final IRenderable[] array = new IRenderable[127];
    /**
     * Limite inicial da camada atualmente selecionada.
     */
    private byte layerFrom;
    /**
     * Limite final da camada atualmente selecionada.
     */
    private byte layerTo;

    /**
     * Construtor padrao.
     */
    public GraphicObjectsList() {
        reset();
        log.addDebug("Nova instancia de GraphicObjectsList criada.", this);
    }

    /**
     * Remove todos os objetos atualmente na lista.
     */
    public void reset() {
        for (byte i = 0; i < GraphicObjectsList.LAST_ELEMENT; i++) {
            array[i] = null;
        }
    }

    /**
     * <p>
     * Adiciona um novo objeto a lista. Selecione a camada apropriada para que o
     * objeto possa ser manipulado corretamente.
     * </p>
     * <p>
     * Caso nao haja mais espaao livre na camada solicitada, o objeto nao sera
     * adicionado.
     * </p>
     *
     * @param obj
     *     Instancia de IRenderable a ser adicionada a lista.
     * @param layer
     *     Camada que ira receber o objeto. Use os atributos
     *     <b>GraphicObjectsList.<i>LAYER_*</i></b> para selecionar uma
     *     camada.
     */
    public void add(IRenderable obj, final byte layer) {
        log.addDebug("Adicionando uma instancia de " + obj.getClass() + " a camada " + layer, this);
        boolean success = false;
        // setando a camada
        setLimits(layer);
        // adicionando o objeto no proximo slot livre
        for (byte i = layerFrom; i < layerTo; i++) {
            if (array[i] == null) {
                obj.setId(i);
                array[i] = obj;
                log.addDebug("Instancia adicionada com sucesso.", this);
                success = true;
                break;
            }
        }
        if (!success) {
            obj = null;
        }
    }

    /**
     * Redefine os limites atuais de posiaao de objetos, conforme a camada
     * selecionada.
     *
     * @param layer
     *     Camada que ira receber o objeto. Use os atributos
     *     <b>GraphicObjectsList.<i>LAYER_*</i></b> para selecionar uma
     *     camada.
     */
    public void setLimits(final byte layer) {
        if (layer == GraphicObjectsList.LAYER_BGOBJECTS) {
            // objetos de background
            layerFrom = 0;
            layerTo = 9;
        } else if (layer == GraphicObjectsList.LAYER_SHOTS) {
            // tiros = 85
            layerFrom = 10;
            layerTo = 94;
        } else if (layer == GraphicObjectsList.LAYER_ACTORS) {
            // atores (players, ships) = 22
            layerFrom = 95;
            layerTo = 116;
        } else if (layer == GraphicObjectsList.LAYER_TOP) {
            // camada superior. Effects = 10
            layerFrom = 117;
            layerTo = 126;
        } else {
            // camada invalida
            layerFrom = 0;
            layerTo = 0;
        }
    }

    /**
     * Renderiza todos os objetos atualmente na lista.
     */
    public abstract void draw(Graphics2D gfx, Canvas cnv);

    /**
     * Retorna um objeto da lista.
     *
     * @param id
     *     Identificador anico do objeto desejado.
     *
     * @return Instancia de IRenderable, ou <b>null</b> caso o ID nao seja
     * valido.
     */
    public IRenderable get(final byte id) {
        if ((id >= 0) && (id <= GraphicObjectsList.LAST_ELEMENT)) {
            return array[id];
        }
        return null;
    }

    /**
     * @return A maxima posiaao para objetos na lista.
     */
    public byte getLastElement() {
        return GraphicObjectsList.LAST_ELEMENT;
    }

    /**
     * @return Limite inicial da camada atualmente selecionada.
     */
    public byte getLayerFrom() {
        return layerFrom;
    }

    /**
     * @param layerFrom
     *     Novo limite inicial para a camada atualmente selecionada.
     */
    public void setLayerFrom(final byte layerFrom) {
        this.layerFrom = layerFrom;
    }

    /**
     * @return Limite final da camada atualmente selecionada.
     */
    public byte getLayerTo() {
        return layerTo;
    }

    /**
     * @param layerTo
     *     Novo limite final para a camada atualmente selecionada.
     */
    public void setLayerTo(final byte layerTo) {
        this.layerTo = layerTo;
    }

    /**
     * Remove o objeto solicitado da lista, se encontrado.
     *
     * @param id
     *     Identificador anico do objeto a remover.
     */
    public void remove(final byte id) {
        log.addDebug("Removendo o objeto " + id, this);
        array[id] = null;
    }
}