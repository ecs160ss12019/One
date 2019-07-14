package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;

public class Asteroid extends MovableObject {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////


    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Asteroid() {
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
