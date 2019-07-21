package com.example.asteroids;

import android.util.Log;

public class WaitingState implements State{
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("WaitingState: ", "Inside");
    }

    @Override
    public boolean isAvailable() {
        return true;
    }
}
