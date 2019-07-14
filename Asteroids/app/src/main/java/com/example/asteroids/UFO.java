package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.Point;

public class UFO extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    //Testing
    public UFO(Point dp) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super(dp);
    }


    ///////////////////////////
    //      METHODS
    ///////////////////////////
    public void update(){

    }


    public Path draw() {
        return shape;
    }
}
