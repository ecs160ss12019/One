package com.example.asteroids;

import android.util.Log;

public class InsideState implements State {
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("InsideState: ", "Inside stateAction");
        ufo.ufoUpdateX(fps);
        ufo.ufoUpdateY(fps);
        ufo.checkBounds();
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
