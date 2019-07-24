package com.example.asteroids;

// Brian Coe

import android.graphics.Path;
import android.graphics.PointF;

public class Projectile extends MovableObject{
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private PointF directionVector;
    public long startTime;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Projectile(PointF blockSize, PointF speed, PointF pos1,
                      PointF pos2, float rotation) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        directionVector = new PointF();

        directionVector.x = ((float)Math.cos(rotation)*pos1.x);
        directionVector.y = ((float)Math.sin(rotation)*pos1.y);
        shapeCoords = new PointF[3];
        shapeCoords[0] = new PointF(pos2.x, pos2.y);
        shapeCoords[1] = new PointF(pos2.x - directionVector.x, pos2.y - directionVector.y);
        shapeCoords[2] = new PointF(pos2.x, pos2.y);
        startTime = System.currentTimeMillis();
        mass = 10;
        float mag = (float)Math.sqrt(directionVector.x*directionVector.x +
                directionVector.y*directionVector.y);
        directionVector.x = directionVector.x/mag;
        directionVector.x = directionVector.y/mag;

        currVelocity.set(directionVector.x,
                directionVector.y);

//        updatePhysics(fps, new PointF( (float)(speed.x + 10 * directionVector.x/mag),
  //              (float)(speed.x + 10 * directionVector.y/mag)));

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void update(long fps){
        rotation = 0;
        updatePhysics(fps,directionVector);
    }
}
