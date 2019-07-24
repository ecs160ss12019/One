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
                      PointF pos2, float rotate) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        directionVector = new PointF();
        directionVector.x = ((float)Math.cos(rotate)*(pos2.x - pos1.x))-((float)Math.sin(rotate)*(pos2.y - pos1.y));
        directionVector.y = ((float)Math.cos(rotate)*(pos2.y - pos1.y))+((float)Math.sin(rotate)*(pos2.x - pos1.x));
        shapeCoords = new PointF[3];
        shapeCoords[0] = new PointF(pos2.x, pos2.y);
        shapeCoords[1] = new PointF(pos2.x - directionVector.x, pos2.y - directionVector.y);
        shapeCoords[2] = new PointF(pos2.x, pos2.y);
        startTime = System.currentTimeMillis();
        mass = 10;

        if(rotation < 90){
            directionVector.x = ((float)Math.cos(rotate)*(pos2.x - pos1.x))-((float)Math.sin(rotate)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotate)*(pos2.y - pos1.y))+((float)Math.sin(rotate)*(pos2.x - pos1.x));
        }else if(rotation < 180){
            directionVector.x = ((float)Math.cos(rotate)*(pos2.x - pos1.x))-((float)Math.sin(rotate)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotate)*(pos2.y - pos1.y))+((float)Math.sin(rotate)*(pos2.x - pos1.x));
        }else if(rotation < 270){
            directionVector.x = ((float)Math.cos(rotate)*(pos2.x - pos1.x))-((float)Math.sin(rotation)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotate)*(pos2.y - pos1.y))+((float)Math.sin(rotate)*(pos2.x - pos1.x));
        }else{
            directionVector.x = ((float)Math.cos(rotate)*(pos2.x - pos1.x))-((float)Math.sin(rotate)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotate)*(pos2.y - pos1.y))+((float)Math.sin(rotate)*(pos2.x - pos1.x));
        }
        float mag = (float)Math.sqrt(directionVector.x*directionVector.x +
                directionVector.y*directionVector.y);
        directionVector.x = directionVector.x*10;
        directionVector.x = directionVector.y*10;

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
