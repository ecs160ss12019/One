package com.example.asteroids;

// Kyle Muldoon

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

abstract class MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    //scaled screen resolution with domain of 0-100
    protected PointF blockSize;

    protected long fps;
    // position / direction / speed / physics
    protected Point position;
    protected int mass;

    protected PointF currThrust;
    protected PointF currVelocity;

    protected float drctnVector;



    //shapeCoords will store the default shape starting from (0,0)
    PointF[] shapeCoords;

    // Shape of the object needs to be a path which can form any polygon
    protected Path shape;

    ///////////////////////////
    //      Constructor
    ///////////////////////////


    public MovableObject(PointF blockSize) {
        this.blockSize = blockSize;

        currThrust = new PointF(0,0);
        currVelocity = new PointF(0,0);
        position = new Point();

        //Initialize our shape
        shape = new Path();
        shape.reset(); //TODO: Might not be needed

    }
    /*TODO: update moveableobject constructor with fps*/
    public MovableObject(long fps, PointF blockSize) {
        this.blockSize = blockSize;
        this.fps = fps;

        currThrust = new PointF(0,0);
        currVelocity = new PointF(0,0);
        position = new Point();

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
        return shape;
    }

    public void setMass(int m) {
        mass = m;
    }


    public void setThrust(PointF thrust) {
        currThrust = thrust;
        Log.d("Thrust", "Thrust: " + currThrust);
    }


    public void calcVelocity() {
        currVelocity.x += (currThrust.x) / (mass * fps);
        Log.d("currVel", "currVel: " + currVelocity.x);

    }

    public void calcPos() {

        for(PointF i : shapeCoords) {

            i.x += currVelocity.x + 1/2 * (currThrust.x / mass) * Math.pow((currThrust.x / mass), 2);
            Log.d("ship-poss", "ship currPoss: " + i.x);

        }

    }

}
