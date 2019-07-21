package com.example.asteroids;

public class StateContext {
    private State state;
    public StateContext(){state = new WaitingState();}

    void setState(State newState) {state = newState;}

    public void stateAction(UFO ufo, long fps){
        state.stateAction(this, ufo, fps);
    }

    public boolean isAvailable(){
        return state.isAvailable();
    }

}
