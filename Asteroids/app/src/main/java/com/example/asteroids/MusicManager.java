package com.example.asteroids;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;
import java.util.Random;

public class MusicManager {

    private MediaPlayer song;
    private int songIDs[];
    private int numSongs = 4;
    private int currSong;
    private Context context;
    private boolean mute;
    Random random;
    MusicManager(Context context, boolean mute){
        songIDs = new int[numSongs];
        songIDs[3] = context.getResources().getIdentifier
                ("chibininja", "raw", context.getPackageName());
        Log.d("MusicManager: ", "ID_CHIBI " + songIDs[3]);

        songIDs[2] = context.getResources().getIdentifier
                ("dizzyspells", "raw", context.getPackageName());
        Log.d("MusicManager: ", "ID_DIZZY " + songIDs[2]);

        songIDs[1] = context.getResources().getIdentifier
                ("ascending", "raw", context.getPackageName());
        Log.d("MusicManager: ", "ID_ASCENDING " + songIDs[1]);

        songIDs[0] = context.getResources().getIdentifier
                ("underclocked", "raw", context.getPackageName());
        Log.d("MusicManager: ", "ID_UNDERCLOCKED" + songIDs[0]);

        currSong = 0;
        this.context = context;
        this.mute = mute;
        random = new Random();
    }

    void loadSong(int songNum){
        song = MediaPlayer.create(context, songIDs[songNum]);
    }

    void play(){
        if(!mute)
            song.start();
    }

    void pause(){
        song.pause();
    }

    void update(){
        if(!song.isPlaying()){
            nextSong();
        }
    }

    void nextSong(){
        currSong = (currSong + 1) % numSongs;
        loadSong(currSong);
        song.start();
    }
}
