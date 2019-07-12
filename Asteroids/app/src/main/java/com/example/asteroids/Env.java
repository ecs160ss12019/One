
package com.example.asteroids;

/*NOTE: I am alphabetically sorting the object's methods so we can find them later easily, besides
the constructor which will always be on top */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;


/*TODO: since we can't implement both runnable and drawable, maybe we should rethink the drawable interface*/
public class Env extends SurfaceView implements Runnable {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    //Objects that are used for rendering to screen
    private SurfaceHolder surfaceHolder;
    private Canvas canvas;
    private Paint paint;

    //FPS
    private long fps;
    private final int MILLIS_IN_SECOND = 1000;


    protected Point resolution;

    //Game objects
    private Spaceship spaceship;

    //Here is the thread and two control variables
    private Thread gameThread = null;
    //This volatile variable can be accessed from inside and outside the thread
    private volatile boolean mPlaying;
    private boolean mPaused = true;

    ///////////////////////////
    //     CONSTRUCTOR
    ///////////////////////////
    public Env(Context context, Point res) {
        super(context);

        //Pass the resolution to our local variables
        resolution = new Point();
        resolution.x = res.x;
        resolution.y = res.y;

        //Initializer the objects reading for drawing with getHolder, a method of SurfaceView
        surfaceHolder = getHolder();
        paint = new Paint();

        //Initialize our game objects
        spaceship = new Spaceship();

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public void draw() {

    }

    public void pause() {
        mPlaying = false;
        try {
            //Try stopping the thread
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error", "Joining Thread");
        }
    }

    public void resume() {
        mPlaying = true;
        //Initialize the instance of the thread
        gameThread = new Thread(this);

        gameThread.start();
    }


    /*this method basically runs entirely on the new thread we created*/
    @Override
    public void run() {
        while(mPlaying) {



        }


    }

}
