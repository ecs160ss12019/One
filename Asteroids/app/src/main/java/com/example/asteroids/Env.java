
package com.example.asteroids;

/*NOTE: I am alphabetically sorting the object's methods so we can find them later easily, besides
the constructor which will always be on top */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Arrays;
import java.util.Vector;

/*TODO: since we can't implement both runnable and drawable, maybe we should rethink the drawable interface*/

public class Env extends SurfaceView implements Runnable {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    private final boolean DEBUGGING = true;

    // collision detection
    private boolean isHit = false;
    private long timeHit = 0;
    private CollisionDetection cd;

    //Objects that are used for rendering to screen
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

    //Sound objects
    private MediaPlayer music;
    private SFXManager sfxManager;

    //FPS
    private long fps;
    private final int MILLIS_IN_SECOND = 1000;

    //Resolution and font sizes
    protected Point resolution;
    private float fontSize;
    private float fontMargin;

    //Using blockSize to make a consistent ui that scales resolutions
    //It is a scaled screen resolution with domain of 0-100
    private PointF blockSize;

    //Game objects
    private HUD hud;
    private Spaceship spaceship;
    private AsteroidManager asteroidManager;
    public ProjectileManager projectileManager;
    private UFOManager ufoManager;
    private UFO[] ufoArr;

    //Here is the thread and two control variables
    private Thread gameThread = null;

    /*isGameOnFocus is true when game is in the foreground,
    * and false when it is in the background
    * */
    private volatile boolean isGameOnFocus;
    private boolean paused = true;


    ///////////////////////////
    //     CONSTRUCTOR
    ///////////////////////////
    public Env(Context context, Point res) {
        super(context);

        //Pass the resolution to our local variables, and set our fontsize
        resolution = new Point();
        resolution = res;

        //1 value in blockSize = 1/100th of the screen
        blockSize = new PointF();
        blockSize.x =  (float)resolution.x / 100;
        blockSize.y = (float) resolution.y / 100;

        fontSize = resolution.x / 20;
        fontMargin = resolution.x / 75;

        //Initialize the objects reading for drawing with getHolder, a method of SurfaceView
        surfaceHolder = getHolder();
        paint = new Paint();

        //Initialize sound obj.
        //music = MediaPlayer.create(context, R.raw.chibininja);
       // music.start();
        sfxManager = new SFXManager(context);

        //Initialize our game objects
        hud = new HUD(blockSize);
        projectileManager = new ProjectileManager(blockSize);
        spaceship = new Spaceship(blockSize,projectileManager);

        ufoManager = new UFOManBuilder(resolution)
                        .setMaxUFO(10)
                        .wantActive(5)
                        .setTimeOut(3000)
                        .setSpawnGap(1000)
                        .setResources(getResources())
                        .setProjectileManager(projectileManager)
                        .setSFXManager(sfxManager)
                        .setBlockSize(blockSize)
                        .build();


        asteroidManager = new AsteroidManager(blockSize);

        cd = new CollisionDetection(blockSize);

    }



    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public void calcGlobalCollisions() {
        //cd.checkBinaryCollision(spaceship.draw())
        //see first if spaceship collides with any asteroids

        Vector<Projectile> projs = projectileManager.projectileVector;
        Vector<UFO> ufos = new Vector(Arrays.asList(ufoManager.getUFOS()));;
        Vector<Asteroid> asts = asteroidManager.asteroidTracker;

        // CHECK WHAT HIT PLAYER'S SHIP
        long halfSecond = MILLIS_IN_SECOND - 500;
        if (System.currentTimeMillis() - timeHit > halfSecond) {
            isHit = false;
        }


        // asteroid hit the ship?
        for(int i = 1; i < asts.size(); i++) {
            if (cd.checkBinaryCollision(spaceship.draw(), asts.get(i).draw())) {
                // collision detected, kill player
                isHit = true;
                timeHit = System.currentTimeMillis();
                break;
            }
        }

        //ufo hit the ship?
        for(int i = 0; i < ufos.size(); i++) {
            if (cd.checkBinaryCollision(spaceship.draw(), ufos.get(i).draw())) {
                // collision detected, kill player
                isHit = true;
                timeHit = System.currentTimeMillis();
                break;
            }
        }


        // alien projectile hit the ship?
        for(int i = 0; i < projs.size(); i++) {
            if (cd.checkBinaryCollision(spaceship.draw(), projs.get(i).draw())) {
                // collision detected, kill player
                isHit = true;
                timeHit = System.currentTimeMillis();
                break;
            }
        }

        // CHECK WHAT HIT THE UFOS

        // CHECK WHAT HIT THE ASTEROIDS
    }

    /*
    *Is responsible for drawing everything to screen
    */
    public void draw() {

        //If canvas is unlocked and ready to be drawn on, lock it and draw to the screen
        if(surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            // Antialiasing
            paint.setAntiAlias(true);

            //Fill the screen with a solid black for now
            canvas.drawColor(Color.argb(255,0,0,0));

            //Draw Space ship
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            if(isHit) {
                paint.setColor(Color.argb(255,255,0,0));
            } else {
                paint.setColor(Color.argb(255,255,255,255));
            }
            canvas.drawPath(spaceship.draw(), paint);
            paint.setStyle(Paint.Style.FILL);

            //Draw UFO's
            paint.setColor(Color.argb(255, 0, 255, 0));
            ufoArr = ufoManager.getUFOS();
            for(int i = 0; i < ufoManager.maxUFO; i++){
                if(ufoArr[i].state.isDead()){
                    canvas.drawBitmap(ufoArr[i].explosion.bitMap, ufoArr[i].explosion.frameToDraw,
                            ufoArr[i].explosion.whereToDraw, paint);
                }
                else if(ufoArr[i].state.isDrawable()){
                    canvas.drawPath(ufoArr[i].draw(), paint);
                }
            }

            //Draw asteroids
            paint.setColor(Color.argb(255,200,255,255));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(1);
            for(Asteroid ast : asteroidManager.asteroidTracker){
                canvas.drawPath(ast.draw(), paint);
            }

            paint.setColor(Color.argb(255,255,100,100));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            paint.setStrokeWidth(5);
            for(Projectile p : projectileManager.projectileVector){
                Log.d("projectile", "FIRE!! " + p.shapeCoords[0]);//            if( System.nanoTime() / 1000000 - p.startTime < 10000)
                canvas.drawPath(p.draw(), paint);
            }

            //Print pause button
            paint.setColor(Color.argb(255,255,0,0));
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawRect(hud.pauseButton.button, paint);

            //JoyStick should be drawn last to be below all other objects
            paint.setColor(Color.argb(255,255,255,255));
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(3);
            canvas.drawPath(hud.joyStick.draw()[0], paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(200,255,0,0));
            canvas.drawPath(hud.joyStick.draw()[1], paint);


            if(DEBUGGING) {
                printDebugging();
            }


            //Unlock canvas after you are done with it
            surfaceHolder.unlockCanvasAndPost(canvas);
        }

    }

    
    @Override
    public boolean onTouchEvent(MotionEvent e) {

        paused = false;


        int pointerIndex = e.getActionIndex();

        int maskedAction = e.getActionMasked();

        switch(maskedAction) {

            //If 1 touch is registered, shoot
            case MotionEvent.ACTION_DOWN:
                if (e.getX() / blockSize.x > 50)
                    spaceship.firing = true;
                break;

            //If the touch is moving, call joyStick.update
            case MotionEvent.ACTION_MOVE:
                if(e.getX() / blockSize.x < 50)
                    hud.joyStick.updateStick(e.getX() / blockSize.x, e.getY() / blockSize.x);
                break;

            //If there is a secondary touch, shoot
            case MotionEvent.ACTION_POINTER_DOWN:
                if (e.getX(pointerIndex) / blockSize.x > 50)
                    spaceship.firing = true;
                break;

            //If the primary finger is removed, reset joystick
            case MotionEvent.ACTION_UP:
                hud.joyStick.resetJoyStick();
                break;
            }

        return true;

    }
    

    public void pause() {
        isGameOnFocus = false;
        try {
            //Try stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Joining Thread");
        }
    }


    /*
    Prints the only the FPS currently
    */
    private void printDebugging() {
        float debugSize = fontSize / 2;
        paint.setTextSize(debugSize);

        //FPS
        paint.setColor(Color.argb(255,255,255,255));
        canvas.drawText("FPS: " + fps, 10, 150 + debugSize, paint);

        //Joystick Output
       // canvas.drawText("X-Thrust: " + hud.joyStick.getScaledStickPosition().x, 10, 200 + debugSize, paint);
        //canvas.drawText("Y-Thrust: " + hud.joyStick.getScaledStickPosition().y, 10, 250 + debugSize, paint);
        Log.d("FPS", "FPS: " + fps);

    }


    public void resume() {
        isGameOnFocus = true;
        //Initialize the instance of the thread
        gameThread = new Thread(this);

        gameThread.start();
    }


    /*
    This method basically runs entirely on the new thread we created
    */
    @Override
    public void run() {

        while(isGameOnFocus) {

            long frameStartTime = System.currentTimeMillis();

            //Provided the game isn't paused, call the update method
            if(!paused) {
                update();
            }

            draw();

            //Calculate FPS based on how long this loop took
            long frameTime = System.currentTimeMillis() - frameStartTime;

            if (frameTime > 0) {
                fps = MILLIS_IN_SECOND / frameTime;
            }

        }

    }


    /*
    Should update the position of all movable objects here
    */
    public void update() {
        asteroidManager.updateAsteroids();
        ufoManager.update(fps);
        ufoManager.spawnUFO();
        spaceship.update(fps, hud.joyStick.getScaledStickPosition());
        projectileManager.updateProjectiles(fps);
        calcGlobalCollisions();
    }

}
