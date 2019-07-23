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
        shapeCoords[1] = new PointF(Position.x + direction.x, Position.y + direction.x);
        shapeCoords[2] = new PointF(Position.x,Position.y);
        startTime = System.currentTimeMillis();
        mass = 5;
        directionVector = new PointF();
        directionVector.x = (direction.x);
        directionVector.y = (direction.y);
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
