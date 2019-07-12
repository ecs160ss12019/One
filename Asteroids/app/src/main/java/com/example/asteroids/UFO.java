package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;

public class UFO extends MovableObject implements Drawable {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public UFO() {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super();
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        return shape;
    }
}
