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
        directionVector = new PointF();
        directionVector.x = (float)(direction.x / Math.sqrt(direction.x*direction.x
                + direction.y*direction.y));
        directionVector.y = (float)(direction.y/ Math.sqrt(direction.x*direction.x
                + direction.y*direction.y));        shapeCoords = new PointF[3];
        shapeCoords[0] = new PointF(Position.x,Position.y);
        shapeCoords[1] = new PointF(Position.x + 2 * directionVector.x,
                Position.y + 2 * directionVector.y);
        shapeCoords[2] = new PointF(Position.x,Position.y);
        startTime = System.currentTimeMillis();
        mass = 100;

        currVelocity.set((float)(speed.x + 10 * speed.x/Math.sqrt(speed.x*speed.x+speed.y*speed.y)),
                (float)(speed.y + 10 * speed.y/Math.sqrt(speed.x*speed.x+speed.y*speed.y)));
        updatePhysics(fps, new PointF( (float)(speed.x + 10 * speed.x/Math.sqrt(speed.x*speed.x+speed.y*speed.y)),
                (float)(speed.y + 10 * speed.y/Math.sqrt(speed.x*speed.x+speed.y*speed.y))));

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void update(long fps){
        updatePhysics(fps,directionVector);
    }
}
