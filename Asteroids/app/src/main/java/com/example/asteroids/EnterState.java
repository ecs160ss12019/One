package com.example.asteroids;

import android.util.Log;

/**
 * EnterState represents the state when the UFO is entering the
 * UFO from the outside of the screen.
 * @author Jose Torres-Vargas
 */
public class EnterState implements State {
    @Override

    /**
     * Make UFO enter the screen. Must ensure that the velocity is
     * in the right direction so that it goes into the screen and not
     * away. Once inside state is changed to InsideState
     */
    public void stateAction(StateContext context, UFO ufo, long fps) {
        Log.d("EnterState: ", "inside stateAction");
        ufo.isHit = false;
        if(ufo.enterFrom == UFO_Origin.LEFT){
            ufo.velocity.x = Math.abs(ufo.velocity.x);
            ufo.ufoUpdateX(fps);
            if(ufo.body.left > 0){
                //Log.d("UFOLife: ", "Change state to INSIDE UFO ID: " + ufo.id );
                context.setState(new InsideState());
            }
        }
        else if(ufo.enterFrom == UFO_Origin.TOP){
            ufo.velocity.y = Math.abs(ufo.velocity.y);
            ufo.ufoUpdateY(fps);
            if(ufo.body.top > 0){
                //Log.d("UFOLife: ", "Change state to INSIDE UFO ID: " + ufo.id);
                context.setState(new InsideState());
            }
        }
        else if(ufo.enterFrom == UFO_Origin.RIGHT){
            ufo.velocity.x = -1 * Math.abs(ufo.velocity.x);
            ufo.ufoUpdateX(fps);
            if(ufo.body.right  < ufo.xRBound){
                //Log.d("UFOLife: ", "Change state to INSIDE UFO ID: " + ufo.id);
                context.setState(new InsideState());
            }
        }
        //Bottom
        else{
            ufo.velocity.y = -1 * Math.abs(ufo.velocity.y);
            ufo.ufoUpdateY(fps);
            if(ufo.body.bottom < ufo.yBBound){
                //Log.d("UFOLife: ", "Change state to INSIDE UFO ID: " + ufo.id);
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

    @Override
    public boolean isInside() {
        return false;
    }
}

