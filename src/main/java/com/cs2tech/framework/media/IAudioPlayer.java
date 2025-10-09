package com.cs2tech.framework.media;

public interface IAudioPlayer {

    void playNextTrack();

    void pause();

    void play();

    void play(int index);

    void previous();

    void reset();

    void stop();
}
