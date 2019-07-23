package com.example.asteroids;

public interface State {
    void stateAction(StateContext context, UFO ufo, long fps);
    boolean isAvailable();
    boolean isDead();
    boolean isDrawable();
}
