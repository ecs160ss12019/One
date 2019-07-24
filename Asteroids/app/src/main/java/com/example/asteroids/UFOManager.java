package com.example.asteroids;

import android.content.res.Resources;
import android.graphics.PointF;
import android.graphics.Point;
import android.util.Log;


public class UFOManager {

    //Will hold all of the UFO's to be managed
    private UFO[] ufoArray;
    //Number of MAX UFO's on screen
    private int maxUFO;
    private int alive;
    private Timers timers;

    private long gapTime = 2000;
    private long lastTime = 0;
    private ProjectileManager projectileManager;
    private SFXManager sfx;
    UFOManager(int maxUFO, Point res, PointF blockSize, long timeOut, Resources resources,
               ProjectileManager projectileManager, SFXManager sfx){

        this.maxUFO = maxUFO;
        ufoArray = new UFO[this.maxUFO];
        this.projectileManager = projectileManager;
        timers = new Timers(maxUFO, timeOut);

        for(int i = 0; i < this.maxUFO; i++){
            //Log.d("UFOManager: ", "calling UFO()");
            ufoArray[i] = new UFO(res, blockSize, resources);
        }
        alive = 0;
        this.sfx = sfx;
    }

    public int spawnUFO(int simultaneous){
        Log.d("spawnUFO: ", "Entering fcn");
        int ret;
        if(alive >= maxUFO){
            return -1;
        }

        if(alive >= simultaneous){
            return -1;
        }

        long time = System.currentTimeMillis();
        if(time < lastTime + gapTime){
            return -1;
        }
        lastTime = time;
        alive++;

        ret = findAvailableUFO();
        timers.startTimer(ret);

        if(ret == -1){
            return -1;
        }

        Log.d("UFOLife: ", "alive: " + alive);
        return 0;
    }

    int update(long fps){

        //Update Timers
        timers.updateTimers();
        //Check Timers
        timers.checkTimers();
        for(int i = 0; i < maxUFO; i++){

            if(timers.doneTimers[i]){

                Log.d("update: " , "timer " + i + " : set to Leaving");
                //Change state of ufo to LEAVING
                Log.d("UFOLife: ", "ufo at " + i + " timed out state to DEAD");
                sfx.playExplosion();
                ufoArray[i].state.setState(new DeadState());
                timers.resetTimer(i);
            }
        }

        //update all of the UFO's
        if(alive <= 0){return -1;}

        for(int i = 0; i < maxUFO; i++){
            if(!ufoArray[i].state.isAvailable()) {
                ufoArray[i].update(fps);
                if (ufoArray[i].state.isAvailable()) {
                    alive--;
                    Log.d("UFOLife: ", "decrementing alive: " + alive);

                }
            }
        }
        return 0;
    }

    private int findAvailableUFO(){
        Log.d("findAvailableUFO: ", "entering");
        int i;
        for(i = 0; i < maxUFO; i++){
            if(ufoArray[i].state.isAvailable()){
                ufoArray[i].state.setState(new ReadyState());
                Log.d("UFOLife: ", "found UFO at " + i + " changing state to READY");
                return i;
            }
        }
        Log.d("UFOLife: ", "didn't find available UFO");
        return -1;
    }

    public UFO[] getUFOS(){
        return ufoArray;
    }

    void killUFO(){
        alive--;
    }

}

