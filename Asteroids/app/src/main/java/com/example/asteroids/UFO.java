package com.example.asteroids;

// Jose Torres-Vargas
import android.content.res.Resources;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;

import java.util.Random;

enum UFO_Origin{
    LEFT, TOP, RIGHT, BOTTOM
}
public class UFO extends MovableObject {

    //UFO body
    RectF body;
    private float bodyWidth, bodyHeight;
    private float circleX, circleY, radius;
    private float circleXOffset, circleYOffset;
    boolean phase;
    float mXVelocity, mYVelocity;
    PointF bulletOrigin1;
    PointF bulletOrigin2;
    Point res;
    Resources resources;
    StateContext state;

    Explosion explosion;
    UFO_Origin enterFrom;

    //Max Boundary for UFO
    float xLBound, xRBound;
    float yTBound, yBBound;
    //Screen has four sides
    private int[] ufoEntry = new int[4];

    Random random = new Random();
    ProjectileManager projectileManager;

    UFO(Point res, PointF blockSize, Resources resources, ProjectileManager projectileManager) {
        super(blockSize);
        this.resources = resources;

        projectileOwner = 2;

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

        state = new StateContext();
        explosion = new Explosion(4,4, resources);
        this.projectileManager = projectileManager;
        bulletOrigin1 = new PointF();
        bulletOrigin2 = new PointF();
        this.res = new Point();
        this.res.set(res.x,res.y);
        phase = false;
    }

    void update(long fps){
        phase = false;


        if(this.isHit && !state.isDead()){
            if(astHitUfo){
                phase = true;
            }
            else{
                phase = false;
                state.setState(new DeadState());
            }
        }
        state.stateAction(this, fps);
    }

    public Path draw(){
        shape.rewind();
        shape.addOval(body, Path.Direction.CW);
        shape.addCircle(circleX, circleY, radius, Path.Direction.CW );
        return shape;
    }

    void ufoSetPosition(int side){
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

    void isOut(){
        //Top
        if(body.bottom < yTBound){

            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
        //Right
        else if(body.left > xRBound){
            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
        //Bottom
        else if((body.top - radius) > yBBound ){
            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
        //Left
        else if(body.right < xLBound){
            state.setState(new WaitingState());
            Log.d("isOut: ", "changing state to " + state);
        }
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
            body.right = xRBound;
            body.left = body.right - bodyWidth;
            circleX = body.left + circleXOffset;
            reverseXVelocity();
        }
        else if(body.left < xLBound){
            body.left = xLBound;
            body.right = body.left + bodyWidth;
            circleX = body.left + circleXOffset;
            reverseXVelocity();
        }
        else if(body.top < (yTBound + radius) ){
            body.top = yTBound + radius;
            body.bottom = body.top + bodyHeight;
            circleY = body.top + circleYOffset;
            reverseYVelocity();
        }
        else if(body.bottom > yBBound){
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