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
        mass = 10;
        shapeCoords = new PointF[5];
        thrust = new PointF(0,0);
        genShape();

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void genShape() {
        shapeCoords[0] = new PointF(50, 50);
        shapeCoords[1] = new PointF(51, 53);
        shapeCoords[2] = new PointF(50, 52);
        shapeCoords[3] = new PointF(49, 53);
        shapeCoords[4] = new PointF(50, 50);

    }

    public void update(long fps, PointF joyStickPos) {
        setForce(joyStickPos);
        calcRotation(joyStickPos, fps);
        calcVelocity(fps);
        calcPos(fps);
        checkBounds();
    }

    public void checkBounds() {
        for(PointF i: shapeCoords) {
            if (i.x > 100) {
                for (PointF j : shapeCoords)
                    j.x -= 100;
            }
            if (i.x < 0) {
                for (PointF j : shapeCoords)
                    j.x += 100;
            }
            if (i.y > 100) {
                for (PointF j : shapeCoords)
                    j.y -= 100;
            }
            if (i.y < 0) {
                for (PointF j : shapeCoords)
                    j.y += 100;
            }
        }
    }

}
