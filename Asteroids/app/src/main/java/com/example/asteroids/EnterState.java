package com.example.asteroids;

import android.util.Log;

public class EnterState implements State {
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("EnterState: ", "inside stateAction");
        if(ufo.enterFrom == UFO_Origin.LEFT){
            ufo.ufoUpdateX(fps);
            if(ufo.body.left > 0){
                context.setState(new InsideState());
            }
        }
        else if(ufo.enterFrom == UFO_Origin.TOP){
            ufo.ufoUpdateY(fps);
            if(ufo.body.top > 0){
                context.setState(new InsideState());
            }
        }
        else if(ufo.enterFrom == UFO_Origin.RIGHT){
            ufo.mXVelocity = -1 * Math.abs(ufo.mXVelocity);
            ufo.ufoUpdateX(fps);
            if(ufo.body.right  < ufo.xRBound){
                context.setState(new InsideState());
            }
        }
        //Bottom
        else{
            ufo.mYVelocity = -1 * Math.abs(ufo.mYVelocity);
            ufo.ufoUpdateY(fps);
            if(ufo.body.bottom < ufo.yBBound){
                context.setState(new InsideState());
            }
        }
    }

    @Override
    public boolean isAvailable() {
        return false;
    }
}

