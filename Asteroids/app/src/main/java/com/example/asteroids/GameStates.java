package com.example.asteroids;

import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;

public class GameStates {
}

interface GameState {
    public void update(Env env);
    public void draw(Env env);
    public void onTouch(Env env);
}



class NewGameState implements GameState {
    @Override
    public void update(Env env) {
        //handles the new game (resetting all objects, etc)

        env.blockSize = new PointF((float) env.resolution.x/ 100, (float) env.resolution.y / 100);
        env.fontSize = 5 * env.blockSize.x;

        env.surfaceHolder = env.getHolder();
        env.paint = new Paint();

        //Initialize sound objects
        /*TODO: Add sound later*/

        //Initialize our game objects
        env.hud = new HUD(env.blockSize);
        env.cd = new CollisionDetection(env.blockSize);
        env.projectileManager = new ProjectileManager(env.blockSize);
        env.spaceship = new Spaceship(env.blockSize, env.projectileManager);
        env.asteroidManager = new AsteroidManager(env.blockSize);
        env.ufoManager = new UFOManBuilder(env.resolution)
                .setMaxUFO(10)
                .wantActive(5)
                .setTimeOut(3000)
                .setSpawnGap(1000)
                .setResources(env.getResources())
                .setProjectileManager(env.projectileManager)
                //.setSFXManager(sfxManager)
                .setBlockSize(env.blockSize)
                .build();



    }

    @Override
    public void draw(Env env) {
        //Maybe not needed, draws the new game
    }

    @Override
    public void onTouch(Env env) {
        /*if(pauseButton was touched)
            setState(new PausedState)
        */
    }

}

class PauseGameState implements GameState {
    @Override
    public void update(Env env) {
        //Handles the paused Game (sets paused to true)
        //ctx.isPaused = true;
    }

    @Override
    public void draw(Env env) {
        //Draws the menu
    }

    @Override
    public void onTouch(Env env) {
        //Interface with the menu
    }
}

class PlayingGameState implements GameState {
    @Override
    public void update(Env env) {
        //Handles the paused Game (sets paused to true)
        //ctx.isPaused = true;
    }

    @Override
    public void draw(Env env) {
        //Draws the menu
    }

    @Override
    public void onTouch(Env env) {
        //Interface with the menu
    }
}