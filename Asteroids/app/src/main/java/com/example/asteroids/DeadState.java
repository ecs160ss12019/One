package com.example.asteroids;

import android.util.Log;

/**
 * Represents the Dead state of the UFO. In the Dead state
 * the UFO should simply explode on screen.
 * @author Jose Torres-Vargas
 */
public class DeadState implements State {
    //Current frame of sprite sheet
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

    @Override
    public boolean isInside() {
        return false;
    }
}
