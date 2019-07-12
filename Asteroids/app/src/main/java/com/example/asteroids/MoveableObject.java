package com.example.asteroids;

// Kyle Muldoon

abstract class MoveableObject {
    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    // position / direction / speed / physics
    protected int posX;
    protected int posY;
    protected int mass;
    protected int maxVelocity;
    protected int minVelocity;
    protected float drctnVector;

    // shape
    protected int[] shape;

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // getters / setters
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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
}
