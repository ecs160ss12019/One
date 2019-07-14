package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.PointF;

public class UFO extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    //Testing
    public UFO(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);


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
