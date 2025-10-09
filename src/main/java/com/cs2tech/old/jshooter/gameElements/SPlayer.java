package com.cs2tech.old.jshooter.gameElements;

import com.cs2tech.old.jshooter.components.GameFactory;
import com.cs2tech.old.jshooter.components.control.IControllable;
import com.cs2tech.old.jshooter.components.graphics.IRenderable;
import com.cs2tech.old.jshooter.gameCore.kernel.collision.CollisionHandler;
import com.cs2tech.old.jshooter.gameElements.internal.Actor;

import java.awt.*;

/**
 * <p>
 * Esta classe implementa um ator (classe Actor) controlado pelo jogador. Sua
 * maquina de estados internos e suas aaaes sao baseadas nos comandos do
 * jogador.
 * </p>
 * <p>
 * Esta classe deve ser herdada e ter todos os matodos abstratos implementados
 * conforme documentaaao de cada um.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public abstract class SPlayer extends Actor implements IControllable {

    /**
     * Margens para movimentaaao do objeto.
     */
    private static final byte MOVEMENT_MARGINS = 10;

    /**
     * Intervalo manimo entre tiros.
     */
    private static final byte MIN_SHOT_INTERVAL = 1;

    /**
     * Namero de quadros a aguardar quando a nave for destruada, antes da
     * praxima vida.
     */
    private static final int FRAMES_BTW_LIVES = IRenderable.gameUtils.getNFrames(2);

    /**
     * Tempo maximo que o Player deve ficar invencavel.
     */
    private static final int MAX_INVINCIBLE_TIME = IRenderable.gameUtils.getNFrames(5);
    /**
     * Velocidade maxima que o jogador pode atingir.
     */
    private final int maxSpeed;
    /**
     * Velocidade original do ator controlado pelo jogador, em pixels/segundo.
     */
    private final int originalSpeed;
    /**
     * Indica se o ator controlado pelo jogador esta morto.
     */
    private boolean dead;
    /**
     * Contador de tempo usado para controlar o tempo que o jogador aguarda apas
     * perder uma nave, para que a praxima seja carregada na tela.
     */
    private int deadCounter;
    /**
     * Tempo para o jogador ficar invencavel apas a perda da nave.
     */
    private int invincibleTime;
    /**
     * Pontuaaao do jogador.
     */
    private int score;
    /**
     * Quantidade atual de atores (vidas) do jogador.
     */
    private byte lives;
    /**
     * Namero de bombas que o jogador tem atualmente.
     */
    private byte bombs;
    /**
     * Quantidade original de atores (vidas) do jogador.
     */
    private byte originalLives;
    /**
     * Posiaao onde o player deve ser colocado a cada reinacio.
     */
    private Point originalPosition;
    /**
     * Indica se o ator controlado pelo jogador esta pronto para atirar
     */
    private boolean shooting;
    /**
     * Maximo de energia que o ator controlado pelo jogador pode armazenar.
     */
    private byte maxEnergy;
    /**
     * Contador interno de quadros entre os disparos.
     */
    private int frameShotCounter = 0;
    /**
     * Contador interno de quadros entre as bombas.
     */
    private int frameBombCounter = 0;
    /**
     * Arma que o ator controlado pelo jogador possui no momento.
     */
    private byte weapon;
    /**
     * Navel atual da arma do jogador, que indica a velocidade e o poder de
     * destruiaao atual da arma.
     */
    private byte weaponLevel = 0;
    /**
     * Navel maximo que a arma do jogador pode atingir.
     */
    private byte numberOfWeaponLevels;
    /**
     * Namero de quadros a aguardar entre um tiro e outro.
     */
    private int framesBtwShots;
    /**
     * Namero maximo de milisegundos entre os disparos.
     */
    private int maxMilisShotInterval;
    /**
     * Namero de milisegundos entre os disparos, que serao decrescidos a cada
     * novo navel de arma do jogador.
     */
    private int shotIntervalDecrease;
    /**
     * Incremento de velocidade a cada novo navel de velocidade, em
     * pixels/segundo.
     */
    private int speedIncrease;

    /**
     * Construtor padrao.
     *
     * @param initialPos
     *     Posiaao inicial do objeto na tela.
     * @param sprites
     *     Conjunto de sprites para este objeto.
     * @param spriteInterval
     *     Intervalo (em quadros) entre os sprites.
     * @param speed
     *     Velocidade de movimento do objeto (em pixels/seg).
     * @param maxSpeed
     *     Velocidade maxima que o objeto pode atingir (em pixels/seg).
     * @param speedLevels
     *     Namero de incrementos entre a velocidade original e a
     *     velocidade maxima.
     * @param matrix
     *     Matriz de colisao para este objeto.
     * @param energy
     *     Energia (padrao) da nave do jogador.
     * @param weaponLevels
     *     Namero de incrementos entre o navel de arma original e o navel
     *     maximo.
     */
    public SPlayer(final Point initialPos, final int[] sprites, final byte spritesInterval, final int speed, final int maxSpeed, final byte speedLevels, final boolean[][] matrix,
                   final byte energy, final byte weaponLevels, final byte bombs) {
        super(initialPos, sprites, spritesInterval, (byte) 0, matrix, CollisionHandler.TYPE_PLAYER, energy);
        setOriginalPosition(initialPos);
        maxEnergy = energy;
        lives = IRenderable.gameConfig.getNumberOfLives();
        originalLives = IRenderable.gameConfig.getNumberOfLives();
        numberOfWeaponLevels = weaponLevels;
        this.bombs = bombs;
        // velocidade ------------------------------------
        setPixelsPerSecond(speed);
        this.maxSpeed = maxSpeed;
        originalSpeed = speed;
        speedIncrease = (maxSpeed - speed) / speedLevels;
        if (speedIncrease < 1) {
            speedIncrease = 1;
        }
        // -----------------------------------------------
        // limites de movimento
        setLimitUL(new Point(SPlayer.MOVEMENT_MARGINS, SPlayer.MOVEMENT_MARGINS));
        setLimitLR(new Point(IRenderable.gameConfig.getGameResolution().width - getSize().width - SPlayer.MOVEMENT_MARGINS,
            IRenderable.gameConfig.getGameResolution().height - getSize().height - SPlayer.MOVEMENT_MARGINS));

        IRenderable.log.addDebug(
            "-------------------------------------------\n" + "Novo PLAYER criado. \n" + "Tamanho = " + getSize().width + "x" + getSize().height + "px." + "\n" + "Limites de movimento: (" + getLimitUL().x + "," + getLimitUL().y + ") ata (" + getLimitLR().x + "," + getLimitLR().y + ")\n" + "Numero de vidas: " + lives + "\n" + "Energia: " + maxEnergy + "\n" + "-------------------------------------------",
            this);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#actionA()
     */
    @Override
    public void actionA() {
        // TIRO
        setActingA(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#actionB()
     */
    @Override
    public void actionB() {
        // BOMBA
        throwBomb();
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#actionC()
     */
    @Override
    public void actionC() {
        // PAUSA/CONTINUACAO
        if (IRenderable.gameStatus.isPlayingMovie()) {
            // testing skip movie
            GameFactory.getInstance()
                .getCurrentMovie()
                .skip();
        } else {
            IRenderable.gameStatus.setPaused(!IRenderable.gameStatus.isPaused());
            // Pausa na musica de fundo
            audio.pauseMusic();
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#actionD()
     */
    @Override
    public void actionD() {
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#moveDown()
     */
    @Override
    public void moveDown() {
        setMovingDown(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#moveLeft()
     */
    @Override
    public void moveLeft() {
        setMovingLeft(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#moveRight()
     */
    @Override
    public void moveRight() {
        setMovingRight(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#moveUp()
     */
    @Override
    public void moveUp() {
        setMovingUp(true);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopActionA()
     */
    @Override
    public void stopActionA() {
        setActingA(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopActionB()
     */
    @Override
    public void stopActionB() {
        setActingB(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopActionC()
     */
    @Override
    public void stopActionC() {
        setActingC(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopActionD()
     */
    @Override
    public void stopActionD() {
        setActingD(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopMovingDown()
     */
    @Override
    public void stopMovingDown() {
        setMovingDown(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopMovingLeft()
     */
    @Override
    public void stopMovingLeft() {
        setMovingLeft(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopMovingRight()
     */
    @Override
    public void stopMovingRight() {
        setMovingRight(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.control.IControllable#stopMovingUp()
     */
    @Override
    public void stopMovingUp() {
        setMovingUp(false);
    }

    /**
     * <p>
     * Lanaa uma bomba.
     * </p>
     * <p>
     * Este matodo deve APENAS gerenciar a instancia da classe correspondente,
     * de acordo com a bomba atualmente selecionada (quando aplicavel). O
     * intervalo de tempo entre as bombas e o tempo ata que ela exploda sao
     * gerenciados automaticamente pelo Framework.
     * </p>
     */
    protected abstract void throwBomb();

    /**
     * Adiciona um novo ator (vida) a reserva do jogador.
     */
    public void addLife() {
        lives++;
    }

    /**
     * Adiciona pontos a pontuaaao atual do jogador.
     *
     * @param points
     *     Pontos a adicionar a pontuaaao do jogador.
     */
    public void addScore(final int points) {
        score += points;
    }

    /**
     * Verifica se o jogador pode lanaar uma bomba.
     */
    protected boolean canThrowBomb() {
        if ((frameBombCounter == 0) && (getBombs() > 0)) {
            // lancar outra bomba
            removeBomb();
            frameBombCounter = 1;
            return true;
        }
        return false;
    }

    /**
     * @return the bombs
     */
    public byte getBombs() {
        return bombs;
    }

    /**
     * Remove uma bomba do arsenal.
     */
    protected void removeBomb() {
        bombs--;
    }

    /**
     * <p>
     * Informa ao Famework que o jogador capturou um item.
     * </p>
     *
     * @param obj
     *     Item capturado pelo jogador.
     */
    public void catchedItem(final SItem obj) {
        IRenderable.log.addWarning(
            "Item capturado. ID = " + obj.getId() + "; TIPO = " + obj.getType() + "; TIPO DE ITEM = " + obj.getItemType() + "; VALOR = " + obj.getValue() + ". Arma atual = " + getWeapon() + "; Navel de arma = " + getWeaponLevel(),
            this);

        if (obj.getItemType() == getWeapon()) {
            // Item capturado a uma arma do mesmo tipo que a atual. Subindo um
            // navel.
            if (weaponLevel + obj.getValue() < numberOfWeaponLevels) {
                weaponLevel += obj.getValue();
            } else {
                weaponLevel = numberOfWeaponLevels;
            }
            IRenderable.log.addWarning("Arma atualizada. Navel atual = " + weaponLevel + "; Intervalo entre disparos atual = " + getFramesBtwShots(), this);
        } else if (!(obj.getItemType() == SItem.TYPE_SPEED) && !(obj.getItemType() == SItem.TYPE_BOMB)) {
            // Item capturado a uma arma de tipo diferente da atual. Trocando de
            // arma.
            setWeapon(obj.getItemType());
            maxMilisShotInterval = ((IWeaponItem) obj).getMaxMilisShotInterval();
            // recalculando o intervalo entre tiros para o nivel atual
            shotIntervalDecrease = (maxMilisShotInterval - ((IWeaponItem) obj).getMinMilisShotInterval()) / numberOfWeaponLevels;
            IRenderable.log.addWarning("Nova arma capturada: " + obj.getItemType() + "; Intervalo basico (em ms) da nova arma = " + maxMilisShotInterval, this);
        } else if (obj.getItemType() == SItem.TYPE_SPEED) {
            // Item capturado a um incremento de velocidade. Aumentando a
            // velocidade do ator.
            if (getPixelsPerSecond() < maxSpeed) {
                setPixelsPerSecond(getPixelsPerSecond() + speedIncrease);
            } else {
                setPixelsPerSecond(maxSpeed);
            }
        } else if (obj.getItemType() == SItem.TYPE_BOMB) {
            // Item capturado a uma bomba. Aumentando arsenal
            addBomb();
        }

        // Atualizando intervalo entre os disparos.
        setFramesBtwShots(maxMilisShotInterval - weaponLevel * shotIntervalDecrease);
        if (framesBtwShots < SPlayer.MIN_SHOT_INTERVAL) {
            framesBtwShots = SPlayer.MIN_SHOT_INTERVAL;
        }
    }

    /**
     * @return O valor atual de weapon.
     */
    public byte getWeapon() {
        return weapon;
    }

    /**
     * @param weapon
     *     Redefine o valor de weapon para o valor informado por
     *     parametro.
     */
    public void setWeapon(final byte weapon) {
        this.weapon = weapon;
    }

    /**
     * @return O valor atual de weaponLevel.
     */
    public byte getWeaponLevel() {
        return weaponLevel;
    }

    /**
     * @return O valor atual de framesBtwShots
     */
    public int getFramesBtwShots() {
        return framesBtwShots;
    }

    /**
     * Adiciona uma bomba ao arsenal do jogador.
     */
    public void addBomb() {
        bombs++;
    }

    /**
     * @param framesBtwShots
     *     Redefine o valor de framesBtwShots para o valor informado por
     *     parametro.
     */
    public void setFramesBtwShots(final double milis_interval) {
        framesBtwShots = (int) Math.round((milis_interval / 1000) * IRenderable.gameConfig.getFrameRate());
        IRenderable.log.addDebug("Frames enre tiros setado para " + framesBtwShots + ".", this);
    }

    /**
     * @param weaponLevel
     *     Redefine o valor de weaponLevel para o valor informado por
     *     parametro.
     */
    public void setWeaponLevel(final byte weaponLevel) {
        this.weaponLevel = weaponLevel;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * jShooter.gameCore.kernel.collision.CollisionObject#draw(java.awt.Graphics2D
     * , java.awt.Canvas)
     */
    @Override
    public void draw(final Graphics2D gfx, final Canvas cnv) {
        // verificando se o player esta morto
        if (!isDead()) {
            super.draw(gfx, cnv);
            handleShotInterval();
            handleBombInterval();
            handleInvincibleTime();
            // atualizando movimento
            move();
            // manipulando tiros
            if (isActingA() && canShot()) {
                handleShot(gfx, cnv);
                shooting = true;
            }
        } else if (isDead() && (getLives() > 0)) {
            // se estiver morto, o jogo aguarda um intervalo e poe o player de
            // volta
            IRenderable.log.addDebug("Jogador morreu. Aguardando " + SPlayer.FRAMES_BTW_LIVES + " frames.", this);
            if (deadCounter < SPlayer.FRAMES_BTW_LIVES) {
                // aguardando...
                deadCounter++;
            } else {
                deadCounter = 0;
                setDead(false);
                // carregando proxima nave
                IRenderable.log.addDebug("Checando se jogador ainda tem vidas restantes.", this);
                // carrega proxima nave
                IRenderable.log.addDebug("Jogador ainda tem " + getLives() + " vidas. Carregando novamente.", this);
                // voltando todos os valores ao estado inicial, exceto pontos e
                // vidas
                // ------------------------------------------------------------------------------
                setPixelsPerSecond(originalSpeed);
                setFramesBtwShots(maxMilisShotInterval);
                setWeaponLevel((byte) 0);
                setEnergy(maxEnergy);
                setWeapon(SItem.TYPE_WEAPON_M);
                setPosition(originalPosition);
                IRenderable.gameStatus.setP1ControllerEnabled(true);
                // ------------------------------------------------------------------------------
            }
        } else {
            // jogador perdeu a ultima vida
            GameFactory.getInstance()
                .getCurrentStage()
                .setFinishing(true);
            GameFactory.getInstance()
                .getCurrentStage()
                .setVictory(false);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.CollisionObject#getBonus()
     */
    @Override
    public int getBonus() {
        return 0;
    }

    /**
     * @return O valor atual de dead.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Gerencia os disparos, considerando o intervalo entre disparos atualmente
     * configurado.
     */
    private void handleShotInterval() {
        if (shooting && (frameShotCounter < framesBtwShots)) {
            // aguardando
            frameShotCounter++;
        } else if (shooting && (frameShotCounter >= framesBtwShots)) {
            // novo tiro esta pronto
            frameShotCounter = 0;
            shooting = false;
        }
    }

    /**
     * Gerencia o lancamento de bombas, considerando o intervalo entre os
     * lanaamentos.
     */
    private void handleBombInterval() {
        if (frameBombCounter == 0) {
            // nada a fazer
        } else {
            frameBombCounter++;
            if (frameBombCounter > (framesBtwShots * 10)) {
                // tempo d eespera acabou
                frameBombCounter = 0;
            }
        }
    }

    /**
     * Gerencia o tempo de invencibilidade do jogador.
     */
    private void handleInvincibleTime() {
        if (isInvincible() && (invincibleTime > SPlayer.MAX_INVINCIBLE_TIME)) {
            // tempo invencivel acabou. Voltando ao normal
            setInvincible(false);
            invincibleTime = 0;
        } else if (isInvincible()) {
            // continua contando, caso tenha sido setado
            invincibleTime++;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.components.graphics.GraphicObject#move()
     */
    @Override
    public void move() {
        if (isMovingLeft() && !isMovingRight() && (getPosition().x > getLimitUL().x)) {
            getPosition().x -= getPixelsPerFrame();
        } else if (isMovingRight() && !isMovingLeft() && (getPosition().x < getLimitLR().x)) {
            getPosition().x += getPixelsPerFrame();
        }
        if (isMovingUp() && !isMovingDown() && (getPosition().y > getLimitUL().y)) {
            getPosition().y -= getPixelsPerFrame();
        } else if (isMovingDown() && !isMovingUp() && (getPosition().y < getLimitLR().y)) {
            getPosition().y += getPixelsPerFrame();
        }
    }

    /**
     * @return <b>True</b> se o ator pode efetuar um novo disparo, ou
     * <b>False</b> caso contrario.
     */
    public boolean canShot() {
        return !shooting;
    }

    /**
     * <p>
     * Gerencia as requisiaaes de disparo, testando a arma corrente e criando as
     * instancias necessarias.
     * </p>
     * <p>
     * Este matodo deve gerenciar APENAS a instancia dos disparos; o intervalo
     * entre os disparos e o poder destrutivo de cada arma variam de acordo com
     * o navel atual e sao gerenciados automaticamente pelo Framework.
     * </p>
     *
     * @param gfx
     *     Ponteiro para o buffer grafico central.
     * @param cnv
     *     Ponteiro para o canvas central.
     */
    protected abstract void handleShot(Graphics2D gfx, Canvas cnv);

    /**
     * @return O valor atual de lives.
     */
    public byte getLives() {
        return lives;
    }

    /**
     * @param dead
     *     Redefine o valor de dead para o valor informado por parametro.
     */
    public void setDead(final boolean dead) {
        this.dead = dead;
    }

    /**
     * Libera o jogador para continuar jogando.
     */
    public void enable() {
        IRenderable.gameStatus.setP1ControllerEnabled(true);
    }

    /**
     * @return O valor atual de maxEnergy.
     */
    public byte getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * @param maxEnergy
     *     Redefine o valor de maxEnergy para o valor informado por
     *     parametro.
     */
    public void setMaxEnergy(final byte maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    /**
     * @return O valor atual de numberOfWeaponLevels.
     */
    public byte getNumberOfWeaponLevels() {
        return numberOfWeaponLevels;
    }

    /**
     * @param numberOfWeaponLevels
     *     Redefine o valor de numberOfWeaponLevels para o valor
     *     informado por parametro.
     */
    public void setNumberOfWeaponLevels(final byte numberOfWeaponLevels) {
        this.numberOfWeaponLevels = numberOfWeaponLevels;
    }

    /**
     * @return O valor atual de originalPosition
     */
    protected Point getOriginalPosition() {
        return originalPosition;
    }

    /**
     * @param originalPosition
     *     Redefine o valor de originalPosition para o valor informado
     *     por parametro.
     */
    public void setOriginalPosition(final Point originalPosition) {
        this.originalPosition = originalPosition;
    }

    /**
     * @return O valor atual de score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score
     *     Redefine o valor de score para o valor informado por
     *     parametro.
     */
    public void setScore(final int score) {
        this.score = score;
    }

    /**
     * Reinicia a pontuaaao e naveis do jogador para o estado inicial.
     */
    public void reset() {
        // setando a velocidade original
        setPixelsPerSecond(originalSpeed);
        // setando o intervalo entre tiros
        setFramesBtwShots(maxMilisShotInterval);
        // resetando arma
        setWeaponLevel((byte) 0);
        setEnergy(maxEnergy);
        lives = originalLives;
        setScore(0);
        setWeapon(SItem.TYPE_WEAPON_M);
        setDead(false);
    }

    /**
     * @param lives
     *     Redefine o valor de lives para o valor informado por
     *     parametro.
     */
    public void setOriginalLives(final byte lives) {
        originalLives = lives;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameElements.internal.Actor#takeHit(int)
     */
    @Override
    public void takeHit(final int hitPoints) {
        takeHit(hitPoints, false);
    }

    /**
     * Diminui em 1 unidade o namero de atores (vidas) do jogador.
     */
    public void lostLife() {
        lives--;
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#die()
     */
    @Override
    public void die() {
        IRenderable.log.addWarning("Jogador morreu.", this);
        setDead(true);
        setInvincible(true);
        disable();
    }

    /**
     * Paralisa todas as aaaes do jogador e desabilita o controle.
     */
    public void disable() {
        stopActionA();
        stopActionB();
        stopActionC();
        stopActionD();
        stopMovingDown();
        stopMovingLeft();
        stopMovingUp();
        stopMovingRight();
        IRenderable.gameStatus.setP1ControllerEnabled(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see jShooter.gameCore.kernel.collision.IHitable#takeHit(int, boolean)
     */
    @Override
    public void takeHit(final int hitPoints, final boolean noSound) {
        if (!isInvincible()) {
            setEnergy((byte) (getEnergy() - hitPoints));
            if (getEnergy() <= 0) {
                // morreu
                lostLife();
                die();
                // de volta ao tiro do comeco
                setWeapon(SItem.TYPE_WEAPON_M);
                setWeaponLevel((byte) 0);
                // desenhando a explosao
                GameFactory.getInstance()
                    .createSpecialEffect(GameFactory.EFF_EXPLOSION, this);
            }
        }
    }
}