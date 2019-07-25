package com.example.asteroids;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.Log;

import java.io.IOException;

public class MusicManager {

    private MediaPlayer song;
    private int songIDs[];
    private int numSongs = 2;
    private int currSong;
    private Context context;
    private boolean mute;
    MusicManager(Context context, boolean mute){
        songIDs = new int[numSongs];
        songIDs[0] = context.getResources().getIdentifier
                ("chibininja", "raw", context.getPackageName());
        Log.d("MusicManager: ", "ID_CHIBI " + songIDs[0]);

        songIDs[1] = context.getResources().getIdentifier
                ("dizzyspells", "raw", context.getPackageName());
        Log.d("MusicManager: ", "ID_DIZZY " + songIDs[1]);

        currSong = 0;
        this.context = context;
        this.mute = mute;
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

}
