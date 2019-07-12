package com.example.asteroids;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

// AUTHOR NAME HERE

public class MainActivity extends Activity {

    private Env env;


    /*Basically followed the book's example of pong*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point resolution = new Point();

        display.getSize(resolution);

        env = new Env(this, resolution);
        setContentView(env);

    }

    @Override
    protected void onResume() {

        super.onResume();
        env.resume();
    }

    @Override
    protected void onPause() {

        super.onPause();
        env.pause();
    }

}
