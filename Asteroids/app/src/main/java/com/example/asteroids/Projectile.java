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
        super(blockSize);
        isHit = false;
        int addWx = 0;
        int addWy = 0;
        float rotateRads = (float)(Math.toRadians(rotate));// + 3.14/2);
        directionVector = new PointF();

        if(rotate > 0  && rotate <= 90){
            setDirectionVector(pos2, pos1, rotateRads);
            addWx =1;
        }else if(rotate > 90 && rotate <= 180){
            setDirectionVector(pos2, pos1, rotateRads);
            addWy =1;
        }else if(rotate > 180 && rotate <= 270){
            setDirectionVector(pos2, pos1, rotateRads);
            addWx =1;
        }else if(rotate > 270 && rotate <= 360){
            setDirectionVector(pos2, pos1, rotateRads);
            addWy =1;
        }


        shapeCoords = new PointF[5];
        shapeCoords[0] = new PointF(pos2.x, pos2.y);
        shapeCoords[1] = new PointF(pos2.x + directionVector.x, pos2.y + directionVector.y);
        shapeCoords[2] = new PointF(pos2.x + directionVector.x, pos2.y + directionVector.y);
        shapeCoords[3] = new PointF(pos2.x+addWx, pos2.y+addWy);
        shapeCoords[4] = new PointF(pos2.x, pos2.y);
        startTime = System.currentTimeMillis();
        mass = 10;

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public void setDirectionVector(PointF pos1, PointF pos2, float rotateRads){
        directionVector.x = ((float)Math.cos(rotateRads)*(pos2.x - pos1.x))-((float)Math.sin(rotateRads)*(pos2.y - pos1.y));
        directionVector.y = ((float)Math.cos(rotateRads)*(pos2.y - pos1.y))+((float)Math.sin(rotateRads)*(pos2.x - pos1.x));
        float mag = (float)Math.sqrt(directionVector.x*directionVector.x +
                directionVector.y*directionVector.y);
        directionVector.x = directionVector.x/mag;
        directionVector.y = directionVector.y/mag;
    }

    public void update(long fps){
//        if(System.currentTimeMillis() - startTime  < 100)
//            isHit = false;
        for(PointF s: shapeCoords){
            s.x += directionVector.x;
            s.y += directionVector.y;
        }
    }
}