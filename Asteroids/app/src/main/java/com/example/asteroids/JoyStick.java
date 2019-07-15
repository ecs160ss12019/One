package com.example.asteroids;

import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;

public class JoyStick {

    ///////////////////////////
    //      VARIABLES
    ///////////////////////////

    private PointF blockSize;
    private PointF baseCenter;
    private PointF hatCenter;
    private PointF stickPosition;
    private float baseRadius;
    private float hatRadius;

    private Path base;
    private Path hat;

    ///////////////////////////
    //      CONSTRUCTOR
    ///////////////////////////

    public JoyStick(PointF blockSize) {

        baseCenter = new PointF();
        hatCenter = new PointF();
        stickPosition = new PointF();

        base = new Path();
        hat = new Path();


        this.blockSize = blockSize;
        baseCenter.x = (50 * blockSize.x);
        baseCenter.y = (50 * blockSize.y);
        hatCenter.x = (50 * blockSize.x);
        hatCenter.y = (50 * blockSize.y);
        baseRadius = (int) (20 * blockSize.x);
        hatRadius = (int) (8 * blockSize.x);
        stickPosition.x = 0;
        stickPosition.y = 0;

        base.addCircle(baseCenter.x, baseCenter.y, baseRadius, Path.Direction.CW);
        hat.addCircle(hatCenter.x, hatCenter.y, hatRadius, Path.Direction.CW);



    }

    ///////////////////////////
    //      METHODS
    ///////////////////////////

    // TODO: make mapped stick functionality.
    public PointF getScaledStickPosition() {
        // this method returns the percentage of how far the stick is engaged in x-y directions
        // bounds are [-100f - +100f], where -100f means -100% engaged on the axis and +100f means
        // %100% engaged on the axis.

        float xEngaged = 100 * (stickPosition.x / baseRadius);
        float yEngaged = 100 * (stickPosition.y / baseRadius);
        PointF scaledOutput = new PointF(xEngaged, yEngaged);
        return scaledOutput;
    }

    public void resetJoyStick() {
        hat.reset();
        hat.addCircle(hatCenter.x, hatCenter.y, hatRadius, Path.Direction.CW);
    }

    //X and Y have values of 0-100
    public void updateStick(float x, float y) {

        //distance of touch from joystick
        float xCentered = x - baseCenter.x / blockSize.x;
        float yCentered = y - baseCenter.y / blockSize.x;

        //distance from the center of joystick
        float distanceFromCenter = (float) Math.sqrt(Math.pow(xCentered, 2 ) + Math.pow(yCentered, 2));


        if (distanceFromCenter < baseRadius / blockSize.x) { //If the touch is in bounds of joystick

            stickPosition.x = x;
            stickPosition.y = y;

        } else { // when hat needs constraining to base radius
            float ratio = baseRadius / blockSize.x / distanceFromCenter ;

            float constrainedX = baseCenter.x / blockSize.x + (xCentered) * ratio;
            float constrainedY = baseCenter.y / blockSize.x + (yCentered) * ratio;
            stickPosition.x = constrainedX;
            stickPosition.y = constrainedY;

        }

        hat.reset();
        hat.addCircle(stickPosition.x * blockSize.x ,stickPosition.y * blockSize.x, hatRadius, Path.Direction.CW);


        //Log.d("bs", "blockSize: "+ blockSize.x);
        //Log.d("displacement", "distanceFromCenter: " + distanceFromCenter);
        //Log.d("displacement", "stickDisplacement: " + stickDisplacement);
        //Log.d("hat", "stickPosition: (" + stickPosition.x + "," + stickPosition.y + ")");

    }


    public Path[] draw() {
        Path[] joyStick = new Path[2];
        joyStick[0] = base;
        joyStick[1] = hat;
        return joyStick;
    }


}
