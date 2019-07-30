package com.example.asteroids;

import android.util.Log;

/**
 * LeavingState: Similar to InsideState except there are no more boundires.
 * Once out of bounds state is changed to waiting.
 */
public class LeavingState implements State {
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("LeavingState: ", "Inside stateAction");
        ufo.ufoUpdateX(fps);
        ufo.ufoUpdateY(fps);
        ufo.isOut();
        ufo.isHit = false;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isDead() {
        return false;
    }

    @Override
    public boolean isDrawable() {
        return true;
    }
}
