package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.PointF;

public class Projectile extends MovableObject implements Drawable {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Projectile(PointF blockSize) {
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
