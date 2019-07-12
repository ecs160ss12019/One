package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;

public class Projectile extends MovableObject implements Drawable {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Projectile() {
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
