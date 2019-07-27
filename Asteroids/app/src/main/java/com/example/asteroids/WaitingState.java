package com.example.asteroids;

import android.util.Log;

public class WaitingState implements State {
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("UFOLife: ", "Waiting UFO ID: " + ufo.id);
        ufo.isHit = false;

    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isDrawable() {
        return false;
    }
}
