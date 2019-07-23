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
        shapeCoords = new PointF[4];
        shapeCoords[0] = new PointF(Position.x,Position.y);
        shapeCoords[1] = new PointF(Position.x + 25, Position.y + 25);
        shapeCoords[2] = new PointF(Position.x + 25, Position.y);
        shapeCoords[3] = new PointF(Position.x,Position.y);
        startTime = System.nanoTime() / 1000000;
        mass = 100;
        directionVector = new PointF();
        directionVector.x = (speed.x + Position.x);
        directionVector.y = (speed.y + Position.y);
        currVelocity.set(directionVector.x,directionVector.y);
        updatePhysics(fps, speed);

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
