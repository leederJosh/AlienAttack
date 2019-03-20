package com.mygdx.game.assets;

import com.badlogic.gdx.audio.Music;

import java.util.HashMap;

/**
 * Manages the music in each level
 * @Author Josh Leeder
 * @Date 07/03/19
 */
public class MusicManager implements Music {

    private boolean isPlaying;
    private Music songName;
    private boolean isLooping;
    private float volume;


    public MusicManager(Music songName){
        this.songName = songName;
    }

    @Override
    public void play() {
        songName.play();
    }

    @Override
    public void pause() {
        songName.pause();
    }

    @Override
    public void stop() {
        songName.stop();
    }

    @Override
    public boolean isPlaying() {
        return isPlaying;
    }

    @Override
    public void setLooping(boolean isLooping) {
        songName.setLooping(isLooping);
    }

    @Override
    public boolean isLooping() {
        return isLooping;
    }

    @Override
    public void setVolume(float volume) {
        songName.setVolume(volume);
    }

    @Override
    public float getVolume() {
        return volume;
    }

    @Override
    public void dispose() {
        songName.dispose();
    }



    @Override
    public void setPan(float pan, float volume) {

    }

    @Override
    public void setPosition(float position) {

    }

    @Override
    public float getPosition() {
        return 0;
    }

    @Override
    public void setOnCompletionListener(OnCompletionListener listener) {

    }
}
