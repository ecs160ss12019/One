package com.example.asteroids;

// Kyle Muldoon

import android.graphics.Path;
import android.graphics.PointF;

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

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    public MovableObject(PointF blockSize) {

        this.blockSize = blockSize;

        //Initialize our shape

        shape = new Path();
        shape.reset(); //TODO: Might not be needed

    }


    /*

    TODO: For now I'm gonna create an empty constructor, though maybe all we need is an empty constructor and have default positions for the ship and asteroids
    public MovableObject(int x, int y, int m, int maxV, int minV, float dir) {
        position.x = x;
        position.y = y;
        mass = m;
        maxVelocity = maxV;
        minVelocity = minV;
        drctnVector = dir;


    }
    */
    ///////////////////////////
    //      METHODS
    ///////////////////////////


    public void update(long fps) {




    }





}
