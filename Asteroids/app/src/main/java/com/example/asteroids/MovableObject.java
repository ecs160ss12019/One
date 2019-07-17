package com.example.asteroids;

// Kyle Muldoon

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;

abstract class MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    //scaled screen resolution with domain of 0-100
    protected PointF blockSize;

    // position / direction / speed / physics
    protected Point position;
    protected int mass;

    protected PointF currVelocity;
    protected int maxVelocity;
    protected int minVelocity;
    protected float drctnVector;

    // Shape of the object needs to be a path which can form any polygon
    protected Path shape;

    //shapeCoords will store the default shape starting from (0,0)
    PointF[] shapeCoords;


    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public MovableObject(PointF blockSize) {

        this.blockSize = blockSize;

        currVelocity = new PointF(0,0);
        position = new Point();

        //Initialize our shape

        shape = new Path();
        shape.reset(); //TODO: Might not be needed

    }


    ///////////////////////////
    //      Constructor
    ///////////////////////////

    public Path draw() {
        shape.rewind();
        for(int i = 0; i < shapeCoords.length; ++i) {
            shape.lineTo(shapeCoords[i].x * blockSize.x, shapeCoords[i].y * blockSize.y);
        }

        return shape;
    }


}
