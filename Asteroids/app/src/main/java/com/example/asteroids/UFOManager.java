package com.example.asteroids;

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
    private ProjectileManager projectileManager;

    UFOManager(int maxUFO, Point res, PointF blockSize, long timeOut, ProjectileManager projectileManager){
        this.maxUFO = maxUFO;
        ufoArray = new UFO[this.maxUFO];
        this.projectileManager = projectileManager;
        timers = new Timers(maxUFO, timeOut);

        for(int i = 0; i < this.maxUFO; i++){
            //Log.d("UFOManager: ", "calling UFO()");
            ufoArray[i] = new UFO(res, blockSize);
        }
        alive = 0;

    }

    public int spawnUFO(){
        Log.d("spawnUFO: ", "Entering fcn");
        int ret;
        if(alive >= maxUFO){
            return -1;
        }
        alive++;

        ret = findAvailableUFO();
        timers.startTimer(ret);

        if(ret == -1){
            return -1;
        }

        Log.d("spawnUFO: ", "Leaving");
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
                ufoArray[i].state.setState(new LeavingState());
                timers.resetTimer(i);
            }
        }

        //update all of the UFO's
        if(alive <= 0){return -1;}

        for(int i = 0; i < maxUFO; i++){
            if(!ufoArray[i].state.isAvailable()) {

                ufoArray[i].update(fps);

                if(ufoArray[i].state.isAvailable()) {
                    alive--;
                }
            }
        }
        return 0;
    }

    private int findAvailableUFO(){
        Log.d("findAvailableUFO: ", "entering");
        for(int i = 0; i < maxUFO; i++){
            if(ufoArray[i].state.isAvailable()){
                Log.d("findAvailableUFO: ", "found at " + i);
                ufoArray[i].state.setState(new ReadyState());
                return i;
            }
        }
        Log.e("findAvailableUFO: ", "Did not find available UFO");
        return -1;
    }

    public UFO[] getUFOS(){
        return ufoArray;
    }

    void killUFO(){
        alive--;
    }

}

