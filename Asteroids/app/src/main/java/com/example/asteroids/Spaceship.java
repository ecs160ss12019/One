package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.Path;
import android.graphics.PointF;


public class Spaceship extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Spaceship(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);


    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public Path draw() {
        shape.rewind();
        shape.moveTo(10 * blockSize.x, 10 * blockSize.x);
        shape.lineTo(20 * blockSize.x, (float) 12.5 * blockSize.x);
        shape.lineTo(10 * blockSize.x, (float) 15 * blockSize.x);
        shape.lineTo((float) 12.5 * blockSize.x, (float) 12.5 * blockSize.x);
        shape.lineTo(10 * blockSize.x, 10 * blockSize.x);

        return shape;

    }
}
