package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

public class HUD {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    protected JoyStick joyStick;
    protected Button pauseButton;
    protected Button shootButton;

    //protected Path[] shapes;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HUD(PointF blockSize) {

        //Setting JoyStick to 50% of screen, with baseRad of 20% of screen, and hatRadius of 8% of screen
        Point pos = new Point(20,70);
        joyStick = new JoyStick(pos, 10, 4, blockSize);

        pos = new Point(80, 70);
        shootButton = new Button(pos, blockSize, "Shoot");


/*
        //Pause button is in the top right corner
        PointF pausePosition = new PointF(85,5);
        pauseButton = new Button(pausePosition, blockSize);


        PointF shootPosition = new PointF(85, 95);
        shootButton = new Button(shootPosition, blockSize);
*/
        // Holds shapes to return in draw method.
       // shapes = new Path[2];
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


}
