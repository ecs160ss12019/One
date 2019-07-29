package com.example.asteroids;

import android.util.Log;

public class DeadState implements State {
    int curFrame;

    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        ufo.explosion.placeExplosion(ufo.body.left, ufo.body.top);
        curFrame = ufo.explosion.getCurrentFrame();
        if(curFrame >= ufo.explosion.numFrames){
            Log.d("UFOLife: ", "changing state to WAITING");
            context.setState(new WaitingState());
            ufo.explosion.resetExplosion();
        }
        ufo.isHit = false;
    }

    @Override
    public boolean isAvailable() {
        return false;
    }

    @Override
    public boolean isDead() {
        return true;
    }

    @Override
    public boolean isDrawable() {
        return false;
    }
}
