package com.example.asteroids;

// Kyle Muldoon

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

abstract class MovableObject {
    ///////////////////////////
    //      Constants
    ///////////////////////////
    private final int ROTATION_SCALAR = 2;
    private final int VELOCITY_SCALAR = 10;
    private final int MAX_SPEED = 50;


    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    //scaled screen resolution with domain of 0-100
    protected PointF blockSize;


    protected PointF currThrust;
    protected PointF currVelocity;


    protected int mass;
    private float speed;
    //Value in degrees. 0 is pointing upwards
    private float rotation;
    Matrix transform;
    RectF bounds;

    //shapeCoords will store the default shape starting from (0,0)
    PointF[] shapeCoords;

    // Shape of the object needs to be a path which can form any polygon
    protected Path shape;

    ///////////////////////////
    //      Constructor
    ///////////////////////////


    public MovableObject(PointF blockSize) {
        this.blockSize = blockSize;

        currVelocity = new PointF(0,0);
        currThrust = new PointF(0,0);
        //Initialize our shape
        shape = new Path();
        shape.reset(); //TODO: Might not be needed

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public Path draw() {
        shape.rewind();
        shape.moveTo(shapeCoords[0].x * blockSize.x, shapeCoords[0].y * blockSize.y);
        for(int i = 1; i < shapeCoords.length; ++i) {
            shape.lineTo(shapeCoords[i].x * blockSize.x, shapeCoords[i].y * blockSize.y);
        }
        rotateShape();
        return shape;
    }

    //X=x0 + v0t + 1/2at^2
    public void calcPos(long fps) {
        for(PointF i : shapeCoords) {
            i.x += ((currVelocity.x * 1/fps) + 1/2 * (currThrust.x / mass) * Math.pow(1/ fps, 2));
            i.y += ((currVelocity.y * 1/fps) + 1/2 * (currThrust.y / mass) * Math.pow(1/ fps, 2));
        }
    }

    public void calcRotation(PointF joyStickPos, long fps) {

        rotation += Math.toRadians(ROTATION_SCALAR * joyStickPos.x/fps);
    }

    private float updateAndGetSpeed() {
        return speed = (float) Math.sqrt(Math.pow(currVelocity.x, 2) + Math.pow(currVelocity.y,2));

    }

    //V = v0 + a*t
    public void calcVelocity(long fps) {
            currVelocity.x += (currThrust.x) / (mass * fps);
            currVelocity.y += (currThrust.y) / (mass * fps);
            Log.d("vel", "CurrVelocity: " + currVelocity);
    }

    private void rotateShape() {
        //Rotate ship based on the rotation
        transform = new Matrix();
        bounds = new RectF();
        shape.computeBounds(bounds, true);
        transform.postRotate((float) Math.toDegrees(rotation), bounds.centerX(), bounds.centerY());
        shape.transform(transform);

    }


    public void setForce(PointF forceVector) {
        double rad = Math.toRadians(rotation);


            currThrust.x = VELOCITY_SCALAR * (float) (forceVector.y * Math.sin(rotation));
            currThrust.y = -1 * VELOCITY_SCALAR * (float) (forceVector.y * Math.cos(rotation));


        if(updateAndGetSpeed() > MAX_SPEED) {
            if(currThrust.x > 0 && currVelocity.x > 0) {
                currThrust.x = 0;

            }
            if(currThrust.x < 0 && currVelocity.x < 0) {
                currThrust.x = 0;

            }
            if(currThrust.y > 0 && currVelocity.y > 0) {
                currThrust.y = 0;
            }
            if(currThrust.y < 0 && currVelocity.y < 0) {
                currThrust.y = 0;
            }
        }





    }


}
