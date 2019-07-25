package com.example.asteroids;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;

public class SFXManager {
    SoundPool soundPool;
    int explosionID = -1;
    private AudioAttributes audioAttributes;
    boolean mute;

    //Assuming we're playing on Android Lollipop or Later
    //Set up code is from book example
    public SFXManager(Context context, boolean mute){
        audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        soundPool = new SoundPool.Builder()
                .setMaxStreams(5)
                .setAudioAttributes(audioAttributes)
                .build();

        //Attempt to load files
        try{
            AssetManager assetManager = context.getAssets();
            AssetFileDescriptor descriptor;

            descriptor = assetManager.openFd("exp.ogg");
            explosionID = soundPool.load(descriptor, 0);
        }catch (IOException e){
            e.getCause();
            Log.e("error", e.getMessage());
        }

        this.mute = mute;
    }

    public void playExplosion(){
        if(!mute)
            soundPool.play(explosionID, .2f, .2f, 0, 0, 1);
    }
}
