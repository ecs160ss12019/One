package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.Point;

public class Asteroid extends MovableObject {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////


    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Asteroid(Point dp) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super(dp);


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        return shape;
    }
}
