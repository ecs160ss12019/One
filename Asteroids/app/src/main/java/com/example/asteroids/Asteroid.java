package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.PointF;

public class Asteroid extends MovableObject {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////


    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Asteroid(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        return shape;
    }
}
