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

    public Projectile(PointF blockSize, PointF speed, PointF direction, PointF Position1, PointF Position2, float rotation, long fps) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        this.rotation = rotation;
        directionVector = new PointF();
        directionVector.x = Position1.x - Position2.x;
        directionVector.y = Position1.y - Position2.y;
        float mag = (float)Math.sqrt(directionVector.x*directionVector.x
                + directionVector.y*directionVector.y);
        directionVector.x = (directionVector.x / mag);
        directionVector.y = (directionVector.y/ mag);
        shapeCoords = new PointF[3];
        shapeCoords[0] = new PointF(Position1.x,Position1.y);
        shapeCoords[1] = new PointF(Position1.x + 2 * directionVector.x,
                Position1.y + 2 * directionVector.y);
        shapeCoords[2] = new PointF(Position1.x,Position1.y);
        startTime = System.currentTimeMillis();
        mass = 100;

        currVelocity.set(10 * directionVector.x,
                10 * directionVector.y);
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
