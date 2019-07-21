package com.example.asteroids;

import android.util.Log;

public class LeavingState implements State {
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("LeavingState: ", "Inside stateAction");
        ufo.ufoUpdateX(fps);
        ufo.ufoUpdateY(fps);
        ufo.isOut();
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}
