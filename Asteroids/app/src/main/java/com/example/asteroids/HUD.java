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
        Point position = new Point(50,50);
        joyStick = new JoyStick(position, 20, 8, blockSize);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public JoyStick getJoyStick() {
        return this.joyStick;
    }
}
