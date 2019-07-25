package com.example.asteroids;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

// AUTHOR NAME HERE

public class MainActivity extends Activity {

    private Point resolution;

    private Env env;
    Display display;

    /*Basically followed the book's example of pong*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*Initialize the env when entering the menu, though game doesn't start
        * until it is infocus
        * */

        resolution = new Point();
        display = getWindowManager().getDefaultDisplay();
        display.getSize(resolution);


        //Sets the content view to the activity_main.xml
        setContentView(R.layout.activity_main);


    }

    //Called by the button in activity_main.xml
    public void resumeGame(View view) {
        if(env != null)
            env.resume();
            setContentView(env);
    }

    //Called by the button in activity_main.xml
    public void newGame(View view) {
        env = new Env(this, resolution);
        env.resume();
        setContentView(env);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onPause() {

        super.onPause();

        env.pause();
    }



}
