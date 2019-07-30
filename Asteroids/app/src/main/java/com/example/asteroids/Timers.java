package com.example.asteroids;

import android.util.Log;

/**
 * Timers that are used to keep track of how long a
 * UFO has been alive.
 */
public class Timers {
    private final long INACTIVE = -1;
    private final long DONE = -2;

    private long[] timerArray;
    private long[] prevTime;
    boolean[] doneTimers;
    private long currentTime;
    private int numTimers;

    //In terms of MILLISECONDS
    private long timeOut;


    Timers(int numTimers, long timeOut){
        this.numTimers = numTimers;
        timerArray = new long[numTimers];
        prevTime = new long[numTimers];
        doneTimers = new boolean[numTimers];

        for(int i = 0; i < numTimers; i++){
            timerArray[i] = INACTIVE;
            doneTimers[i] = false;
        }

        this.timeOut = timeOut;
    }

    void startTimer(int index){
        timerArray[index] = 0;
        prevTime[index] = System.currentTimeMillis();
    }


    void updateTimers(){
        currentTime = System.currentTimeMillis();

        for(int i = 0; i < numTimers; i++){
            if(timerArray[i] != INACTIVE){
                timerArray[i] = timerArray[i] + (currentTime - prevTime[i]);
                Log.d("Timers: ", "value of timer " + i + " is " + timerArray[i]);
                if(timerArray[i] >= timeOut){
                    timerArray[i] = DONE;
                }
                else{
                    prevTime[i] = currentTime;
                }
            }
        }

    }

    /**
     * Boolean array parallel to "Timers" array used to keep
     * track of which timer has finished
     */
    void checkTimers(){

        for(int i = 0; i < numTimers; i++){
            if(timerArray[i] == DONE){
                Log.d("checkTimers", "timer " + i + DONE);
                doneTimers[i] = true;
            }
        }
        for(int i = 0; i < numTimers; i++){
            Log.d("doneTimers[" + i + "] ", "" + doneTimers[i]);
        }
    }

    void resetTimer(int index){
        timerArray[index] = INACTIVE;
        doneTimers[index] = false;
    }

}
