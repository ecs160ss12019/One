package com.example.asteroids;

// AUTHOR NAME HERE
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;


enum UFO_State{
    READY, ENTERING, LEAVING, INSIDE, DEAD;
}

public class UFO extends MovableObject {

    //UFO body
    RectF body;
    private float bodyWidth, bodyHeight;
    private float circleX, circleY, radius;
    private float mXVelocity, mYVelocity;
    private float bodyL, bodyT, bodyR, bodyB;

    //Max Boundary for UFO
    private final float xLBound, xRBound;
    private final float yTBound, yBBound;

    //UFO State Variable
    UFO_State state;

    Random random = new Random();

    public UFO(Point res, PointF blockSize) {
        super(blockSize);

        //UFO BODY(Center of Display)
        bodyL = 900;
        bodyT = 300;
        bodyR = 1000;
        bodyB = 340;

        body = new RectF(bodyL, bodyT, bodyR, bodyB);
        bodyWidth = body.right - body.left;
        bodyHeight = body.bottom - body.top;

        //UFO HEAD
        circleX = 950;
        circleY = 310;
        radius = 30;

        mXVelocity = res.x / 10;
        mYVelocity = res.x / 10;
        xLBound = 0;
        xRBound = res.x;
        yTBound = 0;
        yBBound = res.y;
        state = UFO_State.READY;
    }


    private void positionUFO(){

    }


    public void update(long fps){
        Log.d("UFO::update: ", "entering fcn");
        switch(state){
            case READY:
                //Do Ready stuff here
                Log.d("UFO::update: ", "entering ufoReady");
                ufoReady();
                break;
            case ENTERING:
                //Do Entering stuff here
                Log.d("UFO::update: ", "entering ufoEnter");
                ufoEnter(fps);
                break;
            case INSIDE:
                // Do inside stuff here
                Log.d("UFO::update: ", "entering ufoInside");
                ufoInside(fps);
                break;
            case LEAVING:
                //DO leaving stuff here
                break;
            case DEAD:
                // Do dead stuff here
                break;
            default:
                //Do default stuff here
                break;
        }

        Log.d("In update(): ", "END");
    }


    /*
     * Returns the path of the UFO that will be drawn.
     */
    public Path draw(){
        shape.rewind();
        shape.addOval(body, Path.Direction.CW);
        shape.addCircle(circleX, circleY, radius, Path.Direction.CW );
        return shape;
    }



    int ufoReady(){

        int rand = random.nextInt(3) + 1;
        //set UFO outside of screen so it can enter
        if(rand == 1){
            body.set(bodyL - 1000, bodyT, bodyL + bodyWidth, bodyB );
            circleX = circleX - 1000;
            state = UFO_State.ENTERING;
        }else if(rand == 2){
            body.set(bodyL - 1000, bodyT - 100, bodyL + bodyWidth, bodyT + bodyHeight );
            circleX = circleX - 1000;
            circleY = circleY - 100;
            state = UFO_State.ENTERING;
        }else{
            body.set(bodyL - 1000 , bodyT + 100, bodyL + bodyWidth, bodyT + bodyHeight );
            circleX = circleX - 1000;
            circleY = circleY + 100;
            state = UFO_State.ENTERING;
        }


        return 0;
    }

    void ufoEnter(long fps){
        ufoUpdateX(fps);
        if(body.left > 10){
            state = UFO_State.INSIDE;
        }
    }

    void ufoInside(long fps){
        ufoUpdateX(fps);
        ufoUpdateY(fps);
        checkBounds();
    }

    void ufoUpdateX(long fps){
        body.left = body.left + (mXVelocity / fps);
        body.right = body.left + bodyWidth;
        circleX = circleX + (mXVelocity / fps);
    }

    void ufoUpdateY(long fps){
        body.top = body.top + (mYVelocity / fps);
        body.bottom = body.top + bodyHeight;
        circleY = circleY + (mYVelocity / fps);
    }

    void checkBounds(){
        if(body.right >= xRBound){
            reverseXVelocity();
        }
        if(body.left <= xLBound){
            reverseXVelocity();
        }
        if(body.top <= yTBound){
            reverseYVelocity();
        }
        if(body.bottom >= yBBound){
            reverseYVelocity();
        }
    }

    void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }






}
