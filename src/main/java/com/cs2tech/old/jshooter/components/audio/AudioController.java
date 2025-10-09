package com.cs2tech.old.jshooter.components.audio;

import com.cs2tech.old.jshooter.components.GameConfig;
import com.cs2tech.framework.media.MidiPlayer;
import com.cs2tech.framework.media.WavPlayer;

/**
 * <p>
 * A classe <code>AudioController</code> a a classe responsavel por manipular o
 * audio para o jShooter Framework.
 * </p>
 * <p>
 * Esta classe encapsula todos os reprodutores de todos os formatos de audio
 * disponaveis para o jogo, portanto o jogo precisa requisitar todos os eventos
 * de audio apenas atravas dela.
 * </p>
 * <p>
 * Os matodos desta classe que recebem parametros geralmente usam um andice para
 * carregar uma masica ou efeito. Estes andices correspondem ao identificador
 * deste no arquivo de configuraaao correspondente (para masicas MIDI este
 * arquivo a o <b>musics.properties</b>; para os efeitos de audio,
 * <b>effects.properties</b>).
 * </p>
 *
 * @author Cristiano Souza
 * @version 1.0
 */
public class AudioController {

    /**
     * Ponteiro para o configurador central.
     */
    private static final GameConfig cfg = GameConfig.getInstance();
    /**
     * Instancia anica de <code>AudioController</code>;
     */
    private static AudioController instance = null;
    /**
     * Instancia do reprodutor de masicas MIDI.
     */
    private final MidiPlayer bgm = new MidiPlayer();
    /**
     * Instancia do reprodutor de efeitos sonoros.
     */
    private final WavPlayer eff = new WavPlayer();

    /**
     * <p>
     * Construtor padrao.
     * </p>
     */
    private AudioController() {
        super();
    }

    /**
     * @return A instancia anica de <code>AudioController</code>;
     */
    public static AudioController getInstance() {
        if (AudioController.instance == null) {
            AudioController.instance = new AudioController();
        }
        return AudioController.instance;
    }

    /**
     * Solicita uma pausa na masica atualmente em execuaao.
     */
    public void pauseMusic() {
        if (cfg.isMusicOn()) {
            bgm.pause();
        }
    }

    /**
     * Solicita ao reprodutor de masicas que continue reproduzindo a masica
     * atualmente carregada (se estiver pausada), ou que inicie a reproduaao (se
     * estiver parada).
     */
    public void playMusic() {
        if (cfg.isMusicOn()) {
            bgm.play();
        }
    }

    /**
     * Solicita a execuaao de uma masica.
     *
     * @param index
     *     O andice da masica solicitada na lista de masicas.
     */
    public void playMusic(final int index) {
        if (cfg.isMusicOn()) {
            bgm.play(index);
        }
    }

    /**
     * Solicita a execuaao de uma masica.
     *
     * @param index
     *     O andice da masica solicitada na lista de masicas.
     */
    public void playMusicOnce(final int index) {
        if (cfg.isMusicOn()) {
            // bgm.set
            bgm.setContinuous(false);
            bgm.play(index);
        }
    }

    /**
     * Solicita a execuaao de um efeito sonoro.
     *
     * @param index
     *     O andice do efeito sonoro solicitado na lista de efeitos
     *     sonoros.
     */
    public void playSnd(final int index) {
        if (cfg.isEffectsOn()) {
            eff.play(index);
        }
    }

    /**
     * Solicita ao reprodutor de masicas que pare a execuaao da masica corrente.
     */
    public void stopMusic() {
        if (cfg.isMusicOn()) {
            bgm.stop();
        }
    }
}
