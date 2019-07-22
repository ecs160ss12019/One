package com.example.asteroids;

// Brian Coe

import android.graphics.Path;
import android.graphics.PointF;

public class Projectile extends MovableObject{
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Projectile(PointF blockSize, PointF speed, PointF direction, long fps) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);

        updatePhysics(fps, direction);



    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        return shape;
    }

    public void update(long fps){

    }
}
