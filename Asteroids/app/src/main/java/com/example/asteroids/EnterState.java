package com.example.asteroids;

import android.util.Log;

public class EnterState implements State {
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("EnterState: ", "inside stateAction");
        if(ufo.enterFrom == UFO_Origin.LEFT){
            ufo.mXVelocity = Math.abs(ufo.mXVelocity);
            ufo.ufoUpdateX(fps);
            if(ufo.body.left > 0){
                Log.d("UFOLife: ", "Change state to INSIDE");
                context.setState(new InsideState());
            }
        }
        else if(ufo.enterFrom == UFO_Origin.TOP){
            ufo.mYVelocity = Math.abs(ufo.mYVelocity);
            ufo.ufoUpdateY(fps);
            if(ufo.body.top > 0){
                Log.d("UFOLife: ", "Change state to INSIDE");
                context.setState(new InsideState());
            }
        }
        else if(ufo.enterFrom == UFO_Origin.RIGHT){
            ufo.mXVelocity = -1 * Math.abs(ufo.mXVelocity);
            ufo.ufoUpdateX(fps);
            if(ufo.body.right  < ufo.xRBound){
                Log.d("UFOLife: ", "Change state to INSIDE");
                context.setState(new InsideState());
            }
        }
        //Bottom
        else{
            ufo.mYVelocity = -1 * Math.abs(ufo.mYVelocity);
            ufo.ufoUpdateY(fps);
            if(ufo.body.bottom < ufo.yBBound){
                Log.d("UFOLife: ", "Change state to INSIDE");
                context.setState(new InsideState());
            }
        }
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

