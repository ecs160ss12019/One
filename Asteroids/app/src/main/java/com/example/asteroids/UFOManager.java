package com.example.asteroids;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Point;
import android.util.Log;

enum UFO_Type{
    GREEN, YELLOW, RED
}


public class UFOManager {
    //Will hold all of the UFO's to be managed
    private UFO[] ufoArray;
    int maxUFO;
    private int alive;
    private int wantActive;

    //Time stuff
    private Timers timers;
    private long gapTime;
    private long lastTime = 0;
    private long timeOut;
    private SFXManager sfx;
    private UFO_Type currentDifficulty;
    Paint paint;
    UFOManager(Point res, PointF blockSize, Resources resources,
               ProjectileManager projectileManager, SFXManager sfx,
               int maxUFO, int wantActive, long timeOut, long ufoGapTime){
        this.maxUFO = maxUFO;
        ufoArray = new UFO[this.maxUFO];
        this.wantActive = wantActive;

        this.timeOut = timeOut;
        gapTime = ufoGapTime;
        this.sfx = sfx;
        timers = new Timers(maxUFO, timeOut);

        for(int i = 0; i < this.maxUFO; i++){
            ufoArray[i] = new UFO(res, blockSize, resources, projectileManager);
        }
        alive = 0;
        paint = new Paint();
        currentDifficulty = UFO_Type.GREEN;
    }


    int spawnUFO(){
        Log.d("spawnUFO: ", "Entering fcn");
        int ret;
        if(alive >= maxUFO){
            return -1;
        }
        if(alive >= wantActive){
            return -1;
        }
        long time = System.currentTimeMillis();
        if(time < lastTime + gapTime){
            return -1;
        }
        lastTime = time;
        alive++;
        ret = findAvailableUFO();
        if(ret == -1){
            return -1;
        }
        timers.startTimer(ret);
        configureUFO(ret);
        Log.d("UFOLife: ", "alive: " + alive);
        return 0;
    }


    int update(long fps){

        timers.updateTimers();

        timers.checkTimers();
        for(int i = 0; i < maxUFO; i++){
            if(ufoArray[i].state.isDead()){
                timers.resetTimer(i);
            }

            if(timers.doneTimers[i]){
                Log.d("update: " , "timer " + i + " : set to Leaving");
                //Change state of ufo to LEAVING
                Log.d("UFOLife: ", "ufo at " + i + " timed out state to DEAD");
                //sfx.playExplosion();
                ufoArray[i].state.setState(new LeavingState());
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


    UFO[] getUFOS(){
        return ufoArray;
    }

    void setCurrentDifficulty(UFO_Type difficulty){
        currentDifficulty = difficulty;
    }

    void configureUFO(int index){
        Log.d("ConfigureUFO: ", "Index: " + index);
        ufoArray[index].difficulty = currentDifficulty;
        Log.d("ConfigureUFO: ", "Do we make it here: ");

        switch (ufoArray[index].difficulty){
            case GREEN:
                ufoArray[index].paint.setColor(Color.argb(255, 0, 255, 0));
                break;
            case RED:
                ufoArray[index].paint.setColor(Color.argb(255, 255, 0, 0));
                break;
            case YELLOW:
                ufoArray[index].paint.setColor(Color.argb(255, 255, 255, 0));
                break;
            default:
                ufoArray[index].paint.setColor(Color.argb(255, 0, 0, 255));
                break;
        }
    }



}