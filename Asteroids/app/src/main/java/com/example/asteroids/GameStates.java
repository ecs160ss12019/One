/*
* METHODOLOGY: Currently, I have 4 game states: EndGame, NewGame, PlayingGame, and PauseGame
* Think of the game states as an NFA where NewGame -> PlayingGame -> EndGame
*
* Endgame is reached when you are in newGame and your ship is hit (within PlayingGame.update)
* Pause game is reached when you touch the top right corner of the screen. In Pause game if you
* touch the top rectangle, you resume the game (state changes to PlayingGame), but if you touch the
* bottom rectangle, you restart the game (state changes to NewGame).
*
* Each state implements GameState that has 3 methods (update/draw/onTouch). Each of these methods
* only needs to handle its own state. In other words, EndGame's ontouch is only concerned with
* a touch event anywhere on the screen, whereas PlayingGame's ontouch needs to handle multitouch
*
* Each of these classes can also have methods added to them that are unique to the state.
* Ideally, I think we should migrate globalCollision and calcGlobalCollision into PlayingGameState
*
* */



package com.example.asteroids;

//Martin Petrov

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;


interface GameState {
    public void update(Env env);
    public void draw(Env env);
    public void onTouch(Env env, MotionEvent e);
}

class EndGameState implements GameState{

    @Override
    public void draw(Env env) {

        env.canvas.drawColor(Color.argb(255,255,0,0));

        env.paint.setStyle(Paint.Style.FILL);
        env.paint.setColor(Color.argb(255,255,255,255));
        env.paint.setTextSize(10 * env.blockSize.x);
        //env.paint.setStyle(Paint.Style.FILL);
        env.canvas.drawText("EndGame", 30 * env.blockSize.x, 40 * env.blockSize.y, env.paint);
        env.paint.setTextSize(7 * env.blockSize.x);
        env.canvas.drawText("Touch Screen to Restart", 13 * env.blockSize.x, 60 * env.blockSize.y, env.paint);
    }

    @Override
    public void onTouch(Env env, MotionEvent e) {

        int maskedAction = e.getActionMasked();
        switch (maskedAction) {
            //If 1 touch is registered, shoot
            case MotionEvent.ACTION_DOWN:
                env.musicManager.nextSong();
                env.currState = new NewGameState();
                break;
        }
    }
    @Override
    public void update(Env env) {


    }
}

class MainMenuState implements GameState {

    private Menu menu;

    public MainMenuState(Env env) {

        env.blockSize = new PointF((float) env.resolution.x/ 100, (float) env.resolution.y / 100);
        menu = new Menu(env.blockSize, "mainMenu");


        env.fontSize = 4 * env.blockSize.x;

        env.surfaceHolder = env.getHolder();
        env.paint = new Paint();
    }


    public void update(Env env) {
        //if (env.restarting)
          //  env.currState = new PlayingGameState();
    }

    public void draw(Env env) {

            //Draws the menu
            env.canvas.drawColor(Color.argb(255, 0, 0, 0));

            env.paint.setColor(Color.argb(255, 255, 255, 255));
            env.paint.setStyle(Paint.Style.FILL);

            for (int i = 0; i < menu.numOfButtons; i++) {
                env.paint.setColor(Color.argb(255, 255, 255, 255));
                env.canvas.drawPath(menu.buttons[i].shape, env.paint);
                env.paint.setColor(Color.argb(255, 0, 0, 0));
                env.canvas.drawText(menu.buttons[i].textBox, menu.buttons[i].pos.x + 75,
                        menu.buttons[i].pos.y + 50, env.paint);
            }
        }


    public void onTouch(Env env, MotionEvent e) {
        //Interface with the menu

        switch (e.getActionMasked()) {

            //If 1 touch is registered, shoot
            case MotionEvent.ACTION_DOWN:

                if (menu.buttons[0].touchBox.contains((int) e.getX(), (int) e.getY()))
                    env.currState = new NewGameState();

                break;
        }
    }
}

class NewGameState implements GameState {
    @Override
    public void update(Env env) {
        //handles the new game (resetting all objects, etc)



        //Initialize sound objects
        /*TODO: Add sound later*/

        //Initialize our game objects
        env.cd = new CollisionDetection(env.blockSize);
        env.projectileManager = new ProjectileManager(env.blockSize);
        env.spaceship = new Spaceship(env.blockSize, env.projectileManager);
        env.hud = new HUD(env.blockSize, 3);
        env.asteroidManager = new AsteroidManager(env.blockSize);
        env.sfxManager = new SFXManager(env.getContext(), env.SFXMute);
        env.ufoManager = new UFOManBuilder(env.resolution)
                .setMaxUFO(10)
                .wantActive(3)
                .setTimeOut(15000)
                .setSpawnGap(1000)
                .setResources(env.getResources())
                .setProjectileManager(env.projectileManager)
                .setSFXManager(env.sfxManager)
                .setBlockSize(env.blockSize)
                .build();

        env.powerUpManager = new PowerUpManager(env.blockSize, env.spaceship);

        //After creating a new game, we should move to playGameState
        env.currState = new PlayingGameState();

    }

    @Override
    public void draw(Env env) {
        //Maybe not needed, draws the new game
    }

    @Override
    public void onTouch(Env env, MotionEvent e) {

    }

}

class PauseGameState implements GameState {

    private Menu menu;

    public PauseGameState(Env env) {
        menu = new Menu(env.blockSize, "pauseMenu");
    }

    @Override
    public void update(Env env) {
        //Handles the paused Game (sets paused to true)
    }

    @Override
    public void draw(Env env) {
        //Draws the menu
        env.canvas.drawColor(Color.argb(255,0,0,0));

        env.paint.setStyle(Paint.Style.FILL);

        for (int i = 0; i < menu.numOfButtons; i++) {
            env.paint.setColor(Color.argb(255, 255, 255, 255));
            env.canvas.drawPath(menu.buttons[i].shape, env.paint);
            env.paint.setColor(Color.argb(255, 0, 0, 0));
            env.canvas.drawText(menu.buttons[i].textBox, menu.buttons[i].pos.x + 75,
                    menu.buttons[i].pos.y + 50, env.paint);
        }
    }

    @Override
    public void onTouch(Env env, MotionEvent e) {
        //Interface with the menu

        switch(e.getActionMasked()) {

            //If 1 touch is registered, shoot
            case MotionEvent.ACTION_DOWN:


                if (menu.buttons[0].touchBox.contains((int) e.getX(), (int) e.getY()))
                    env.currState = new PlayingGameState();
                if (menu.buttons[1].touchBox.contains((int) e.getX(), (int) e.getY())) {
                   // env.restarting = true;
                    env.currState = new NewGameState();
                }
                if (menu.buttons[2].touchBox.contains((int) e.getX(), (int) e.getY())) {
                    //env.restarting = false;
                    env.currState = new NewGameState();
                }


                break;
        }

    }
}

class PlayingGameState implements GameState {

    public int score;

    @Override
    public void draw(Env env) {
        //Draws the playing game

        //Fill game with solid black background
        env.canvas.drawColor(Color.BLACK);


        env.canvas.drawPath(env.spaceship.draw(), env.spaceship.getPaint());

        //Draw the UFO's
        env.paint.setStyle(Paint.Style.FILL);
        for(UFO ufo : env.ufoManager.getUFOS()){
            if(ufo.state.isDead()){
                env.canvas.drawBitmap(ufo.explosion.bitMap, ufo.explosion.frameToDraw,
                        ufo.explosion.whereToDraw, env.paint);
            }
            else if(ufo.state.isDrawable()){
                if(ufo.phase)
                    ufo.phaseThrough();
                else
                    ufo.solidUFO();
                env.canvas.drawPath(ufo.draw(), ufo.blurPaint);
                env.canvas.drawPath(ufo.draw(), ufo.normalPaint);
            }
        }

        //Draw the Asteroids
        env.paint.setStyle(Paint.Style.STROKE);
        env.paint.setStrokeWidth(1);
        for(Asteroid ast : env.asteroidManager.asteroidTracker){
            if(ast.isHit)
                env.paint.setColor(Color.RED);
            else
                env.paint.setColor(Color.argb(255,255,255,255));
            env.canvas.drawPath(ast.draw(), env.paint);
        }

        //Draw the Projectiles
        env.paint.setColor(Color.argb(255,255,100,100));
        env.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        env.paint.setStrokeWidth(5);
        for(Projectile p : env.projectileManager.projectileVector){
            Log.d("projectile", "FIRE!! " + p.shapeCoords[0]);//            if( System.nanoTime() / 1000000 - p.startTime < 10000)
            env.canvas.drawPath(p.draw(), env.paint);
        }

        //Draw powerup
        env.canvas.drawPath(env.powerUpManager.powerUpObject.draw(), env.powerUpManager.powerUpObject.paint);


        //Draw number of lives
        env.hud.updateLives(env.spaceship.numLives);
        env.paint.setStyle(Paint.Style.STROKE);
        env.paint.setColor(Color.WHITE);
        env.paint.setStrokeWidth(3);
        //env.hud.shipIcon.moveTo(20 * (int)env.blockSize.x, 20 * (int)env.blockSize.y);
        //env.hud.shipIcon.rewind();
        env.canvas.drawPath(env.hud.shipIcon, env.paint);

        //Draw the score
        env.paint.setStyle(Paint.Style.FILL);
        env.paint.setTextSize(env.fontSize);
        env.paint.setColor(Color.WHITE);
        env.paint.setTypeface(env.gameFont);
        env.canvas.drawText("Score: " + env.hud.score, 50 * env.blockSize.x, 10 * env.blockSize.y, env.paint);

        //Draw the fire button
        // set color red, draw a filled rectangle
        env.paint.setColor(Color.argb(200,255,0,0));
        env.canvas.drawPath(env.hud.shootButton.draw(), env.paint);
        // set color gray, draw stroked rectangle
        env.paint.setColor(Color.WHITE);
        env.paint.setStyle(Paint.Style.STROKE);
        env.paint.setStrokeWidth(3);
        env.canvas.drawPath(env.hud.shootButton.draw(), env.paint);


        //Draw the Joystick on top of all other objects
        //JoyStick should be drawn last to be below all other objects

        env.canvas.drawPath(env.hud.joyStick.draw(0), env.hud.joyStick.setPaint(0));
        env.canvas.drawPath(env.hud.joyStick.draw(1), env.hud.joyStick.setPaint(0));
        env.canvas.drawPath(env.hud.joyStick.draw(1), env.hud.joyStick.setPaint(1));
    }

    @Override
    public void onTouch(Env env, MotionEvent e) {
        //Handle Joystick, shooting, and pause button presses

        int pointerIndex = e.getActionIndex();

        int maskedAction = e.getActionMasked();
        //Log.d("touch", "Touch-x: " + e.getX()/ env.blockSize.x);
        switch(maskedAction) {

            //If 1 touch is registered, shoot
            case MotionEvent.ACTION_DOWN:

                //Lower left touch controls the space ship's shooting
                if (e.getX() / env.blockSize.x > 50)
                    env.spaceship.firing = true;

                //upper right touch pauses
                if ((e.getX() / env.blockSize.x ) > 80 && (e.getY() / env.blockSize.y < 20) )
                    env.currState = new PauseGameState(env);

                break;

            //If the primary finger is removed, reset joystick
            case MotionEvent.ACTION_UP:
                env.hud.joyStick.resetJoyStick();
                break;

            //If the touch is moving, call joyStick.update
            case MotionEvent.ACTION_MOVE:
                if(e.getX() / env.blockSize.x < 50)
                    env.hud.joyStick.updateStick(e.getX() / env.blockSize.x, e.getY() / env.blockSize.x);
                break;

            //If there is a secondary touch, shoot
            case MotionEvent.ACTION_POINTER_DOWN:
                if (e.getX(pointerIndex) / env.blockSize.x > 50)
                    env.spaceship.firing = true;
                break;

            case MotionEvent.ACTION_POINTER_UP:
                env.spaceship.firing = false;
        }
    }

    @Override
    public void update(Env env) {
        env.printDebugging();
        env.asteroidManager.updateAsteroids();
        env.ufoManager.update(env.fps, env.hud.score);
        env.ufoManager.spawnUFO();
        env.spaceship.update(env.fps, env.hud);
        env.projectileManager.updateProjectiles(env.fps);
        env.calcGlobalCollisions();
        env.powerUpManager.update();
        env.musicManager.update();
        if(env.spaceship.numLives == 0) {
            env.currState = new EndGameState();
        }

    }

}