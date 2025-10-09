package com.cs2tech.old.jshooter.components;

import java.awt.*;
import java.io.*;
import java.util.Properties;

/**
 * <p>
 * Esta classe armazena as configuraaaes do jogo, definidas nos arquivos de
 * configuraaao.
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class GameConfig {

    /**
     * Resoluaao de tela: 640x480px.
     */
    public static final byte RES_640x480 = 0;

    /**
     * Resoluaao de tela: 800x600px.
     */
    public static final byte RES_800x600 = 1;

    /**
     * Resoluaao de tela: 800x600px, tela inteira.
     */
    public static final byte RES_FULL_SCREEN = 2;

    /**
     * Chave LIGADO - DESLIGADO, valor DESLIGADO.
     */
    public static final byte VALUE_OFF = 0;

    /**
     * Chave LIGADO - DESLIGADO, valor LIGADO.
     */
    public static final byte VALUE_ON = 1;

    /**
     * Chave: Resoluaao.
     */
    public static final String KEY_RESOLUTION = "gameResolution";

    /**
     * Chave: Frame Rate.
     */
    public static final String KEY_REFRESH_RATE = "frameRate";

    /**
     * Chave: JOGADOR 1 - CIMA
     */
    public static final String KEY_P1UP = "p1Up";

    /**
     * Chave: JOGADOR 1 - BAIXO
     */
    public static final String KEY_P1DOWN = "p1Down";

    /**
     * Chave: JOGADOR 1 - ESQUERDA
     */
    public static final String KEY_P1LEFT = "p1Left";

    /**
     * Chave: JOGADOR 1 - DIREITA
     */
    public static final String KEY_P1RIGHT = "p1Right";

    /**
     * Chave: JOGADOR 1 - BOTAO A
     */
    public static final String KEY_P1A = "p1ActionA";

    /**
     * Chave: JOGADOR 1 - BOTAO B
     */
    public static final String KEY_P1B = "p1ActionB";

    /**
     * Chave: JOGADOR 1 - BOTAO C
     */
    public static final String KEY_P1C = "p1ActionC";

    /**
     * Chave: JOGADOR 1 - BOTAO D
     */
    public static final String KEY_P1D = "p1ActionD";

    /**
     * Chave: JOGADOR 2 - CIMA
     */
    public static final String KEY_P2UP = "p2Up";

    /**
     * Chave: JOGADOR 2 - BAIXO
     */
    public static final String KEY_P2DOWN = "p2Down";

    /**
     * Chave: JOGADOR 2 - ESQUERDA
     */
    public static final String KEY_P2LEFT = "p2Left";

    /**
     * Chave: JOGADOR 2 - DIREITA
     */
    public static final String KEY_P2RIGHT = "p2Right";

    /**
     * Chave: JOGADOR 2 - BOTAO A
     */
    public static final String KEY_P2A = "p2ActionA";

    /**
     * Chave: JOGADOR 2 - BOTAO B
     */
    public static final String KEY_P2B = "p2ActionB";

    /**
     * Chave: JOGADOR 2 - BOTAO C
     */
    public static final String KEY_P2C = "p2ActionC";

    /**
     * Chave: JOGADOR 2 - BOTAO D
     */
    public static final String KEY_P2D = "p2ActionD";

    /**
     * Arquivo de log.
     */
    public static final String KEY_LOG_FILE = "logFile";

    /**
     * Nivel de log.
     */
    public static final String KEY_LOG_LEVEL = "logLevel";

    /**
     * Renderizar log na saada padrao.
     */
    public static final String KEY_LOG_VISIBLE = "logVisible";

    /**
     * Tamanho do eixo X da matriz de colisao.
     */
    public static final String KEY_MATRIX_X = "matrixX";

    /**
     * Tamanho do eixo Y da matriz de colisao.
     */
    public static final String KEY_MATRIX_Y = "matrixY";

    /**
     * Renderizar em tela informacoes do kernel.
     */
    public static final String KEY_ROSCREEN = "rederOnScreen";

    /**
     * Renderizar em tela a matriz de colisao.
     */
    public static final String KEY_RCOLLISION = "rederCollision";

    /**
     * Namero de vidas do jogador.
     */
    public static final String KEY_DIFFICULTY_HARD = "hardGame";

    /**
     * Namero de vidas do jogador.
     */
    public static final String KEY_LIVES = "lives";

    /**
     * Executar masicas.
     */
    public static final String KEY_MUSIC_ON = "bgmOn";

    /**
     * Executar efeitos sonoros.
     */
    public static final String KEY_EFFECTS_ON = "effOn";

    /**
     * Cadigos especiais.
     */
    public static final String KEY_CHEATING = "cheating";

    /**
     * <p>
     * Cabecalho e Manual de uso do arquivo de configuraaao.
     * </p>
     */
    private static final String COMMENT = "-----------------------------------#\n" + "# jShooter Framework - CONFIGURACAO #\n" + "#         by Cristiano Souza        #\n" + "#-----------------------------------#\n" + "#\n" + "# 1. gameResolution\n" + "# ----------------------------\n" + "# Redefine a resolucao de tela.\n" + "# Opcoes:\n" + "#   0 = Windowed, 640 x 480\n" + "#   1 = Windowed, 800 x 600\n" + "#   2 = Fullscreen, 800 x 600 32-bit\n" + "\n" + "# 2. frameRate\n" + "# ----------------------------\n" + "# Muda a taxa de atualizacao do kernel.\n" + "# (minimo = 10 - maximo = 60 - recomendado = 30)\n" + "\n" + "# 3. Configuracoes de teclado\n" + "# ----------------------------\n" + "# Definem as teclas usadas para controlar o jogo.\n" + "# Todas as chaves comecando com p1 (para o jogador 1) e p2 (para o jogador 2).\n" + "\n" + "# 4. logFile\n" + "# ----------------------------\n" + "# Caminho e nome de arquivo para armazenar o log.\n" + "\n" + "# 5. logLevel\n" + "# ----------------------------\n" + "# Selecao do nivel de log. Opcoes:\n" + "#   OFF = 0 (padrao)\n" + "#   ERRORS = 1\n" + "#   WARINGS = 2\n" + "#   DEBUG = 3\n" + "\n" + "# 6. logVisible.\n" + "# ----------------------------\n" + "#   OFF = 0 (padrao)\n" + "#   ON  = 1\n" + "#\n" + "# ATENCAO: Nao ligue esta opaao, vai deixar o jogo muito mais lento.\n" + "\n" + "# 7. matrixX e matrixY\n" + "# ---------------------------\n" + "# Tamanho (em pixels) da matriz de colisao. Padrao: matrixX = 80 e matrixY = 60.\n" + "#\n" + "# ATENCAO: modificar este valor pode causar erros no jogo.\n" + "\n" + "# 8. rederOnScreen\n" + "# ---------------------------\n" + "# Renderiza na tela informaaaes sobre o kernel.\n" + "# OFF = 0 (padrao)\n" + "# ON  = 1\n" + "\n" + "# 9. rederCollision\n" + "# ---------------------------\n" + "# Renderiza na tela a matriz de colisao.\n" + "# OFF = 0 (padrao)\n" + "# ON  = 1\n" + "\n" + "# 10. lives\n" + "# ---------------------------\n" + "# Numero de vidas iniciais do jogador.\n" + "\n" + "# 11. bgmOn\n" + "# ---------------------------\n" + "# Reproduaao de masicas durante o jogo.\n" + "# OFF = 0\n" + "# ON  = 1 (padrao)\n" + "\n" + "# 12. effOn\n" + "# ---------------------------\n" + "# Reproduaao de efeitos sonoros durante o jogo.\n" + "# OFF = 0\n" + "# ON  = 1 (padrao)\n" + "\n" + "# 13. cheating\n" + "# ---------------------------\n" + "# Codigos especiais (consulte o manual do jogo).\n";
    /**
     * Instancia anica de <code>GameConfig</code>.
     */
    private static GameConfig instance;
    /**
     * Resoluaao do jogo.
     */
    private Dimension gameResolution;
    /**
     * Resoluaao do jogo.
     */
    private byte cfgGameResolution;
    /**
     * Indica se o jogo sera executado em uma janela ou em tela inteira.
     */
    private boolean fullScreen = false;
    /**
     * Taxa de atualizaaao do jogo (frame rate).
     */
    private int frameRate;
    /**
     * Valor para JOGADOR 1 - CIMA.
     */
    private int p1Up;
    /**
     * Valor para JOGADOR 1 - BAIXO.
     */
    private int p1Down;
    /**
     * Valor para JOGADOR 1 - ESQUERDA.
     */
    private int p1Left;
    /**
     * Valor para JOGADOR 1 - DIREITA.
     */
    private int p1Right;
    /**
     * Valor para JOGADOR 1 - A.
     */
    private int p1ActionA;
    /**
     * Valor para JOGADOR 1 - B.
     */
    private int p1ActionB;
    /**
     * Valor para JOGADOR 1 - C.
     */
    private int p1ActionC;
    /**
     * Valor para JOGADOR 1 - D.
     */
    private int p1ActionD;
    /**
     * Valor para JOGADOR 2 - CIMA.
     */
    private int p2Up;
    /**
     * Valor para JOGADOR 2 - BAIXO.
     */
    private int p2Down;
    /**
     * Valor para JOGADOR 2 - ESQUERDA.
     */
    private int p2Left;
    /**
     * Valor para JOGADOR 2 - DIREITA.
     */
    private int p2Right;
    /**
     * Valor para JOGADOR 2 - A.
     */
    private int p2ActionA;
    /**
     * Valor para JOGADOR 2 - B.
     */
    private int p2ActionB;
    /**
     * Valor para JOGADOR 2 - C.
     */
    private int p2ActionC;
    /**
     * Valor para JOGADOR 2 - D.
     */
    private int p2ActionD;
    /**
     * <p>
     * <b>CHEAT</b>.
     * Invencibilidade (cadigo 101) - Impede o jogador de ser atingido.
     * </p>
     */
    private boolean invincible = false;
    /**
     * <p>
     * Dificuldade do jogo. <b>False</b> significa normal, <b>True</b> muda a
     * dificuldade para alta.
     * </p>
     */
    private boolean difficultyHard = false;
    /**
     * Endereao em disco do arquivo de log.
     */
    private String logFile;
    /**
     * Indica se o framework deve ou nao exibir o log na saada padrao.
     */
    private boolean logVisible;
    /**
     * Navel de informaaaes do arquivo de log.
     */
    private byte logLevel;
    /**
     * Tamanho da matriz de colisao (em linhas x colunas).
     */
    private byte[] matrixSize;
    /**
     * Indica se o framework deve ou nao renderizar a matriz de colisao na tela.
     */
    private boolean rederCollision;
    /**
     * Indica se o framework deve ou nao renderizar informaaaes de debug na
     * tela.
     */
    private boolean rederOnScreen;
    /**
     * Armazena o namero de vidas definido pelo usuario atravas do menu.
     */
    private byte numberOfLives;
    /**
     * Indica se o usuario deseja ou nao masica no jogo.
     */
    private boolean musicOn;
    /**
     * Indica se o usuario deseja ou nao efeitos sonoros no jogo.
     */
    private boolean effectsOn;
    /**
     * Indica se o usuario deseja ou nao efeitos sonoros no jogo.
     */
    private byte cheating;

    /**
     * <p>
     * Construtor padrao.
     * </p>
     * <p>
     * Carrega os dados de configuraaao para os atributos internos da instancia.
     * </p>
     */
    private GameConfig() {
        try {
            final Properties prop = new Properties();
            prop.load(new FileInputStream("cfg/config.properties"));
            setCfgResolution(Byte.parseByte(prop.getProperty(GameConfig.KEY_RESOLUTION)
                .trim()));
            setFrameRate(Integer.parseInt(prop.getProperty(GameConfig.KEY_REFRESH_RATE)
                .trim()));

            setP1Up(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1UP)
                .trim()));
            setP1Down(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1DOWN)
                .trim()));
            setP1Left(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1LEFT)
                .trim()));
            setP1Right(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1RIGHT)
                .trim()));
            setP1ActionA(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1A)
                .trim()));
            setP1ActionB(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1B)
                .trim()));
            setP1ActionC(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1C)
                .trim()));
            setP1ActionD(Integer.parseInt(prop.getProperty(GameConfig.KEY_P1D)
                .trim()));

            setP2Up(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2UP)
                .trim()));
            setP2Down(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2DOWN)
                .trim()));
            setP2Left(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2LEFT)
                .trim()));
            setP2Right(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2RIGHT)
                .trim()));
            setP2ActionA(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2A)
                .trim()));
            setP2ActionB(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2B)
                .trim()));
            setP2ActionC(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2C)
                .trim()));
            setP2ActionD(Integer.parseInt(prop.getProperty(GameConfig.KEY_P2D)
                .trim()));

            setLogFile(prop.getProperty(GameConfig.KEY_LOG_FILE)
                .trim());
            setLogVisible(prop.getProperty(GameConfig.KEY_LOG_VISIBLE)
                .trim()
                .equals("1"));
            setLogLevel(Byte.parseByte(prop.getProperty(GameConfig.KEY_LOG_LEVEL)
                .trim()));

            setMatrixSize(new byte[]{Byte.parseByte(prop.getProperty(GameConfig.KEY_MATRIX_X)
                .trim()), Byte.parseByte(prop.getProperty(GameConfig.KEY_MATRIX_Y)
                .trim())});

            setRederCollision(prop.getProperty(GameConfig.KEY_RCOLLISION)
                .trim()
                .equals("1"));
            setRederOnScreen(prop.getProperty(GameConfig.KEY_ROSCREEN)
                .trim()
                .equals("1"));

            setDifficultyHard(prop.getProperty(GameConfig.KEY_DIFFICULTY_HARD)
                .trim()
                .equals("1"));
            setNumberOfLives(Byte.parseByte(prop.getProperty(GameConfig.KEY_LIVES)
                .trim()));
            setMusicOn(prop.getProperty(GameConfig.KEY_MUSIC_ON)
                .trim()
                .equals("1"));
            setEffectsOn(prop.getProperty(GameConfig.KEY_EFFECTS_ON)
                .trim()
                .equals("1"));
            setCheating(Byte.parseByte(prop.getProperty(GameConfig.KEY_CHEATING)
                .trim()));
        } catch (final NumberFormatException e) {
            System.out.println("Erro ao tentar converter um numero no arquivo de configuraaao: " + e.getMessage());
            e.printStackTrace();
        } catch (final FileNotFoundException e) {
            System.out.println("Arquivo de configuraaao nao encontrado.");
            e.printStackTrace();
        } catch (final IOException e) {
            System.out.println("Erro geral de entrada e saada ao tentar ler o arquivo de configuraaao: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * @param gameResolution
     *     Redefine a resoluaao do jogo.
     */
    public void setCfgResolution(final byte cfgResolution) {
        // mudando a
        if (cfgResolution == GameConfig.RES_640x480) {
            setGameResolution(new Dimension(640, 480));
            setFullScreen(false);
        } else if (cfgResolution == GameConfig.RES_FULL_SCREEN) {
            setGameResolution(Toolkit.getDefaultToolkit()
                .getScreenSize());
            setFullScreen(true);
        } else {
            // resolucao padrao a 800x600
            setGameResolution(new Dimension(800, 600));
            setFullScreen(false);
        }
        cfgGameResolution = cfgResolution;
        updateKey(GameConfig.KEY_RESOLUTION, Byte.toString(cfgResolution));
    }

    /**
     * @param cheating
     *     the cheating to set
     */
    private void setCheating(byte cheating) {
        this.cheating = cheating;
        // definindo o resultado do codigo
        if (this.cheating == 101) {
            // invencibilidade
            this.invincible = true;
        }
    }

    /**
     * <p>
     * Atualiza o valor de uma chave no arquivo de propriedades e salva a nova
     * versao do arquivo em disco.
     * </p>
     *
     * @param key
     *     Chave a ser atualizada.
     * @param value
     *     Novo valor a armazenar na chave.
     */
    private void updateKey(final String key, final String value) {
        try {
            final Properties prop = new Properties();
            prop.load(new FileInputStream("cfg/config.properties"));
            final Writer w = new FileWriter("cfg/config.properties");
            prop.setProperty(key, value);
            prop.store(w, GameConfig.COMMENT);
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return A Instancia anica de <code>GameConfig</code>.
     */
    public static GameConfig getInstance() {
        if (GameConfig.instance == null) {
            GameConfig.instance = new GameConfig();
        }
        return GameConfig.instance;
    }

    /**
     * @return Taxa de atualizaaao atual.
     */
    public double getFrameRate() {
        return frameRate;
    }

    /**
     * @param frameRate
     *     Redefine a taxa de atualizaaao.
     */
    public void setFrameRate(final int refreshRate) {
        frameRate = refreshRate;
        updateKey(GameConfig.KEY_REFRESH_RATE, Integer.toString(refreshRate));
    }

    /**
     * @return A resoluaao do jogo.
     */
    public Dimension getGameResolution() {
        return gameResolution;
    }

    /**
     * @param gameResolution
     *     Redefine o valor de gameResolution para o valor informado por
     *     parametro.
     */
    private void setGameResolution(final Dimension gameResolution) {
        this.gameResolution = gameResolution;
    }

    /**
     * @return O valor atual de logFile.
     */
    public String getLogFile() {
        return logFile;
    }

    /**
     * @param logFile
     *     Redefine o valor de logFile para o valor informado por
     *     parametro.
     */
    public void setLogFile(final String logFile) {
        this.logFile = logFile;
        updateKey(GameConfig.KEY_LOG_FILE, logFile);
    }

    /**
     * @return O valor atual de logLevel.
     */
    public byte getLogLevel() {
        return logLevel;
    }

    /**
     * @param logLevel
     *     Redefine o valor de logLevel para o valor informado por
     *     parametro.
     */
    public void setLogLevel(final byte logLevel) {
        this.logLevel = logLevel;
        updateKey(GameConfig.KEY_LOG_LEVEL, Byte.toString(logLevel));
    }

    /**
     * @return O valor atual de matrixSize.
     */
    public byte[] getMatrixSize() {
        return matrixSize;
    }

    /**
     * @param matrixSize
     *     Redefine o valor de matrixSize para o valor informado por
     *     parametro.
     */
    public void setMatrixSize(final byte[] matrixSize) {
        this.matrixSize = matrixSize;
        updateKey(GameConfig.KEY_MATRIX_X, Byte.toString(matrixSize[0]));
        updateKey(GameConfig.KEY_MATRIX_Y, Byte.toString(matrixSize[1]));
    }

    /**
     * @return O valor atual de p1ActionA.
     */
    public int getP1ActionA() {
        return p1ActionA;
    }

    /**
     * @param p1ActionA
     *     Redefine o valor de p1ActionA para o valor informado por
     *     parametro.
     */
    public void setP1ActionA(final int actionA) {
        p1ActionA = actionA;
        updateKey(GameConfig.KEY_P1A, Integer.toString(actionA));
    }

    /**
     * @return O valor atual de p1ActionB.
     */
    public int getP1ActionB() {
        return p1ActionB;
    }

    /**
     * @param p1ActionB
     *     Redefine o valor de p1ActionB para o valor informado por
     *     parametro.
     */
    public void setP1ActionB(final int actionB) {
        p1ActionB = actionB;
        updateKey(GameConfig.KEY_P1B, Integer.toString(actionB));
    }

    /**
     * @return O valor atual de p1ActionC.
     */
    public int getP1ActionC() {
        return p1ActionC;
    }

    /**
     * @param p1ActionC
     *     Redefine o valor de p1ActionC para o valor informado por
     *     parametro.
     */
    public void setP1ActionC(final int actionC) {
        p1ActionC = actionC;
        updateKey(GameConfig.KEY_P1C, Integer.toString(actionC));
    }

    /**
     * @return O valor atual de p1ActionD.
     */
    public int getP1ActionD() {
        return p1ActionD;
    }

    /**
     * @param p1ActionD
     *     Redefine o valor de p1ActionD para o valor informado por
     *     parametro.
     */
    public void setP1ActionD(final int actionD) {
        p1ActionD = actionD;
        updateKey(GameConfig.KEY_P1D, Integer.toString(actionD));
    }

    /**
     * @return O valor atual de p1Down.
     */
    public int getP1Down() {
        return p1Down;
    }

    /**
     * @param p1Down
     *     Redefine o valor de p1Down para o valor informado por
     *     parametro.
     */
    public void setP1Down(final int down) {
        p1Down = down;
        updateKey(GameConfig.KEY_P1DOWN, Integer.toString(down));
    }

    /**
     * @return O valor atual de p1Left.
     */
    public int getP1Left() {
        return p1Left;
    }

    /**
     * @param p1Left
     *     Redefine o valor de p1Left para o valor informado por
     *     parametro.
     */
    public void setP1Left(final int left) {
        p1Left = left;
        updateKey(GameConfig.KEY_P1LEFT, Integer.toString(left));
    }

    /**
     * @return O valor atual de p1Right.
     */
    public int getP1Right() {
        return p1Right;
    }

    /**
     * @param p1Right
     *     Redefine o valor de p1Right para o valor informado por
     *     parametro.
     */
    public void setP1Right(final int right) {
        p1Right = right;
        updateKey(GameConfig.KEY_P1RIGHT, Integer.toString(right));
    }

    /**
     * @return O valor atual de p1Up.
     */
    public int getP1Up() {
        return p1Up;
    }

    /**
     * @param p1Up
     *     Redefine o valor de p1Up para o valor informado por parametro.
     */
    public void setP1Up(final int up) {
        p1Up = up;
        updateKey(GameConfig.KEY_P1UP, Integer.toString(up));
    }

    /**
     * @return O valor atual de p2ActionA.
     */
    public int getP2ActionA() {
        return p2ActionA;
    }

    /**
     * @param p2ActionA
     *     Redefine o valor de p2ActionA para o valor informado por
     *     parametro.
     */
    public void setP2ActionA(final int actionA) {
        p2ActionA = actionA;
        updateKey(GameConfig.KEY_P2A, Integer.toString(actionA));
    }

    /**
     * @return O valor atual de p2ActionB.
     */
    public int getP2ActionB() {
        return p2ActionB;
    }

    /**
     * @param p2ActionB
     *     Redefine o valor de p2ActionB para o valor informado por
     *     parametro.
     */
    public void setP2ActionB(final int actionB) {
        p2ActionB = actionB;
        updateKey(GameConfig.KEY_P2B, Integer.toString(actionB));
    }

    /**
     * @return O valor atual de p2ActionC.
     */
    public int getP2ActionC() {
        return p2ActionC;
    }

    /**
     * @param p2ActionC
     *     Redefine o valor de p2ActionC para o valor informado por
     *     parametro.
     */
    public void setP2ActionC(final int actionC) {
        p2ActionC = actionC;
        updateKey(GameConfig.KEY_P2C, Integer.toString(actionC));
    }

    /**
     * @return O valor atual de p2ActionD.
     */
    public int getP2ActionD() {
        return p2ActionD;
    }

    /**
     * @param p2ActionD
     *     Redefine o valor de p2ActionD para o valor informado por
     *     parametro.
     */
    public void setP2ActionD(final int actionD) {
        p2ActionD = actionD;
        updateKey(GameConfig.KEY_P2D, Integer.toString(actionD));
    }

    /**
     * @return O valor atual de p2Down.
     */
    public int getP2Down() {
        return p2Down;
    }

    /**
     * @param p2Down
     *     Redefine o valor de p2Down para o valor informado por
     *     parametro.
     */
    public void setP2Down(final int down) {
        p2Down = down;
        updateKey(GameConfig.KEY_P2DOWN, Integer.toString(down));
    }

    /**
     * @return O valor atual de p2Left.
     */
    public int getP2Left() {
        return p2Left;
    }

    /**
     * @param p2Left
     *     Redefine o valor de p2Left para o valor informado por
     *     parametro.
     */
    public void setP2Left(final int left) {
        p2Left = left;
        updateKey(GameConfig.KEY_P2LEFT, Integer.toString(left));
    }

    /**
     * @return O valor atual de p2Right.
     */
    public int getP2Right() {
        return p2Right;
    }

    /**
     * @param p2Right
     *     Redefine o valor de p2Right para o valor informado por
     *     parametro.
     */
    public void setP2Right(final int right) {
        p2Right = right;
        updateKey(GameConfig.KEY_P2RIGHT, Integer.toString(right));
    }

    /**
     * @return O valor atual de p2Up.
     */
    public int getP2Up() {
        return p2Up;
    }

    /**
     * @param p2Up
     *     Redefine o valor de p2Up para o valor informado por parametro.
     */
    public void setP2Up(final int up) {
        p2Up = up;
        updateKey(GameConfig.KEY_P2UP, Integer.toString(up));
    }

    /**
     * @return <b>True</b> se o jogo estiver em dificuldade alta, ou
     * <b>False</b> se estiver em dificuldade normal.
     */
    public boolean isDifficultyHard() {
        return difficultyHard;
    }

    /**
     * @param difficultyHard
     *     Redefine o valor de difficultyHard para o valor informado por
     *     parametro.
     */
    public void setDifficultyHard(final boolean difficultyHard) {
        this.difficultyHard = difficultyHard;
        updateKey(GameConfig.KEY_DIFFICULTY_HARD, (difficultyHard ? "1" : "0"));
    }

    /**
     * @return O valor atual de fullScreen.
     */
    public boolean isFullScreen() {
        return fullScreen;
    }

    /**
     * @param fullScreen
     *     Redefine o valor de fullScreen para o valor informado por
     *     parametro.
     */
    private void setFullScreen(final boolean fullScreen) {
        this.fullScreen = fullScreen;
    }

    /**
     * @return O valor atual de logVisible.
     */
    public boolean isLogVisible() {
        return logVisible;
    }

    /**
     * @param logVisible
     *     Redefine o valor de logVisible para o valor informado por
     *     parametro.
     */
    public void setLogVisible(final boolean logVisible) {
        this.logVisible = logVisible;
        updateKey(GameConfig.KEY_LOG_VISIBLE, (logVisible ? "1" : "0"));
    }

    /**
     * @return O valor atual de rederCollision.
     */
    public boolean isRederCollision() {
        return rederCollision;
    }

    /**
     * @param rederCollision
     *     Redefine o valor de rederCollision para o valor informado por
     *     parametro.
     */
    public void setRederCollision(final boolean rederCollision) {
        this.rederCollision = rederCollision;
        updateKey(GameConfig.KEY_RCOLLISION, (rederCollision ? "1" : "0"));
    }

    /**
     * @return O valor atual de rederOnScreen.
     */
    public boolean isRederOnScreen() {
        return rederOnScreen;
    }

    /**
     * @param rederOnScreen
     *     Redefine o valor de rederOnScreen para o valor informado por
     *     parametro.
     */
    public void setRederOnScreen(final boolean rederOnScreen) {
        this.rederOnScreen = rederOnScreen;
        updateKey(GameConfig.KEY_ROSCREEN, (rederOnScreen ? "1" : "0"));
    }

    /**
     * @return the effectsOn
     */
    public boolean isEffectsOn() {
        return effectsOn;
    }

    /**
     * @param effectsOn
     *     the effectsOn to set
     */
    public void setEffectsOn(final boolean effectsOn) {
        this.effectsOn = effectsOn;
        updateKey(GameConfig.KEY_EFFECTS_ON, (effectsOn ? "1" : "0"));
    }

    /**
     * @return the musicOn
     */
    public boolean isMusicOn() {
        return musicOn;
    }

    /**
     * @param musicOn
     *     the musicOn to set
     */
    public void setMusicOn(final boolean musicOn) {
        this.musicOn = musicOn;
        updateKey(GameConfig.KEY_MUSIC_ON, (musicOn ? "1" : "0"));
    }

    /**
     * @return the cfgGameResolution
     */
    public byte getCfgGameResolution() {
        return cfgGameResolution;
    }

    /**
     * @return the numberOfLives
     */
    public byte getNumberOfLives() {
        return numberOfLives;
    }

    /**
     * @param numberOfLives
     *     the numberOfLives to set
     */
    public void setNumberOfLives(final byte numberOfLives) {
        this.numberOfLives = numberOfLives;
        updateKey(GameConfig.KEY_LIVES, Byte.toString(numberOfLives));
    }

    /**
     * @return the invincible
     */
    public boolean isInvincible() {
        return invincible;
    }
}
