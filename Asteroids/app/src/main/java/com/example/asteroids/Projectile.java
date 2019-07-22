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
        shapeCoords[0] = new PointF(Position.x,Position.y);
        shapeCoords[1] = new PointF(Position.x +1, Position.y + 1);
        startTime = System.nanoTime() / 1000000;
        directionVector = new PointF();
        directionVector.x = (speed.x + direction.x);
        directionVector.y = (speed.y + direction.y);
        updatePhysics(fps, directionVector);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {
        return shape;
    }

    public void update(long fps){
        updatePhysics(fps,directionVector);
    }
}
