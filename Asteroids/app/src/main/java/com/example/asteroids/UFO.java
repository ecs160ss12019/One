package com.example.asteroids;

// AUTHOR NAME HERE
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;


enum UFO_State{
    WAITING, READY, ENTERING, LEAVING, INSIDE, DEAD;
}

public class UFO extends MovableObject {

    //UFO body
    RectF body;
    private float bodyWidth, bodyHeight;
    private float circleX, circleY, radius;
    private float circleXOffset, circleYOffset;
    private float mXVelocity, mYVelocity;
    private float bodyL, bodyT, bodyR, bodyB;
    private int enterFrom;
    //Max Boundary for UFO
    private float xLBound, xRBound;
    private float yTBound, yBBound;
    //Screen has four sides
    private int[] ufoEntry = new int[4];

    //UFO State Variable
    UFO_State state;


    Random random = new Random();

    public UFO(Point res, PointF blockSize) {
        super(blockSize);
        //From parent class
        //describe center of the screen
        position.set(res.x/2, res.y/2);
        Log.d("UFO: ", "center: " + position);
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


    private void positionUFO(){

    }


    public void update(long fps){
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

        int rand = random.nextInt(4);
        //UFO Will enter from the sides
        if(rand % 2 == 0){
            ufoPositionSide(rand);
        }
        else{
            ufoPositionVertical(rand);
        }
        state = UFO_State.ENTERING;
        enterFrom = rand;
        return 0;
    }



    void ufoPositionSide(int r){
        //0 = Left
        //2 = Right
        int yPosition = random.nextInt((int)yBBound);
        int xPosition;
        //Left side
        if(r == 0){
            xPosition = ufoEntry[r];
            body.set(xPosition, yPosition, xPosition + bodyWidth, yPosition + bodyHeight);
            circleX = xPosition + circleXOffset;
            circleY = yPosition + circleYOffset;
        }
        //Right side
        else{
            xPosition = ufoEntry[r];
            body.set(xPosition, yPosition, xPosition + bodyWidth, yPosition + bodyHeight );
            circleX = xPosition + circleXOffset;
            circleY = yPosition + circleYOffset;
        }
    }


    void ufoPositionVertical(int r){
        //1 = Top
        //3 = Bottom
        int xPosition = random.nextInt((int)xRBound);
        int yPosition;
        //Top side
        if(r == 1){
            yPosition = ufoEntry[r];
            body.set(xPosition, yPosition, xPosition + bodyWidth, yPosition + bodyHeight);
            circleX = xPosition + circleXOffset;
            circleY = yPosition + circleYOffset;
        }
        //Bottom side
        else{
            yPosition = ufoEntry[r];
            body.set(xPosition, yPosition, xPosition + bodyWidth, yPosition + bodyHeight);
            circleX = xPosition + circleXOffset;
            circleY = yPosition + circleYOffset;
        }
    }


















    void ufoEnter(long fps){

        //Left
        if(enterFrom == 0){
            ufoUpdateX(fps);
            if(body.left > 0){
                state = UFO_State.INSIDE;
            }
        }
        //Top
        else if(enterFrom == 1){
            ufoUpdateY(fps);
            if(body.top > 0){
                state = UFO_State.INSIDE;
            }
        }
        //Right
        else if(enterFrom == 2){
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
        if(body.right > xRBound){
            reverseXVelocity();
        }
        if(body.left < xLBound){
            reverseXVelocity();
        }
        if(body.top < (yTBound + radius) ){
            reverseYVelocity();
        }
        if(body.bottom > yBBound){
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
