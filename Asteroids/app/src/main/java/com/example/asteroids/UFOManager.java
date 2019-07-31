package com.example.asteroids;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.Point;
import android.util.Log;


/*
 * UFO_Type denotes the UFO difficulty.
 * GREEN: Default state easy
 * YELLOW: Medium difficulty.
 * RED: Hard difficulty.
 */
enum UFO_Type{
    GREEN, YELLOW, RED
}


/**
 * UFOManager
 *
 * This class is in charge of managing the UFOs in the game.
 * It holds all of the UFO objects in an array. Aside from holding
 * them this class is in charge of "spawning" in new UFOs. It keeps
 * track of how long the UFOs have been alive. It also manages
 * the current difficulty of UFOs and allows them to be configured.
 *
 * @author  Jose Torres-Vargas
 */

public class UFOManager {
    private UFO[] ufoArray;
    int maxUFO;
    private int alive;
    private int wantActive;

    //Time stuff
    private Timers timers;
    private long gapTime;
    private long lastTime = 0;
    private UFO_Type currentDifficulty;

    UFOManager(Point res, PointF blockSize, Resources resources,
               ProjectileManager projectileManager, SFXManager sfx,
               int maxUFO, int wantActive, long timeOut, long ufoGapTime){

        this.maxUFO = maxUFO;
        ufoArray = new UFO[this.maxUFO];
        this.wantActive = wantActive;

        gapTime = ufoGapTime;
        timers = new Timers(maxUFO, timeOut);

        for(int i = 0; i < this.maxUFO; i++){
            ufoArray[i] = new UFO(res, blockSize, resources, projectileManager, sfx);
        }
        alive = 0;
        currentDifficulty = UFO_Type.GREEN;
    }


    /**
     * This method spawns a UFO into the screen.
     * It first checks to ensure that there is enough room
     * to spawn a new UFO. It also checks to see if there are
     * already a number of UFOs alive that env. wants(wantActive)
     *
     * The timing mechanism is to ensure that there is some time
     * (gapTime) in between each UFO spawning. If we do find an
     * available UFO we start a "timer" to keep track of how long it
     * has been alive.
     *
     * @return -1 on failure to spawn UFO, else non-negative int is returned
     * on success
     */
    int spawnUFO(){
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

        if(currentDifficulty != ufoArray[ret].difficulty) {
            configureUFO(ret);
        }

        Log.d("UFOLife: ", "alive: " + alive);
        return 0;
    }


    /**
     *  method updates all of our timers to see if any UFO has timed out.
     *  If so UFO state is changed to leaving. After timers are updated the
     *  UFOs are all updated. This is just calculating the new position of the
     *  UFO. Update the number of alive UFOs as well in case a UFO has "died"
     *
     * @param fps : Frames per second
     * @return
     */
    int update(long fps){

        timers.updateTimers();

        timers.checkTimers();
        for(int i = 0; i < maxUFO; i++){
            if(ufoArray[i].state.isDead()){
                timers.resetTimer(i);
            }

            if(timers.doneTimers[i]){
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
                }
            }
        }
        return 0;
    }


    /**
     * Finds availabe UFO. This means that any UFO that is not busy
     * i.e. UFO in the WAITING state.
     *
     * @return  -1 on failure, else return index of array element
     * that holds available UFO.
     */
    private int findAvailableUFO(){

        for(int i = 0; i < maxUFO; i++){
            if(ufoArray[i].state.isAvailable()){
                ufoArray[i].state.setState(new ReadyState());
                return i;
            }
        }
        return -1;
    }


    /**
     * @return array that holds all UFOs. Called by env.
     */
    UFO[] getUFOS(){
        return ufoArray;
    }


    /**
     * called by env in order to set the current difficulty of UFOs
     * that are being spawned.
     *
     * @param difficulty: UFO difficulty that we want current UFOs to spawn
     *                    as.
     */
    void setCurrentDifficulty(UFO_Type difficulty){
        currentDifficulty = difficulty;
    }


    /**
     * UFO configuration is done here.
     * Change UFO speed, color, shooting frequency, etc.
     * @param index: element in array that holds UFO to configure
     */
    void configureUFO(int index){

        ufoArray[index].difficulty = currentDifficulty;

        switch (ufoArray[index].difficulty){
            case GREEN:
                ufoArray[index].normalPaint.setColor(Color.argb(255, 0, 255, 0));
                break;
            case RED:
                ufoArray[index].normalPaint.setColor(Color.argb(255, 255, 0, 0));
                ufoArray[index].velocity.x = ufoArray[index].velocity.x*2.0f;
                ufoArray[index].velocity.y = ufoArray[index].velocity.y*2.0f;
                break;
            case YELLOW:
                ufoArray[index].normalPaint.setColor(Color.argb(255, 255, 255, 0));
                ufoArray[index].velocity.x = ufoArray[index].velocity.x*1.50f;
                ufoArray[index].velocity.y = ufoArray[index].velocity.y*1.50f;
                break;
            default:
                ufoArray[index].paint.setColor(Color.argb(255, 0, 0, 255));
                break;
        }
    }



}