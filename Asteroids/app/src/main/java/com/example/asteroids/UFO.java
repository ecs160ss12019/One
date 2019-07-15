package com.example.asteroids;

// AUTHOR NAME HERE
import android.graphics.Path;
import android.graphics.RectF;

/*TODO: create class to manage UFO objects.
 * Keep track of how long has UFO been alive
 * Spawn UFO's
 * Set UFO "Difficulty"
 */
public class UFO extends MovableObject {

    //UFO body
    RectF body;
    private float bodyWidth, bodyHeight;
    private float circleX, circleY, radius;
    private float mXVelocity, mYVelocity;

    //Max Boundry for UFO
    private final float xLBound, xRBound;
    private final float yTBound, yBBound;

    public UFO(int x, int y) {
        // posX, posY, mass, maxVelocity, minVelocity, drctnVector, shape
        super();

        //UFO BODY
        body = new RectF(900, 300, 1000, 340);
        bodyWidth = body.right - body.left;
        bodyHeight = body.bottom - body.top;

        //UFO HEAD
        circleX = 950;
        circleY = 310;
        radius = 30;

        mXVelocity = y / 3;
        mYVelocity = y / 3;
        xLBound = 0 - radius;
        xRBound = x;
        yTBound = 0;
        yBBound = y;
    }


    /*
     * Update the UFO's position.
     */
    public void update(int x, int y, long fps){
        //Update relative to X-axis
        body.left = body.left + (mXVelocity / fps);
        body.right = body.left + bodyWidth;
        circleX = circleX + (mXVelocity / fps);


        //Update relative to Y-axis
        body.top = body.top + (mYVelocity / fps);
        body.bottom = body.top + bodyHeight;
        circleY = circleY + (mYVelocity / fps);

        //Check Bounds
        if(body.right > xRBound){
            reverseXVelocity();
        }
        if(body.left < xLBound){
            reverseXVelocity();
        }
        if(body.top < yTBound){
            reverseYVelocity();
        }
        if(body.bottom > yBBound){
            reverseYVelocity();
        }
    }

    /*
     * Retruns the path of the UFO that will be drawn.
     */

    public Path draw(){
        shape.reset();
        shape.addOval(body, Path.Direction.CW);
        shape.addCircle(circleX, circleY, radius, Path.Direction.CW );
        return shape;
    }

    void reverseXVelocity(){
        mXVelocity = -mXVelocity;
    }

    void reverseYVelocity(){
        mYVelocity = -mYVelocity;
    }
}
