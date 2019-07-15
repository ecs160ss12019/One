package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.PointF;

public class HUD {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    JoyStick joyStick;



    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public HUD(PointF blockSize) {

        joyStick = new JoyStick(blockSize);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public JoyStick getJoyStick() {
        return this.joyStick;
    }
}
