package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.PointF;


public class Spaceship extends MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private PointF thrust;
    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Spaceship(PointF blockSize) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);

        shapeCoords = new PointF[5];
        thrust = new PointF(0,0);
        genShape();

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void genShape() {
        shapeCoords[0] = new PointF(0, 0);
        shapeCoords[1] = new PointF(10, 3);
        shapeCoords[2] = new PointF(0, 6);
        shapeCoords[3] = new PointF(3, 3);
        shapeCoords[4] = new PointF(0, 0);

    }

    public void update(PointF joyStickPos) {
        thrust = joyStickPos;

    }


}
