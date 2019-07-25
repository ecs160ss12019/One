package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;

public class HUD {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    protected JoyStick joyStick;
    protected Button pauseButton;
    protected Button shootButton;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HUD(PointF blockSize) {

        //Setting JoyStick to 50% of screen, with baseRad of 20% of screen, and hatRadius of 8% of screen
        Point joyStickPos = new Point(20,70);
        joyStick = new JoyStick(joyStickPos, 10, 4, blockSize);

/*
        //Pause button is in the top right corner
        PointF pausePosition = new PointF(85,5);
        pauseButton = new Button(pausePosition, blockSize);


        PointF shootPosition = new PointF(85, 95);
        shootButton = new Button(shootPosition, blockSize);
*/
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

}
