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


    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public JoyStick(Point res) {
         innerCircle = new Path();
         outerCircle = new Path();


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {




        outerCircle.addCircle(500,500, 100, Path.Direction.CCW);

        return outerCircle;

    }



}
