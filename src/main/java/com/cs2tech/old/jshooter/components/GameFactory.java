package com.cs2tech.old.jshooter.components;

import com.cs2tech.old.jshooter.components.graphics.GameFrame;
import com.cs2tech.old.jshooter.components.graphics.GraphicObject;
import com.cs2tech.old.jshooter.components.graphics.lists.ActorsList;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionCore;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameElements.SMenu;
import com.cs2tech.old.jshooter.gameElements.SMovie;
import com.cs2tech.old.jshooter.gameElements.SPlayer;
import com.cs2tech.old.jshooter.gameElements.SStage;
import com.cs2tech.old.jshooter.gameElements.effects.*;

/**
 * <p>
 * Implementaaao do padrao <i>Factory</i> para o Framework.
 * </p>
 * <p>
 * Esta classe centraliza as instancias e todas as informaaaes compartilhadas
 * por todo o projeto, exceto as que sao compartilhadas utilizando o padrao
 * <i>Singleton</i>.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GameFactory {

    /**
     * <p>
     * Framerate base, usado para calculo de sincronia dos movimentos e animaaao
     * de todos os objetos graficos.
     * </p>
     * <p>
     * Este valor a estatico e nao define o Framerate, apenas a usado como
     * referencial de calculo. O Framerate a definido atravas do valor
     * configurado no arquivo <code>config.properties</code>, chave
     * <i>framerate</i>.
     */
    public static byte BASE_FRAMERATE = 30;

    /**
     * Efeito especial = Flash de luz.
     */
    public static byte EFF_FLASHLIGHT = 1;

    /**
     * Efeito especial = Explosao Comum.
     */
    public static byte EFF_EXPLOSION = 2;

    /**
     * Efeito especial = Explosao de um chefe.
     */
    public static byte EFF_BOSS_EXPLOSION = 3;

    /**
     * Efeito especial = Clarear a tela.
     */
    public static byte EFF_FADE_FROM_BLACK = 4;

    /**
     * Efeito especial = Escurecer a tela.
     */
    public static byte EFF_FADE_TO_BLACK = 5;
    /**
     * Instancia anica de <code>GameFactory</code>.
     */
    private static GameFactory instance = null;
    /**
     * Lista de atores do jogo.
     */
    private final ActorsList actors = new ActorsList();
    /**
     * Indica se jogo esta habilitado para dois jogadores simultaneos.
     */
    private boolean twoPlayerGame = false;
    /**
     * Ponteiro para a janela do jogo.
     */
    private GameFrame frm;
    /**
     * Jogador 1.
     */
    private SPlayer player1;
    /**
     * Jogador 2 (somente para jogos de 2 jogadores).
     */
    private SPlayer player2;
    /**
     * Centro de colisao.
     */
    private CollisionCore colision = null;
    /**
     * Manipulador de colisao.
     */
    private CollisionHandler colHandler = null;
    /**
     * Filme atualmente em execuaao.
     */
    private SMovie currentMovie = null;
    /**
     * Menu atualmente em execuaao.
     */
    private SMenu currentMenu = null;
    /**
     * Estagio atualmente em execuaao.
     */
    private SStage currentStage = null;

    /**
     * <p>
     * Construtor Principal.
     * </p>
     */
    private GameFactory() {
        super();
    }

    /**
     * @return A instancia atual de <code>GameFactory</code>.
     */
    public static GameFactory getInstance() {
        if (GameFactory.instance == null) {
            GameFactory.instance = new GameFactory();
        }
        return GameFactory.instance;
    }

    /**
     * Cria e executa um efeito especial.
     *
     * @param effect
     *     Tipo de efeito especial. Use os atributos estaticos
     *     <b>GameFactory.<i>EFF_*</i></b> para selecionar um efeito.
     */
    public void createSpecialEffect(final byte effect, final GraphicObject obj) {
        if (effect == GameFactory.EFF_FLASHLIGHT) {
            // criando um flash de luz
            actors.add(new Flashlight((byte) 2), GraphicObjectsList.LAYER_TOP);
        } else if (effect == GameFactory.EFF_EXPLOSION) {
            // criando uma explosao comum
            actors.add(new CommonExplosion(obj), GraphicObjectsList.LAYER_BGOBJECTS);
        } else if (effect == GameFactory.EFF_BOSS_EXPLOSION) {
            // criando uma grande explosao
            actors.add(new VerticalBossExplosion(obj, (byte) 5), GraphicObjectsList.LAYER_TOP);
        } else if (effect == GameFactory.EFF_FADE_FROM_BLACK) {
            // clareando a tela
            actors.add(new FadeFromBlack((byte) 1), GraphicObjectsList.LAYER_TOP);
        } else if (effect == GameFactory.EFF_FADE_TO_BLACK) {
            // escurecendo a tela
            actors.add(new FadeToBlack((byte) 1), GraphicObjectsList.LAYER_TOP);
        }
    }

    /**
     * @return O valor atual de actors.
     */
    public ActorsList getActors() {
        return actors;
    }

    /**
     * @return O valor atual de colHandler.
     */
    public CollisionHandler getColHandler() {
        return colHandler;
    }

    /**
     * @param colHandler
     *     Redefine o valor de colHandler para o valor informado por
     *     parametro.
     */
    public void setColHandler(final CollisionHandler colHandler) {
        this.colHandler = colHandler;
    }

    /**
     * @return O valor atual de colision.
     */
    public CollisionCore getColision() {
        if (colision == null) {
            colision = new CollisionCore();
        }
        return colision;
    }

    /**
     * @return O valor atual de currentMenu.
     */
    public SMenu getCurrentMenu() {
        return currentMenu;
    }

    /**
     * @param currentMenu
     *     Redefine o valor de currentMenu para o valor informado por
     *     parametro.
     */
    public void setCurrentMenu(final SMenu currentMenu) {
        this.currentMenu = currentMenu;
        GameStatus.getInstance()
            .setAtMenu(true);
    }

    /**
     * @return O valor atual de currentMovie.
     */
    public SMovie getCurrentMovie() {
        return currentMovie;
    }

    /**
     * @param currentMovie
     *     Redefine o valor de currentMovie para o valor informado por
     *     parametro.
     */
    public void setCurrentMovie(final SMovie currentMovie) {
        this.currentMovie = currentMovie;
        GameStatus.getInstance()
            .setPlayingMovie(true);
    }

    /**
     * @return O valor atual de currentStage.
     */
    public SStage getCurrentStage() {
        return currentStage;
    }

    /**
     * @param currentStage
     *     Redefine o valor de currentStage para o valor informado por
     *     parametro.
     */
    public void setCurrentStage(final SStage currentStage) {
        this.currentStage = currentStage;
        GameStatus.getInstance()
            .setAtStage(true);
    }

    /**
     * @return O valor atual de frm.
     */
    public GameFrame getFrm() {
        return frm;
    }

    /**
     * @param frm
     *     Redefine o valor de frm para o valor informado por parametro.
     */
    public void setFrm(final GameFrame frm) {
        this.frm = frm;
    }

    /**
     * @return O valor atual de player1.
     */
    public SPlayer getPlayer1() {
        return player1;
    }

    /**
     * @param player1
     *     Redefine o jogador 1.
     */
    public void setPlayer1(final SPlayer player1) {
        this.player1 = player1;
    }

    /**
     * @return O valor atual de player2.
     */
    public SPlayer getPlayer2() {
        return player2;
    }

    /**
     * @param player2
     *     Redefine o jogador 2.
     */
    public void setPlayer2(final SPlayer player2) {
        this.player2 = player2;
    }

    /**
     * @return O valor atual de twoPlayerGame.
     */
    public boolean isTwoPlayerGame() {
        return twoPlayerGame;
    }

    /**
     * @param twoPlayerGame
     *     <b>True</b> para um jogo para 2 jogadores, ou <b>False</b>
     *     para um jogador apenas. <br>
     *     <b>NOTA:</b> O modo de dois jogadores ainda nao foi
     *     implementado na versao atual.
     */
    public void setTwoPlayerGame(final boolean twoPlayerGame) {
        this.twoPlayerGame = twoPlayerGame;
        if (!twoPlayerGame) {
            GameStatus.getInstance()
                .setP2ControllerEnabled(false);
        }
    }
}
