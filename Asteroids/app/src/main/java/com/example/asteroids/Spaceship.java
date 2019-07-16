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

        defaultShapeCoords = new PointF[5];
        genShape();

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void genShape() {
        defaultShapeCoords[0] = new PointF(0, 0);
        defaultShapeCoords[1] = new PointF(10, 3);
        defaultShapeCoords[2] = new PointF(0, 6);
        defaultShapeCoords[3] = new PointF(3, 3);
        defaultShapeCoords[4] = new PointF(0, 0);

    }

    public void update() {

    }


}
