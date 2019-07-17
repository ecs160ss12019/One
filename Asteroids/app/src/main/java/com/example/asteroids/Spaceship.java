package com.example.asteroids;

// AUTHOR NAME HERE

import android.graphics.PointF;
import android.util.Log;


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
        setMass(50);
        shapeCoords = new PointF[5];
        thrust = new PointF(0,0);
        genShape();

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void genShape() {
        shapeCoords[0] = new PointF(0, 0);
        shapeCoords[1] = new PointF(1, 3);
        shapeCoords[2] = new PointF(0, 6);
        shapeCoords[3] = new PointF(3, 3);
        shapeCoords[4] = new PointF(0, 0);

    }

    public void update(long fps, PointF joyStickPos) {


        setThrust(joyStickPos);
        calcVelocity(fps);
        calcPos(fps);
        outOfBounds();

        /*
        Log.d("js", "JoyStick: (" + joyStickPos.x + "," + joyStickPos.y);
        Log.d("shape", "ShapeCoords: (" + shapeCoords[0].x + "," + shapeCoords[0].y);

        thrust.x = joyStickPos.x;
        thrust.y = joyStickPos.y;

        currVelocity.x = thrust.x;
        currVelocity.y = thrust.y;


        for (int i = 0; i < shapeCoords.length; ++i) {



            shapeCoords[i].x +=  currVelocity.x * .1;
            shapeCoords[i].y -=  currVelocity.y * .1;

        } */



    }

    public void outOfBounds() {

        for(PointF i: shapeCoords) {
            if (i.x > 110) {
                for (PointF j : shapeCoords)
                    j.x -= 100;
            }
            if (i.x < -10) {
                for (PointF j : shapeCoords)
                    j.x += 100;
            }
            if (i.y > 110) {
                for (PointF j : shapeCoords)
                    j.y -= 100;
            }
            if (i.y < -10) {
                for (PointF j : shapeCoords)
                    j.x += 100;
            }
        }

    }

}
