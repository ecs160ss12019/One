package com.example.asteroids;

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Point;
import android.util.Log;

/*
 * TODO: ADD "timer" that keeps track of how long UFO has been alive
 */
public class UFOManager {

    //Will hold all of the UFO's to be managed
    private UFO[] ufoArray;

    //Number of MAX UFO's on screen
    private int maxUFO;
    private int alive;

    UFOManager(int maxUFO, Point res, PointF blockSize){
        this.maxUFO = maxUFO;
        ufoArray = new UFO[this.maxUFO];
        for(int i = 0; i < this.maxUFO; i++){
            ufoArray[i] = new UFO(res, blockSize);
        }

        alive = 0;
    }

    //Spawn UFO onto screen
    //return 0 on success, else failed
    public int spawnUFO(){
        Log.d("spawnUFO: ", "Entering fcn");
        int index;
        if(alive >= 10){
            return -1;
        }
        //Update number of UFO's active
        alive++;

        index = findAvailableUFO();

        if(index == -1){
            return -1;
        }

        Log.d("spawnUFO: ", "Leaving");
        return 0;
    }



    int update(long fps){
        //update all of the UFO's
        if(alive <= 0){return -1;}
        for(int i = 0; i < maxUFO; i++){
            ufoArray[i].update(fps);
        }
        return 0;
    }

    private int findAvailableUFO(){
        Log.d("findAvailableUFO: ", "entering");
        for(int i = 0; i < maxUFO; i++){
            if(ufoArray[i].state == UFO_State.READY){
                Log.d("findAvailableUFO: ", "found at " + i);
                return i;
            }
        }
        Log.e("findAvailableUFO: ", "Did not find available UFO");
        return -1;
    }

    public UFO[] getUFOS(){
        return ufoArray;
    }

}

//    entering ufoInside