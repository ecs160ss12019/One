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

    public Projectile(PointF blockSize, PointF speed, PointF direction, PointF Position, long fps) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        shapeCoords = new PointF[3];
        shapeCoords[0] = new PointF(Position.x,Position.y);
        shapeCoords[1] = new PointF(Position.x + 3 * direction.x/Math.abs(direction.x),
                Position.y + 3*direction.y/Math.abs(direction.y));
        shapeCoords[2] = new PointF(Position.x,Position.y);
        startTime = System.currentTimeMillis();
        mass = 100;
        directionVector = new PointF();
        directionVector.x = (float)(direction.x / Math.sqrt(direction.x*direction.x
                + direction.y*direction.y));
        directionVector.y = (float)(direction.y/ Math.sqrt(direction.x*direction.x
                + direction.y*direction.y));
        currVelocity.set(directionVector.x,directionVector.y);
        updatePhysics(fps, speed);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void update(long fps){
        updatePhysics(fps,directionVector);
    }
}
