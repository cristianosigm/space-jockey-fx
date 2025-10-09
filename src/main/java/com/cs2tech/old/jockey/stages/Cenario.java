package com.cs2tech.old.jockey.stages;

import com.cs2tech.old.jockey.actors.*;
import com.cs2tech.old.jockey.core.Definitions;
import com.cs2tech.old.jshooter.components.Directions;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.components.graphics.lists.GraphicObjectsList;
import com.cs2tech.old.jshooter.gameElements.SStage;

import java.awt.*;

/**
 * <p>
 * Esta classe define o unico cenario do jogo. O cenario utiliza um background
 * composto de multiplas camadas.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class Cenario extends SStage {

    /**
     * Tamanho do bloco de colisao.
     */
    public static final Dimension BLOCK = GameFactory.getInstance()
        .getColision()
        .getCollisionBlockSize();

    /**
     * Publicado para permitir sincronizar objetos com o cenario.
     */
    public static final int BG_SPEED = 90;

    /**
     * Posi��o vertical do ch�o do cen�rio
     */
    public static final int GROUND = 5 * (gameConfig.getGameResolution().height / 6);

    /**
     * Posi��o de lan�amento da nave no cen�rio = ALTO.
     */
    public static final byte SLOT_TOP = 1;

    /**
     * Posi��o de lan�amento da nave no cen�rio = MEIO.
     */
    public static final byte SLOT_MID = 2;

    /**
     * Posi��o de lan�amento da nave no cen�rio = BAIXO.
     */
    public static final byte SLOT_LOW = 3;

    /**
     * Imagem de background - camada inferior.
     */
    private static final int BG_LAYER_LOW = 6;

    /**
     * Imagem de background - camada m�dia.
     */
    private static final int BG_LAYER_MID = 7;

    /**
     * Imagem de background - camada superior.
     */
    private static final int BG_LAYER_TOP = 8;

    /**
     * Imagem de background - composi��o do cen�rio.
     */
    private static final int BG_LAYER_SCENERY = 9;

    /**
     * Intervalo de lan�amento dos objetos das camadas mais altas.
     */
    private static final int INT_TOP = IRenderable.gameUtils.getNFrames(5);

    /**
     * Intervalo de lan�amento dos objetos das camadas do meio.
     */
    private static final int INT_MID = IRenderable.gameUtils.getNFrames(6);

    /**
     * Intervalo de lan�amento dos objetos das camadas de baixo.
     */
    private static final int INT_LOW = IRenderable.gameUtils.getNFrames(4);

    /**
     * Intervalo de lan�amento dos objetos de solo.
     */
    private static final int INT_GROUND = IRenderable.gameUtils.getNFrames(7);

    /**
     * <p>
     * Construtor padr�o.
     * </p>
     */
    public Cenario() {
        super(Cenario.BG_LAYER_LOW, Directions.DIR_LEFT, Cenario.BG_SPEED - 60);
        // adicionando as camadas adicionais
        addBgLayer(Cenario.BG_LAYER_MID, Directions.DIR_LEFT, Cenario.BG_SPEED - 30);
        addBgLayer(Cenario.BG_LAYER_TOP, Directions.DIR_LEFT, Cenario.BG_SPEED - 15);
        addBgLayer(Cenario.BG_LAYER_SCENERY, Directions.DIR_LEFT, Cenario.BG_SPEED);
        // Setando arma padrao
        GameFactory.getInstance()
            .getPlayer1()
            .catchedItem(new DefaultWeapon());
        // colocando o player em jogo
        GameFactory.getInstance()
            .getPlayer1()
            .setPosition(new Point(IRenderable.gameConfig.getGameResolution().width / 20, (IRenderable.gameConfig.getGameResolution().height - GameFactory.getInstance()
                .getPlayer1()
                .getSize().height) / 2));
        GameFactory.getInstance()
            .getActors()
            .add(GameFactory.getInstance()
                .getPlayer1(), GraphicObjectsList.LAYER_ACTORS);
        IRenderable.gameStatus.setP1ControllerEnabled(true);
        // tocando a musica de fundo
        audio.playMusic(Definitions.MUS_STAGE1);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.SStage#draw(java.awt.Graphics2D,
     * java.awt.Canvas)
     */
    @Override

    public void draw(final Graphics2D gfx, final Canvas cnv) {
        super.draw(gfx, cnv);
        // log.addDebug("Renderizando quadro " + getFrame(), this);
        // lancando naves
        if (getFrame() % Cenario.INT_TOP == 0) {
            lancarNave(Cenario.SLOT_TOP);
        }
        if (getFrame() % Cenario.INT_MID == 0) {
            lancarNave(Cenario.SLOT_MID);
        }
        if (getFrame() % Cenario.INT_LOW == 0) {
            lancarNave(Cenario.SLOT_LOW);
        }
        if (getFrame() % Cenario.INT_GROUND == 0) {
            // adicionando um elemento no solo
            lancarSolo();
        }
    }

    /**
     * <p>
     * Este m�todo interno ajuda a simplificar a adi��o de objetos ao cen�rio.
     * N�o � obrigat�rio implementar algo semelhante nos jogos, mas ele mostra
     * que � poss�vel ficar bastante confort�vel para personalizar o c�digo
     * conforme necess�rio.
     * </p>
     *
     * @param posicao
     *     Altura de lan�amento (ou "slot") para a nave a ser lan�ada.
     */
    private void lancarNave(final byte posicao) {
        final int sel = IRenderable.gameUtils.getRandomInt(4);
        int posY = 0;
        if (posicao == Cenario.SLOT_TOP) {
            posY = Cenario.BLOCK.height * 9;
        } else if (posicao == Cenario.SLOT_MID) {
            posY = Cenario.BLOCK.height * 22;
        } else {
            posY = Cenario.BLOCK.height * 34;
        }
        IRenderable.log.addDebug("Selecionando objeto: " + sel, this);
        if (sel == 1) {
            // Monomotor
            GameFactory.getInstance()
                .getActors()
                .add(new Monomotor(posY), GraphicObjectsList.LAYER_ACTORS);
        } else if (sel == 2) {
            // Helicoptero
            GameFactory.getInstance()
                .getActors()
                .add(new Helicoptero(posY), GraphicObjectsList.LAYER_ACTORS);
        } else if (sel == 3) {
            // JATO
            GameFactory.getInstance()
                .getActors()
                .add(new Jato(posY), GraphicObjectsList.LAYER_ACTORS);
        } else {
            // Balao
            GameFactory.getInstance()
                .getActors()
                .add(new Balao(posY), GraphicObjectsList.LAYER_ACTORS);
        }
    }

    /**
     * Este m�todo facilita o lan�amento de objetos de solo.
     */
    private void lancarSolo() {
        final int sel = IRenderable.gameUtils.getRandomInt(3);
        IRenderable.log.addDebug("Selecionando objeto de solo: " + sel, this);
        if (sel == 1) {
            // Arvore
            GameFactory.getInstance()
                .getActors()
                .add(new Arvore(), GraphicObjectsList.LAYER_ACTORS);
        } else if (sel == 2) {
            // Casa
            GameFactory.getInstance()
                .getActors()
                .add(new Casa(), GraphicObjectsList.LAYER_ACTORS);
        } else {
            // Tanque
            GameFactory.getInstance()
                .getActors()
                .add(new Tanque(), GraphicObjectsList.LAYER_ACTORS);
        }
    }

    /* (non-Javadoc)
     * @see jShooter.gameElements.SStage#executeVictory()
     */
    @Override
    public void executeVictory() {
    }
}
