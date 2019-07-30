package com.example.asteroids;

import android.util.Log;

/**
 * InsideState represents state when UFO is inside the screen
 */
public class InsideState implements State {
    private long lastTime;
    private long time;
    private long gapTime = 3000;
    InsideState(){
        lastTime = 0;
        time = 0;
    }

    /**
     * State action is to update UFO position within a boudned region. UFO
     * also fires in a random direction periodically.
     * @param context
     * @param ufo UFO object
     * @param fps Frames Per Second
     */
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        ufo.ufoUpdateX(fps);
        ufo.ufoUpdateY(fps);
        ufo.checkBounds();
        setShot(ufo);
        time = System.currentTimeMillis();
        if(time > lastTime + gapTime) {
            ufo.projectileManager.fire(ufo.bulletOrigin1, ufo.bulletOrigin2, 1, ufo.projectileOwner);
            lastTime = time;
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

    private void setShot(UFO ufo){
        float x, y;
        float xMin = ufo.body.left;
        float xMax = ufo.body.right;
        float yMin = ufo.body.top;
        float yMax = ufo.body.bottom;

        x = xMin + ufo.random.nextFloat()*(xMax - xMin);
        y = yMin + ufo.random.nextFloat()*(yMax - yMin);
        ufo.bulletOrigin1.set((x/ufo.res.x)*100, (y/ufo.res.y)*100);
        x = xMin + ufo.random.nextFloat()*(xMax - xMin);
        y = yMin + ufo.random.nextFloat()*(yMax - yMin);
        ufo.bulletOrigin2.set((x/ufo.res.x)*100, (y/ufo.res.y)*100);
    }
}