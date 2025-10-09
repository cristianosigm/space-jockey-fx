package com.cs2tech.old.jshooter.gameCore.kernel.collision;

import com.cs2tech.old.jshooter.components.GameConfig;
import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.GameLogger;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa a Central de Detecaao de Colisao. Ela manipula a
 * Matriz de Colisao central do jogo, responsavel por armazenar todos os objetos
 * passaveis de colisao instanciados.
 * </p>
 * <p>
 * A Matriz central de colisao a testada a cada atualizaaao do kernel, atravas
 * do matodo <code>getCollision(CollisionObject)</code>. Este matodo devolve ao
 * objeto solicitante o ID do objeto com que ele colidiu, caso ocorra uma
 * colisao. O objeto automaticamente (conforme implementaaao da superclasse
 * <b>CollisionObject</b>) dispara uma chamada para
 * <code>CollisionHandler.handleCollision( CollisionObject, CollisionObject)</code>
 * , que toma as aaaes necessarias.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class CollisionCore {

    /**
     * Gerenciador de logs.
     */
    private final GameLogger log = GameLogger.getInstance();

    /**
     * Repositario de configuraaaes.
     */
    private final GameConfig cfg = GameConfig.getInstance();

    /**
     * Matriz de colisao central.
     */
    private final byte[][] matrix;

    /**
     * Tamanho de cada bloco de colisao (em <b>pixels</b>).
     */
    private Dimension collisionBlockSize;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     * <p>
     * Inicializa a matriz central de colisao e calcula o tamanho do bloco,
     * conforme configurado no arquivo de propriedades.
     * <p>
     */
    public CollisionCore() {
        final GameFactory fac = GameFactory.getInstance();
        collisionBlockSize = new Dimension(Math.round(fac.getFrm()
            .getSize().width / cfg.getMatrixSize()[0]), Math.round(fac.getFrm()
            .getSize().height / cfg.getMatrixSize()[1]));
        matrix = new byte[cfg.getMatrixSize()[0]][cfg.getMatrixSize()[1]];
        reset();
        log.addDebug("CollisionCore instanciado com sucesso.", this);
    }

    /**
     * Limpa a matriz de colisao.
     */
    public void reset() {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                matrix[x][y] = 0;
            }
        }
    }

    /**
     * Renderiza a matriz de colisao graficamente na tela.
     *
     * @param gfx
     *     Ponteiro para o buffer grafico.
     */
    public void draw(final Graphics2D gfx) {
        final GameFactory fac = GameFactory.getInstance();
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[0].length; y++) {
                if (matrix[x][y] > 0) {
                    gfx.setColor(Color.WHITE);
                } else {
                    gfx.setColor(Color.DARK_GRAY);
                }
                gfx.drawRect(x * fac.getColision()
                    .getCollisionBlockSize().width, y * fac.getColision()
                    .getCollisionBlockSize().height, fac.getColision()
                    .getCollisionBlockSize().width, fac.getColision()
                    .getCollisionBlockSize().height);
            }
        }
    }

    /**
     * @return Tamanho atual do bloco de colisao.
     */
    public Dimension getCollisionBlockSize() {
        return collisionBlockSize;
    }

    /**
     * @param collisionBlockSize
     *     Redefine o tamanho do bloco de colisao.
     */
    public void setCollisionBlockSize(final Dimension collisionBlockSize) {
        this.collisionBlockSize = collisionBlockSize;
    }

    /**
     * <p>
     * Testa se algum objeto passavel de colisao colidiu com o objeto recebido
     * como parametro.
     * <p>
     * <p>
     * Caso positivo, retorna o identificador anico do objeto que colidiu.
     * </p>
     *
     * @param obj
     *     Instancia da classe <b>CollisionObject</b> que deseja
     *     verificar se houve uma colisao.
     *
     * @return Identificador anico do objeto que colidiu com o que foi recebido
     * como parametro, ou <b>0</b> caso nao tenha acontecido nenhuma
     * colisao.
     */
    public byte getCollision(final CollisionObject obj) {
        try {
            // calculating the matrix position
            final int[] coord = getPosition(obj.getPosition());
            final int endCoordX = coord[0] + obj.getMatrix()[0].length;
            final int endCoordY = coord[1] + obj.getMatrix().length;
            int objX = 0;
            int objY = 0;

            // adicionando o objeto na matriz
            for (int x = coord[0]; x < endCoordX; x++) {
                for (int y = coord[1]; y < endCoordY; y++) {
                    if ((objX >= 0) && (objY >= 0) && (x >= 0) && (y >= 0) && (objX < obj.getMatrix().length) && (objY < obj.getMatrix()[0].length) && (x < matrix.length) && (y < matrix[0].length) && (obj.getMatrix()[objX][objY]) && (matrix[x][y] > 0)) {
                        // colidiu. retorna o ID do objeto colidido.
                        return matrix[x][y];
                    } else if ((objX >= 0) && (objY >= 0) && (x >= 0) && (y >= 0) && (objX < obj.getMatrix().length) && (objY < obj.getMatrix()[0].length) && (x < matrix.length) && (y < matrix[0].length) && (obj.getMatrix()[objX][objY])) {
                        matrix[x][y] = obj.getId();
                    }
                    objX++;
                }
                objX = 0;
                objY++;
            }
        } catch (final Exception e) {
            log.addError("Falha geral ao testar colisao para o objeto: " + obj.getClass(), e, this);
        }
        return 0;
    }

    /**
     * <p>
     * Calcula um ponto aproximado correspondente a uma posiaao valida na matriz
     * de colisao.
     * </p>
     *
     * @param p
     *     Posiaao atual do objeto.
     *
     * @return Posiaao correspondente na matriz de colisao.
     */
    private int[] getPosition(final Point p) {
        return new int[]{Math.round(p.x / collisionBlockSize.width), Math.round(p.y / collisionBlockSize.height)};
    }
}
