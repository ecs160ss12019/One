package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Point;
import android.graphics.PointF;

public class HUD {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    protected JoyStick joyStick;



    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HUD(PointF blockSize) {

        //Setting JoyStick to 50% of screen, with baseRad of 20% of screen, and hatRadius of 8% of screen
        Point joyStickPos = new Point(20,80);
        joyStick = new JoyStick(joyStickPos, 14, 5, blockSize);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

}
