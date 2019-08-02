
package com.example.asteroids;

/*NOTE: I am alphabetically sorting the object's methods so we can find them later easily, besides
the constructor which will always be on top */


/*
* METHODOLOGY: Ideally, Env only holds the methods and variables that are consistent and
* throughout the game and not dependent on a game state.
* This obviously includes all of our game objects, as well as vars like fps, blocksize, etc
*
* If you are adding a method to env that only needs to exist while the game is playing,
* add it to PlayingGameState instead.
*
* Currently, the game only runs in the env.run() method which essentially
* loops between the gameState's update and draw methods. I change the states of the game mostly
* in the actual game states(see comment there). The only exception is env.onPause where I
* set the game to be paused
* */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;
import java.util.Vector;


public class Env extends SurfaceView implements Runnable {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    public GameState currState;


    private final boolean DEBUGGING = true;

    // collision detection
    CollisionDetection cd;

    //Objects that are used for rendering to screen
    SurfaceHolder surfaceHolder;
    Canvas canvas;
    Paint paint;
    public Paint neonPaint;
    //Sound objects
    MusicManager musicManager;
    boolean MusicMute = true;
    SFXManager sfxManager;
    boolean SFXMute = false;

    //FPS
    long fps;
    final int MILLIS_IN_SECOND = 1000;

    //Resolution and font sizes
    protected Point resolution;
    protected float fontSize;
    Typeface gameFont;

    //Using blockSize to make a consistent ui that scales resolutions
    //To place an object at 50% of screen width, do 50 * blockSize.x
    protected PointF blockSize;

    //Game objects
    HUD hud;
    Spaceship spaceship;
    AsteroidManager asteroidManager;
    ProjectileManager projectileManager;
    UFOManager ufoManager;
    PowerUpManager powerUpManager;
    long time = 0;
    long spaceTime = 2000;
    long lastTime = 0;
    // This is a temporary fix to get the restart and main menu states working
    //boolean restarting;

    //Here is the thread and two control variables
    Thread gameThread = null;

    /*isGameOnFocus is true when game is in the foreground,
    * and false when it is in the background
    * */
    volatile boolean isGameOnFocus;


    ///////////////////////////
    //     CONSTRUCTOR
    ///////////////////////////
    public Env(Context context, Point res) {
        super(context);

        musicManager = new MusicManager(context, MusicMute);
        gameFont = Typeface.createFromAsset(context.getAssets(), "fonts/ARCADECLASSIC.TTF");
        //Pass the resolution to our local variables, and set our fontsize
        resolution = new Point();
        resolution = res;

        //Set the state to new game to reset all variables
        //restarting = false;
        currState = new MainMenuState(this);
        //currState = new MainMenuState(this);
        currState.update(this);
    }


    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public void calcGlobalCollisions() {
        //creating list of all objects that need to be checked
        Vector<Projectile> projs = projectileManager.projectileVector;
        Vector<UFO> ufos = new Vector(Arrays.asList(ufoManager.getUFOS()));
        Vector<Asteroid> asts = asteroidManager.asteroidTracker;


        // debug code for changing color on spaceship collision.
        long halfSecond = MILLIS_IN_SECOND - 500;
        if (System.currentTimeMillis() - spaceship.timeHit > halfSecond) {
            spaceship.isHit = false;
        }

        // CHECK WHAT HIT PLAYER'S SHIP
        if(!spaceship.isHit)
            checkHit(asts, spaceship);
        if(!spaceship.isHit)
            checkHit(ufos, spaceship);
        if(!spaceship.isHit)
            checkHit(projs, spaceship);
        if(!spaceship.isHit){
            Vector<MovableObject> temp = new Vector<MovableObject>();
            temp.addElement(powerUpManager.powerUpObject);
            checkHit(temp, spaceship);
            checkHit(projs, powerUpManager.powerUpObject);
        }


        // CHECK WHAT HIT THE UFOS
        for(UFO currUFO : ufos) {
            if(!currUFO.isHit)
                checkHit(projs, currUFO);
            if(!currUFO.isHit)
                checkHit(asts, currUFO);
        }


        // CHECK WHAT HIT THE ASTEROIDS
        for(Asteroid currAst : asts) {
            if(!currAst.isHit)
                checkHit(projs, currAst);
            //if(!currAst.isHit)
                //checkHit(asts, currAst);
        }

      //TODO: Could be wrong, but this seems like it might not be needed
//        for(Projectile currP : projs) {
//            if(!currP.isHit)
//                checkHit(ufos, currP);
//            if(!currP.isHit)
//                checkHit(asts, currP);
//        }
    }

    public void checkHit(Vector object, MovableObject thisObject){
        for(MovableObject mov : (Vector<MovableObject>)object) {

            if((mov == thisObject) || ((thisObject.projectileOwner == mov.projectileOwner)
                    && (thisObject.projectileOwner != 3))){
                continue;
            }

            if (cd.checkBinaryCollision(thisObject.draw(), (mov.draw()))) {
                // collision detected, kill player
               thisObject.isHit = true;
               mov.isHit = true;
               thisObject.timeHit = System.currentTimeMillis();
               //if UFO and Asteroid collide
                if((thisObject.projectileOwner == 2 && mov.projectileOwner == 3)
                        || (thisObject.projectileOwner == 3 && mov.projectileOwner == 2) ){
                    if(thisObject.projectileOwner == 3)
                        mov.astHitUfo = true;
                    else
                        thisObject.astHitUfo = true;
                    mov.isHit = false;
                    thisObject.isHit = false;

                }
                //IF thisObject(SpaceShip) hit something
                if (thisObject.projectileOwner == 1 || mov.projectileOwner == 1) {
                    if(thisObject.projectileOwner == 2 || mov.projectileOwner == 2){
                        time = System.currentTimeMillis();
                        if(time > lastTime + spaceTime) {
                            lastTime = time;
                            hud.score += 20;
                        }
                    }
                    else {
                        hud.score += 10;
                    }
                        //Log.d("score", "Score: " + hud.score);
                }
                if(mov.projectileOwner == 5 && thisObject.projectileOwner == 1){
                    mov.isHit = true;
                    thisObject.isHit = false;
                    if(thisObject.projectileOwner == 5 && mov.projectileOwner == 1)
                        thisObject.isHit = true;
                }
                break;
            }

        }
    }


    //Is responsible for drawing everything to screen
    public void draw() {
        //Make sure canvas is unlocked
        if(surfaceHolder.getSurface().isValid()) {
            //Lock the canvas while drawing each frame
            canvas = surfaceHolder.lockCanvas();

            //Enable Antialiasing
            paint.setAntiAlias(true);

            //Draws the game based on its current state
            //If game is not paused, just draws the game
            //If the game is paused, draws the menu
            currState.draw(this);

            //Unlock the canvas after drawing the frame
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    //OntouchEvent just calls the current state's onTouch Event
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        currState.onTouch(this, e);
        return true;
    }
    

    public void pause() {
        isGameOnFocus = false;
        currState = new PauseGameState(this);
        try {
            //Try stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Joining Thread");
        }

        musicManager.pause();
    }

    public void printDebugging() {
        Log.d("FPS", "FPS: " + fps);


    }


    public void resume() {
        Log.d("Resume", "Resuming");
        isGameOnFocus = true;

        //Initialize the instance of the thread
        gameThread = new Thread(this);
        gameThread.start();
        musicManager.loadSong(0);
        musicManager.play();
    }


    //This method basically runs entirely on the new thread we created
    @Override
    public void run() {
        long frameStartTime;
        long frameTime;

        //setting fps to random value for a very brief second to get states going
        fps = 60;

        //Keep running game if it is in foreground
        while(isGameOnFocus) {
            frameStartTime = System.currentTimeMillis();
            draw();
            currState.update(this);
            frameTime = System.currentTimeMillis() - frameStartTime;
            if (frameTime > 0)
                fps = MILLIS_IN_SECOND / frameTime;

        }






    }

}
