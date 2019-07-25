package com.example.asteroids;

// Brian Coe

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

    public Projectile(PointF blockSize, PointF pos1,
                      PointF pos2, float rotate) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector are the parameters
        super(blockSize);
        float rotateRads = (float)(Math.toRadians(rotate) - 3.14/2);
        directionVector = new PointF();
//        directionVector.x = ((float)Math.cos(rotateRads)*(pos2.x - pos1.x))-((float)Math.sin(rotateRads)*(pos2.y - pos1.y));
//        directionVector.y = ((float)Math.cos(rotateRads)*(pos2.y - pos1.y))+((float)Math.sin(rotateRads)*(pos2.x - pos1.x));

        if(rotate > 0  && rotate <= 90){
            directionVector.x = ((float)Math.cos(rotateRads)*(pos1.x - pos2.x))-((float)Math.sin(rotateRads)*(pos1.y - pos2.y));
            directionVector.y = ((float)Math.cos(rotateRads)*(pos1.y - pos2.y))+((float)Math.sin(rotateRads)*(pos1.x - pos2.x));
        }else if(rotate > 90 && rotate <= 180){
            rotateRads += 3.14;
            directionVector.x = ((float)Math.cos(rotateRads)*(pos2.x - pos1.x))-((float)Math.sin(rotateRads)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotateRads)*(pos2.y - pos1.y))+((float)Math.sin(rotateRads)*(pos2.x - pos1.x));
        }else if(rotate > 180 && rotate <= 270){
            rotateRads += 3.14;
            directionVector.x = ((float)Math.cos(rotateRads)*(pos2.x - pos1.x))-((float)Math.sin(rotateRads)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotateRads)*(pos2.y - pos1.y))+((float)Math.sin(rotateRads)*(pos2.x - pos1.x));
        }else if(rotate > 270 && rotate <= 360){
            rotateRads += 3.14;
            directionVector.x = ((float)Math.cos(rotateRads)*(pos2.x - pos1.x))-((float)Math.sin(rotateRads)*(pos2.y - pos1.y));
            directionVector.y = -((float)Math.cos(rotateRads)*(pos2.y - pos1.y))+((float)Math.sin(rotateRads)*(pos2.x - pos1.x));
        }
        float mag = (float)Math.sqrt(directionVector.x*directionVector.x +
                directionVector.y*directionVector.y);
        directionVector.x = directionVector.x/mag;
        directionVector.y = directionVector.y/mag;

        shapeCoords = new PointF[3];
        shapeCoords[0] = new PointF(pos2.x, pos2.y);
        shapeCoords[1] = new PointF(pos2.x + directionVector.x, pos2.y + directionVector.y);
        shapeCoords[2] = new PointF(pos2.x, pos2.y);
        startTime = System.currentTimeMillis();
        mass = 10;

//        currVelocity.set(directionVector.x,
//                directionVector.y);

//        updatePhysics(fps, new PointF( (float)(speed.x + 10 * directionVector.x/mag),
  //              (float)(speed.x + 10 * directionVector.y/mag)));

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void update(long fps){
//        rotation = 0;
//        updatePhysics(fps,directionVector);
        for(PointF s: shapeCoords){
            s.x += directionVector.x;
            s.y += directionVector.y;
        }
    }
}
