package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.Point;


public class Spaceship extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Spaceship(Point dp) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(dp);


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public Path draw() {

        shape.moveTo(10 * dp.x,10 * dp.x );
        shape.lineTo(20 * dp.x,(float) 12.5 * dp.x);
        shape.lineTo(10 * dp.x, (float) 15 * dp.x);
        shape.lineTo((float) 12.5 * dp.x,(float) 12.5 * dp.x);
        shape.lineTo(10 * dp.x,10 * dp.x);

        return shape;

    }
}
