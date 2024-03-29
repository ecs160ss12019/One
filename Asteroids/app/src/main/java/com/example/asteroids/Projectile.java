package com.example.asteroids;

// Brian Coe

import android.graphics.PointF;

public class Projectile extends MovableObject{
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////
    private PointF directionVector;
    public long startTime;
    float mag;
    float speed = 300;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public Projectile(PointF blockSize, PointF pos1,
                      PointF pos2, float rotate) {
        super(blockSize);
        isHit = false;
        PointF addW = new PointF(0,0);
        float rotateRads = (float)(Math.toRadians(rotate) + 3.14);
        directionVector = new PointF();
        setDirectionVector(pos2, pos1, rotateRads);
        setWidth(rotate, addW);
        setDraw(pos2);
        startTime = System.currentTimeMillis();
        mass = 1;
        rotation = rotate;
//        currVelocity = new PointF (pos2.x - pos1.x, pos2.y - pos1.y);

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    private void setDirectionVector(PointF pos1, PointF pos2, float rotateRads){
        /*directionVector.x = ((float)Math.cos(rotateRads)*(pos2.x - pos1.x))
                -((float)Math.sin(rotateRads)*(pos2.y - pos1.y));
        directionVector.y = ((float)Math.cos(rotateRads)*(pos2.y - pos1.y))
                +((float)Math.sin(rotateRads)*(pos2.x - pos1.x));

        */
        directionVector.x = (float) Math.sin(rotateRads);
        directionVector.y = -(float) Math.cos(rotateRads);

        mag = (float)Math.sqrt(directionVector.x*directionVector.x +
                directionVector.y*directionVector.y);
        directionVector.x = -directionVector.x/mag;
        directionVector.y =  directionVector.y/mag;
    }

    private void setDraw(PointF pos2){
        shapeCoords = new PointF[5];
        shapeCoords[0] = new PointF(pos2.x, pos2.y);
        shapeCoords[1] = new PointF((float)(pos2.x + .2), pos2.y);
        shapeCoords[2] = new PointF((float)(pos2.x + .2), (float)(pos2.y + .3));
        shapeCoords[3] = new PointF(pos2.x, (float)(pos2.y + .3));
        shapeCoords[4] = new PointF(pos2.x, pos2.y);
    }

    private void setWidth(float rotate, PointF addW){
        if(rotate >= 0  && rotate < 90){
            addW.x = 1/blockSize.x;
        }else if(rotate >= 90 && rotate < 180){
            addW.y = 1/blockSize.y;
//            directionVector = new PointF(directionVector.y, directionVector.x);
        }else if(rotate >= 180 && rotate < 270){
            addW.x = -1/blockSize.x;
        }else if(rotate >= 270 && rotate <= 360){
            addW.y = -1/blockSize.x;
  //          directionVector = new PointF(directionVector.y, directionVector.x);
        }

    }

    public void update(long fps){
        for(PointF s: shapeCoords){
//            s.x += directionVector.x;
  //          s.y += directionVector.y;
        }
        updatePhysics(fps, new PointF (directionVector.x*mag*speed,directionVector.y*mag*speed));

    }
}