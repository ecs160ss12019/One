package com.example.asteroids;

// Kyle Muldoon

import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;

abstract class MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    //scaled screen resolution with domain of 0-100
    protected PointF blockSize;

    // position / direction / speed / physics
    protected PointF position;
    protected int mass;
    protected int maxVelocity;
    protected int minVelocity;
    protected float drctnVector;

    // Shape of the object needs to be a path which can form any polygon
    protected Path shape;

    //defaultShapeCoords will store the default shape starting from (0,0)
    PointF[] defaultShapeCoords;

    ///////////////////////////
    //      Constructor
    ///////////////////////////

    public MovableObject(PointF blockSize) {

        this.blockSize = blockSize;

        //Initialize our shape

        shape = new Path();
        shape.reset(); //TODO: Might not be needed

    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public Path draw() {

        for(int i = 0; i < defaultShapeCoords.length; ++i) {
            shape.lineTo(defaultShapeCoords[i].x * blockSize.x, defaultShapeCoords[i].y * blockSize.y);
        }

        return shape;
    }




    public void update(long fps) {




    }





}
