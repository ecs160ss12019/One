package com.example.asteroids;

// AUTHOR NAME HERE
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;


enum UFO_State{
    WAITING, READY, ENTERING, LEAVING, INSIDE, DEAD
}

enum UFO_Origin{
    LEFT, TOP, RIGHT, BOTTOM
}
public class UFO extends MovableObject {

    //UFO body
    private RectF body;
    private float bodyWidth, bodyHeight;
    private float circleX, circleY, radius;
    private float circleXOffset, circleYOffset;
    private float mXVelocity, mYVelocity;

    private UFO_Origin enterFrom;

    //Max Boundary for UFO
    private float xLBound, xRBound;
    private float yTBound, yBBound;
    //Screen has four sides
    private int[] ufoEntry = new int[4];

    //UFO State Variable
    UFO_State state;

    private Random random = new Random();

    UFO(Point res, PointF blockSize) {
        super(blockSize);

        body = new RectF();
        bodyWidth = 100;
        bodyHeight = 40;
        radius = 30;
        circleXOffset = 50;
        circleYOffset = 10;

        mXVelocity = res.x / 10;
        mYVelocity = res.x / 10;

        xLBound = 0;
        xRBound = res.x;
        yTBound = 0;
        yBBound = res.y;

        //Left
        ufoEntry[0] = (int)xLBound - (int)bodyWidth;
        //Top
        ufoEntry[1] = (int)yTBound - (int)bodyHeight;
        //Right
        ufoEntry[2] = (int)xRBound + (int)bodyWidth;
        //Bottom
        ufoEntry[3] = (int)yBBound + (int)bodyHeight;
        state = UFO_State.WAITING;
    }



     void update(long fps){
        Log.d("UFO::update: ", "entering fcn");
        switch(state){
            case WAITING:
                //Do waiting stuff here
                break;
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
                Log.d("UFO::leaving: ", "entering ufoLeaving");
                ufoLeaving(fps);
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



    private void ufoReady(){

        int side = random.nextInt(4);

        if(side == 0){enterFrom = UFO_Origin.LEFT;}
        else if(side == 1){enterFrom = UFO_Origin.TOP;}
        else if(side == 2){enterFrom = UFO_Origin.RIGHT;}
        else{enterFrom = UFO_Origin.BOTTOM;}


        ufoSetPosition(side);

        state = UFO_State.ENTERING;
    }


    private void ufoSetPosition(int side){
        int xPosition, yPosition;

        if( (side % 2) == 0 ){
            yPosition = random.nextInt((int)yBBound);
            xPosition = ufoEntry[side];
        }
        else{
            xPosition = random.nextInt((int)xRBound);
            yPosition = ufoEntry[side];
        }
        body.set(xPosition, yPosition, xPosition + bodyWidth, yPosition + bodyHeight );
        circleX = xPosition + circleXOffset;
        circleY = yPosition + circleYOffset;

    }





    private void ufoEnter(long fps){

        if(enterFrom == UFO_Origin.LEFT){
            ufoUpdateX(fps);
            if(body.left > 0){
                state = UFO_State.INSIDE;
            }
        }
        else if(enterFrom == UFO_Origin.TOP){
            ufoUpdateY(fps);
            if(body.top > 0){
                state = UFO_State.INSIDE;
            }
        }
        else if(enterFrom == UFO_Origin.RIGHT){
            mXVelocity = -1 * Math.abs(mXVelocity);
            ufoUpdateX(fps);
            if(body.right  < xRBound){
                state = UFO_State.INSIDE;
            }
        }
        //Bottom
        else{
            mYVelocity = -1 * Math.abs(mYVelocity);
            ufoUpdateY(fps);
            if(body.bottom < yBBound){
                state = UFO_State.INSIDE;
            }
        }

    }

    private void ufoLeaving(long fps){
        ufoUpdateX(fps);
        ufoUpdateY(fps);
        isOut();
    }

    void isOut(){
        //Top
        if(body.bottom < yTBound){

            state = UFO_State.WAITING;
            Log.d("isOut: ", "changing state to " + state);
        }
        //Right
        else if(body.left > xRBound){
            state = UFO_State.WAITING;
            Log.d("isOut: ", "changing state to " + state);
        }
        //Bottom
        else if((body.top - radius) > yBBound ){
            state = UFO_State.WAITING;
            Log.d("isOut: ", "changing state to " + state);
        }
        //Left
        else if(body.right < xLBound){
            state = UFO_State.WAITING;
            Log.d("isOut: ", "changing state to " + state);
        }
        else{}
    }

    private void ufoInside(long fps){
        ufoUpdateX(fps);
        ufoUpdateY(fps);
        checkBounds();
    }

    private void ufoUpdateX(long fps){
        body.left = body.left + (mXVelocity / fps);
        body.right = body.left + bodyWidth;
        circleX = circleX + (mXVelocity / fps);
    }

    private void ufoUpdateY(long fps){
        body.top = body.top + (mYVelocity / fps);
        body.bottom = body.top + bodyHeight;
        circleY = circleY + (mYVelocity / fps);
    }

    private void checkBounds(){
        if(body.right > xRBound){
            body.right = xRBound;
            body.left = body.right - bodyWidth;
            circleX = body.left + circleXOffset;
            reverseXVelocity();
        }
        if(body.left < xLBound){
            body.left = xLBound;
            body.right = body.left + bodyWidth;
            circleX = body.left + circleXOffset;
            reverseXVelocity();
        }
        if(body.top < (yTBound + radius) ){
            body.top = yTBound + radius;
            body.bottom = body.top + bodyHeight;
            circleY = body.top + circleYOffset;
            reverseYVelocity();
        }
        if(body.bottom > yBBound){
            body.bottom = yBBound;
            body.top = body.bottom - bodyHeight;
            circleY = body.top + circleYOffset;
            reverseYVelocity();
        }
    }

    private void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    private void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }






}
