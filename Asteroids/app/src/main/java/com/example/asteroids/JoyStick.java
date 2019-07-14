package com.example.asteroids;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

public class JoyStick {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    Point position;
    Path innerCircle;
    Path outerCircle;

    Paint paint;
    Point dp;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public JoyStick(Point screenDimensions) {
         innerCircle = new Path();
         outerCircle = new Path();
         dp = screenDimensions;


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {


        outerCircle.addCircle(5 * dp.x, 80 * dp.y, 20 * dp.x, Path.Direction.CCW);

        return outerCircle;

    }



}
