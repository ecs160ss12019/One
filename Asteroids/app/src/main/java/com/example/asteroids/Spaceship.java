package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;


public class Spaceship extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Spaceship() {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super();


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////



    public Path updatePos() {

        shape.moveTo(100,100);
        shape.lineTo(200,125);
        shape.lineTo(100, 150);
        shape.lineTo(125,125);
        shape.lineTo(100,100);

        return shape;

    }
}
