
package com.example.asteroids;

/*NOTE: I am alphabetically sorting the object's methods so we can find them later easily, besides
the constructor which will always be on top */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*TODO: since we can't implement both runnable and drawable, maybe we should rethink the drawable interface*/

public class Env extends SurfaceView implements Runnable {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    private final boolean DEBUGGING = true;


    //Objects that are used for rendering to screen
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

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
    private Asteroid[] asteroid = new Asteroid[10];

    //private UFOManager ufoManager;
    private int maxUFO = 3;
    private UFO ufo;
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
        resolution.x = res.x;
        resolution.y = res.y;

        //1 value in blockSize = 1/100th of the screen
        blockSize = new PointF();
        blockSize.x =  (float)resolution.x / 100;
        blockSize.y = (float) resolution.y / 100;

        fontSize = resolution.x / 20;
        fontMargin = resolution.x / 75;

        //Initialize the objects reading for drawing with getHolder, a method of SurfaceView
        surfaceHolder = getHolder();
        paint = new Paint();

        //Initialize our game objects
        hud = new HUD(blockSize);
        spaceship = new Spaceship(blockSize);



        //ufoManager = new UFOManager(maxUFO,resolution.x, resolution.y);
        ufo = new UFO(resolution.x, resolution.y, blockSize);
        for(int i = 0; i < 10; i++) {
            asteroid[i] = new Asteroid(blockSize);
        }


    }



    ///////////////////////////
    //      METHODS
    ///////////////////////////


    /*
    *Is responsible for drawing everything to screen
    */
    public void draw() {

        //If canvas is unlocked and ready to be drawn on, lock it and draw to the screen
        if(surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();

            //Fill the screen with a solid black for now
            canvas.drawColor(Color.argb(255,0,0,0));

            paint.setColor(Color.argb(255,255,255,255));

            //Draw Space ship
            canvas.drawPath(spaceship.draw(), paint);

            //Draw UFO
            paint.setColor(Color.argb(255, 0, 255, 90));
            canvas.drawPath(ufo.draw(), paint);

            paint.setColor(Color.argb(255,255,255,255));

            //Draw asteroids
            for(int i = 0; i <10; i++){
                //canvas.drawPath(asteroid[i].draw(), paint);
            }

            paint.setColor(Color.argb(100,255,255,255));

            //Draw Joystick
            canvas.drawPath(hud.joyStick.draw()[0], paint);
            paint.setColor(Color.argb(255,255,0,0));
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

        //Touch coordinates are scaled to be values between 0-100
        float scaledX = e.getX() / blockSize.x;
        float scaledY = e.getY() / blockSize.x;

        if (e.getAction() == e.ACTION_MOVE || e.getAction() == e.ACTION_DOWN) {
            hud.joyStick.updateStick(scaledX, scaledY);
        } else
            hud.joyStick.resetJoyStick();

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
        canvas.drawText("X-Thrust: " + hud.joyStick.getScaledStickPosition().x, 10, 200 + debugSize, paint);
        canvas.drawText("Y-Thrust: " + hud.joyStick.getScaledStickPosition().y, 10, 250 + debugSize, paint);
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
        ufo.update(resolution.x, resolution.y, fps);
    }

}
