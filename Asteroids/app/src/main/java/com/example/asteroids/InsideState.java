package com.example.asteroids;

import android.util.Log;

public class InsideState implements State {
    private long lastTime;
    private long time;
    private long gapTime = 3000;
    InsideState(){
        lastTime = 0;
        time = 0;
    }
    @Override
    public void stateAction(StateContext context, UFO ufo, long fps) {
        //Log.d("InsideState: ", "Inside stateAction");
        if(ufo.isHit && !ufo.state.isDead()){
            Log.d("UFOLife: ", "From INSIDE to DEAD UFO ID: " + ufo.id);
            ufo.state.setState(new DeadState());
        }
        else {
            ufo.ufoUpdateX(fps);
            ufo.ufoUpdateY(fps);
            ufo.checkBounds();
            setShot(ufo);
//          Log.d("InsideState: ", "bullet1: " + ufo.bulletOrigin1);
//          Log.d("InsideState: ", "bullet2: " + ufo.bulletOrigin2);
            time = System.currentTimeMillis();
            if (time > lastTime + gapTime) {
                ufo.projectileManager.fire(ufo.bulletOrigin1, ufo.bulletOrigin2, 1);
                lastTime = time;
            }
        }
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
