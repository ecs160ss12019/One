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

        Point position = new Point(50,50);
        joyStick = new JoyStick(position, blockSize);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public JoyStick getJoyStick() {
        return this.joyStick;
    }
}
