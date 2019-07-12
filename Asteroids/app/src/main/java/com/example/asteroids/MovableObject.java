package com.example.asteroids;

// Kyle Muldoon

import android.graphics.Point;

abstract class MovableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    // position / direction / speed / physics
    protected Point position;
    protected int mass;
    protected int maxVelocity;
    protected int minVelocity;
    protected float drctnVector;

    // shape
    protected int[] shape;

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    MovableObject() {
    }

    public MovableObject(int x, int y, int m, int maxV, int minV, float dir, int[] s) {
        position.x = x;
        position.y = y;
        mass = m;
        maxVelocity = maxV;
        minVelocity = minV;
        drctnVector = dir;
        shape = s;
    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // getters / setters
    public int getPosX() {
        return position.x;
    }

    public void setPosX(int posX) {
        this.position.x = posX;
    }

    public int getPosY() {
        return position.y;
    }

    public void setPosY(int posY) {
        this.position.y = posY;
    }

    public int getMass() {
        return mass;
    }

    public void setMass(int mass) {
        this.mass = mass;
    }

    public int getMaxVelocity() {
        return maxVelocity;
    }

    public void setMaxVelocity(int maxVelocity) {
        this.maxVelocity = maxVelocity;
    }

    public int getMinVelocity() {
        return minVelocity;
    }

    public void setMinVelocity(int minVelocity) {
        this.minVelocity = minVelocity;
    }

    public float getDrctnVector() {
        return drctnVector;
    }

    public void setDrctnVector(float drctnVector) {
        this.drctnVector = drctnVector;
    }

    public int[] getShape() {
        return shape;
    }

    public void setShape(int[] shape) {
        this.shape = shape;
    }

    // abstract methodes (same as using interface)
    public abstract void draw();
}
