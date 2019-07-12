package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.view.SurfaceHolder;

public class Spaceship extends MovableObject implements Drawable {
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



    public Path draw() {

        shape.moveTo(100,100);
        shape.lineTo(200,150);
        shape.lineTo(100, 200);
        shape.lineTo(100,100);

        return shape;

    }
}
