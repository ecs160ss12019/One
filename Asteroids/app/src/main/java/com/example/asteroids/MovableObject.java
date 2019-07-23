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
    public final int ROTATION_SCALAR = 2;
    public final int VELOCITY_SCALAR = 5;
    public final int FORCE_SCALAR = 10;
    private final int MAX_SPEED = 25;


    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    //scaled screen resolution with domain of 0-100
    protected PointF blockSize;

    private PointF force;
    protected int mass;

    //Value in degrees. 0 is pointing upwards
    protected float rotation;

    private RectF bounds; //Maybe can use for collision detection




    protected PointF currVelocity;

    //shapeCoords will store the default shape starting from (0,0)
    PointF[] shapeCoords;

    // Shape of the object needs to be a path which can form any polygon
    protected Path shape;

    ///////////////////////////
    //      Constructor
    ///////////////////////////


    public MovableObject(PointF blockSize) {
        this.blockSize = blockSize;

        force = new PointF(0,0);
        currVelocity = new PointF(0,0);

        //Initialize our shape
        shape = new Path();
        bounds = new RectF(); //Initialize bounds
        shape.reset(); //TODO: Might not be needed

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    //X=x0 + v0t + 1/2at^2
    private void calcPos(long fps) {
        for(PointF i : shapeCoords) {
            i.x += ((currVelocity.x * 1/fps) + 1/2 * (force.x / mass) * Math.pow(1/ fps, 2));
            i.y += ((currVelocity.y * 1/fps) + 1/2 * (force.y / mass) * Math.pow(1/ fps, 2));
        }
    }

    private void calcRotation() {
        //Rotate shape based on the rotation value
        Matrix transform = new Matrix();
        shape.computeBounds(bounds, true);
        transform.postRotate(rotation, bounds.centerX(), bounds.centerY());
        shape.transform(transform);
    }

    //Speed is the absolute value of velocity
    private float calcSpeed() {
        return (float) Math.sqrt(Math.pow(currVelocity.x, 2) + Math.pow(currVelocity.y,2));
    }

    //V = v0 + a*t
    private void calcVelocity(long fps) {
        currVelocity.x += (force.x) / (mass * fps);
        currVelocity.y += (force.y) / (mass * fps);
    }

    public Path draw() {
        shape.rewind();
        shape.moveTo(shapeCoords[0].x * blockSize.x, shapeCoords[0].y * blockSize.y);

        for(int i = 1; i < shapeCoords.length; ++i)
            shape.lineTo(shapeCoords[i].x * blockSize.x, shapeCoords[i].y * blockSize.y);

        calcRotation();
        return shape;
    }


    private void setForce(PointF forceVector) {

        //force.y needs to be * -1 since we are drawing from top of screen
        force.x = FORCE_SCALAR * forceVector.x;
        force.y = -1 * FORCE_SCALAR * forceVector.y;

        //If the speed > MAX_SPEED, then set force to be 0
        if(calcSpeed() > MAX_SPEED) {
            if(force.x > 0 && currVelocity.x > 0)
                force.x = 0;

            if(force.x < 0 && currVelocity.x < 0)
                force.x = 0;

            if(force.y > 0 && currVelocity.y > 0)
                force.y = 0;

            if(force.y < 0 && currVelocity.y < 0)
                force.y = 0;
        }

    }

    public void updatePhysics(long fps, PointF force) {
        setForce(force);
        calcVelocity(fps);
        calcPos(fps);
    }

}
